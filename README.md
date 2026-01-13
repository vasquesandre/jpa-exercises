# JPA Orders System

**JPA Orders System** is a backend learning project developed in **Java** using **JPA (Hibernate)** and **MySQL**. The main goal of this project was to practice **entity relationships, cascading, transaction handling, and service-layer design**, with a strong focus on correctness and test coverage.

---

## Features Implemented

- User, Product, and Order creation
- Order item management using an `OrderProduct` association entity
- Automatic update of quantity and price when adding the same product to an order
- Removal of order items with `orphanRemoval`
- Business rule validation (invalid quantity, non-existing entities, protected deletions)
- Prevention of product deletion when linked to orders
- Integration tests covering all core flows
- Automatic database cleanup after tests

---

## Tech Stack

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) 
![JPA Hibernate](https://img.shields.io/badge/-Hibernate-59666C?logo=Hibernate&logoColor=FFF)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/MAVEN-000000?style=for-the-badge&logo=apachemaven&logoColor=blue)
![JUnit5](https://img.shields.io/badge/junit5-none?logo=junit5)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/vasquesandre)

---

## How It Works

1. A **User** is created  
2. **Products** are registered  
3. An **Order** is created for a user  
4. Products are added to the order:
   - Existing items are updated
   - New items are inserted
5. Items can be removed from the order
6. Products linked to orders cannot be deleted

---

## Project Structure

- **Entities**: `User`, `Product`, `Order`, `OrderProduct`
- **Services**: encapsulate business rules and validations
- **DAO layer**: handles persistence logic with JPA
- **Tests**: integration tests validating real database behavior

---

## Testing Strategy

- Used tests to validate business rules and guide refactoring (TDD mindset)
- Service-level integration tests
- Validation of success and failure scenarios
- Real database usage (no mocks)
- Database cleaned after each test to ensure isolation

---

## AI Usage

AI was used as a **development assistant** to:
- Validate design decisions
- Assist in test case design

---

## Overview

This project consolidates key backend concepts such as:
- JPA relationship mapping
- Transaction and cascade management
- Clean service-layer design
- Test-driven and test-supported development

It serves as a solid foundation for future migration to frameworks like **Spring Boot**.

---
