# Etapa 1: Construcción (puedes saltarte esto si subes el .jar ya compilado)
FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
# Limitamos la memoria de la JVM dentro del contenedor
ENTRYPOINT ["java", "-Xmx300m", "-Xms128m", "-jar", "/app.jar"]