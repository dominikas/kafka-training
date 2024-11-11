package com.example.orderconsumer.infrastructure.kafka;

import com.example.orderconsumer.domain.Order;
import com.example.orderconsumer.infrastructure.persistence.OrderMapperImpl;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.ConfluentKafkaContainer;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KafkaOrderListenerIntegrationTest {

    @Value("${kafka.topic-name}")
    private String orderTopicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaOrderListener kafkaOrderListener;

    @MockBean
    private OrderServiceImpl orderService;

    @MockBean
    private OrderMapperImpl orderMapper;

    @Test
    void shouldSendMessageToKafkaTopic() {
        //given
        String expectedOrder = """
                {
                "item"":"expected name",
                "number":5
                }
                """;

        //when
        kafkaTemplate.send(orderTopicName, expectedOrder);

        //then
        Order order = new Order("expected name", 5);
        verify(orderService, times(1)).saveOrder(order);
    }

    @Container
    private static final ConfluentKafkaContainer
            kafka = new ConfluentKafkaContainer(DockerImageName.parse("confluentinc/cp-kafka")
            .withTag("7.4.1-1-ubi8"));

    @DynamicPropertySource
    private static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }


}