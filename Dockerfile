# Imagen base con Java 17 (compatible con Spring Boot 3.x)
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR compilado al contenedor
COPY target/envio-pedidos-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto 8080 (puerto por defecto de Spring Boot)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
