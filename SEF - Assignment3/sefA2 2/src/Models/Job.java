package Models;
import java.util.*;

public class Job{
	private String courseCode, name, start, end, time;
	private int numOfStaff;
	private double payRate;
	private ArrayList<String> applicants = new ArrayList<String>();

	public Job(String courseCode, String name, String start, String end, String time,
			int numOfStaff, double payRate) {
		this.courseCode = courseCode;
		this.name = name;
		this.start = start;
		this.end = end;
		this.time = time;
		this.numOfStaff = numOfStaff;

		this.payRate = payRate;
	}

	public String getCourseCode() {
		return courseCode;
	}
	
	public String getName() {
		return name;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public String getTime() {
		return time;
	}

	public int getNumOfStaff() {
		return numOfStaff;
	}

	public double getPayRate() {
		return payRate;
	}
	
	public void printInfo()
	{
		System.out.print("Course:"+this.name);
		System.out.print(" Start: "+this.start);
		System.out.print(" End : "+this.end);
		System.out.print(" Time : "+this.time);
		System.out.print(" Number required: "+this.numOfStaff);
		System.out.println(" Pay rate: "+this.payRate);
	}

	/*
	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}
	*/
	
	public void setApplicants(ArrayList<String> applicants) {
		this.applicants = applicants;
	}
	
	public ArrayList<String> getApplicants() {
		return applicants;
	}
	
	public String getDetails() {
		//TO DO
		String courseCodeDetail = "\tNCourse code: " + courseCode + "\n";
		String nameDetail = "\tName: " + name + "\n";
		String startDetail = "\tStart: " + start + "\n";
		String endDetail = "\tEnd: " + end + "\n";
		String timeDetail = "\tTime: " + time + "\n";
		String numOfStaffDetail = "\tNumber of staff: " + numOfStaff + "\n";
		String payRateDetail = "\tPayrate: " + payRate + "\n";
		String jobDetails = nameDetail + startDetail + endDetail + timeDetail + numOfStaffDetail + payRateDetail;
		return jobDetails;
	}
	
	public void printApplicantsIDs() {
		System.out.println("\t List of staff: ");
		for (int i = 0; i < applicants.size(); i++) {
			System.out.println("\t\t" + applicants.get(i));
		}
	}
}
