package app.studentmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import app.studentmanagement.model.Course;

public class CourseDAO {

	// Create
	public boolean addCourse(Course course) {
		return true;
	}

	// Read
	public List<Course> getAllCourses() {
		List<Course> courses = new ArrayList<Course>();
		return courses;
	}

	public Course getCourse(int id) {
		Course course = null;
		return course;
	}

	// Update
	public boolean updateCourse(int id, Course course) {
		return true;
	}

	// Delete
	public boolean deleteCourse(int id) {
		return true;
	}
}
