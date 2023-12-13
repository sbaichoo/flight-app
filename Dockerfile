LABEL authors="sbaichoo"

FROM openjdk:17-jdk-alpine

WORKDIR /maureva-flight-app

RUN mvn clean install

COPY realm-export.json /maureva-flight-app
COPY target/maureva-flight-app-0.0.1-SNAPSHOT.jar /maureva-flight-app

CMD mvn spring-boot:run
