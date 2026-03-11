# ---------- BUILD ----------
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copiar todo el monorepo para que Maven resuelva dependencias entre módulos
COPY . .

# Compilar solo auth y sus dependencias
RUN mvn clean package -DskipTests -pl microservice-auth -am


# ---------- RUNTIME ----------
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/microservice-auth/target/*.jar app.jar

EXPOSE 5401  # Cambiar según el servicio

ENTRYPOINT ["java","-Xmx300m","-Xms128m","-jar","app.jar"]