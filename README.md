# User Management System

A Spring Boot-based User Management System with MongoDB Atlas, REST APIs, and JWT authentication. This project demonstrates a microservices architecture with three main services:

- **Auth Service**: Handles user authentication, registration, and JWT token management
- **User Service**: Manages user profiles and related data
- **Email Service**: Handles email notifications

## Technologies Used

- Java 17+
- Spring Boot 3.x
- MongoDB Atlas
- JWT for Authentication
- Spring Security
- Spring WebFlux for Service Communication
- Maven
- Spring Mail for Email Notifications

## Project Structure

```
user-management-system/
├── auth-service/       # Authentication and Authorization Service
├── user-service/       # User Profile Management Service
└── email-service/     # Email Notification Service
```

## Prerequisites

- Java 17 or higher
- Maven
- MongoDB Atlas account
- Gmail account (for email service)

## Environment Setup

Each service requires its own environment variables. Create a `.env` file in each service directory using the provided templates:

### Auth Service
```properties
SPRING_DATA_MONGODB_URI=mongodb+srv://<username>:<password>@<cluster>.mongodb.net/auth_db?retryWrites=true&w=majority
JWT_SECRET_KEY=<your-256-bit-secret-key>
```

### User Service
```properties
SPRING_DATA_MONGODB_URI=mongodb+srv://<username>:<password>@<cluster>.mongodb.net/user_db?retryWrites=true&w=majority
JWT_SECRET_KEY=<your-256-bit-secret-key>
```

### Email Service
```properties
SPRING_DATA_MONGODB_URI=mongodb+srv://<username>:<password>@<cluster>.mongodb.net/email_db?retryWrites=true&w=majority
MAIL_USERNAME=<your-gmail-address>
MAIL_PASSWORD=<your-gmail-app-password>
```

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/Hasanth-kumar/user-management-system.git
   cd user-management-system
   ```

2. Set up environment variables:
   - Copy the `env.template` files in each service directory to `.env`
   - Fill in your MongoDB Atlas credentials and other required values

3. Build each service:
   ```bash
   cd auth-service
   mvn clean install
   
   cd ../user-service
   mvn clean install
   
   cd ../email-service
   mvn clean install
   ```

4. Run the services:
   ```bash
   # In separate terminals
   cd auth-service
   mvn spring-boot:run

   cd user-service
   mvn spring-boot:run

   cd email-service
   mvn spring-boot:run
   ```

The services will be available at:
- Auth Service: http://localhost:8081
- User Service: http://localhost:8082
- Email Service: http://localhost:8083

## API Documentation

### Auth Service Endpoints

- POST `/api/auth/register` - Register a new user
- POST `/api/auth/login` - Login and get JWT token

### User Service Endpoints

- GET `/api/profiles` - Get all user profiles (requires JWT)
- GET `/api/profiles/{id}` - Get user profile by ID (requires JWT)
- POST `/api/profiles` - Create user profile (requires JWT)
- PUT `/api/profiles/{id}` - Update user profile (requires JWT)
- DELETE `/api/profiles/{id}` - Delete user profile (requires JWT)

### Email Service Endpoints

- POST `/api/email/send` - Send email notification

## Security

- All sensitive information is stored in environment variables
- JWT is used for authentication between services
- Passwords are hashed using BCrypt
- CSRF protection is enabled
- MongoDB Atlas connection uses secure URI

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details. 