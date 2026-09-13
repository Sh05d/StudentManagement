package app.studentmanagement;

public class Student {
	private String name;
	private int age;
	private double grade;

	// Constructor
	public Student(String name, int age, double grade) {
		this.name = name;
		this.age = age;
		this.grade = grade;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	// Getters
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public double getGrade() {
		return grade;
	}

	/** @return a summary of the student's information. */
	public String studentInfo() {
		return "Student Name: " + this.name 
				+ ", Age: " + this.age 
				+ ", Grade: " + this.grade 
				+ ", Grade Level: " + getGradeLevel() 
				+ ", Status: " + getPassFailStatus();

	}

	/** @return "Passed" if grade is 60 or higher, otherwise "Failed". */
	public String getPassFailStatus() {
		if (this.grade >= 60)
			return "Passed";
		else
			return "Failed";

	}
	
	/** @return the student's grade level based on their grade. */
	public String getGradeLevel() {
		if (this.grade >= 90)
			return "Excellent";
		else if (this.grade >= 80)
			return "Very Good";
		else if (this.grade >= 70)
			return "Good";
		else if (this.grade >= 60)
			return "Pass";
		else
			return "Fail";
	}

}
