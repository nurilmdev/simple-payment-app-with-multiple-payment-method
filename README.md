# Payment Service API

Simple payment service built with Spring Boot, MySQL, Flyway, and Strategy Design Pattern.
This project demonstrates a scalable payment architecture with idempotency, global exception handling, and payment lifecycle (soon).

## Tech Stack

- Java 21
- Spring Boot 3
- Spring Data JPA
- MySQL 8
- Flyway Migration
- Maven

## Features

- Create Payment API
- Multiple Payment Methods (Strategy Pattern)
- Idempotency using Reference Number
- Global Exception Handling
- Database Migration with Flyway
- Unique Constraint Protection
- Clean Architecture Structure


## How to Run

### 1. Clone repository
```bash
git clone https://github.com/nurilmdev/simple-payment-app-with-multiple-payment-method.git
cd payment-service
```
### 2. Create database for app
```
CREATE DATABASE payment_db;
```

### 3. Configure database settings
```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/payment_db
    username: root
    password: yourpassword
```

### 4. Run App with
```
mvn spring-boot:run
```

