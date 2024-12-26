# Career Portal Macroproject

This repository contains a macroproject composed of multiple microservices, including the **Career Portal Service**, **Talent Request Service**, **Talent Fulfillment Service**, API Gateway, and Discovery Service. The project leverages Spring Boot, Axon Framework, and Eureka for a distributed, scalable architecture. You may find the front-end repository [here](https://github.com/IsaiasMalvar/talent-acquisition-front-end).

---

## Table of Contents

- [Overview](#overview)
- [Microservices Architecture](#microservices-architecture)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Folder Structure](#folder-structure)
- [Running the Application](#running-the-application)

---

## Overview

The **Career Portal Macroproject** is a collection of microservices designed for handling career-related operations, service discovery, and API gateway management. Each service is independently deployable and communicates with others via REST, events, and Eureka Discovery.

---

## Microservices Architecture

### Core Components

1. **Career Portal Service**:
   - Manages career-related operations with CQRS and Event Sourcing.
   - Uses Axon Framework and connects to Axon Server.

2. **Talent Request Service**:
   - Handles talent request submissions and processing.

3. **Talent Fulfillment Service**:
   - Manages the fulfillment of talent requests.

4. **API Gateway**:
   - Central entry point for all client requests.
   - Routes requests to the appropriate microservices.

5. **Discovery Service**:
   - Implements service registration and discovery using Eureka.
   - Ensures scalability and dynamic service management.

### Communication

- **REST APIs** for synchronous communication.
- **Axon Server** for asynchronous event-driven communication.
- **Eureka Discovery** for dynamic service registration and lookup.

---

## Technologies Used

- **Spring Boot** (3.2.0)
- **Spring Cloud Netflix Eureka** for service discovery
- **Axon Framework** for CQRS and Event Sourcing
- **Axon Server** for event storage and messaging
- **H2 Database** for testing and prototyping
- **Maven** for dependency management

---

## Prerequisites

Before running the project, ensure the following are installed:

1. **Java Development Kit (JDK) 17** or higher.
2. **Maven** for dependency management and builds.
3. **Axon Server** for event storage and messaging.

### Download and Run Axon Server

1. [Download Axon Server](https://download.axoniq.io/axonserver/AxonServer.zip).
2. Extract the downloaded zip file.
3. Navigate to the extracted directory and run:
   ```bash
   java -jar axonserver.jar
   ```
   Axon Server will start on `localhost:8024` by default.

---

## Getting Started

### Installation Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/IsaiasMalvar/talent-acquisition-microservices.git
   cd career-portal-macroproject
   ```

2. Navigate to each microservice directory and install dependencies:
   ```bash
   mvn clean install
   ```

3. Start Axon Server as described in the [Prerequisites](#prerequisites).

4. Start the Discovery Service:
   ```bash
   cd tams-discovery-service
   mvn spring-boot:run
   ```

5. Start the remaining microservices (e.g., Career Portal Service, Talent Request Service, Talent Fulfillment Service):
   
   ```bash
   cd <career-portal-service>
   mvn spring-boot:run
   ```
   ```bash
   cd <talent-fulfillment-service>
   mvn spring-boot:run
   ```
   ```bash
   cd <talent-request-service>
   mvn spring-boot:run
   ```

7. Start the API Gateway:
   ```bash
   cd tams-api-gateway
   mvn spring-boot:run
   ```

8. Verify that all services have registered with the Discovery Service and are accessible.

---

## Folder Structure

```
TAMS
├── tams-api-gateway               # API Gateway service
├── discovery-service         # Eureka Discovery Service
├── career-portal-service     # Career Portal Service
├── talent-request-service    # Talent Request Service
├── talent-fulfillment-service# Talent Fulfillment Service
├── tam-core-api             # Shared CQRS modules for saga/process management.
└── README.md                 # Macroproject README
```

---

## Running the Application

1. **Start Axon Server**: Ensure Axon Server is running in the background for event storage and messaging.

2. **Start the Discovery Service**: Launch the Eureka Discovery Service to allow all other services to register.

3. **Start Microservices**: Launch the individual services (e.g., Career Portal Service, Talent Request Service, Talent Fulfillment Service) one by one.

4. **Start the API Gateway**: Finally, launch the API Gateway.

5. **Access the Application**:
   - API Gateway will be accessible at `http://localhost:8080` (default port).
   - Each service will have its own base URL, as registered with Eureka.

6. **Test Communication**:
   - Use tools like Postman or curl to send requests through the API Gateway.
   - Verify event handling via Axon Server's dashboard at `http://localhost:8024`.

---

