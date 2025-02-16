# Use a base image with Java installed
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy any .jar file from the target directory to the container as app.jar
COPY target/*.war app.war

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-Djdk.util.jar.enableMultiRelease=false", "-jar", "app.war"]
