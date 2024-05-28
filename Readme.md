The **Customer Feedback Management System** is a comprehensive Spring Boot application designed to manage customer feedback effectively. It incorporates functionalities to collect, store, update, retrieve, and delete feedback, alongside managing related Product and User entities. This system is built to facilitate streamlined feedback management, essential for enhancing customer service and product quality.
### Key Features of the System:
- **CRUD Operations:** Complete Create, Read, Update, and Delete operations for Feedback, Product, and User entities.
- **Data Validation:** Ensures that all input data meets expected formats and constraints before processing.
- **Relational Database Integration:** Utilizes a MySQL database to maintain relationships and data integrity.
  - **Product-Feedback Relationship:** One-to-many relationship allowing multiple feedback entries per product.
  - **User-Feedback Relationship:** One-to-many relationship allowing users to provide multiple feedback entries.
- **Dockerized Environment:** Simplifies deployment and ensures consistency across different environments through Docker and Docker Compose.
### Project Structure Overview:
- **Java Source Files:** Contain the main application logic and are organized into controllers, models, and repositories.
  - **Controllers:** Handle HTTP requests and responses.
  - **Models:** Define entities (Feedback, Product, User) with attributes and relationships.
  - **Repositories:** Interface with the database to perform CRUD operations.
- **Resources:**
  - **application.properties:** Configures application settings including database connections.
  - **data.sql:** Contains SQL statements for initial data loading.
- **Tests:**
  - **Unit Tests:** Test individual components for correctness.
  - **Integration Tests:** Test the interaction between components and the complete integration with the database.
### Setup Instructions:
1. **Environment Preparation:** Requires Java 22, Maven, Docker, and Docker Compose installed on your machine.
2. **Repository Setup:** Involves cloning the Git repository and navigating into the project directory.
3. **Database Configuration:** Setup and configuration of a MySQL database named `feedback_db`, including user creation and permission setting.
4. **Application Configuration:** Adjustment of the `application.properties` to align with the local database settings.
5. **Building and Running the Application:** Utilizes Maven to compile the project and run it, making the application accessible via a local server.
### API Endpoints:
- **Feedback Operations:** From creating new feedback entries to retrieving, updating, and deleting existing entries.
- **Product Management:** Manage products including creation, retrieval, update, and deletion.
- **User Management:** Includes operations for creating new users, retrieving user details, updating user information, and deleting users.
### Testing:
- **Execution of Tests:** Details commands to run both unit and integration tests to ensure system reliability and performance.
### Documentation and Resources:
- **Comprehensive Guides and References:** Links to external documentation for Spring Boot, Spring Data JPA, and MySQL provide additional resources for deeper understanding and troubleshooting.
  This documentation effectively outlines the capabilities, structure, and operational procedures of the Customer Feedback Management System, serving as a valuable resource for developers and administrators involved in its deployment and maintenance.