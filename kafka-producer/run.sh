#!/bin/sh
java -javaagent:"C:\Users\domin\OneDrive\Dokumenty\developing\repos\kafka-training-1\dd-java-agent.jar" \
-Ddd.profiling.enabled=true \
-Ddd.logs.injection=true \
-Ddd.service=my-app \
-Ddd.env=staging \
-Ddd.version=1.0 \
-Ddd.trace.sample.rate=1 \
-jar kafka-producer.jar
