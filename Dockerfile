
# Stage 1: Build Spring Boot application
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy source code
COPY src ./src

# Give permission to Maven wrapper
RUN chmod +x mvnw

# Build application
RUN ./mvnw clean package -DskipTests


# Stage 2: Run Spring Boot application
FROM eclipse-temurin:17-jre

WORKDIR /app

# Expose backend port
EXPOSE 8001

# Copy generated JAR
COPY --from=build /app/target/*.jar app.jar

# Run Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
