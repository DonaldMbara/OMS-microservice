

# OMS Microservice 
Make show discovery and api-gateway are up and running with no interruption

### To start up the ALL the containers
``docker compose up -d``

### To start up INDIVIDUAL container

``docker compose up <container name> ``

### To shutdown containers

``docker compose down``

### configure dump database
``mysql -u root -p'Do1999ld#' < /docker-entrypoint-initdb.d/local-database.sql ``



