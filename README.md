# ATA-IT Financial Instruments System

This project is a sample application for managing financial instruments. It consists of a **backend API** built with Spring Boot and a **frontend** developed with React (using React Hook Form and Tailwind CSS 4.0).

The backend API exposes endpoints for placing buy/sell orders for various financial instruments (Mutual Funds, Fixed Income, etc.) and supports validation rules, notification to related services, and persistent storage via a relational database. We use Liquibase changelogs to manage database schema and seed mock data.

The frontend provides a modern, responsive interface with:
- Tab-based navigation (separated as pages using React Router)
- Dynamic filtering forms (with fields depending on the selected instrument type)
- Form handling with React Hook Form
- Tailwind CSS for styling with a custom theme based on the ATA‑IT / National Bank of Canada Group brand colors

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Backend Setup](#backend-setup)
- [Frontend Setup](#frontend-setup)
- [Database Seeding with Liquibase](#database-seeding-with-liquibase)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Project Overview

This project demonstrates a full-stack financial instruments management system. It is designed for:
- **Backend API:** Creating and processing buy/sell orders, instrument validations, and notifications.
- **Frontend UI:** Searching and filtering instruments with different fields per instrument type (Mutual Funds vs. Fixed Income) and handling form submissions using React Hook Form.

## Features

- **Backend (Spring Boot & Liquibase):**
    - RESTful API for instrument orders
    - Instrument-specific validation and formatting
    - Notification to external services (mocked)
    - Liquibase changelogs to manage schema changes and insert mock data (20 records each for MUTUAL_FUND and FIXED_INCOME)

- **Frontend (React, React Hook Form & Tailwind CSS 4.0):**
    - Tab-based navigation for Mutual Funds and Fixed Income pages
    - Responsive filtering form with dynamic fields:
        - **Mutual Funds:** Funds Name, Account Number, Timeframe
        - **Fixed Income:** Fixed Income Name, Interest Rate, Timeframe
    - Modern UI using Tailwind CSS with custom primary (`#01334e`) and secondary (`#ed1c24`) colors
    - Form management using React Hook Form with Zod validation

## Run demo
Docker is required to run the demo application.

### Build the Docker compose

```bash
docker-compose build
````

### Run the Docker compose

```bash
docker-compose up
```

then access the FE application at http://localhost:3000
for the BE application at http://localhost:8080
and Swagger-UI http://localhost:8080/docs/swagger-ui/index.html

## Development

### Backend Setup

1. **Prerequisites:**
    - Java 11+
    - Maven or Gradle
    - A relational database (e.g., PostgreSQL, MySQL)

2. **Build:**

   ```bash
   mvn clean install
   ```

3. **Liquibase Configuration:**
   Ensure your database connection is configured in your `application.properties` (or `application.yml`).

4. **Run the Application:**

   ```bash
   mvn spring-boot:run
   ```

### Frontend Setup

1. **Prerequisites:**
    - Node.js (v21+ recommended)
    - npm or pnpm

2. **Install Dependencies:**

   ```bash
   npm install
   ```

3. **Run the Frontend:**

   ```bash
   npm run dev
   ```

## Usage

- **Backend API:**
    - Please use OpenAPI to test APIs http://localhost:8080/docs/swagger-ui/index.html
    - Endpoint example: `POST /api/orders` for placing an order.
    - Instrument search API: `GET /api/instruments/search` with query parameters based on filtering criteria.

- **Frontend UI:**
    - Navigate to http://localhost:3000/
    - Use the navigation tabs to switch between Mutual Funds and Fixed Income pages.
    - Fill in the search form filters and click **Search** to display instrument records.
    - Use the **Reset** button to clear the form fields.
