from openjdk:12-alpine
COPY ./target/api-0.0.1-SNAPSHOT.jar /app/api-0.0.1-SNAPSHOT.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "app-0.0.1-SNAP
SHOT.jar"]