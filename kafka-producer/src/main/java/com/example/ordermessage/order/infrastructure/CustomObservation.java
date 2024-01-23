package com.example.ordermessage.order.infrastructure;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
class CustomObservation {

    private final ObservationRegistry observationRegistry;

    void myOperation() {
        Observation observation = Observation.createNotStarted("some-operation", this.observationRegistry);
        observation.lowCardinalityKeyValue("my-observation-tag", "my-observation-value");
        observation.observe(() -> {
            log.info("My observation");
        });
    }

}
