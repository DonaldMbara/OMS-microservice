
---

# OMS Microservices Overview

The Order Management System (OMS) is designed with a microservices architecture comprising seven individual services, including a discovery server and an API gateway. This architecture provides a scalable and modular solution for managing orders, inventory, notifications, products, users, and customer reviews.

## Microservices Components

1. **API Gateway**:
    - **Function**: Serves as the entry point to the microservices ecosystem, routing requests to the appropriate services.
    - **Technology**: Zuul or Spring Cloud Gateway.

2. **Discovery Server (Eureka)**:
    - **Function**: Manages service registration and discovery, ensuring services are aware of each other's health and availability.
    - **Access**: [http://localhost:8761/](http://localhost:8761/)

3. **Inventory Service**:
    - **Function**: Handles stock information and provides details on product availability.
    - **Endpoint**: [http://localhost:8084/api/inventory](http://localhost:8084/api/inventory)

4. **Notification Service**:
    - **Function**: Sends notifications to customers regarding their orders.
    - **Endpoint**: [http://localhost:8085/api/notification](http://localhost:8085/api/notification)

5. **Order Service**:
    - **Function**: Manages all order-related operations.
    - **Endpoint**: [http://localhost:8088/api/order](http://localhost:8088/api/order)

6. **Product Service**:
    - **Function**: Manages details about the products available for purchase.
    - **Endpoint**: [http://localhost:8086/api/product](http://localhost:8086/api/product)

7. **Review Service**:
    - **Function**: Handles customer reviews for products.
    - **Endpoint**: [http://localhost:8087/api/review](http://localhost:8087/api/review)

8. **User Service**:
    - **Function**: Manages user roles and permissions, including manager and admin roles.
    - **Endpoint**: [http://localhost:8083/api/user](http://localhost:8083/api/user)

## Running the Microservices

Docker Compose is utilized for container orchestration to manage the microservices:

- **Start all services**:
  ```bash
  docker-compose up -d
  ```

- **Start a specific container**:
  ```bash
  docker-compose up <container_name>
  ```

- **Stop all services**:
  ```bash
  docker-compose down
  ```

### Keycloak Authentication

Keycloak is run for authentication purposes, managing access tokens for users:

```bash
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:22.0.4 start-dev
```

Run Kafka:

```bash
docker run -d --name broker -p 9092:9092 -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=PLAINTEXT:PLAINTEXT -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 apache/kafka:latest
```

Run Zookeeper:

```bash
docker run --name zookeeper bitnami/zookeeper:latest
```

- **Keycloak Access**: [http://localhost:8080/](http://localhost:8080/)
    - **Realm**: Use the realm named "spring-boot-microservices-realm."
    - **Client Configuration**: Configure necessary clients and secrets.
    - **Note**: If Keycloak does not generate access tokens, update the `/etc/hosts` file to redirect traffic to localhost (DEV only).

### Database Configuration

To initialize the database, import an SQL dump using the following command:

```bash
mysql -u root -p'Do1999ld#' < /docker-entrypoint-initdb.d/local-database.sql
```

Locally:

```bash
mysql -u root -p < /path/to/local-database.sql
```

## Monitoring and Logging

Various tools are included for monitoring, tracing, and managing services:

- **Eureka Discovery Server**:
    - **Access**: [http://localhost:8761/](http://localhost:8761/)

- **Zipkin (Request Tracing)**:
    - **Access**: [http://localhost:9411/zipkin/](http://localhost:9411/zipkin/)
    - **Run**:
    ```bash
    docker run -d -p 9411:9411 openzipkin/zipkin
    ```

- **Actuator (Monitoring & Health)**:
    - **Access**: [http://localhost:8081/actuator/health](http://localhost:8081/actuator/health)

## Testing Microservices Endpoints

Each service exposes REST endpoints accessible locally:

- **Order Service**: [http://localhost:8088/api/order](http://localhost:8088/api/order)
- Replace `/api/order` with the appropriate service endpoint (e.g., `/api/inventory`, `/api/product`).

## Future Enhancements

- Implement rate limiting and throttling for the API gateway.
- Introduce OAuth2 for securing service communication beyond Keycloak.
- Expand the database schema to accommodate scaling for user reviews and order management.

---

