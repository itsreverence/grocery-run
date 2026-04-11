# GroceryRun App

## Recommended IDE

Use **IntelliJ IDEA** for this project. The repository already follows a Maven + Spring Boot layout that IntelliJ handles very well:
- Maven import and dependency sync are automatic.
- Spring Boot run/debug setup is easier.

## Prerequisites

Install these before running locally:
- JDK 21
- Docker Desktop (or Docker Engine + Docker Compose)
- IntelliJ IDEA (Community or Ultimate)

## Environment Variables

The app reads its datasource configuration from environment variables:

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

For local IntelliJ runs, configure them directly in the run configuration:

1. Open the run configuration for `AppApplication`
2. Find `Environment variables`
3. Add:
   - `SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5332/groceryrun`
   - `SPRING_DATASOURCE_USERNAME=groceryrun`
   - `SPRING_DATASOURCE_PASSWORD=it326admin`

If you run from the terminal instead, export them before starting the app:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5332/groceryrun
export SPRING_DATASOURCE_USERNAME=groceryrun
export SPRING_DATASOURCE_PASSWORD=it326admin
```

## Quick Start (IntelliJ + Docker)

1. Clone the repo and open it in IntelliJ as a Maven project.
2. Set project SDK to **Java 21**:
   - `File` -> `Project Structure` -> `Project SDK` -> select JDK 21.
3. Start PostgreSQL:
   ```bash
   docker compose up -d
   ```
4. Wait until container `postgres-spring-boot` is healthy/running.
5. Make sure the datasource environment variables above are configured in your IntelliJ run configuration.
6. Run the app from IntelliJ:
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
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5332/groceryrun
export SPRING_DATASOURCE_USERNAME=groceryrun
export SPRING_DATASOURCE_PASSWORD=it326admin
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
