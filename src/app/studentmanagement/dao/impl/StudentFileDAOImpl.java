package app.studentmanagement.dao.impl;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.studentmanagement.constants.FileConstant;
import app.studentmanagement.dao.StudentDAO;
import app.studentmanagement.model.Student;

public class StudentFileDAOImpl implements StudentDAO {
	
	private static final Logger logger = LogManager.getLogger(StudentFileDAOImpl.class);
	
	// Create
	@Override
	public boolean addStudent(Student student) throws IOException {
		try (FileWriter writer = new FileWriter(FileConstant.FILE_PATH, true)) {

			logger.debug("Opening students.txt for writing.");

			writer.write(student.getName() + "," 
					+ student.getAge() + "," 
					+ student.getGrade() + ","
					+ student.getNationalId()
					+ "\n");

			logger.info("Student added to students.txt successfully.");

			return true;
		}
	}

	// Read
	@Override
	public List<Student> getAllStudent() throws IOException {
		List<Student> students = new ArrayList<Student>();

		logger.debug("Reading students from students.txt.");

		try (Scanner fileScanner = new Scanner(new File(FileConstant.FILE_PATH))) {

			while (fileScanner.hasNextLine()) {

				String line = fileScanner.nextLine();

				if (line.trim().isEmpty()) {
					continue;
				}

				String[] data = line.split(",");

				String name = data[FileConstant.NAME_INDEX];
				int age = Integer.parseInt(data[FileConstant.AGE_INDEX]);
				double grade = Double.parseDouble(data[FileConstant.GRADE_INDEX]);
				String nationalId = data[FileConstant.NATIONAL_ID_INDEX];

				Student student = new Student(name, age, grade, nationalId);

				students.add(student);
			}

		}

		logger.info("Finished reading students.txt.");
		return students;
	}

	@Override
	public Student getStudentByName(String searchName) throws IOException {
		Student student = null;
		logger.debug("Reading student from students.txt.");

		try (Scanner fileScanner = new Scanner(new File(FileConstant.FILE_PATH))) {

			while (fileScanner.hasNextLine()) {

				String line = fileScanner.nextLine();

				if (line.trim().isEmpty()) {
					continue;
				}

				String[] data = line.split(",");

				String name = data[FileConstant.NAME_INDEX];

				if (name.equalsIgnoreCase(searchName)) {
					int age = Integer.parseInt(data[FileConstant.AGE_INDEX]);
					double grade = Double.parseDouble(data[FileConstant.GRADE_INDEX]);
					String nationalId = data[FileConstant.NATIONAL_ID_INDEX];

					student = new Student(name, age, grade, nationalId);

					logger.info("Student found: {}", searchName);

					break;
				}
			}
			logger.info("Finished reading students.txt.");
		}

		return student;
	}

	// Update
	@Override
	public boolean updateStudent(String nationalId, Student student) throws IOException {
		List<String> lines = new ArrayList<>();
		boolean updated = false;

		logger.debug("Updating student in students.txt: {}", nationalId);

		try (Scanner fileScanner = new Scanner(new File(FileConstant.FILE_PATH))) {

			while (fileScanner.hasNextLine()) {

				String line = fileScanner.nextLine();

				if (line.trim().isEmpty()) {
					continue;
				}

				String[] data = line.split(",");

				String existingNationalId = data[FileConstant.NATIONAL_ID_INDEX];

				if (existingNationalId.equals(nationalId)) {

					// Keep the existing national ID unchanged.
					String updatedLine = student.getName() + "," 
										+ student.getAge() + "," 
										+ student.getGrade() + ","
										+ existingNationalId;

					lines.add(updatedLine);
					updated = true;

				} else {

					lines.add(line);
				}
			}
		}

		if (!updated) {
			logger.warn("Student not found: {}", nationalId);
			return false;
		}

		try (FileWriter writer = new FileWriter(FileConstant.FILE_PATH, false)) {

			for (String line : lines) {
				writer.write(line + "\n");
			}
		}

		logger.info("Student updated successfully: {}", nationalId);

		return true;

	}

	// Delete
	@Override
	public boolean deleteStudent(String nationalId) throws IOException {

		List<String> lines = new ArrayList<>();
		boolean deleted = false;

		logger.debug("Deleting student from students.txt: {}", nationalId);

		try (Scanner fileScanner = new Scanner(new File(FileConstant.FILE_PATH))) {

			while (fileScanner.hasNextLine()) {

				String line = fileScanner.nextLine();

				if (line.trim().isEmpty()) {
					continue;
				}

				String[] data = line.split(",");

				String existingNationalId = data[FileConstant.NATIONAL_ID_INDEX];

				if (existingNationalId.equals(nationalId)) {
					deleted = true;

				} else {

					lines.add(line);
				}
			}
		}

		if (!deleted) {
			logger.warn("Student not found: {}", nationalId);
			return false;
		}

		try (FileWriter writer = new FileWriter(FileConstant.FILE_PATH, false)) {

			for (String line : lines) {
				writer.write(line + "\n");
			}
		}

		logger.info("Student deleted successfully: {}", nationalId);

		return true;
	}

	@Override
	public Student getStudentByNationalId(String searchId) throws IOException {
		Student student = null;
		logger.debug("Reading student from students.txt.");

		try (Scanner fileScanner = new Scanner(new File(FileConstant.FILE_PATH))) {

			while (fileScanner.hasNextLine()) {

				String line = fileScanner.nextLine();

				if (line.trim().isEmpty()) {
					continue;
				}

				String[] data = line.split(",");

				String nationalId = data[FileConstant.NATIONAL_ID_INDEX];

				if (nationalId.equals(searchId)) {
					String name = data[FileConstant.NAME_INDEX];
					int age = Integer.parseInt(data[FileConstant.AGE_INDEX]);
					double grade = Double.parseDouble(data[FileConstant.GRADE_INDEX]);

					student = new Student(name, age, grade, nationalId);

					logger.info("Student found: {}", searchId);

					break;
				}
			}
			logger.info("Finished reading students.txt.");
		}

		return student;
	}

}
