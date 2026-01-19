#!/bin/sh
COMMAND="exec java
    -jar ${microservice_name}.jar
    $@"

echo ${COMMAND}
${COMMAND}
