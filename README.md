# Scalable Social Platform API

A high-performance, production-ready backend REST API for a social blogging and community platform. Built to handle complex entity relationships and heavy read-traffic by leveraging a robust relational database alongside an in-memory caching layer.

## 🚀 Tech Stack & Architecture

*   **Language:** Java 17+
*   **Framework:** Spring Boot 3.x (Spring Web, Spring Data JPA)
*   **Primary Database:** PostgreSQL
*   **Caching Layer:** Redis
*   **Build Tool:** Maven

## ✨ Key Features & Engineering Focus

*   **Advanced Relational Data Modeling:** 
    *   Designed a normalized schema using **PostgreSQL** and **Hibernate/JPA**.
    *   Implemented complex bidirectional mappings (`@OneToMany`, `@ManyToOne`) across `User`, `Post`, `Comment`, and `Bot` entities.
    *   Utilized cascading operations and orphan removal to maintain strict data integrity.
*   **High-Speed Read Operations (Caching):** 
    *   Integrated **Redis** to cache high-traffic endpoints (like fetching post feeds or comment trees).
    *   Significantly reduced database I/O, protecting the PostgreSQL instance from read-heavy bottlenecks.
*   **Clean Architecture:** 
    *   Strict separation of concerns using Controllers, Services, and Repositories.
    *   Data Transfer Objects (DTOs) used to cleanly decouple internal database models from API payload structures.

## 🛠️ Local Development Setup

### Prerequisites
*   Java Development Kit (JDK) 17 or higher
*   PostgreSQL running on port `5432`
*   Redis running on port `6379`

### 1. Database Configuration
Create a PostgreSQL database named `griddb` (or let the app create it based on your properties):
```sql
CREATE DATABASE griddb;
```
*Note: Update the username and password in `src/main/resources/application.properties` if yours differ from `postgres` / `admin123`.*

### 2. Start Redis
If you have Docker installed, you can quickly spin up a Redis instance:
```bash
docker run -d --name redis-stack -p 6379:6379 redis:latest
```

### 3. Run the Application
Use the included Maven wrapper to build and run the Spring Boot application:

**Windows:**
```cmd
mvnw.cmd spring-boot:run
```

**Mac/Linux:**
```bash
./mvnw spring-boot:run
```

## 📈 Future Enhancements
*   Implement Redis Cache Eviction policies for updated/deleted posts.
*   Add Spring Security with JWT for stateless user authentication.
*   Paginate the Post and Comment feeds to optimize payload sizes."# Scalable-Social-API" 
