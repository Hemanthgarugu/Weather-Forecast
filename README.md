# ⛅ Enterprise Weather Application

A production-ready, full-stack **Weather Application** built with **Java 17 (Core Java)**, **Spring Boot 3**, **Spring Security**, **JWT Authentication**, **Spring Data JPA**, **Swagger / OpenAPI 3**, **Docker**, **Postman**, and a modern **Glassmorphic Web UI**.

---
## Live Demo

https://weather-forecast-n8q6.onrender.com

## 🚀 Features

- **Core Backend**: Java 17 & Spring Boot 3 REST API architecture.
- **Spring Security & JWT**: Stateless Authentication filter chain with JJWT 0.12.5.
- **Data Persistence**: Spring Data JPA with Hibernate. Out-of-the-box support for H2 In-Memory DB, PostgreSQL, and MySQL.
- **Smart Weather Engine**: Consumes OpenWeatherMap API with automatic, rich fallback mock engine if no API key is set.
- **Interactive OpenAPI / Swagger UI**: Built-in interactive documentation at `/swagger-ui/index.html`.
- **Exported Postman Collection**: `Weather_App_Postman_Collection.json` for one-click API testing.
- **Glassmorphic Web UI**: Responsive single-page interface with dynamic weather background transitions, unit conversion (°C/°F), 5-day forecasts, and favorited cities ribbon.
- **Containerization & Deployment**: Dockerfile, `docker-compose.yml`, and `render.yaml` deployment scripts.

---

## 🛠️ Technology Stack

| Component | Technology |
| :--- | :--- |
| **Language** | Java 17 |
| **Build Tool** | Apache Maven (`pom.xml`) |
| **Framework** | Spring Boot 3.2.4 |
| **Security** | Spring Security 6 + JJWT `0.12.5` |
| **Databases** | H2 (Dev/Demo), PostgreSQL, MySQL |
| **API Specs** | Springdoc OpenAPI 3 / Swagger UI |
| **Containers** | Docker, Docker Compose |
| **Cloud Deployment** | Render, AWS (EC2 / Beanstalk) |
| **Frontend** | HTML5, Glassmorphism CSS3, Vanilla JS |

---

## ⚡ Quick Start Options
### Option A: Run directly in your IDE (IntelliJ IDEA / Eclipse / VS Code) - Recommended!

1. Clone the repository:

```bash
git clone https://github.com/Hemanthgarugu/Weather-Forecast.git
```

2. Navigate to the project directory:

```bash
cd Weather-Forecast
```

3. Open the project in **IntelliJ IDEA**, **Eclipse**, or **VS Code**.

4. Wait for Maven to download all dependencies.

5. Run the main class:

```
WeatherApplication.java
```

6. Open your browser and visit:

- 🌐 Application: `http://localhost:8080`
- 📖 Swagger UI: `http://localhost:8080/swagger-ui/index.html`

---

### Option B: Run via Terminal (Maven Wrapper)

Open PowerShell, Command Prompt, or Git Bash inside the project folder and run:

```bash
.\mvnw.cmd spring-boot:run
```

For Linux/macOS:

```bash
./mvnw spring-boot:run
```

*(Or if you have Maven installed on your system PATH: `mvn spring-boot:run`)*

### 2. Access the Application
- 🌐 **Web App Dashboard**: `http://localhost:8080`
- 📑 **Swagger / OpenAPI Documentation**: `http://localhost:8080/swagger-ui/index.html`
- 🗄️ **H2 In-Memory Database Console**: `http://localhost:8080/h2-console`
  - *JDBC URL*: `jdbc:h2:mem:weatherdb`
  - *Username*: `sa`
  - *Password*: `password`

---

## 🐳 Beginner Guide to Docker (Optional)

### What is Docker?
Docker allows you to run software inside an isolated "container" box without having to manually install software dependencies (like databases) on your system.

### How to Run with Docker Compose
If you have Docker Desktop installed, you can launch both the Spring Boot app and a real PostgreSQL database with one command:

```bash
docker compose up --build
```

To stop the containers:
```bash
docker compose down
```

---

## 📬 Testing with Postman

1. Open Postman.
2. Click **Import** -> Select `Weather_App_Postman_Collection.json`.
3. Test requests:
   - `POST /api/auth/signup` to create a user account.
   - `POST /api/auth/login` to get your JWT Bearer token (Postman automatically saves the token to environment variables).
   - `GET /api/favorites` and `POST /api/favorites` to test protected user endpoints.

---

## ☁️ Deployment Guides

### Deploying to Render
1. Connect your GitHub repository containing this codebase to [Render](https://render.com).
2. Render will automatically detect `render.yaml` and build the containerized web service.
3. Add an optional environment variable `WEATHER_API_KEY` for live global weather data.

### Deploying to AWS (EC2)
1. Launch an EC2 instance with Ubuntu/Amazon Linux.
2. Install Docker: `sudo apt update && sudo apt install docker.io docker-compose -y`.
3. Clone repository and run `sudo docker-compose up -d`.
