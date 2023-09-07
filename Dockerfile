FROM openjdk:17
EXPOSE 8070
ADD target/CloudStorageApi-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
