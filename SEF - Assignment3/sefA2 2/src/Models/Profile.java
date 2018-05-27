package Models;

import java.util.Scanner;

public class Profile {

	Scanner scanner = new Scanner(System.in);

	private String givenName;
	private String familyName;

	private String dob;
	private String phone;
	private String email;
	private String type;

	public Profile(String givenName, String familyName, String dob,
			String phone, String email) {
		this.givenName = givenName;
		this.familyName = familyName;
		this.dob = dob;
		this.phone = phone;
		this.email = email;
	}

	public void updateProfile() {
		// to-do
	}

	public void viewProfile() {
		System.out.println();
		System.out.println("-------View user profile-------");
		System.out.println("Given name: " + this.givenName);
		System.out.println("Family name: " + this.familyName);
		System.out.println("Date of birth: " + this.dob);
		System.out.println("Phone: " + this.phone);
		System.out.println("Email: " + this.email);
		System.out.println();
	}

	public void updateResume() {
		// to-do
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void modifyProfile() {
		System.out.println("Input new phone");
		this.phone = scanner.next();

		System.out.println("Input new email");
		this.email = scanner.next();

	}

}
