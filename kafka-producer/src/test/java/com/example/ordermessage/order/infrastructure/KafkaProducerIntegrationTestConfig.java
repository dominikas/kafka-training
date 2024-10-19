package com.example.ordermessage.order.infrastructure;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
class KafkaProducerIntegrationTestConfig {

    @Bean
    public String orderTopicName(){
        return "order_topic-test";
    }

}
