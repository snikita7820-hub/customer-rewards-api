# Rewards API - Spring Boot

## Overview

This project is a Spring Boot based REST API that calculates customer reward points based on transaction amounts.

Reward points are calculated using the following rules:

- 2 points for every dollar spent above 100
- 1 point for every dollar spent between 50 and 100

---

## Tech Stack

- Java 17
- Spring Boot
- Maven
- REST API
- Swagger / OpenAPI
- Spring Boot Actuator
- JUnit
- Jackson

---

## Postman Collection

Postman collection is added to the project for easy API testing.

### Location

```text
customer-rewards-api/customer-rewards-apis.postman_collection.json
```

---

## Project Structure

```text
src/main/java
│
├── controller
│   └── RewardsController.java
│
├── service
│   └── RewardsService.java
│
├── dto
│   ├── CustomerRewardSummary.java
│   └── MonthlyReward.java
│
├── entity
│   └── Transaction.java
│
├── exception
│   ├── GlobalExceptionHandler.java
│   └── InvalidTransactionException.java
│
└── CustomerRewardsApiApplication.java

src/test/java
│
├── controller
│   └── RewardControllerIntegrationTest.java
│
└── service
    └── RewardServiceTest.java
```

---

## Additional Features

- Swagger/OpenAPI documentation
- Spring Boot Actuator for health monitoring
- Unit test cases using JUnit and Mockito
- Global exception handling
- Layered architecture design
- Postman collection for API testing
