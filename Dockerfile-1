FROM amazoncorretto:17

COPY kafka-consumer.jar /microservice/kafka-consumer.jar
COPY entrypoint.sh /entrypoint.sh

RUN chmod +x /entrypoint.sh
WORKDIR /microservice/

EXPOSE 8081
ENV microservice_name=kafka-consumer

ENTRYPOINT ["/entrypoint.sh"]
