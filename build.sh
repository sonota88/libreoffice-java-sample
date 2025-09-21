#!/bin/bash

MVN_CMD=./mvnw

setup() {
  echo "Install Maven Wrapper"
  mvn wrapper:wrapper -Dmaven=3.9.11
}

case "$1" in
  setup)
    setup
    ;;
  docker-image)
    docker build -t libo-sample:ubuntu2404_lo24 .
    ;;
  package)
    export MAVEN_OPTS="-Dmaven.repo.local=${PWD}/maven_repo_local"
    $MVN_CMD clean
    $MVN_CMD package -Dmaven.test.skip=true
    ;;
  *)
    echo "invalid command" >&2
    exit 1
    ;;
esac
