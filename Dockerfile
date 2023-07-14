FROM eclipse-temurin:11
COPY target/api-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8092
ENTRYPOINT ["java", "-jar", "/app.jar"]