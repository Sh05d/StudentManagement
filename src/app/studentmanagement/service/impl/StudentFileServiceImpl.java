package app.studentmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import app.studentmanagement.model.Student;
import app.studentmanagement.service.StudentService;
import app.studentmanagement.dao.StudentDAO;
import app.studentmanagement.dao.impl.StudentFileDAOImpl;

public class StudentFileServiceImpl implements StudentService {

	private StudentDAO studentDAO;

	public StudentFileServiceImpl() {
		studentDAO = new StudentFileDAOImpl();

	}

	// Create
	@Override
	public boolean addStudent(Student student) {
		boolean flag = false;
		try {
			return studentDAO.addStudent(student);
		} catch (Exception e) {
			System.out.println("[ERROR] Failed to add student to students.txt.");
			System.out.println("[ERROR] " + e.getMessage());
		}
		return flag;
	}

	// Read
	@Override
	public List<Student> showStudents() {
		List<Student> students = new ArrayList<Student>();
		try {
			students = studentDAO.getAllStudent();
		} catch (Exception e) {
			System.out.println("[ERROR] Failed to retrieve students from students.txt.");
			System.out.println("[ERROR] " + e.getMessage());
		}
		return students;
	}

	@Override
	public String searchStudent(String studentName) {
		Student student = null;
		try {
			student = studentDAO.getStudentByName(studentName);
		} catch (Exception e) {
			System.out.println("[ERROR] Failed to retrieve student from students.txt.");
			System.out.println("[ERROR] " + e.getMessage());
		}

		if (student == null) {
			return "Student not found";
		} else {
			return student.studentInfo();
		}
	}

	// Update
	@Override
	public boolean updateStudent(int id, Student student) {
		boolean flag = studentDAO.updateStudent(id, student);
		return flag;
	}

	// Delete
	@Override
	public boolean deleteStudent(int id) {
		boolean flag = studentDAO.deleteStudent(id);
		return flag;
	}

}
