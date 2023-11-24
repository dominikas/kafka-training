package com.example.ordermessage.order.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@Slf4j
public class OrderEventSerializer implements Serializer<OrderEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, OrderEvent orderEvent) {
        try {
            if (orderEvent == null) {
                log.info("Null received at serializing");
                return null;
            }
            log.info("Serializing");
            return objectMapper.writeValueAsBytes(orderEvent);
        } catch (Exception e) {
            log.error("Exception when serializing ");
            throw new SerializationException("(Error when serializing MessageDto to byte[]");
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, Headers headers, OrderEvent data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
