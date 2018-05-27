package Models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class JobBoard {

	private static ArrayList<Job> jobs = new ArrayList<Job>();

	public JobBoard(ArrayList<Job> jobs)
	{
		this.jobs=jobs;
	}
	
	
	public void viewJobs(ArrayList<String> coureseSelected)
	{
		int jobListLength=jobs.size();
		flag:for(int i=0;i<jobListLength;i++)
		{
			if(coureseSelected.size()!=0)
			{
				for(int j=0;j<coureseSelected.size();j++)
				{
					if(coureseSelected.get(j)==jobs.get(i).getName())
						continue flag;
				}
				System.out.print("No."+i+": ");
				jobs.get(i).printInfo();
			}
			else
			{
				System.out.print("No."+i+": ");
				jobs.get(i).printInfo();
			}
		}
	}
	
	public String applyPosition(int choice,String username) throws IOException
	{
		String selectedCourse="";
		File application=new File("Database/application.txt");
		Writer os = null;
		

		try {
			os=new FileWriter(application,true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		selectedCourse=jobs.get(choice).getName();
		os.write(selectedCourse+","+username+",open"+"\n");
		
		
		System.out.println(selectedCourse+" has been choosen!");
		os.flush();
		os.close();
		
		return selectedCourse;
	}
	
	
	
	
	public static ArrayList<Job> getJobs() {
		return jobs;
	}

	public static void setJobs(ArrayList<Job> jobs) {
		JobBoard.jobs = jobs;
	}
	
	
	
	
}
