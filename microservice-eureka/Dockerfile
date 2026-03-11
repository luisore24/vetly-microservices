FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copiar TODO el monorepo (necesario para resolver parent POM)
COPY ../../ .

# Instalar todos los módulos en el repo local
RUN mvn install -DskipTests

# Construir microservice-eureka y sus dependencias
RUN mvn clean package -DskipTests -pl microservice-eureka -am

# ---------- RUNTIME ----------
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=build /app/microservice-eureka/target/*.jar app.jar

EXPOSE 5401

ENTRYPOINT ["java","-Xmx256m","-Xms128m","-jar","app.jar"]