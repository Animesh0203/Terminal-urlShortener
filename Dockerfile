FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/URLshortner-0.0.1-SNAPSHOT.jar /app/myApp.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/myApp.jar"]