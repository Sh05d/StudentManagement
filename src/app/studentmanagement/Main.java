package app.studentmanagement;
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
				index = addStudent(students, index);
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
	 * Adds a new student to the students array.
	 *
	 * @param students the array containing the students
	 * @param index    the position where the new student will be stored
	 * @return the updated index after adding the student
	 */
	static int addStudent(Student[] students, int index) {

		if (index >= students.length) {
			System.out.println("You can't add more students");
			return index;
		}

		scanner.nextLine();

		System.out.println("Enter student " + (index + 1) + " Name:");
		String name = scanner.nextLine();

		System.out.println("Enter student " + (index + 1) + " age:");
		int age = scanner.nextInt();

		System.out.println("Enter student " + (index + 1) + " grade:");
		double grade = scanner.nextDouble();

		students[index] = new Student(name, age, grade);

		System.out.println("Student added successfully");

		return index + 1;
	}

}
