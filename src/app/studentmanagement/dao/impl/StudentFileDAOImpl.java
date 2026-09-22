package app.studentmanagement.dao.impl;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.studentmanagement.constants.FileConstant;
import app.studentmanagement.dao.StudentDAO;
import app.studentmanagement.model.Student;

public class StudentFileDAOImpl implements StudentDAO {

	// Create
	@Override
	public boolean addStudent(Student student) throws IOException {
		try (FileWriter writer = new FileWriter(FileConstant.FILE_PATH, true)) {

			System.out.println("[INFO] Opening students.txt for writing.");

			writer.write(student.getId() + ","
					+ student.getName() + "," 
					+ student.getAge() + "," 
					+ student.getGrade()
					+ "\n");

			System.out.println("[INFO] Student added to students.txt successfully.");

			return true;
		}
	}

	// Read
	@Override
	public List<Student> getAllStudent() throws IOException {
		List<Student> students = new ArrayList<Student>();

		System.out.println("[INFO] Reading students from students.txt.");

		try (Scanner fileScanner = new Scanner(new File(FileConstant.FILE_PATH))) {

			while (fileScanner.hasNextLine()) {

				String line = fileScanner.nextLine();

				String[] data = line.split(",");

				int id = Integer.parseInt(data[FileConstant.ID_INDEX]);

				String name = data[FileConstant.NAME_INDEX];

				int age = Integer.parseInt(data[FileConstant.AGE_INDEX]);

				double grade = Double.parseDouble(data[FileConstant.GRADE_INDEX]);

				Student student = new Student(id, name, age, grade);

				students.add(student);
			}

		}

		System.out.println("[INFO] Finished reading students.txt.");
		return students;
	}

	@Override
	public Student getStudentByName(String searchName) throws IOException {
		Student student = null;
		System.out.println("[INFO] Reading student from students.txt.");

		try (Scanner fileScanner = new Scanner(new File(FileConstant.FILE_PATH))) {

			while (fileScanner.hasNextLine()) {

				String line = fileScanner.nextLine();

				String[] data = line.split(",");

				String name = data[FileConstant.NAME_INDEX];

				if (name.equalsIgnoreCase(searchName)) {
					int id = Integer.parseInt(data[FileConstant.ID_INDEX]);

					int age = Integer.parseInt(data[FileConstant.AGE_INDEX]);

					double grade = Double.parseDouble(data[FileConstant.GRADE_INDEX]);

					student = new Student(id, name, age, grade);

					System.out.println("[INFO] Student found: " + name);

					break;
				}
			}
			System.out.println("[INFO] Finished reading students.txt.");
		}

		return student;
	}

	// Update
	@Override
	public boolean updateStudent(int id, Student student) {
		return true;
	}

	// Delete
	@Override
	public boolean deleteStudent(int id) {
		return true;
	}

}
