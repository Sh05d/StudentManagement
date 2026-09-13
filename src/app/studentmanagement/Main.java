package app.studentmanagement;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class Main {

	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		Student students[] = new Student[5];
		int index = 0;
		int option;

		do {
			showMenu();

			option = scanner.nextInt();

			switch (option) {

			case 1:
				addStudent();
				break;
				
			case 2:
				showStudents();
				break;
				
			case 3:
				searchStudent();
				break;

			case 4:
				System.out.println("Thank you");
				break;

			default:
				System.out.println("Incorrect option try again");
			}

		} while (option != 4);

		scanner.close();
	
		
	}
	
	/** 
	 * Displays the main menu and asks the user to select an option.*/
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
	 *
	 * The method reads the student's name, age, and grade
	 * from the user and saves the student data to the file.
	 *
	 * If an I/O error occurs while writing to the file,
	 * the error message is displayed.
	 */
	static void addStudent() {

	    scanner.nextLine();

	    System.out.println("Enter student name:");
	    String name = scanner.nextLine();

	    System.out.println("Enter student age:");
	    int age = scanner.nextInt();

	    System.out.println("Enter student grade:");
	    double grade = scanner.nextDouble();

	    try {
	        FileWriter writer = new FileWriter("students.txt", true);

	        writer.write(name + "," + age + "," + grade + "\n");

	        writer.close();

	        System.out.println("Student added successfully");

	    } catch (IOException e) {
	        System.out.println(e.getMessage());
	    }
	}
	/**
	 * Displays all students currently stored in the file.
	 */
	private static void showStudents() {
		try {
			Scanner fileScanner = new Scanner(new File("students.txt"));

			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();

				String[] data = line.split(",");

				String name = data[0];
				int age = Integer.parseInt(data[1]);
				double grade = Double.parseDouble(data[2]);

				Student student = new Student(name, age, grade);

				System.out.println(student.studentInfo());
			}

			fileScanner.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
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

		boolean found = false;

		try {
			Scanner fileScanner = new Scanner(new File("students.txt"));

			while (fileScanner.hasNextLine()) {

				String line = fileScanner.nextLine();
				String[] data = line.split(",");

				String name = data[0];

				if (name.equalsIgnoreCase(searchName)) {

					int age = Integer.parseInt(data[1]);
					double grade = Double.parseDouble(data[2]);

					Student student = new Student(name, age, grade);

					System.out.println(student.studentInfo());

					found = true;
					break;
				}

			}

			fileScanner.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
			if (!found)
				System.out.println("Student NOT found");
		}
	}

}
