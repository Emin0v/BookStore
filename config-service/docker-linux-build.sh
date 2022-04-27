#!/bin/bash

cd ..

mvn clean package docker:build -DskipTests=true

docker rm -f ms-config-service

docker run \
    --restart=always \
    --name ms-config-service \
    --net spring-microservice-network \
    -p 8888:8888 \
    -v /etc/timezone:/etc/timezone \
    -v /etc/localtime:/etc/localtime \
    -d bookstore/spring-microservice-config-service