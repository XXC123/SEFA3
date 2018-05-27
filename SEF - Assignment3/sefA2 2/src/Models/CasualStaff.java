package Models;

import java.io.IOException;
import java.util.ArrayList;

public class CasualStaff extends Staff {

	JobBoard jobBoard;
	ArrayList<String> coureseSelected =new ArrayList<String>();
	
	public ArrayList<String> getCoureseselected() {
		return coureseSelected;
	}



	public void setCoureseselected(ArrayList<String> coureseselected) {
		this.coureseSelected = coureseselected;
	}



	public CasualStaff(String username, String password) {
		super(username, password);
	}



	public void viewJobs(ArrayList<Job> jobs)
	{
		jobBoard=new JobBoard(jobs) ;
		jobBoard.viewJobs(coureseSelected);
	}
	
	public void applyJob(int choice)
	{
		String  courseSelected="";
		try {
			 courseSelected = jobBoard.applyPosition(choice,super.getUsername());
		} catch (IOException e) {

			e.printStackTrace();
		}
		coureseSelected.add(courseSelected);
	}
	
	
}
