# ShopSwiftly SaaS Platform

To read this document in Spanish, click [here](README.ESP.BackEnd.md).

## Introduction and Overview

ShopSwiftly offers a customizable e-commerce platform as a service, designed to empower clients with their own tailored selling solutions. The platform leverages a microservices architecture to enable rapid development, on-demand scalability, and cost efficiency.

## Unique Selling Points

Scalable Monorepo: Simplifies scaling through a Docker-compose administered monorepo.
Agile and Cost-Effective: Facilitates agile development practices and minimizes long-term costs.
SSL Termination and Request Aggregation: Enhances security and performance through the API Gateway.

### Microservices Description

API Gateway
The API Gateway is the primary entry point for all client requests, directing them to the appropriate service and managing SSL termination, request aggregation, circuit breaking, and rollbacks.

### Auth Service

This service handles authentication across the platform, issuing and validating JWT tokens to ensure secure access to business logic services.

### Config Server

A centralized configuration manager, the Config Server provides microservices with necessary configurations without hard-coded settings.

### Eureka Server

Serving as the service discovery registry, it enables microservices to locate and communicate with one another dynamically.

## Core Business Logic Services

- Product Service: Manages product information and inventory.
- User Service: Handles user profiles and authentication details.
- Transaction Service: Processes and records transactions.

## Database Design

Each microservice operates with its own PostgreSQL schema, ensuring data encapsulation and independence.

## Containerization and Environment Configuration

Docker containers encapsulate microservices, streamlining deployment, scaling, and environment management. Different .env files (dev and prod) allow for flexible environment-specific configurations.

## Repository Structure

The repository adopts a monorepo strategy, with all microservices housed within a single repository. This approach simplifies version control and inter-service coordination.

## Getting Started

### Local Setup

Navigate to the root directory containing the Docker-compose files and the parent POM.
Set the .env.dev and .env.prod files according to the required environment.
Compile the project using Maven with the command:

```bash
 mvn clean package
```

Build and start the services with Docker-compose:

```bash
docker-compose up --build
```

For individual microservices, use:

```bash
docker-compose build <service-name>
```

```bash
docker-compose up <service-name>
```

## Prerequisites

- Maven
- Linux environment with Docker or Docker on WSL

## CI/CD

We use GitHub Actions for automation.
It currently ensures everything is correct with the deployment to DockerHub. So, when something is pushed to your branch, it automatically performs checks and allows or disallows merges into the main branch, where the image on DockerHub gets updated.
Deployment from DockerHub to Render is done manually.
What does it specifically check?

That Java Maven compiles everything and builds the packages correctly.
That the images are generated correctly with docker-compose.
That the secrets are applied.
That the deployments are executed in the correct order.

### Error Handling

The pipeline has error management built-in. It automatically suspends upon encountering the first error and has logs ready for you to check where the failure occurred.
