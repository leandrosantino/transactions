FROM openjdk:17-jdk-slim
WORKDIR /usr/src/app

COPY ./build/libs/transactions-0.0.1.jar ./application.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "./application.jar"]