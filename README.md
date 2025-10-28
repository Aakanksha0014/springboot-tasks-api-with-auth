# 🧩 Spring Boot Tasks API (To-Do Manager)

A simple yet robust **RESTful API** built with **Java Spring Boot** and **MySQL**, designed to manage tasks with full **CRUD operations**, optional **JWT-based authentication**, and proper validation and error handling.

---

## 🚀 Features

- ✨ Create, Read, Update, and Delete (CRUD) operations for tasks
- 🧠 Input validation using `jakarta.validation`
- ⚙️ Proper error handling with meaningful HTTP status codes
- 🗄️ MySQL database integration via Spring Data JPA
- 🔐 (Optional) JWT authentication (register/login)
- 🔍 Filter tasks by status (`/api/tasks?status=completed`)
- 🌱 Environment variables support via `.env`
- 📦 Ready-to-import **Postman collection**

---

## 🛠️ Tech Stack

| Component | Technology |
|------------|-------------|
| **Backend Framework** | Spring Boot 3.1.4 |
| **Language** | Java 17 |
| **Database** | MySQL |
| **Build Tool** | Maven |
| **Security** | Spring Security + JWT |
| **Validation** | Jakarta Validation API |

---

## 📂 Folder Structure

```
springboot-tasks-api/
│
├── src/main/java/com/example/tasksapi/
│   ├── controller/        # REST Controllers (Auth, Task)
│   ├── dto/               # Request/Response DTOs
│   ├── exception/         # Custom exceptions + handlers
│   ├── model/             # JPA Entities (Task, User)
│   ├── repository/        # JPA Repositories
│   ├── security/          # JWT + Spring Security configuration
│   └── service/           # Business logic for tasks & auth
│
├── src/main/resources/
│   └── application.properties   # Environment-based config
│
├── postman/
│   └── TasksAPI.postman_collection.json
│
├── pom.xml
└── README.md
```

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/<your-username>/springboot-tasks-api.git
cd springboot-tasks-api
```

### 2️⃣ Configure the Environment

Create a `.env` file in the project root:
```bash
DB_URL=jdbc:mysql://localhost:3306/tasksdb
DB_USER=root
DB_PASSWORD=yourpassword
JWT_SECRET=your-very-secret-key
JWT_EXP_MS=86400000
PORT=8080
```

### 3️⃣ Set Up MySQL Database

```sql
CREATE DATABASE tasksdb;
```

Ensure your MySQL service is running and credentials match your `.env`.

### 4️⃣ Build & Run the Application

Using Maven:
```bash
mvn spring-boot:run
```

Or package and run the jar:
```bash
mvn clean package
java -jar target/tasks-api-0.0.1-SNAPSHOT.jar
```

The server will start at:
```
http://localhost:8080
```

---

## 🧾 API Endpoints

### ✅ Task Management

| Method | Endpoint | Description |
|--------|-----------|-------------|
| `POST` | `/api/tasks` | Create new task |
| `GET` | `/api/tasks` | Get all tasks |
| `GET` | `/api/tasks/{id}` | Get task by ID |
| `PUT` | `/api/tasks/{id}` | Update task |
| `DELETE` | `/api/tasks/{id}` | Delete task |
| `GET` | `/api/tasks?status=completed` | Filter by status |

#### Example: Create a Task
```bash
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d '{
  "title": "Finish assignment",
  "description": "Submit Java REST API project",
  "status": "PENDING"
}'
```

#### Example: Get All Tasks
```bash
curl http://localhost:8080/api/tasks
```

---

## 🔐 Authentication (Optional)

Endpoints:
| Method | Endpoint | Description |
|--------|-----------|-------------|
| `POST` | `/api/auth/register` | Register a new user |
| `POST` | `/api/auth/login` | Authenticate user & get JWT token |

#### Example: Register
```bash
curl -X POST http://localhost:8080/api/auth/register -H "Content-Type: application/json" -d '{"username": "user1", "password": "secret"}'
```

#### Example: Login
```bash
curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d '{"username": "user1", "password": "secret"}'
```

Response:
```json
{
  "token": "<your-jwt-token>"
}
```

You can include this token in future requests (if JWT is enabled) using:
```
Authorization: Bearer <your-jwt-token>
```

---

## 🧪 Postman Collection

Import the following file into Postman:
```
postman/TasksAPI.postman_collection.json
```

It includes preconfigured requests for:
- Register / Login
- Create / Get / Update / Delete tasks
- Filter tasks by status

---

## 🧰 Environment Variables Used

| Variable | Default | Description |
|-----------|----------|-------------|
| `DB_URL` | `jdbc:mysql://localhost:3306/tasksdb` | MySQL connection URL |
| `DB_USER` | `root` | MySQL username |
| `DB_PASSWORD` | `password` | MySQL password |
| `JWT_SECRET` | `changeit` | Secret key for JWT |
| `JWT_EXP_MS` | `86400000` | Token validity in milliseconds |
| `PORT` | `8080` | Server port |

---

## 📘 License

This project is licensed under the **MIT License**.  
Feel free to use, modify, and distribute it.

---

## 🤝 Contributing

Pull requests are welcome!  
For major changes, please open an issue first to discuss what you’d like to change.

---

## 📧 Contact

**Author:** Aakanksha Patil  
**Email:** aakankshapatil721@gmail.com 
**GitHub:** [github.com/Aakanksha0014](https://github.com/Aakanksha0014)

---

### 💡 Pro Tip
You can easily secure task endpoints by updating `SecurityConfig.java`:
```java
.requestMatchers("/api/auth/**").permitAll()
.anyRequest().authenticated()
```
Then, require a JWT token in all `/api/tasks/**` requests.
