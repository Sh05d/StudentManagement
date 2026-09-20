package app.studentmanagement.service;

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
	public boolean addStudent(Student student) {
		double totalGrade = student.getGrade() + ATTENDENT_GRADE;
		student.setGrade(totalGrade);

		boolean flag = studentDAO.addStudent(student);
		return flag;
	}

	// Read
	public List<Student> showStudents() {
		List<Student> students = new ArrayList<Student>();
		students = studentDAO.getAllStudent();
		return students;
	}

	public String searchStudent(String studentName) {
		Student student = studentDAO.getStudentByName(studentName);

		if (student == null) {
			return "Student not found";
		} else {
			return student.studentInfo();
		}
	}

	// Update
	public boolean updateCourse(int id, Student student) {
		boolean flag = studentDAO.updateStudent(id, student);
		return flag;
	}

	// Delete
	public boolean deleteCourse(int id) {
		boolean flag = studentDAO.deleteStudent(id);
		return flag;
	}
}
