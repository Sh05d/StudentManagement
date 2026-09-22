package app.studentmanagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.studentmanagement.exception.StudentAlreadyExistsException;
import app.studentmanagement.model.Student;
import app.studentmanagement.service.impl.StudentBDServiceImpl;
import app.studentmanagement.util.DBConnection;

public class Main {

	static Scanner scanner = new Scanner(System.in);
	static StudentBDServiceImpl studentService = new StudentBDServiceImpl();
	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {

		testConnection();

		int option = 0;

		do {

			logger.debug("Displaying main menu.");

			showMenu();
			try {

				option = scanner.nextInt();

				logger.info("User selected menu option: {}", option);

				switch (option) {

				case 1:
					logger.info("Starting Add Student operation.");
					addStudent();
					break;

				case 2:
					logger.info("Starting Show Students operation.");
					showStudents();
					break;

				case 3:
					logger.info("Starting Search Student operation.");
					searchStudent();
					break;

				case 4:
					logger.info("User selected Exit.");
					System.out.println("Thank you");
					break;

				default:
					logger.warn("Invalid menu option entered: {}", option);
					System.out.println("Incorrect option try again");
				}
			} catch (InputMismatchException e) {
			    logger.warn("User Entered Invalid input.");
			    System.out.println("Invalid input. Please try again.");
			    scanner.nextLine();
			
			}

		} while (option != 4);

		logger.info("Application closed.");

		scanner.close();
	}

	/**
	 * Displays the main menu and asks the user to select an option.
	 */
	private static void showMenu() {

		System.out.println("----------------------------\n" 
		+ "MENU:\n" 
		+ "1. Add Student\n"
		+ "2. Show Students\n"
		+ "3. Find Student\n" 
		+ "4. Exit\n" 
		+ "Enter the number of the option you want: ");
	}

	/**
	 * Adds a new student to the students file.
	 */
	static void addStudent() {

		System.out.println("Enter student name:");
		scanner.nextLine();
		String name = scanner.nextLine();

		System.out.println("Enter student age:");
		int age = scanner.nextInt();

		System.out.println("Enter student grade:");
		double grade = scanner.nextDouble();

		Student student = new Student(name, age, grade);

		try {
			boolean flag = studentService.addStudent(student);
			if (flag) {
				System.out.println("Student added successfully.");
			}
		} catch (StudentAlreadyExistsException e) {
			System.out.println(e.getMessage());
			logger.warn("Student already exists: {}", e.getMessage());

		} catch (Exception e) {
			System.out.println("An unexpected error occurred.");
			logger.error("Error while adding student.", e);
		}
	}

	/**
	 * Retrieves and displays all students.
	 */
	static void showStudents() {

		List<Student> students = studentService.showStudents();

		for (Student student : students) {
			System.out.println(student.studentInfo());
		}
	}

	/**
	 * Searches for a student by name and displays the student's information if a
	 * matching student is found.
	 */
	private static void searchStudent() {

		System.out.println("enter student name:");

		scanner.nextLine();

		String searchName = scanner.nextLine();

		logger.info("User Searching for student: {}", searchName);

		String searchResult = studentService.searchStudent(searchName);
		System.out.println(searchResult);

	}

	/**
	 * Tests the database connection.
	 */
	public static void testConnection() {

		try {
			Connection connection = DBConnection.getConnection();
			logger.info("Database test connection started.");

			System.out.println("Connected to the database successfully.");

			connection.close();
			logger.info("Database test connection closed.");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			logger.error("Database test connection failed.", e);
		}
	}
}