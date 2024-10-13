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
* service discovery
* authentication and authorization
* webflux