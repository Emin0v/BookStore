FROM openjdk:11

MAINTAINER Mahammad Eminov

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

ARG JAR_FILE=target/auth-service-1.0.0.jar

COPY ${JAR_FILE} /app.jar

EXPOSE 8503

ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]