FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp

# Usamos un comodín doble para que busque el jar dentro de cualquier carpeta target
COPY microservice-eureka/target/*.jar app.jar

# Limitamos la memoria de la JVM (esto está perfecto para Railway)
ENTRYPOINT ["java", "-Xmx300m", "-Xms128m", "-jar", "/app.jar"]