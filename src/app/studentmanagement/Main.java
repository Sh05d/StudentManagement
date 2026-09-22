package app.studentmanagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import app.studentmanagement.model.Student;
import app.studentmanagement.service.impl.StudentBDServiceImpl;
import app.studentmanagement.util.DBConnection;

public class Main {

	static Scanner scanner = new Scanner(System.in);
	static StudentBDServiceImpl studentService = new StudentBDServiceImpl();

	public static void main(String[] args) {

		testConnection();

		int option;

		try {
			do {

				System.out.println("[INFO] Displaying main menu.");

				showMenu();

				option = scanner.nextInt();

				System.out.println("[INFO] User selected option: " + option);

				switch (option) {

				case 1:
					System.out.println("[INFO] Starting Add Student operation.");
					addStudent();
					break;

				case 2:
					System.out.println("[INFO] Starting Show Students operation.");
					showStudents();
					break;

				case 3:
					System.out.println("[INFO] Starting Search Student operation.");
					searchStudent();
					break;

				case 4:
					System.out.println("[INFO] User selected Exit.");
					System.out.println("Thank you");
					break;

				default:
					System.out.println("[ERROR] Incorrect option: " + option);
					System.out.println("Incorrect option try again");
				}

			} while (option != 4);
		} catch (InputMismatchException e) {
			System.out.println("[ERROR] Invalid input");
		}
		System.out.println("[INFO] Application closed.");

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

	
		System.out.println("Enter student id:");
		int id = scanner.nextInt();

		System.out.println("Enter student name:");
		scanner.nextLine();
		String name = scanner.nextLine();

		System.out.println("Enter student age:");
		int age = scanner.nextInt();

		System.out.println("Enter student grade:");
		double grade = scanner.nextDouble();

		Student student = new Student(id, name, age, grade);

		boolean flag = studentService.addStudent(student);

		if (flag) {
			System.out.println("Student added successfully.");
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
		System.out.println("[INFO] Students displayed successfully.");
	}

	/**
	 * Searches for a student by name and displays the student's information if a
	 * matching student is found.
	 */
	private static void searchStudent() {

		System.out.println("enter student name:");

		scanner.nextLine();

		String searchName = scanner.nextLine();

		System.out.println("[INFO] Searching for student: " + searchName);

		String searchResult = studentService.searchStudent(searchName);
		System.out.println(searchResult);

	}

	/**
	 * Tests the database connection.
	 */
	public static void testConnection() {

		try {
			Connection connection = DBConnection.getConnection();
			System.out.println("[INFO] Database connection started.");

			System.out.println("Connected to the database successfully.");

			connection.close();
			System.out.println("[INFO] Database connection closed.");
		} catch (SQLException e) {
			System.out.println("[ERROR] " + e.getMessage());
		}
	}
}