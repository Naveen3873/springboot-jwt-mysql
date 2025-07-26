# 🔐 Online User Service – Spring Boot + JWT Auth

This project is a RESTful API built using Spring Boot. It supports secure user registration and login via JWT authentication, with a custom auto-generated user code format.

---

## ✅ Features

- 🔐 JWT Authentication
- 🧑‍💼 User Registration with custom code (`APP250701`)
- 🔁 Unique & sequential code generation per month/year
- 🛡️ Secure API access using Bearer tokens
- 💡 Auto-saving of token in database
- 📦 Clean layered architecture (Controller → Service → Repository)

---

## 🧰 Technologies Used

- Java 17+
- Spring Boot 3.x
- Spring Security
- JSON Web Token (JJWT 0.11.5)
- Lombok
- JPA (Hibernate)
- MySQL
- Maven

---

## 📦 Dependencies (`pom.xml`)

```xml
<!-- Spring Boot Starters -->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- MySQL Connector -->
<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
  <scope>runtime</scope>
</dependency>

<!-- JWT Support -->
<dependency>
  <groupId>io.jsonwebtoken</groupId>
  <artifactId>jjwt-api</artifactId>
  <version>0.11.5</version>
</dependency>
<dependency>
  <groupId>io.jsonwebtoken</groupId>
  <artifactId>jjwt-impl</artifactId>
  <version>0.11.5</version>
  <scope>runtime</scope>
</dependency>
<dependency>
  <groupId>io.jsonwebtoken</groupId>
  <artifactId>jjwt-jackson</artifactId>
  <version>0.11.5</version>
  <scope>runtime</scope>
</dependency>

<!-- Lombok -->
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <optional>true</optional>
</dependency>
```


## 📄 API Endpoints

### 🔹 1. Register User

**POST** `/auth/api/v1/register`

#### ✅ Request Payload:

```json
{
  "name": "Vicky",
  "mobileNumber": "9363164329",
  "password": "test123",
  "email": "vicky@example.com",
  "role": "USER"
}
```

#### ✅ Success Response:

```json
"User registered successfully"
```

---

### 🔹 2. Login User

**POST** `/auth/api/v1/login`

#### ✅ Request Payload:

```json
{
  "mobileNumber": "9363164329",
  "password": "test123"
}
```

#### ✅ Success Response:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

### 🔹 3. Get User by ID (🔐 Protected)

**GET** `/api/v1/users/{id}`

#### ✅ Headers:

```http
Authorization: Bearer <JWT_TOKEN>
```

#### ✅ Success Response:

```json
{
  "id": "user_id",
  "name": "Vicky",
  "email": "vicky@example.com",
  "mobileNumber": "9363164329",
  "role": "USER",
  "code": "APP250701"
}
```

---

## 🔐 JWT Authentication Details

| Field           | Value                      |
|----------------|----------------------------|
| Signing Algo    | HS256                      |
| Token Expiry    | 1 hour                     |
| Header Format   | `Authorization: Bearer <token>` |
| Token Storage   | Saved in `User.token` column |

---

## 🔧 Setup & Run

```bash
# Clone the repo
git clone https://github.com/Naveen3873/springboot-jwt-mysql.git
cd online-user-service

# Build project
mvn clean install

# Run locally
mvn spring-boot:run
```

---

## 🌐 CORS Config

Global CORS config is defined in `SecurityConfig.java` to allow frontend access.

---

## 📁 Project Structure

```
src/
├── config/                 # Security & CORS config
├── controller/             # REST controllers
├── dto/                    # DTO classes
├── entity/                 # JPA entities
├── repository/             # Spring Data JPA Repositories
├── security/               # JWT utils & filters
├── service/                # Business logic
└── OnlineUserServiceApplication.java
```

---

## 📬 Contact

Developed by **Naveen K**  
Email: naveenbe3873@gmail.com  

---
