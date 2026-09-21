package app.studentmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import app.studentmanagement.constants.StudentConstant;
import app.studentmanagement.model.Student;
import app.studentmanagement.util.DBConnection;

public class StudentDAO {

	private DBConnection dbConnection;

	public StudentDAO() {
		dbConnection = new DBConnection();
	}

	// Create
	public boolean addStudent(Student student) throws SQLException {

		String sql = "INSERT INTO student (id, name, age, grade) VALUES (?,?,?,?)";

		System.out.println("[INFO] Writing student to database.");
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

			System.out.println("[INFO] Database connection established.");

			System.out.println("[INFO] Adding student. ID: " + student.getId());

			preparedStatement.setInt(StudentConstant.ID_INDEX, student.getId());
			preparedStatement.setString(StudentConstant.NAME_INDEX, student.getName());
			preparedStatement.setInt(StudentConstant.AGE_INDEX, student.getAge());
			preparedStatement.setDouble(StudentConstant.GRADE_INDEX, student.getGrade());

			preparedStatement.executeUpdate();

			System.out.println("[INFO] Student added successfully. ID: " + student.getId());

			return true;
		}
	}

	// Read
	public List<Student> getAllStudent() throws SQLException {
		List<Student> students = new ArrayList<Student>();

		String sql = "SELECT id, name, age, grade FROM student";
		System.out.println("[INFO] Reading students from database.");
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {

				int id = resultSet.getInt(StudentConstant.ID_INDEX);
				String name = resultSet.getString(StudentConstant.NAME_INDEX);
				int age = resultSet.getInt(StudentConstant.AGE_INDEX);
				double grade = resultSet.getDouble(StudentConstant.GRADE_INDEX);

				Student student = new Student(id, name, age, grade);

				students.add(student);
			}

			System.out.println("[INFO] Finished reading students.");

		}
		return students;

	}

	public Student getStudentByName(String searchName) throws SQLException {

		String sql = "SELECT id, name, age, grade FROM student WHERE name = ?";
		System.out.println("[INFO] Reading student from database.");

		Student student = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, searchName);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				if (resultSet.next()) {

					int id = resultSet.getInt(StudentConstant.ID_INDEX);
					String name = resultSet.getString(StudentConstant.NAME_INDEX);
					int age = resultSet.getInt(StudentConstant.AGE_INDEX);
					double grade = resultSet.getDouble(StudentConstant.GRADE_INDEX);

					student = new Student(id, name, age, grade);

					System.out.println("[INFO] Student found: " + name);
				} else {
					System.out.println("[INFO] Student not found: " + searchName);
				}
			}
		}

		return student;
	}

	// Update
	public boolean updateStudent(int id, Student student) {
		return true;
	}

	// Delete
	public boolean deleteStudent(int id) {
		return true;
	}
}
