# ---------- BUILD ----------
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copiar toda la raíz del monorepo
COPY . .

# Instalar todos los módulos para que el parent POM se resuelva
RUN mvn install -DskipTests

# Compilar solo microservice-eureka y sus dependencias
RUN mvn clean package -DskipTests -pl microservice-eureka -am

# ---------- RUNTIME ----------
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copiar el JAR compilado
COPY --from=build /app/microservice-eureka/target/*.jar app.jar

EXPOSE 5401
ENTRYPOINT ["java","-Xmx256m","-Xms128m","-jar","app.jar"]