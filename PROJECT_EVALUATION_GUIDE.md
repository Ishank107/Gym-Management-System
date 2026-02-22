# Gym Management System - Project Evaluation Guide

This document contains all the technical details, logic, and pseudocode for the Gym Management System to help you during your project evaluation.

---

## 1. Core Architecture (Frameworks & Tools)
- **Backend**: Java 17, Spring Boot 3.5.10, Spring Data JPA, Hibernate.
- **Frontend**: React (Vite), Axios, Bootstrap.
- **Database**: H2 In-Memory Database (No installation required).
- **Communication**: REST APIs (JSON format).

---

## 2. Key Modules & Business Logic (Pseudocode)

### A. Member Management & Plans
**Goal**: Register members and link them to a monthly subscription plan.
**Logic**:
1. Receive name, email, phone, and planId from Frontend.
2. Validate: Name is required, Email format check, Phone exactly 10 digits.
3. Fetch Plan: Find the Plan entity by planId from the database.
4. Link: Set `member.plan = fetchedPlan`.
5. Persistence: Save the Member object to the database.

### B. Trainer Assessment & Assignment
**Goal**: Create trainer profiles and link them to members using a Many-to-Many relationship.
**Logic**:
1. Create Trainer: Save name, specialization, and contact details.
2. Assign Trainer:
   - Identify which Member and which Trainer to link.
   - Fetch both objects from the database.
   - Add the Trainer object into the `member.trainers` list.
   - Save the Member. Hibernate automatically updates the `member_trainer` join table.
3. Member Count: The number of members for a trainer is calculated as the `size()` of the trainer's member list.

---

## 3. Important Annotations (The "Why")

| Annotation | Explanation |
| :--- | :--- |
| `@RestController` | Marks the class as an entry point for API requests. |
| `@Service` | Contains the business logic (e.g., calculating dates or linking entities). |
| `@Repository` | Interfaces with the database via JPA (No manual SQL needed). |
| `@Entity` | Maps a Java class to a Database table. |
| `@ManyToMany` | Handles complex relationships between Members and Trainers. |
| `@Valid` | Triggers the validation rules defined in the DTOs. |
| `@Transactional` | Ensures a task is "all or nothing"—if one step fails, the database revert changes. |

---

## 4. Error Handling & Validation Logic

### Backend (DTO Level)
We use **JSR 303 Validation** annotations in our DTOs:
- `@NotBlank(message = "...")`: Prevents empty strings.
- `@Email`: Ensures proper `a@b.com` format.
- `@Size(min=10, max=10)`: Constraints the phone length.

### Global Exception Handler
When a validation fails, the `GlobalExceptionHandler.java` intercepts the error and returns a clean, user-friendly JSON response:
```json
{
  "message": "Phone number must be exactly 10 digits",
  "errors": { "phone": "Phone number must be exactly 10 digits" }
}
```

---

## 5. Development Utilities

### H2 Database Console
- **URL**: `http://localhost:9999/h2-console`
- **JDBC URL**: `jdbc:h2:mem:gymdb`
- **User/Pass**: `sa` / `password`
- **Use Case**: To view tables and verify that data is actually stored.

### CORS Configuration
Configured in Controllers using `@CrossOrigin` to allow the React app (running on port 5173) to communicate with the Java server (running on port 9999).

---

## 6. Project Setup Instructions
1. **Backend**: Open `gym-backend`, run `./mvnw spring-boot:run`.
2. **Frontend**: Open `gym-frontend`, run `npm install` then `npm run dev`.
3. **Link**: Open `http://localhost:5173` in your browser.

---

## 7. Java Full Stack Developer: Core Concepts to Master

To be a successful Java Full Stack Developer, you must be clear on these "Big 4" areas. Use these points to answer common conceptual questions.

### A. Java Basics (The Foundation)
*   **OOPs Concepts**: 
    *   **Abstraction**: Hiding complexity (Example: `MemberService` interface hides the implementation logic).
    *   **Encapsulation**: Using `private` fields with `public` getters/setters in DTOs.
    *   **Inheritance**: Using `@Entity` base classes.
    *   **Polymorphism**: Overriding service methods.
*   **Collections**: Knowing when to use `List` (ordered data) vs `Set` (unique trainers for a member).

### B. Spring Boot (The Engine)
*   **Dependency Injection (DI)**: Spring "injects" the Repository into the Service for you. You don't use `new MemberRepositoryImpl()`.
*   **MVC Pattern**: 
    *   **Model**: Entities/DTOs.
    *   **View**: React Frontend.
    *   **Controller**: API Endpoints.
*   **Inversion of Control (IoC)**: The Spring container manages the lifecycle of your objects (Beans).

### C. Database & ORM (Data Management)
*   **RDBMS vs NoSQL**: Knowing why we use SQL (Fixed schema, relationships) for a Gym system.
*   **JPA/Hibernate**: Understanding that it converts Java objects to SQL rows.
*   **Relationships**: 
    *   **One-to-Many**: One Plan can have Many Members.
    *   **Many-to-Many**: Many Members can have Many Trainers.

### D. Frontend & Integration (The User Experience)
*   **State Management**: How React `useState` remembers what the user typed.
*   **Lifecycle**: Using `useEffect` to fetch data as soon as a page opens.
*   **REST API**: Understanding HTTP methods:
    *   `GET`: Fetch data.
    *   `POST`: Create new data.
    *   `PUT`: Update existing data.
    *   `DELETE`: Remove data.
*   **JSON**: The universal language (a simple text format) used to send data from Java to React.
