package app.studentmanagement.model;

public class Student {
	private int id;
	private String name;
	private int age;
	private double grade;
	private String nationalId;

	// Constructors
	
	public Student(String name, int age, double grade) {
		this.name = name;
		this.age = age;
		this.grade = grade;
	}

	public Student(String name, int age, double grade, String nationalId) {
		this.name = name;
		this.age = age;
		this.grade = grade;
		this.setNationalId(nationalId);
	}

	public Student(int id, String name, int age, double grade) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.grade = grade;
	}

	// Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	/**
	 * Sets the national ID.
	 *
	 * <p>
	 * The national ID must not be null or empty, must contain exactly 10
	 * characters, and must start with either (1) or(2).
	 *
	 * @param nationalId the national ID to assign
	 * @throws IllegalArgumentException if {@code nationalId} is null or empty, does
	 *                                  not contain exactly 10 characters, or does
	 *                                  not start with 1 or 2
	 */
	public void setNationalId(String nationalId) {
		if (nationalId == null || nationalId.isEmpty()) {
			throw new IllegalArgumentException("National ID is required");
		}
		if (nationalId.length() != 10) {
			throw new IllegalArgumentException("National ID must contain 10 digits");
		}
		if (nationalId.charAt(0) != '1' && nationalId.charAt(0) != '2') {
			throw new IllegalArgumentException("National ID must start with 1 or 2");
		}
		this.nationalId = nationalId;
	}

	// getters
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public double getGrade() {
		return grade;
	}

	public String getNationalId() {
		return nationalId;
	}

	/** @return a summary of the student's information. */
	public String studentInfo() {
		return "Student National ID: " + this.nationalId
				+ ", Name: " + this.name 
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
