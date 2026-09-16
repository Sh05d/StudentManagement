package app.studentmanagement.service;

import app.studentmanagement.dao.StudentDAO;
import app.studentmanagement.model.Student;

public class StudentService {

	private StudentDAO studentDAO;
	private final int ATTENDENT_GRADE = 10;

	public StudentService() {
		studentDAO = new StudentDAO();

	}

	public void addStudent(Student student) {
		double totalGrade = student.getGrade() + ATTENDENT_GRADE;
		student.setGrade(totalGrade);

		studentDAO.addStudent(student);
	}

}
