package Models;

import java.util.ArrayList;

public class Course {


	private String name;
	private String courseCode;
	private Object start;
	private String end;
	private int numOfStaff;
	private double budget;

	public Course(String name, String courseCode, String start, String end,
			int numOfStaff, double budget) {
		this.name = name;
		this.courseCode = courseCode;
		this.start = start;
		this.end = end;
		this.numOfStaff = numOfStaff;
		this.budget = budget;
	}

	public Course(ArrayList<Course> course) {
		// TODO Auto-generated constructor stub
	}

	public void viewCourseBudget() {
		System.out.println("Budget of "+courseCode + " is: "+ budget );
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Object getStart() {
		return start;
	}

	public void setStart(Object start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getNumOfStaff() {
		return numOfStaff;
	}

	public void setNumOfStaff(int numOfStaff) {
		this.numOfStaff = numOfStaff;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}
}
