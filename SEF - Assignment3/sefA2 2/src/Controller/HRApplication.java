package Controller;

import java.util.*;
import java.io.*;
import Models.*;

public class HRApplication {
	private static ArrayList<Job> jobs = importJobs("Database/jobs.txt");
	private static int jobsCount = jobs.size();
	private static ArrayList<Job> pendingJobs = importJobs("Database/pendingJobs.txt");
	private static int pendingJobsCount = pendingJobs.size();
	private static ArrayList<Job> approvedJobs = new ArrayList<Job>();
	private static ArrayList<Course> courses = new ArrayList<Course>();
	
	private static Scanner scanner = new Scanner(System.in);
	private static HashMap<String, Staff> staffMembers = importLoginInfo();

	// Imports all users' login information from loginInfo.txt
	public static HashMap<String, Staff> importLoginInfo() {
		HashMap<String, Staff> staffMembers = new HashMap<String, Staff>();
		File loginInfo = new File("Database/loginInfo.txt");

		if (loginInfo.exists()) {
			try {
				Scanner input = new Scanner(new FileReader(loginInfo));

				while (input.hasNextLine()) {
					StringTokenizer staffLoginInfo = new StringTokenizer(
							input.nextLine(), ",");
					//Gets user's info
					String staffType = staffLoginInfo.nextToken();
					String username = staffLoginInfo.nextToken();
					String password = staffLoginInfo.nextToken();

					// Creates user according to type
					if (staffType.equals("AD")) {
						staffMembers.put(username,
								new Admin(username, password));
					} else if (staffType.equals("AP")) {
						staffMembers.put(username,
								new Approval(username, password));
					} else if (staffType.equals("CC")) {
						staffMembers.put(username,
								new CourseCoordinator(username, password));
					} else if (staffType.equals("FS")) {
						staffMembers.put(username,
								new Staff(username, password));
					} else {
						staffMembers.put(username,
								new CasualStaff(username, password));
					}
					
					importProfile(staffMembers.get(username));
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Cannot find file.");
		}

		return staffMembers;
	}

	public static Staff login() {
		Scanner scan = new Scanner(System.in);
		String inputUsername;
		String inputPassword;

		while (true) {
			// Gets user's input
			System.out.println("Log in");
			System.out.println("Username:");
			inputUsername = scan.next();
			System.out.println("Password:");
			inputPassword = scan.next();

			// Input validation
			if (staffMembers.containsKey(inputUsername)) {
				if (inputPassword.equals(
						staffMembers.get(inputUsername).getPassword())) {
					System.out.println("Welcome to the system.");
					return staffMembers.get(inputUsername);
				} else {
					System.out.println(
							"Incorrect password used for this account.");
				}
			} else {
				System.out.println("No account detected");
			}
		}
	}

	// Imports user's profile
	public static void importProfile(Staff user) {
		String username = user.getUsername();
		File profiles = new File("Database/profiles.txt");
		if (profiles.exists()) {
			try {
				Scanner input = new Scanner(new FileReader(profiles));

				while (input.hasNextLine()) {
					StringTokenizer staffProfile = new StringTokenizer(
							input.nextLine(), ",");
					String fileUsername = staffProfile.nextToken();

					// Finds the right username and imports their profile
					if (fileUsername.equals(username)) {
						String givenName = staffProfile.nextToken();
						String familyName = staffProfile.nextToken();
						String dob = staffProfile.nextToken();
						String phone = staffProfile.nextToken();
						String email = staffProfile.nextToken();

						user.setProfile(givenName, familyName, dob, phone,
								email);
						return;
					}
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/*Imports jobs that has been created*/
	public static ArrayList<Job> importJobs(String jobFileName){
		ArrayList<Job> jobs = new ArrayList<Job>();
		File jobFile = new File(jobFileName);
		
		if (jobFile.exists()) {
			try {
				Scanner input = new Scanner(new FileReader(jobFile));
				
				while (input.hasNextLine()) {
					StringTokenizer jobInfo = new StringTokenizer(input.nextLine(), "|");
					String courseCode = jobInfo.nextToken();
					String name = jobInfo.nextToken();
					String startDate = jobInfo.nextToken();
					String endDate = jobInfo.nextToken();
					String time = jobInfo.nextToken();
					int numOfStaff = Integer.parseInt(jobInfo.nextToken());
					int payRate = Integer.parseInt(jobInfo.nextToken());
					ArrayList<String> applicants = new ArrayList<String>();
					
					while (jobInfo.hasMoreTokens()) {
						String applicantID = jobInfo.nextToken();
						applicants.add(applicantID);
					}
					
					Job job = new Job(courseCode, name, startDate, endDate, time, numOfStaff, payRate);
					job.setApplicants(applicants);
					
					jobs.add(job);
				}
				
				input.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Cannot find " + jobFileName);
		}
		return jobs;
	}
	
	
	
	//import course list
	public static void importCourseInfo() {
		File courseInfo = new File("Database/courseInfo.txt");		
		if (courseInfo.exists()) {
			try {
				Scanner input = new Scanner(new FileReader(courseInfo));
				while (input.hasNextLine()){
			//		System.out.println("read a line");
					StringTokenizer courseFile = new StringTokenizer(
							input.nextLine(), ",");
					
					String name = courseFile.nextToken();
					String courseCode=courseFile.nextToken();
					String start = courseFile.nextToken();
					String end = courseFile.nextToken();
					//String time = jobFile.nextToken();
					int numOfStaff = Integer.valueOf(courseFile.nextToken());
					double budget = Double.valueOf(courseFile.nextToken());
					
					Course course=new Course(name,courseCode,start,end,numOfStaff,budget);
					courses.add(course);
				}
			}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	}
	
	//Navigates to menu according to type of user
	public static void selectMenu(Staff user) {
		if (user instanceof Admin) {
			adminUI(user);
		} else if (user instanceof CourseCoordinator) {
			coordinatorUI(user);
		} else if (user instanceof Approval) {
			System.out.println("Logging in as an approval");
			approvalUI(user);
		} else if (user instanceof CasualStaff || user instanceof Staff) {
			casualUI(user);
		}	
	}

	public static void logout() {
		System.exit(0);
	}

	private static void adminUI(Staff user) {
		showStaffMenu();
		showAdminMenu();

		System.out.print("Please enter your selection: ");
		int selection = scanner.nextInt();
		switch (selection) {
		case 1:
			checkPayrate();
			break;
		case 2:
			seeRoster();
			break;
		case 3:
			viewProfile(user);
			break;
		case 4:
			gatherPayroll();
			break;
		case 5:
			viewStaff();
			break;
		case 6:
			setRoster();
			break;
		case 7:
			allocateStaff();
			break;
		case 8:
			logout();
			break;
		}
	}

	private static void coordinatorUI(Staff user) {
		showStaffMenu();
		showCoordinatorMenu();

		System.out.print("Please enter your selection: ");
		int selection = scanner.nextInt();
		switch (selection) {
		case 1:
			checkPayrate();
			break;
		case 2:
			seeRoster();
			break;
		case 3:
			viewProfile(user);
			break;
		case 4:
			seeCourseBudget(user);
			break;
		case 5:
			approveStaffPay();
			break;
		case 6:
			createCourseEvent();
			break;
		case 7:
			staffForEvent();
			break;
		case 8:
			logout();
			break;
		}
	}

	private static void casualUI(Staff user){
		showStaffMenu();
		showCasualStaffMenu();

		System.out.print("Please enter your selection: ");
		int selection = scanner.nextInt();
		switch (selection) {
		case 1:
			checkPayrate();
			break;
		case 2:
			seeRoster();
			break;
		case 3:
			viewProfile(user);
			break;
		case 4:
			user.getProfile().updateResume();
			break;
		case 5:
			applyPosition(user);
			break;
		case 6:
			logout();
			break;
		}

	}

	private static void approvalUI(Staff user) {
		showStaffMenu();
		showApprovalMenu();

		System.out.print("Please enter your selection: ");
		int selection = scanner.nextInt();
		switch (selection) {
		case 1:
			checkPayrate();
			break;
		case 2:
			seeRoster();
			break;
		case 3:
			viewProfile(user);
			break;
		case 4:
			gatherPayroll();
			break;
		case 5:
			approveStaffPay();
			break;
		case 6:
			viewCourseStaffRequest();
			break;
		case 7:
			logout();
			break;
		}

	}

	private static void showStaffMenu() {
		System.out.println();
		System.out.println("-----------Menu-----------");
		System.out.println("1.Check pay rates");
		System.out.println("2.See weekly roster");
		System.out.println("3.Update profile");
	}

	private static void showAdminMenu() {
		System.out.println("4.Gather report data");
		System.out.println("5.View all staff");
		System.out.println("6.Set rosters");
		System.out.println("7.Allocate staff");
		System.out.println("8.Log out");
	}

	private static void showCoordinatorMenu() {
		System.out.println("4.See course budget");
		System.out.println("5.Set payrate for event");
		System.out.println("6.Create a new job");
		System.out.println("7.Request staff for events");
		System.out.println("8.Log out");
	}

	private static void showCasualStaffMenu() {
		System.out.println("4.Update resume");
		System.out.println("5.Apply for positions");
		System.out.println("6.Log out");
	}

	private static void showApprovalMenu() {
		System.out.println("4.Gather weekly payroll data");
		System.out.println("5.Approve staff weekly pay");
		System.out.println("6.View pending jobs");
		System.out.println("7.Log out");
	}

	//show pay rate
	public static void checkPayrate() {
		System.out.println();
		System.out.println("-------Payrate check:-------");
		int jobListLength=jobsCount;
		for(int i=0;i<jobListLength;i++)
		{
			System.out.println("Course: "+jobs.get(i).getName()+" Payrate: "+jobs.get(i).getPayRate());
		}
	}

	public static void seeRoster() {
		// to-do
	}

	public static void applyPosition(Staff user) {
		
		
		Scanner stdin = new Scanner(System.in);

		System.out.println();
		System.out.println("-------View Job List-------");
		CasualStaff cs=(CasualStaff) user;
		cs.viewJobs(jobs);
		
		try{
				System.out.println("Select one job you want,input the Number before job!");
				System.out.println("Enter -1 to go back to main menu!");
				int choice=stdin.nextInt();
				if(choice==-1)
				{
					casualUI(user);
				}
				cs.applyJob(choice);
				//System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
				casualUI(user);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void viewStaff() {
		// to-do
	}

	public static void viewCourseStaffRequest() {		
		//Prints list of pending jobs
		System.out.println(pendingJobsCount + " pending jobs: ");
		for (int i = 0; i < pendingJobsCount; i++) {
			System.out.println(i + ". " + jobs.get(i).getName());
			System.out.println(pendingJobs.get(i).getDetails());
			pendingJobs.get(i).printApplicantsIDs();
		}
		//gets name of job to approve
		int jobNo = -1;
		while (jobNo < 0 || jobNo >= jobsCount) {
			System.out.print("Please enter the job number to approve/deny: ");
			jobNo = scanner.nextInt();
		}
		
		int choice = 0;
		while (choice < 1 || choice > 2) {
			System.out.println("1. Approve this job\n2. Deny this job\n");
			System.out.println("Please enter your choice: ");
			choice = scanner.nextInt();
		}
		
		if (choice == 1) {
			approvedJobs.add(pendingJobs.get(jobNo));
		}
		
		
		pendingJobs.remove(jobNo);
		pendingJobsCount--;
		
		//TO DO: notify the course coordinator about the decision
		//TO DO: updates the job list file
	}

	public static void setRoster() {
		// to-do
	}

	public static void allocateStaff() {
		// to-do
	}

	public static void gatherPayroll() {
		// to-do
		Report report = new Report();
		report.generateReport();
	}

	public static void approveStaffPay() {
		// to-do
	}

	public static void seeCourseBudget(Staff user) {
		System.out.println("-------View Course List-------");
		CourseCoordinator cc=(CourseCoordinator) user;
		cc.viewCourseBudget(courses);
	}

	public static void createCourseBudget() {
		// to-do
	}

	public static void createCourseEvent() {
		Scanner stdin = new Scanner(System.in);
		String courseCode, name, start, end, time;
		int numOfStaff;
		double payRate;

		// Gets user's input
		System.out.println("Create a new job");
		
		System.out.print("Code of the course: ");
		courseCode = stdin.next();
		System.out.print("Name of the job: ");
		name = stdin.next();
		System.out.print("Start date: ");
		start = stdin.next();
		System.out.print("End date: ");
		end = stdin.next();
		System.out.print("Time: ");
		time = stdin.next();
		System.out.print("Number of staff: ");
		numOfStaff = stdin.nextInt();

		// TO DO: show a list of payrates
		System.out.print("Payrate: ");
		payRate = stdin.nextDouble();

		// Creates new job
		Job j = new Job(courseCode, name, start, end, time, numOfStaff, payRate);
		jobs.add(j);

		System.out.println("Request created successfully.");
	}

	public static void staffForEvent() {
		System.out.print("Enter your course code: ");
		String code = scanner.next();
		//list of jobs created by this coordinator
		int[] createdJobs = new int[jobsCount];
		int count = 0;
		for (int i = 0; i < jobsCount; i++) {
			if (code.equals(jobs.get(i).getCourseCode())) {
				createdJobs[count] = i;
				count++;
			}
		}
		//Prints list of jobs with applicants to approve
		System.out.println("List of jobs that you have created: ");
		for (int i = 0; i < count; i++) {
			System.out.print(i + ". \n" + jobs.get(createdJobs[i]).getDetails());
		}
		System.out.print("Please enter your choice: ");
		int choice = scanner.nextInt();
		
		//Processes applications
		ArrayList<String> applicants = jobs.get(createdJobs[choice]).getApplicants();
		System.out.println("You have received " + applicants.size() + " applications for this position.");
		for (int i = 0; i < applicants.size(); i++) {
			int pos = i + 1;
			System.out.println("Applicant " + pos + ": " + applicants.get(i));
			
			//Options
			while (true) {
				System.out.println("Options\n\t1. See profile\n\t2. Add\n\t3. Remove\n\t4. Exit");
				int op = scanner.nextInt();
				if (op == 1) {
					staffMembers.get(applicants.get(i)).getProfile().viewProfile();
				} else if (op == 2){
					System.out.println("Added " + applicants.get(i));
					break;
				} else if (op == 3) {
					applicants.remove(i);
					System.out.println("Removed " + applicants.get(i));
					break;
				} else if (op == 4) {
					break;
				}
			}
			System.out.println();
		}
	}


	public static void viewProfile(Staff user) {

		user.getProfile().viewProfile();

		System.out.println("-------Modify your profile:-------");
		System.out.println("1.Modify whole profile");
		System.out.println("2.Modify one featrue");

		int selection = scanner.nextInt();
		switch (selection) {
		case 1:
			user.getProfile().modifyProfile();
			break;
		case 2:
			System.out.println("1.Modify phone");
			System.out.println("2.Modify email");

			selection = scanner.nextInt();
			switch (selection) {
			
			case 1:
				System.out.println("Input new phone");
				user.getProfile().setPhone(scanner.next());
				break;
			case 2:
				System.out.println("Input new email");
				user.getProfile().setEmail(scanner.next());
				break;
			}
			break;
		}
		// TO DO: prints changes to the file
		user.getProfile().viewProfile();
	}

}
