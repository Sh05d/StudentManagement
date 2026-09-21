package app.studentmanagement.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.studentmanagement.dao.StudentDAO;
import app.studentmanagement.model.Student;

public class StudentService {

	private StudentDAO studentDAO;
	private final int ATTENDENT_GRADE = 10;

	public StudentService() {
		studentDAO = new StudentDAO();

	}

	// Create
	public boolean addStudent(Student student) throws SQLException {
		double totalGrade = student.getGrade() + ATTENDENT_GRADE;
		student.setGrade(totalGrade);

		return studentDAO.addStudent(student);
	}

	// Read
	public List<Student> showStudents() throws SQLException {
		List<Student> students = studentDAO.getAllStudent();
		return students;
	}

	public String searchStudent(String studentName) throws SQLException {
		Student student = studentDAO.getStudentByName(studentName);

		if (student == null) {
			return "Student not found";
		} else {
			return student.studentInfo();
		}
	}

	// Update
	public boolean updateCourse(int id, Student student) {
		return studentDAO.updateStudent(id, student);
	}

	// Delete
	public boolean deleteCourse(int id) {
		return studentDAO.deleteStudent(id);
	}
}
