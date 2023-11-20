package com.example.ordermessage.order.infrastructure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("kafka")
public class KafkaConfig {
    private String topicName;
    private String replicationFactor;
}
