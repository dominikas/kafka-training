#!/bin/sh
OTEL_EXPORTER_OTLP_ENDPOINT="http://localhost:4317"
OTEL_RESOURCE_ATTRIBUTES="my-service-name"
java -javaagent:/path/to/opentelemetry-javaagent-all.jar -jar kafka-producer.jar
