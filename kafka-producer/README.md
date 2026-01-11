### datadog - locally

-javaagent:"...\dd-java-agent.jar" -Ddd.profiling.enabled=true -Ddd.logs.injection=true -Ddd.service=my-app -Ddd.env=staging -Ddd.version=1.0 -Ddd.trace.sample.rate=1

## Docker container
### build image

docker-compose -f docker-compose-producer.yaml build producer-app

### run image
docker-compose  -f docker-compose-producer.yaml up producer-app