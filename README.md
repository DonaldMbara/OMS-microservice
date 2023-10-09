

# OMS Microservice 

## Introduction

This repository contains a Docker Compose configuration for running a microservice using MySQL as the database. The microservice is designed to be run in a Docker containerized environment.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Docker: You need Docker installed on your system. You can download and install Docker from [Docker's official website](https://docs.docker.com/get-docker/).

## Getting Started

Follow these steps to get the microservice up and running:

1. **Clone the Repository**

   ```bash
   git clone <repository_url>
   cd <repository_directory>
   ```

   Replace `<repository_url>` with the URL of your Git repository and `<repository_directory>` with the directory where you want to clone the repository.

2. **Configure Environment Variables**

   Open the `docker-compose.yml` file and configure the environment variables as needed. These variables may include database connection details and other service-specific settings.

3. **Build and Start the Services**

   Use Docker Compose to build and start the microservice and the associated MySQL database:

   ```bash
   docker-compose up -d
   ```

   The `-d` flag runs the containers in detached mode.

4. **Access the Microservice**

   Once the containers are up and running, you can access the microservice at the specified endpoint. By default, the microservice should be available at `http://localhost:<port>`.

5. **Stop and Remove the Containers**

   When you're done using the microservice, you can stop and remove the containers:

   ```bash
   docker-compose down
   ```

   This will stop and remove the containers, but it won't delete the data volumes.


## Troubleshooting
