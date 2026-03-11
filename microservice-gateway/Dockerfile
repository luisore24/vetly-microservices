# ---------- BUILD ----------
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests
RUN mvn clean package -DskipTests -pl microservice-gateway -am

# ---------- RUNTIME ----------
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=build /app/microservice-gateway/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-Xmx256m","-Xms128m","-jar","app.jar"]