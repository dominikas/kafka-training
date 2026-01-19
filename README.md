# kafka-training

This application consist of two parts:
- producer app
- consumer app

Consumer app reads events from order_topic which producer is sending. 
Consumer app saves orders to the db.

# How to run it locally?

## Infrastructure 

1. Run Docker on your machine
2. Run in the location of docker-compose.yml
```
docker compose up -d 
```
## Producer application

1. Build application from pom.xml location:
```
mvn clean package
```

2. Run application using using configuration stored in .run/KafkaProducerApplication.run.xml

## Consumer application

1. Build application from pom.xml location:
```
mvn clean package
```
2. Run application using using configuration stored in .run/KafkaConsumerApplication.run.xml

# todo
* integration tests
* arch tests
* service discovery
* authentication and authorization
* webflux

### datadog - locally

-javaagent:"...\dd-java-agent.jar" -Ddd.profiling.enabled=true -Ddd.logs.injection=true -Ddd.service=my-app -Ddd.env=staging -Ddd.version=1.0 -Ddd.trace.sample.rate=1

## Docker container
### build image

docker-compose -f docker-compose-consumer.yaml build consumer-app

### run image
docker-compose  -f docker-compose-consumer.yaml up consumer-app