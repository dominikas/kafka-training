package com.example.ordermessage.order.infrastructure;

import com.example.ordermessage.order.domain.Order;
import com.example.ordermessage.order.domain.OrderService;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.kafka.ConfluentKafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = KafkaProducerIntegrationTestConfig.class)
class KafkaProducerIntegrationTest {

    @Autowired
    private String orderTopicName;

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Autowired
    private ProducerFactory<String, OrderEvent> producerFactory;

    @Autowired
    private OrderService orderService;

    static Consumer<String, String> consumer;

    @Autowired
    private ConsumerFactory<String, String> consumerFactory;

    @BeforeEach
    void setUp() {
        if (consumer == null) {
            consumer = consumerFactory.createConsumer("IntegrationTestConsumer", null);
            consumer.subscribe(List.of(orderTopicName));
        }
        consumer.seekToEnd(consumer.assignment());
        KafkaTestUtils.getRecords(consumer, Duration.ofMillis(10_000));
    }

    @Test
    void shouldSendMessageToKafkaTopic() {
        //given
        final Order expectedOrder = Instancio.of(Order.class).create();

        //when
        orderService.sendOrder(expectedOrder);

        //then
        final ConsumerRecord<String, String> singleRecord = KafkaTestUtils.getSingleRecord(consumer, orderTopicName, Duration.ofMillis(10_000));
        assertThat(singleRecord).isNotNull();
        var actualEvent = singleRecord.value();
        assertThat(actualEvent).contains(expectedOrder.getCount());
        assertThat(actualEvent).contains(expectedOrder.getItem());
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

