# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the maintainer label
LABEL maintainer="nabin"

# Copy the JAR file into the container at /app
COPY target/banking-app-0.0.1-SNAPSHOT.jar /app/banking-app.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/banking-app.jar"]
