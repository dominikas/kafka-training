FROM amazoncorretto:17

COPY kafka-producer.jar /microservice/kafka-producer.jar
COPY entrypoint.sh /entrypoint.sh

RUN chmod +x /entrypoint.sh
WORKDIR /microservice/

EXPOSE 8081
ENV microservice_name=kafka-producer

ENTRYPOINT ["/entrypoint.sh"]
