# Student Management System

## Description

Student Management System is a Java application designed to manage student and course information in an organized and simple way. The system provides CRUD operations through a console-based menu.

## Features

### Student Management

- **Add Students:** Add new students with their required information and National ID.
- **View Students:** Display available student records and their information.
- **Search Student:** Search for a student by name and quickly find the student's information.
- **Update Students:** Modify existing student information using the student's National ID.
- **Delete Students:** Remove student records using the student's National ID.

### Course Management

- **Add Courses:** Add new courses with their name, description, and Course Code.
- **View Courses:** Display available course records and their information.
- **Update Courses:** Modify existing course information using the Course Code.
- **Search Courses:** Search for courses using their Course Code.
- **Delete Courses:** Remove course records using the Course Code.

## Technologies

- Java
- Microsoft SQL Server
- JDBC
- Log4j
- Eclipse IDE

## Getting Started

Clone the repository:

    git clone https://github.com/Sh05d/StudentManagement.git

Open the project in Eclipse, configure the database connection, and run the `Main` Java class.

## Project Structure

    StudentManagement/
    ├── src/
    │   └── app/
    │       └── studentmanagement/
    │           ├── constants/
    │           ├── dao/
    │           ├── exception/
    │           ├── model/
    │           ├── service/
    │           ├── util/
    │           └── Main.java
    ├── .classpath
    ├── .project
    └── README.md
