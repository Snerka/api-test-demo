# Use Maven official image with OpenJDK 11
FROM maven:latest

# Set working directory inside the container
WORKDIR /app

# Copy all project files into the container
COPY . .

# Build the project using Maven
RUN mvn clean install

# Run tests
CMD ["mvn", "test"]