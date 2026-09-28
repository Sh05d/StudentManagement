package app.studentmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.studentmanagement.model.Student;
import app.studentmanagement.service.StudentService;
import app.studentmanagement.dao.StudentDAO;
import app.studentmanagement.dao.impl.StudentFileDAOImpl;
import app.studentmanagement.exception.StudentAlreadyExistsException;

public class StudentFileServiceImpl implements StudentService {

	private StudentDAO studentDAO;
	private static final Logger logger = LogManager.getLogger(StudentFileServiceImpl.class);

	public StudentFileServiceImpl() {
		studentDAO = new StudentFileDAOImpl();

	}

	// Create
	@Override
	public boolean addStudent(Student student) throws StudentAlreadyExistsException {

		try {

			if (studentDAO.getStudentByNationalId(student.getNationalId()) != null) {
				throw new StudentAlreadyExistsException("Student " + student.getNationalId() + " already exists");
			}

			return studentDAO.addStudent(student);

		} catch (StudentAlreadyExistsException e) {

			throw e;

		} catch (Exception e) {
			logger.error("Failed to add student to file storage.", e);
			return false;
		}
	}

	// Read
	@Override
	public List<Student> showStudents() {
		List<Student> students = new ArrayList<Student>();
		try {
			students = studentDAO.getAllStudent();
		} catch (Exception e) {
			logger.error("Failed to retrieve students from file storage.", e);
		}
		return students;
	}

	@Override
	public String searchStudent(String studentName) {
		Student student = null;
		try {
			student = studentDAO.getStudentByName(studentName);
		} catch (Exception e) {
			logger.error("Failed to retrieve student from file storage.", e);
		}

		if (student == null) {
			return "Student not found";
		} else {
			return student.studentInfo();
		}
	}

	// Update
	@Override
	public boolean updateStudent(String nationalId, Student student) {

		try {
			return studentDAO.updateStudent(nationalId, student);

		} catch (Exception e) {

			logger.error("Failed to update student in file storage. National ID: " + nationalId, e);
			return false;
		}
	}

	// Delete
	@Override
	public boolean deleteStudent(String nationalId) {

		try {
			return studentDAO.deleteStudent(nationalId);

		} catch (Exception e) {

			logger.error("Failed to delete student from file storage. National ID: " + nationalId, e);
			return false;
		}
	}

}
