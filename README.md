# Flight Management System

This project is a simple Spring Boot application that manages airline flight information. It provides RESTful APIs to search for flights based on source, destination, and date using JPAStreamer.

## Features
- Search for flights by source, destination, and date.
- Filter flights by source and date.
- Find flights based on source and destination.

## Technologies Used
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- JPAStreamer
- MySQL

## Prerequisites
- Java 17 or higher
- Spring Boot 3.x
- MySQL Database

## Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/username/flight-management-system.git
    ```
2. Navigate to the project directory:
    ```bash
    cd flight-management-system
    ```
3. Configure your database connection in `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/flight_management
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```
4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## API Endpoints
### Find Flights by Source, Destination, and Date
```http
GET /airline/findFlightFrom/{source}/to/{destination}/on/{date}
```
**Example:**
```
GET /airline/findFlightFrom/CXB/to/CCU/on/2025-11-17
```

### Find Flights by Source and Date
```http
GET /airline/findFlightFrom/{source}/on/{date}
```
**Example:**
```
GET /airline/findFlightFrom/CXB/on/2025-11-17
```

### Find Flights by Source and Destination
```http
GET /airline/findFlightFrom/{source}/to/{destination}
```
**Example:**
```
GET /airline/findFlightFrom/CXB/to/CCU
```

## License
This project is licensed under the MIT License. See the LICENSE file for more details.


