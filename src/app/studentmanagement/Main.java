package app.studentmanagement;

import java.io.*;
import java.util.Scanner;

import app.studentmanagement.constants.FileConstant;
import app.studentmanagement.model.Student;
import app.studentmanagement.service.StudentService;

public class Main {

	static Scanner scanner = new Scanner(System.in);
	static StudentService studentService = new StudentService();

	public static void main(String[] args) {

		int option;

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

		scanner.nextLine();

		System.out.println("Enter student name:");
		String name = scanner.nextLine();

		System.out.println("[INFO] Student name entered: " + name);

		System.out.println("Enter student age:");
		int age = scanner.nextInt();

		System.out.println("[INFO] Student age entered: " + age);

		System.out.println("Enter student grade:");
		double grade = scanner.nextDouble();

		System.out.println("[INFO] Student grade entered: " + grade);

		Student student = new Student(name, age, grade);

		studentService.addStudent(student);

		System.out.println("[INFO] Student added successifully ");
	}

	/**
	 * Displays all students currently stored in the file.
	 */
	private static void showStudents() {

		System.out.println("[INFO] Reading students from students.txt.");

		try {

			Scanner fileScanner = new Scanner(new File("students.txt"));

			while (fileScanner.hasNextLine()) {

				String line = fileScanner.nextLine();

				String[] data = line.split(",");

				String name = data[FileConstant.NAME_INDEX];

				int age = Integer.parseInt(data[FileConstant.AGE_INDEX]);

				double grade = Double.parseDouble(data[FileConstant.GRADE_INDEX]);

				Student student = new Student(name, age, grade);

				System.out.println(student.studentInfo());
			}

			fileScanner.close();

			System.out.println("[INFO] Finished reading students.");

		} catch (IOException e) {

			System.out.println("[ERROR] Failed to read students file.");
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

		System.out.println("[INFO] Searching for student: " + searchName);

		boolean found = false;

		try {

			Scanner fileScanner = new Scanner(new File("students.txt"));

			while (fileScanner.hasNextLine()) {

				String line = fileScanner.nextLine();

				String[] data = line.split(",");

				String name = data[FileConstant.NAME_INDEX];

				if (name.equalsIgnoreCase(searchName)) {

					int age = Integer.parseInt(data[FileConstant.AGE_INDEX]);

					double grade = Double.parseDouble(data[FileConstant.GRADE_INDEX]);

					Student student = new Student(name, age, grade);

					System.out.println("[INFO] Student found: " + name);

					System.out.println(student.studentInfo());

					found = true;

					break;
				}
			}

			fileScanner.close();

			if (!found) {

				System.out.println("[INFO] Student not found: " + searchName);
			}

		} catch (IOException e) {

			System.out.println("[ERROR] Failed to search students file.");
		}
	}
}