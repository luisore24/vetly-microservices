# ---------- BUILD ----------
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

COPY ../../ .

RUN mvn install -DskipTests
RUN mvn clean package -DskipTests -pl microservice-auth -am

# ---------- RUNTIME ----------
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=build /app/microservice-auth/target/*.jar app.jar

EXPOSE 5402

ENTRYPOINT ["java","-Xmx384m","-Xms128m","-jar","app.jar"]