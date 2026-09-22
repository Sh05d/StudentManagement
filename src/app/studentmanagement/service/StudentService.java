package app.studentmanagement.service;

import java.util.List;

import app.studentmanagement.model.Student;

public interface StudentService {

	public boolean addStudent(Student student) throws Exception;

	public List<Student> showStudents();

	public String searchStudent(String studentName);

	public boolean updateStudent(int id, Student student);

	public boolean deleteStudent(int id);
}
