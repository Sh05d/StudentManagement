package app.studentmanagement.dao;

import java.io.FileWriter;
import java.io.IOException;

import app.studentmanagement.model.Student;

public class StudentDAO {

	public void addStudent(Student student) {
		try {

			System.out.println("[INFO] Opening students.txt for writing.");

			FileWriter writer = new FileWriter("students.txt", true);

			writer.write(student.getName() + "," + student.getAge() + "," + student.getGrade() + "\n");

			writer.close();

			System.out.println("[INFO] Student added successfully.");

		} catch (IOException e) {

			System.out.println("[ERROR] Failed to write student to file.");
		}
	}
}
