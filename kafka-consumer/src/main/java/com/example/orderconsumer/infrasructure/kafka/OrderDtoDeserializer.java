package com.example.orderconsumer.infrasructure.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

@Slf4j
public class OrderDtoDeserializer implements Deserializer<OrderDto> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public OrderDto deserialize(String s, byte[] bytes) {
        return null;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public OrderDto deserialize(String topic, Headers headers, byte[] data) {
        try {
            if (data == null) {
                log.info("Null received at deserializing");
                return null;
            }
            return objectMapper.readValue(new String(data, "UTF-8"), OrderDto.class);
        } catch (Exception e) {
            log.error("Error when serializing OrderDto to byte[]");
            throw new SerializationException("Error when deserializing bytes[] to OrderDto");
        }
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
