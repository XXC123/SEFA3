package Models;

import java.util.ArrayList;

public class CourseCoordinator extends Staff {

	
	public CourseCoordinator(String username, String password) {
		super(username, password);
	}

	
	
	public void viewCourseBudget(ArrayList<Course> course)
	{
		int courseListLength=course.size();
		for(int i=0;i<courseListLength;i++)
		{
			System.out.print("No."+i+": ");
			course.get(i).viewCourseBudget();
		}
	}
	
}
