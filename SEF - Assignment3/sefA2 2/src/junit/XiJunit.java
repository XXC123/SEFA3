package junit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Models.Job;
import Models.JobBoard;

public class XiJunit {

 private JobBoard jb;
 private ArrayList<Job> jobs = new ArrayList<Job>();
 private String username;
 
 @Before
 public void setUp() throws Exception {
  Job job1=new Job("COCS2028","Tutor","201802","201812","Fri1600",5,1200);
  Job job2=new Job("COCS2029","Lab","201802","201812","Fri1600",5,1200);
  Job job3=new Job("COCS2027","Lecture","201802","201812","Fri1600",5,1200);
  jobs.add(job1);
  jobs.add(job2);
  jobs.add(job3);
  username="LP04051993";
  jb=new JobBoard(jobs);  
 }

 @Test
 public void testJob1() {
  String output = null;
  
  try {
   output= jb.applyPosition(0,username);
  } catch (IOException e) {
   e.printStackTrace();
  }

  assertEquals("Tutor",output);
 }
 
 @Test
 public void testJbo2() {
  String output = null;
  
  try {
   output= jb.applyPosition(1,username);
  } catch (IOException e) {
   e.printStackTrace();
  }

  assertEquals("Lab",output);
 }
 
 @Test
 public void testJob3() {
  String output = null;
  
  try {
   output= jb.applyPosition(2,username);
  } catch (IOException e) {
   e.printStackTrace();
  }

  assertEquals("Lecture",output);
 }

}