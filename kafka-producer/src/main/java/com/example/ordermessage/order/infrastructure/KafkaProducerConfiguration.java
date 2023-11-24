package com.example.ordermessage.order.infrastructure;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
class KafkaProducerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServersAddress;

    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;

    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;

    @Bean
    public ProducerFactory<String, OrderEvent> producerFactory(){
        Map<String, Object> configurationProperties = new HashMap<>();
        configurationProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersAddress);
        configurationProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        configurationProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        return new DefaultKafkaProducerFactory<>(configurationProperties);
    }

    @Bean
    public KafkaTemplate<String, OrderEvent> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
