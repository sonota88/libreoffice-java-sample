#!/bin/bash

MVN_CMD=./mvnw

setup() {
  echo "Install Maven Wrapper"
  mvn -N io.takari:maven:0.7.7:wrapper -Dmaven=3.6.3
}

case "$1" in
  setup)
    setup
    ;;
  docker-image)
    docker build -t libo-sample:test .
    ;;
  package)
    $MVN_CMD clean
    $MVN_CMD package -Dmaven.test.skip=true
    ;;
  *)
    echo "invalid command" >&2
    exit 1
    ;;
esac
