#!/bin/bash

cd ..

mvn clean package docker:build -DskipTests=true

docker rm -f ms-book-service

docker run \
    --restart=always \
    --name ms-book-service \
    --net spring-microservice-network \
    -p 8502:8502 \
    -d bookstore/spring-microservice-book-service