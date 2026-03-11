# ---------- BUILD ----------
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copiar todo el monorepo (necesario para el parent POM)
COPY . .

# Instalar todos los módulos en local para resolver el parent POM
RUN mvn clean install -DskipTests

# Construir solo Eureka y sus dependencias
RUN mvn clean package -DskipTests -pl microservice-eureka -am

# ---------- RUNTIME ----------
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=build /app/microservice-eureka/target/*.jar app.jar

EXPOSE 5401

ENTRYPOINT ["java","-Xmx256m","-Xms128m","-jar","app.jar"]