# Customer Rewards API

A Spring Boot REST API that calculates customer reward points based on purchase transactions over the last 3 months.

## Reward Points Rules

| Purchase Amount | Points Earned |
|----------------|---------------|
| $0 – $50       | 0 points |
| $50 – $100     | 1 point per dollar above $50 |
| Over $100      | 2 points per dollar above 100 + 50 points for the $50–$100 tier |

**Example:** A $120 purchase earns `(120 - 100) × 2 + 50 = 90 points`

---

## Tech Stack

- Java 17
- Spring Boot 3.5
- Spring Data JPA + H2 (in-memory)
- Lombok
- JUnit 5 + Mockito
- Swagger

---

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+

### Run the application
```bash
./mvnw spring-boot:run
```

The app starts at `http://localhost:8080`. Sample data is loaded automatically via `data.sql`.

### Run tests
```bash
./mvnw test
```

---

## API Endpoints

### Get reward points for all customers
```
http://localhost:8080/api/rewards/calculate-reward-points
```

**Example:** `GET /api/rewards/calculate-reward-points`

**Sample Response:**
```json
{
  "custId": "customer1",
  "monthlyRewardPoints": {
    "MARCH": 0,
    "FEBRUARY": 150
  },
  "totalRewardPoints": 150
}
```

### Health check
```
GET /actuator/health
```

---

## H2 Console (Dev)

Access the in-memory database at: `http://localhost:8080/h2-console`

- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** 

---

## Project Structure

```
src/
├── main/
│   ├── java/com/rewards/customer/
│   │   ├── controller/   # RewardsController
│   │   ├── service/      # RewardsService, RewardsServiceImpl (points calculation logic)
│   │   ├── entity/       # Customer, Transaction
│   │   ├── dto/          # Reward
│   │   ├── repository/   # TransactionRepository
│   │   ├── util/         # RewardUtil
│   │   ├── exception/    # GlobalExceptionHandler, IllegalArgumentException, InvalidTransactionException
│   │   └── CustomerRewardsApiApplication
│   └── resources/
│       ├── data.sql      # Sample data
│       └── application.properties
└── test/
    └── java/com/rewards/customer/
        ├── controller/   # RewardsControllerTest
        ├── integration/  # RewardsControllerIntegrationTest
        ├── service/      # RewardsServiceImplTest
        ├── util/         # RewardUtilTest
        └── RewardsApplication.java
```
