package com.example.ordermessage.order.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class KafkaAdminConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServersAddress;

    @Value("${kafka.topic-name}")
    private String orderTopicName;

    @Value("${kafka.order.topic.partition-number}")
    private String orderTopicPartitionNumber;

    @Value("${kafka.order.topic.replication-factor}")
    private String orderTopicReplicationFactor;

    @Bean
    KafkaAdmin kafkaAdmin(){
        Map<String, Object> configuration = new HashMap<>();
        configuration.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersAddress);
        return new KafkaAdmin(configuration);
    }

    @Bean
    public NewTopic topic(){
        log.info("****** Creating new topic! ******");
        return new NewTopic(orderTopicName, Integer.parseInt(orderTopicPartitionNumber), Integer.valueOf(orderTopicReplicationFactor).shortValue());
    }
}
