# Use an official OpenJDK as a parent image
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Install OpenJDK for compilation
RUN apt-get update && apt-get install -y openjdk-11-jdk

# Grant execute permissions to the Gradle wrapper script
RUN chmod +x ./gradlew

# Run Gradle build
RUN ./gradlew build

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Define the entry point for the application
CMD ["java", "-jar", "build/libs/your-application-name.jar"]
