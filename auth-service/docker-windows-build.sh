#!/bin/bash

cd ..

mvn clean package docker:build -DskipTests=true

docker rm -f ms-auth-service

docker run \
    --restart=always \
    --name ms-auth-service \
    --net spring-microservice-network \
    -p 8503:8503 \
    -d bookstore/spring-microservice-auth-service