# Client Service
REST-сервис учёта клиентов. Java 17, Spring Boot, Spring Data JPA, PostgreSQL, Liquibase.

## Требования
JDK 17, запущенный Docker, свободные порты 8080 и 5433.

## Запуск
mvnw.cmd spring-boot:run   (Linux/Mac: ./mvnw spring-boot:run)
БД поднимается автоматически из compose.yaml, схема создаётся Liquibase.

## Swagger
http://localhost:8080/swagger-ui/index.html

## База данных
host localhost, port 5433, db mydatabase, user myuser, password secret

## Эндпоинты
POST/GET /api/clients, GET/PUT/DELETE /api/clients/{id}

## Без Docker
spring:
  docker:
    compose:
      enabled: false
  datasource:
    url: jdbc:postgresql://localhost:5432/mydatabase
    username: myuser
    password: secret
