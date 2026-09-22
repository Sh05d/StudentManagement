package app.studentmanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.studentmanagement.constants.StudentConstant;
import app.studentmanagement.dao.StudentDAO;
import app.studentmanagement.model.Student;
import app.studentmanagement.util.DBConnection;

public class StudentDBDAOImpl implements StudentDAO {

	private DBConnection dbConnection;
	private static final Logger logger = LogManager.getLogger(StudentDBDAOImpl.class);

	public StudentDBDAOImpl() {
		dbConnection = new DBConnection();
	}

	// Create
	@Override
	public boolean addStudent(Student student) throws SQLException {

		String sql = "INSERT INTO student (name, age, grade) VALUES (?,?,?)";

		logger.debug("Writing student to database.");
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

			logger.debug("Database connection established.");

			preparedStatement.setString(StudentConstant.NAME_INDEX, student.getName());
			preparedStatement.setInt(StudentConstant.AGE_INDEX, student.getAge());
			preparedStatement.setDouble(StudentConstant.GRADE_INDEX, student.getGrade());

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				logger.info("Student added to database successfully");
				return true;
			}
			logger.warn("Student was not added to database.");

			return true;
		}
	}

	// Read
	@Override
	public List<Student> getAllStudent() throws SQLException {
		List<Student> students = new ArrayList<Student>();

		String sql = "SELECT name, age, grade FROM student";
		logger.debug("Reading students from database.");
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {

				String name = resultSet.getString(StudentConstant.NAME_INDEX);
				int age = resultSet.getInt(StudentConstant.AGE_INDEX);
				double grade = resultSet.getDouble(StudentConstant.GRADE_INDEX);

				Student student = new Student(name, age, grade);

				students.add(student);
			}

			logger.info("Finished reading students from database.");

		}
		return students;

	}

	@Override
	public Student getStudentByName(String searchName) throws SQLException {

		String sql = "SELECT name, age, grade FROM student WHERE name = ?";
		logger.debug("Reading student from database.");
		
		Student student = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, searchName);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				if (resultSet.next()) {

					String name = resultSet.getString(StudentConstant.NAME_INDEX);
					int age = resultSet.getInt(StudentConstant.AGE_INDEX);
					double grade = resultSet.getDouble(StudentConstant.GRADE_INDEX);

					student = new Student(name, age, grade);

					logger.info("Student found: {}", searchName);
				} else {
					logger.info("Student not found: {}", searchName);
				}
			}
		}
		
		logger.info("Finished reading student from database.");

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
