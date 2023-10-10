# OMS Microservices Overview

The OMS (Order Management System) microservices architecture consists of seven individual services, including a discovery server and an API gateway, providing a scalable and modular solution for managing orders and related operations.

### Microservices Components

1. **API Gateway**:
    - Function: Entry point to the entire microservices ecosystem.

2. **Discovery Server (Eureka)**:
    - Function: Manages services' health and status, including the API gateway.
    - Access: [http://localhost:8761/](http://localhost:8761/)

3. **Inventory Service**:
    - Function: Provides information about available stock.

4. **Notification Service**:
    - Function: Handles notifications to customers regarding their orders.

5. **Order Service**:
    - Function: Manages order-related information.

6. **Product Service**:
    - Function: Provides details about the products available for purchase.

7. **Review Service**:
    - Function: Manages customer reviews.

8. **User Service**:
    - Function: Stores user roles, such as manager and admin.

### Running the Microservices

To manage the containers for these microservices, you can use Docker Compose with the following commands:

- To start all containers:
  ```
  docker-compose up -d
  ```

- To start an individual container:
  ```
  docker-compose up <container_name>
  ```

- To shut down all containers:
  ```
  docker-compose down
  ```

### Database Configuration

- To configure the database, you can import an SQL dump file using the following command:
  ```
  mysql -u root -p'Do1999ld#' < /docker-entrypoint-initdb.d/local-database.sql
  ```

### Testing Endpoints

You can test the endpoints of each microservice, typically using the following pattern:
- For the order service: [http://localhost:8088/api/order](http://localhost:8088/api/order)
- Replace `/api/order` with the appropriate service endpoint.

### Tools

The microservices ecosystem includes various tools for monitoring, authentication, and tracing:

- **Eureka Discovery Server (Service Registry)**: [http://localhost:8761/](http://localhost:8761/)
- **Zipkin (Request Tracing)**: [http://localhost:9411/zipkin/](http://localhost:9411/zipkin/)
- **Keycloak (Authentication Token Provider)**: [http://localhost:8080/](http://localhost:8080/)
    - Make sure to use the "spring-boot-microservices-realm" realm.
    - Configure clients and secrets as needed.
    - Sometimes keycloak won't allow you to generate access token
      - to redirect traffic from keycloak to localhost update ``sudo nano /private/etc/hosts`` only in DEV thou
- **Actuator (Monitoring and Management)**: [http://localhost:8081/actuator/health](http://localhost:8081/actuator/health)