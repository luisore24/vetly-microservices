FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY api-gateway ./api-gateway
RUN mvn clean package -DskipTests -pl api-gateway -am

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/api-gateway/target/*.jar app.jar
ENTRYPOINT ["java", "-Xmx300m", "-Xms128m", "-jar", "app.jar"]