# Airplane Parts Inventory Management System

## Description

This repository contains a Spring Boot application designed to manage an inventory of airplane parts. It provides features for CRUD operations on airplane parts and also offers user management capabilities. Role-based authentication is enabled using JWT, ensuring only users with the correct roles can perform specific tasks.

## Features

- **Airplane Parts Management**: CRUD operations on airplane parts.
- **User Management**: CRUD operations on users with role assignments.
- **Role-based Authentication**: Using JWT to assign and verify user roles.
- **Azure GitHub Actions**: Continuous Integration and Deployment to a web application environment.
- **Database**: Utilizes MySQL for data storage and retrieval.

## Endpoints

to test the application go to the link 

https://airplane.azurewebsites.net/app/parts/test

register and authenticate both using postman 

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

