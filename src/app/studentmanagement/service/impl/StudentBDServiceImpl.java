package app.studentmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.studentmanagement.dao.StudentDAO;
import app.studentmanagement.dao.impl.StudentDBDAOImpl;
import app.studentmanagement.exception.StudentAlreadyExistsException;
import app.studentmanagement.model.Student;
import app.studentmanagement.service.StudentService;

public class StudentBDServiceImpl implements StudentService {

	private StudentDAO studentDAO;
	StudentFileServiceImpl studentFileService;
	private static final Logger logger = LogManager.getLogger(StudentBDServiceImpl.class);
	 
	public StudentBDServiceImpl() {
		studentDAO = new StudentDBDAOImpl();
		studentFileService = new StudentFileServiceImpl();
	}

	// Create
	@Override
	public boolean addStudent(Student student) throws StudentAlreadyExistsException {

		try {

			if (studentDAO.getStudentByName(student.getName()) != null) {
				throw new StudentAlreadyExistsException("Student " + student.getName() + " already exists");
			}

			return studentDAO.addStudent(student);

		} catch (StudentAlreadyExistsException e) {
			throw e;

		} catch (Exception e) {
			logger.warn("Failed to add student to database. Using file storage.", e);
			return studentFileService.addStudent(student);
		}
	}

	// Read
	@Override
	public List<Student> showStudents() {
		List<Student> students = new ArrayList<Student>();
		;
		try {
			students = studentDAO.getAllStudent();
		} catch (Exception e) {
			logger.warn("Failed to retrieve students from database. Using file storage.", e);
			students = studentFileService.showStudents();
		}

		return students;
	}

	@Override
	public String searchStudent(String studentName) {
		try {
			Student student = studentDAO.getStudentByName(studentName);

			if (student == null) {
				return "Student not found";
			} else {
				return student.studentInfo();
			}
		} catch (Exception e) {
			logger.error("Failed to retrieve student from database. Using file storage.", e);
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
