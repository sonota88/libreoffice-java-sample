#!/bin/bash

case "$1" in
  docker-image)
    docker build -t libo-sample:test .
    ;;
  package)
    mvn clean
    mvn package -Dmaven.test.skip=true
    ;;
  *)
    echo "invalid command" >&2
    exit 1
    ;;
esac
