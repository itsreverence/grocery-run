# GroceryRun App

## Recommended IDE

Use **IntelliJ IDEA** for this project. The repository already follows a Maven + Spring Boot layout that IntelliJ handles very well:
- Maven import and dependency sync are automatic.
- Spring Boot run/debug setup is easier.

## Prerequisites

Install these before running locally:
- JDK 25
- Docker Desktop (or Docker Engine + Docker Compose)
- IntelliJ IDEA (Community or Ultimate)

## Quick Start (IntelliJ + Docker)

1. Clone the repo and open it in IntelliJ as a Maven project.
2. Set project SDK to **Java 25**:
   - `File` -> `Project Structure` -> `Project SDK` -> select JDK 25.
3. Start PostgreSQL:
   ```bash
   docker compose up -d
   ```
4. Wait until container `postgres-spring-boot` is healthy/running.
5. Run the app from IntelliJ:
   - Open `src/main/java/com/groceryrun/app/AppApplication.java`
   - Click Run on `AppApplication`.

The app is available at: `http://localhost:8080`

## Running from Terminal

Start database:
```bash
docker compose up -d
```

Run app:
```bash
./mvnw spring-boot:run
```

Run tests:
```bash
./mvnw test
```

## Stopping Services

Stop the app in IntelliJ/terminal, then stop database:
```bash
docker compose down
```

To also remove persisted DB volume:
```bash
docker compose down -v
```
