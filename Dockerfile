# -*- mode: shell-script -*-

FROM ubuntu:18.04

RUN apt-get update \
  && apt-get -y install --no-install-recommends \
    libreoffice-calc \
    libreoffice-java-common \
    openjdk-8-jre \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/*

WORKDIR /root/work
