package Models;

public class Staff {

	private String username;
	private String password;
	private Profile profile;

	public Staff(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setProfile(String givenName, String familyName, String dob,
			String phone, String email) {
		this.profile = new Profile(givenName, familyName, dob, phone, email);
	}

	public Profile getProfile() {
		return profile;
	}

}
