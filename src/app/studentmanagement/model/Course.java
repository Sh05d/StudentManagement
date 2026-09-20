package app.studentmanagement.model;

public class Course {

	private int id;
	private String Name;
	private String Description;

	// Constructors
	public Course(String courseName, String courseDescription) {
		this.Name = courseName;
		this.Description = courseDescription;
	}

	public Course(int id, String courseName, String courseDescription) {
		this.id = id;
		this.Name = courseName;
		this.Description = courseDescription;
	}

	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setCourseName(String courseName) {
		this.Name = courseName;
	}

	public void setCourseDescription(String courseDescription) {
		this.Description = courseDescription;
	}

	// Getters
	public int getId() {
		return id;
	}

	public String getCourseName() {
		return Name;
	}

	public String getCourseDescription() {
		return Description;
	}

}
