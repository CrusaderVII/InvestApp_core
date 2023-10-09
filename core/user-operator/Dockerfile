# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container at the defined working directory
COPY target/user-operator-0.0.2.jar ./app/.jar

CMD [ "java", "-jar", "app.jar" ]