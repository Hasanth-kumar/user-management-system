🌱 User Management System – Master Plan Document
✅ 1. Project Overview
The User Management System is a beginner-friendly, microservice-based backend application built using Spring Boot and MongoDB. It provides:

User Registration and Profile Management

JWT-based Authentication

Email Notification Service on registration and other triggers

This system is ideal for learning microservices, REST APIs, MongoDB with Spring Data, JWT authentication, and inter-service communication. It mimics real-world backend design suitable for SaaS applications, admin panels, and user-facing apps.

✅ 2. Tech Stack Overview
Layer	Technology
Language	Java 17+
Framework	Spring Boot 3.x
Data Storage	MongoDB Atlas
API Protocol	REST
Authentication	JWT (JSON Web Tokens)
Communication	REST + WebClient (Future: Feign)
Build Tool	Maven
Email Notification	Spring Boot Starter Mail
DevTools	Spring DevTools
Others	Lombok, SLF4J, Logback, etc.

✅ 3. Architecture Overview
🏗️ Microservices Layout
sql
Copy
Edit
              +--------------------+
              |  Gateway Service   |  (Planned)
              +--------+-----------+
                       |
        +--------------+---------------------+
        |              |                     |
+---------------+  +-------------+   +-----------------+
| auth-service  |  | user-service|   |  email-service  |
+---------------+  +-------------+   +-----------------+
        |                |                    |
   MongoDB (auth_db)  MongoDB (user_db)   MongoDB (email_db)
                          |
                  [profiles collection]
🔄 Future Plans
Gateway Service for central routing and CORS handling

API Versioning and centralized auth via API Gateway

Service Discovery (Eureka) or Spring Cloud for scaling

RabbitMQ/Kafka for async email dispatch (future optimization)

✅ 4. Services Summary
📘 user-service
Feature	Detail
Purpose	Handle profile info (CRUD)
MongoDB Collection	profiles in user_db
Endpoints	/api/users
DB Model	UserProfile: id, name, email, bio, etc.
Tech Notes	Uses Spring Data MongoDB (MongoRepository)
Dependencies	None outside core stack
Communication	Will be consumed by auth-service to store extra details

✅ Sample Endpoints
GET /api/users/{id}

POST /api/users

PUT /api/users/{id}

DELETE /api/users/{id}

📧 email-service
Feature	Detail
Purpose	Send emails and store logs
MongoDB Collection	emails in email_db
Endpoints	/api/emails/send
DB Model	EmailLog: id, to, subject, body, timestamp
Email Config	Uses SMTP via Spring Boot Starter Mail
Communication	Can be triggered from other services via REST (WebClient)

✅ Sample Endpoint
POST /api/emails/send

🔐 auth-service (WIP)
Feature	Detail
Purpose	Handles login, registration, and JWT token management
Planned DB	auth_db, Collection: users
Dependencies	Will call email-service on registration
JWT Token Flow	On login/registration, issues JWT token
Endpoint Plan	/api/auth/register, /api/auth/login, /api/auth/verify
Security	Spring Security, JWT filters, password hashing (BCrypt)

✅ 5. Communication Strategy
Between	Method	Tool Used
auth → email	REST Call	Spring WebClient
auth → user-service	REST Call	(Future: Feign)
gateway → services	Routing	Spring Cloud Gateway (Planned)

Future upgrades can include RabbitMQ for email queues and Eureka for service discovery.

✅ 6. JWT Authentication Flow
User Registers via auth-service

Saves credentials in auth_db.users

Sends confirmation email via email-service

Optional: Inserts user profile into user-service

User Logs In

Credentials validated via MongoDB

JWT is generated and returned

Protected Endpoints

JWT sent in Authorization: Bearer <token>

Interceptor or Filter checks JWT validity

Token Fields

sub (user ID)

email

issuedAt, exp

✅ 7. MongoDB Collections (Per Service)
Service	Database	Collection
user-service	user_db	profiles
email-service	email_db	emails
auth-service	auth_db	users (planned)

✅ 8. Folder Structure (Per Service)
Each Spring Boot service follows the standard structure:

arduino
Copy
Edit
src/
 └── main/
     ├── java/
     │    └── com/yourorg/servicename/
     │         ├── controller/
     │         ├── service/
     │         ├── model/
     │         ├── repository/
     │         ├── dto/
     │         └── config/
     └── resources/
           ├── application.properties
           └── static/templates (if any)
✅ 9. Deployment Plan
✅ Local Development
Run each service separately on:

auth-service → 8081

user-service → 8082

email-service → 8083

Use Postman or Swagger for testing

🐳 Docker-Based (Future Plan)
Create Dockerfile for each service

Use docker-compose for shared MongoDB and services

Use .env files for secrets

🚀 Optional CI/CD
GitHub Actions or GitLab CI to automate test/build/deploy

✅ 10. Enhancements / TODOs / Backlog
Task	Priority	Description
🔄 Finish auth-service	🔥 High	Add controllers, service, JWT logic
🔒 Secure user-service endpoints	Medium	Add JWT-based guards
✉️ Improve email-service	Medium	Add templates, retries, async behavior
🌐 Add API Gateway	Medium	Route requests centrally, manage CORS
🧪 Add Integration Tests	Low	Use TestContainers, MockMVC
🐳 Dockerize	Medium	Add Dockerfiles and Compose setup
📜 Swagger UI	Low	Auto-docs for all services

✅ 11. Glossary of Terms
Term	Meaning
JWT	JSON Web Token, used for stateless authentication
Microservice	Independent service responsible for a specific function
MongoDB Atlas	Cloud-hosted MongoDB database
DTO	Data Transfer Object – used to transfer data between layers
Feign/WebClient	Tools to make REST calls between services
Spring Security	Framework to secure APIs and enforce auth rules

