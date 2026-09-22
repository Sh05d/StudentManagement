package app.studentmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import app.studentmanagement.dao.StudentDAO;
import app.studentmanagement.dao.impl.StudentDBDAOImpl;
import app.studentmanagement.model.Student;
import app.studentmanagement.service.StudentService;

public class StudentBDServiceImpl implements StudentService {

	private StudentDAO studentDAO;
	StudentFileServiceImpl studentFileService;

	public StudentBDServiceImpl() {
		studentDAO = new StudentDBDAOImpl();
		studentFileService = new StudentFileServiceImpl();
	}

	// Create
	@Override
	public boolean addStudent(Student student) {
		boolean flag = false;
		try {
			flag = studentDAO.addStudent(student);
		} catch (Exception e) {
			System.out.println("[ERROR] Failed to add student to database.");
			flag = studentFileService.addStudent(student);
		}
		return flag;
	}

	// Read
	@Override
	public List<Student> showStudents() {
		List<Student> students = new ArrayList<Student>();
		;
		try {
			students = studentDAO.getAllStudent();
		} catch (Exception e) {
			System.out.println("[ERROR] Failed to retrieve students from database.");
			students = studentFileService.showStudents();
		}

		return students;
	}

	@Override
	public String searchStudent(String studentName) {
		try {
			Student student = studentDAO.getStudentByName(studentName);
			studentDAO.updateStudent(1, student);

			if (student == null) {
				return "Student not found";
			} else {
				return student.studentInfo();
			}
		} catch (Exception e) {
			System.out.println("[ERROR] Failed to retrieve student from database.");
			return studentFileService.searchStudent(studentName);
		}

	}

	// Update
	@Override
	public boolean updateStudent(int id, Student student) {
		return studentDAO.updateStudent(id, student);
	}

	// Delete
	@Override
	public boolean deleteStudent(int id) {
		return studentDAO.deleteStudent(id);
	}
}
