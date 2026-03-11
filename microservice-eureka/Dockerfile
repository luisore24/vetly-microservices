FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY microservice-eureka ./microservice-eureka
RUN mvn clean package -DskipTests -pl microservice-eureka -am

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/microservice-eureka/target/*.jar app.jar
ENTRYPOINT ["java", "-Xmx300m", "-Xms128m", "-jar", "app.jar"]