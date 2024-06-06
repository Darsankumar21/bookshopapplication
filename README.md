# Library Web Application

This is a basic library web application where users can add, update, and delete book data. The application is built using Java Servlets, JSP, and MySQL for the backend database.

## Features

- **Add Books**: Users can add new books by providing the book name, edition, and price.
- **View Books**: Users can view a list of all books present in the database.
- **Delete Books**: Users can delete any book from the list.

## Prerequisites

- JDK 8 or higher
- Apache Tomcat 7.0 or higher
- MySQL 5.7 or higher
- IDE (like Eclipse, IntelliJ IDEA) for development

## Setting Up the Environment

### 1. MySQL Setup

1. **Create Database and Table**:
    ```sql
    CREATE DATABASE library;
    USE library;

    CREATE TABLE BOOKDATA (
        BOOKNAME VARCHAR(100) NOT NULL,
        BOOKEDITION VARCHAR(100),
        BOOKPRICE FLOAT,
        PRIMARY KEY (BOOKNAME)
    );
    ```

2. **Configure Database Connection**:
    Ensure you have the MySQL JDBC driver (`mysql-connector-java`) in your project's classpath.

### 2. Apache Tomcat Setup

1. Download and install [Apache Tomcat](http://tomcat.apache.org/).
2. Configure the server in your IDE.

### 3. Project Setup

1. **Clone the Repository**:
    ```bash
    git clone <repository-url>
    ```

2. **Import the Project**:
    - Open your IDE and import the project as an existing Maven/Gradle project.
    - Ensure all dependencies are resolved.

3. **Configure Deployment Descriptor**:
    Since we are using annotations for servlet configuration, there's no need for extensive `web.xml` configuration.

### 4. Running the Application

1. **Deploy on Tomcat**:
    - Right-click on the project.
    - Select `Run As` > `Run on Server`.
    - Choose your configured Tomcat server.

2. **Access the Application**:
    - Open a web browser and navigate to `http://localhost:8080/your-project-name/home.html`.

## Project Structure

- `src/main/java/com/servlet/RegisterServlet.java`: Handles book registration.
- `src/main/java/com/servlet/DisplayBooksServlet.java`: Displays and deletes books.
- `src/main/webapp/home.html`: Home page for adding books and viewing all books.

## Usage

### 1. Add a Book

1. Navigate to `http://localhost:8080/your-project-name/home.html`.
2. Fill in the book details and click "Register".

### 2. View All Books

1. On the home page, click "Click here to view all books".

### 3. Delete a Book

1. On the books display page, click "Remove" next to the book you want to delete.

## Code Overview

### home.html
