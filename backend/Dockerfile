# Build stage: compile the project using Maven with Java 23
FROM maven:3.9.9-eclipse-temurin-23-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage: use Java 23 runtime to run the jar
FROM openjdk:23-jdk
WORKDIR /app
COPY --from=build /app/target/funds-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
