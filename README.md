# Project Overview

This project implements a robust **Full-Stack Restaurant Reservation System**. It is designed to provide end-to-end functionality for customers to check for table availability and secure a booking online, while simultaneously providing a scalable and secure backend for the restaurant's operations.

The goal is to ensure the critical business flows—**Table Availability Search** and **Reservation Submission**—are stable, secure, and highly responsive across the application.

##  Technology Stack

This framework utilizes the following technologies and design patterns:

| Category | Tool / Language | Purpose |
| :--- | :--- | :--- |
| **Backend** | **Spring Boot 3.x, Java** | Serves as the core programming language for the RESTful API and business logic (e.g., availability calculation). |
| **Frontend** | **React (JavaScript)** | Client-side framework for building the dynamic and responsive user interface for searching and booking tables. |
| **Database** | **MS SQL Server** | Provides persistent and reliable storage for all reservation, customer, and table data. |
| **Persistence**| **Spring Data JPA, Hibernate** | Manages the ORM (Object-Relational Mapping) layer, handling all database interactions and ensuring transactional integrity. |
| **Security** | **CORS Configuration** | Explicitly configured in the Spring Boot application to allow secure communication between the `localhost:3000` (React) and `localhost:8080` (Spring) servers. |
| **HTTP Client** | **Axios (in React)** | Used in the frontend to handle asynchronous HTTP requests, simplifying the communication with the Spring Boot REST API endpoints. |
| **Design Pattern** | **RESTful Architecture** | Implemented in the backend to provide stateless, resource-based endpoints (e.g., `/api/reservations`) for the frontend to consume. |
