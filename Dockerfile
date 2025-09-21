# -*- mode: shell-script -*-

FROM ubuntu:24.04

RUN apt-get update \
  && apt-get -y install --no-install-recommends \
    libreoffice-calc \
    liblibreoffice-java \
    openjdk-21-jdk-headless \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/*

WORKDIR /root/work
