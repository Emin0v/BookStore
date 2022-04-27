#!/bin/bash

cd ..

mvn clean package docker:build -DskipTests=true

docker rm -f ms-eureka-service

docker run \
    --restart=always \
    --name ms-eureka-service \
    --net spring-microservice-network \
    -p 8500:8500 \
    -d bookstore/spring-microservice-eureka-service