FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/airline-service.jar /airline-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/airline-service.jar"]