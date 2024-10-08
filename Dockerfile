# Use Maven official image with OpenJDK 11
FROM maven:latest

# Set working directory inside the container
WORKDIR /app

# Copy all project files into the container
COPY . .

# Install Allure
RUN apt-get update && apt-get install -y wget unzip && \
    wget -qO /tmp/allure.zip https://github.com/allure-framework/allure2/releases/download/2.20.1/allure-2.20.1.zip && \
    unzip /tmp/allure.zip -d /opt/ && \
    ln -s /opt/allure-2.20.1/bin/allure /usr/bin/allure

# Run the tests and generate the Allure results
RUN mvn clean test

# Generate the Allure report
RUN allure generate target/allure-results -o target/allure-report