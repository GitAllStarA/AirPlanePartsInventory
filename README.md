# Airplane Parts Inventory Management System

## Description

This repository contains a Spring Boot application designed to manage an inventory of airplane parts. It provides features for CRUD operations on airplane parts and also offers user management capabilities. Role-based authentication is enabled using JWT, ensuring only users with the correct roles can perform specific tasks.

## Features

- **Airplane Parts Management**: CRUD operations on airplane parts.
- **User Management**: CRUD operations on users with role assignments.
- **Role-based Authentication**: Using JWT to assign and verify user roles.
- **Azure GitHub Actions**: Continuous Integration and Deployment to a web application environment.
- **Database**: Utilizes MySQL for data storage and retrieval.

Certainly! Here's a description you can add to your GitHub readme file:

---

## Authentication Process in Our Spring Boot Application

Our Spring Boot application uses JWT (JSON Web Token) for authentication. Below are the steps to authenticate and access the secured endpoints:

### 1. **User Registration**

Before accessing the application, you need to register. To do so, provide the following details:


Send request to : https://airplane.azurewebsites.net/app/login/register

under body as json with below keys and your values 

- First Name
- Last Name
- Email
- Password
- Role 


### 2. **Acquiring Initial JWT Token**

Upon successful registration, you'll receive a JWT token. This token will be used for the next step of authentication.

### 3. **Authenticate with the Initial Token**

To authenticate:



- Copy the JWT token you received from the registration.
- Send a POST request to the authentication endpoint. In postman go to the Authorization tab and select type then select Bearer Tokn paste the token.

Send request to https://airplane.azurewebsites.net/app/login/authenticate 

- In the request body, provide:
  - Email ID
  - Password

Upon successful authentication, you'll receive a new JWT token.

### 4. **Accessing Other Endpoints**

Use the new JWT token to make requests to other secured endpoints. copy this newly reviewed token (similar to step 3) for authentication.

**Note**: Ensure to keep your tokens secure and do not share them publicly.


## Endpoints


### Airplane Parts API

1. **Add a Part**: `POST /api/parts/add`
2. **Update a Part**: `PUT /api/parts/update/{id}`
3. **more to be implemented**

### User Management API

1. **Register a User**: `POST /api/users/register`
2. **Update User Details**: `PUT /api/users/update/{id}`
3. **more to be implemented**

## Setup and Installation

1. **Clone the Repository**:
   ```
   git clone https://github.com/GitAllStarA/AirPlanePartsInventory.git
   ```

2. **Navigate to Project Directory**:
   ```
   cd AirPlanePartsInventory
   ```

3. **Install Dependencies**:
   ```
   mvn clean install
   ```

4. **Set Up MySQL**:
   Ensure you have MySQL installed and running. Update `application.properties` with your MySQL credentials.

5. **Run the Application**:
   ```
   mvn spring-boot:run
   ```

## Deployment

This application is continuously integrated and deployed using Azure GitHub Actions. Every push to the master branch triggers the CI/CD pipeline, deploying the application to the specified Azure web application environment.

