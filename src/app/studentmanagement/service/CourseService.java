package app.studentmanagement.service;

import java.util.List;

import app.studentmanagement.dao.CourseDAO;
import app.studentmanagement.model.Course;

public class CourseService {

	private CourseDAO courseDAO;

	public CourseService() {
		courseDAO = new CourseDAO();
	}

	// Create
	public boolean addCourse(Course course) {
		boolean flag = courseDAO.addCourse(course);
		return flag;
	}

	// Read
	public List<Course> showCourses() {
		List<Course> courses = courseDAO.getAllCourses();
		return courses;
	}

	public Course findCourses(int id) {
		Course course = courseDAO.getCourse(id);
		return course;
	}

	// Update
	public boolean updateCourse(int id, Course course) {
		boolean flag = courseDAO.updateCourse(id, course);
		return flag;
	}

	// Delete
	public boolean deleteCourse(int id) {
		boolean flag = courseDAO.deleteCourse(id);
		return flag;
	}
}
