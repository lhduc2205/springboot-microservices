# Spring Boot Microservice Project

<!-- OVERVIEW -->
## Overview

Provide a brief introduction to the microservice project.

<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

- Ports using

   | Service name         | Port | 
   |----------------------|------|
   | Eureka Server        | 8761 |
   | Api Gateway          | 8000 |
   | Order Service        | 8082 |
   | Inventory Service    | 8083 |
   | Product Service      | 8084 |
   | Notification Service | 8085 |
   |                      |      |

<!-- CONFIGURATION -->
## Configuration

### Docker compose

1. Navigate to the `springboot-microservices` folder. 
Ensure you are in the correct directory by checking the contents:
    ```shell
    ls
    ```
    You should see docker-compose.yml and other Docker-related files.

2. Run Docker Compose to start the services.
    ```shell
    docker compose up -d
    ```
    This command will build and start the Docker containers in the background.
