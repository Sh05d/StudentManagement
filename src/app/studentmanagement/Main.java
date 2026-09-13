package app.studentmanagement;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
				showStudents(students, index);
				break;
				
			case 3:
				searchStudent(students, index);
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
	 * Displays all students currently stored in the array.
	 *
	 * @param students the array containing the students
	 * @param index    the number of students currently stored
	 */
	private static void showStudents(Student[] students, int index) {
		System.out.println("Students:");
		for (int i = 0; i < index; i++) {
			System.out.println(students[i].studentInfo());
		}
	}
	
	/**
	 * Searches for a student by name and displays the student's information if a
	 * matching student is found.
	 *
	 * @param students the array containing the students
	 * @param index    the number of students currently stored
	 */
	private static void searchStudent(Student[] students, int index) {
		System.out.println("enter student name:");

		scanner.nextLine();
		String searchName = scanner.nextLine();

		boolean found = false;

		for (int i = 0; i < index; i++) {
			if (students[i].getName().equalsIgnoreCase(searchName)) {

				System.out.println(students[i].studentInfo());
				found = true;
				break;
			}

		}
		if (!found)
			System.out.println("Student NOT found");
	}

}
