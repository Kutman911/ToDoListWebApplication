# ToDo List Web Application

A simple web-based ToDo List application built with Spring Boot, Spring Data JPA, Thymeleaf, and PostgreSQL.

## Overview

This application allows users to manage their daily tasks. Users can add new tasks, mark them as completed, and delete tasks. The application uses a PostgreSQL database to persist data.

### Tech Stack
- **Language:** Java 21
- **Framework:** Spring Boot 4.0.2
- **Data Access:** Spring Data JPA (Hibernate)
- **Database:** PostgreSQL
- **Template Engine:** Thymeleaf
- **Build Tool:** Maven

## Requirements

- **Java Development Kit (JDK):** Version 21 or higher.
- **PostgreSQL:** A running instance of PostgreSQL database.
- **Maven:** (Optional) You can use the included Maven wrapper (`mvnw`).

## Setup and Run

### 1. Database Configuration

Before running the application, ensure you have a PostgreSQL database created. By default, the application expects the following configuration (found in `src/main/resources/application.properties`):

- **URL:** `jdbc:postgresql://localhost:5433/kutos`
- **Username:** `kutos`
- **Password:** `sk88690733`

You can override these settings using environment variables or by modifying the `application.properties` file.

### 2. Build the Project

Use the Maven wrapper to build the project:

```powershell
./mvnw clean package
```

### 3. Run the Application

You can run the application using the following command:

```powershell
./mvnw spring-boot:run
```

The application will be available at `http://localhost:8080` by default.

## Scripts

- `mvnw` / `mvnw.cmd`: Maven wrapper scripts for building and running the project without a local Maven installation.
- `clean package`: Compiles the code and packages it into a JAR file.
- `spring-boot:run`: Starts the Spring Boot application.

## Environment Variables

The following environment variables can be used to configure the database connection (optional, defaults are in `application.properties`):

| Variable | Description | Default Value |
|----------|-------------|---------------|
| `SPRING_DATASOURCE_URL` | JDBC URL for PostgreSQL | `jdbc:postgresql://localhost:5433/kutos` |
| `SPRING_DATASOURCE_USERNAME` | Database username | `kutos` |
| `SPRING_DATASOURCE_PASSWORD` | Database password | `sk88690733` |

## Tests

Currently, there are no automated tests implemented in this project.
- TODO: Add unit tests for `RecordService`.
- TODO: Add integration tests for `CommonController`.

## Project Structure

```text
ToDoListWebApplication/
├── mvnw                 # Maven wrapper (Unix)
├── mvnw.cmd             # Maven wrapper (Windows)
├── pom.xml              # Maven project configuration
├── src/
│   ├── main/
│   │   ├── java/        # Java source code
│   │   │   └── kg/kut/os/
│   │   │       ├── ToDoListApplication.java   # Entry point
│   │   │       ├── controller/                # Web controllers
│   │   │       ├── dao/                       # Data access objects
│   │   │       ├── entity/                    # Domain entities
│   │   │       └── service/                   # Business logic
│   │   └── resources/
│   │       ├── application.properties         # Application configuration
│   │       ├── static/                        # Static assets (CSS, JS)
│   │       └── templates/                     # HTML templates (Thymeleaf)
│   └── test/            # (Future) Test source code
└── HELP.md              # Spring Boot generated help file
```

## License

This project does not have an explicit license file.
- TODO: Add a LICENSE file (e.g., MIT, Apache 2.0).
