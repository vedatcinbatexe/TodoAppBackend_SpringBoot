# JWT Authentication Implementation Guide

This guide explains how JWT authentication and authorization has been implemented in the TodoApp Spring Boot application.

## Overview

The application now includes a complete JWT-based authentication system with the following features:
- User registration and login
- JWT token generation and validation
- Password encryption using BCrypt
- Protected endpoints requiring authentication
- CORS support for frontend integration

## Architecture

### Key Components

1. **AuthController** (`/api/v1/auth`)
   - `POST /register` - User registration
   - `POST /login` - User authentication
   - `GET /test` - Test endpoint

2. **AuthService** - Business logic for authentication
   - User registration with email validation
   - User login with credential verification
   - JWT token generation

3. **Security Configuration**
   - JWT filter for token validation
   - Password encoder configuration
   - Protected route configuration

4. **JWT Utilities**
   - Token generation and validation
   - User details extraction from tokens

## API Endpoints

### Public Endpoints (No Authentication Required)

#### Register User
```http
POST /api/v1/auth/register
Content-Type: application/json

{
    "email": "user@example.com",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe"
}
```

**Response:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "type": "Bearer",
    "userId": 1,
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe"
}
```

#### Login User
```http
POST /api/v1/auth/login
Content-Type: application/json

{
    "email": "user@example.com",
    "password": "password123"
}
```

**Response:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "type": "Bearer",
    "userId": 1,
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe"
}
```

### Protected Endpoints (Authentication Required)

#### Get User Profile
```http
GET /api/v1/protected/profile
Authorization: Bearer <your-jwt-token>
```

#### Test Protected Endpoint
```http
GET /api/v1/protected/test
Authorization: Bearer <your-jwt-token>
```

## Configuration

### JWT Settings (application.properties)
```properties
# JWT Configuration
jwt.secret=mySecretKey
jwt.expiration=86400000
```

- `jwt.secret`: Secret key for signing JWT tokens (change in production!)
- `jwt.expiration`: Token expiration time in milliseconds (24 hours by default)

### Security Configuration
- All `/api/v1/auth/**` endpoints are public
- All `/api/v1/health/**` endpoints are public
- All other endpoints require authentication
- CORS is enabled for all origins
- CSRF protection is disabled (not needed for JWT)

## Usage Examples

### Frontend Integration

#### 1. Register a new user
```javascript
const registerUser = async (userData) => {
    const response = await fetch('/api/v1/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData)
    });
    return await response.json();
};
```

#### 2. Login user
```javascript
const loginUser = async (credentials) => {
    const response = await fetch('/api/v1/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(credentials)
    });
    return await response.json();
};
```

#### 3. Access protected endpoints
```javascript
const getProtectedData = async (token) => {
    const response = await fetch('/api/v1/protected/profile', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    });
    return await response.json();
};
```

## Security Features

1. **Password Encryption**: All passwords are encrypted using BCrypt
2. **JWT Token Security**: Tokens are signed with HMAC SHA-256
3. **Token Expiration**: Tokens expire after 24 hours by default
4. **Input Validation**: All input is validated using Bean Validation
5. **CORS Support**: Configured for cross-origin requests

## Error Handling

The API returns appropriate HTTP status codes:
- `200 OK`: Successful authentication
- `400 Bad Request`: Invalid input data
- `401 Unauthorized`: Invalid credentials or missing token
- `500 Internal Server Error`: Server-side errors

## Testing the Implementation

1. **Start the application**
2. **Test registration**:
   ```bash
   curl -X POST http://localhost:8080/api/v1/auth/register \
     -H "Content-Type: application/json" \
     -d '{"email":"test@example.com","password":"password123","firstName":"Test","lastName":"User"}'
   ```

3. **Test login**:
   ```bash
   curl -X POST http://localhost:8080/api/v1/auth/login \
     -H "Content-Type: application/json" \
     -d '{"email":"test@example.com","password":"password123"}'
   ```

4. **Test protected endpoint** (replace TOKEN with actual token):
   ```bash
   curl -X GET http://localhost:8080/api/v1/protected/profile \
     -H "Authorization: Bearer TOKEN"
   ```

## Production Considerations

1. **Change JWT Secret**: Use a strong, random secret key
2. **HTTPS**: Always use HTTPS in production
3. **Token Refresh**: Implement token refresh mechanism
4. **Rate Limiting**: Add rate limiting for auth endpoints
5. **Logging**: Add comprehensive logging for security events
6. **Database Security**: Ensure database credentials are secure

## File Structure

```
src/main/java/com/example/todoapp/
├── Config/
│   └── SecurityConfig.java
├── Controllers/
│   ├── AuthV1Controller.java
│   └── ProtectedController.java
├── Models/
│   ├── AuthResponse.java
│   ├── LoginRequest.java
│   └── RegisterRequest.java
├── Services/
│   ├── AuthService.java
│   ├── UserDetailsServiceImpl.java
│   └── UserPrincipal.java
└── Utils/
    ├── JwtUtils.java
    ├── JwtAuthenticationEntryPoint.java
    └── JwtAuthenticationFilter.java
```

This implementation provides a robust foundation for JWT-based authentication in your Spring Boot application.
