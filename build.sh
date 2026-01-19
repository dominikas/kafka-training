#!/usr/bin/env bash
  echo "Running maven clean package"
  mvn clean package verify
  ARTIFACT_NAME=$(mvn help:evaluate -Dexpression=project.build.finalName -q -DforceStdout)
  ARTIFACT=${PWD}"/target"
  cp -fvT ${ARTIFACT}"/"${ARTIFACT_NAME}".jar" ${PWD}"/kafka-consumer.jar"