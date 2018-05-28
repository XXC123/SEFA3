package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;

import Controller.*;

@RunWith(Theories.class)
public class TestCheckJob {
	private static String courseCode, name, start, end, time;
	private static int numOfStaff;
	private static double payRate;

	@DataPoints 
	public static String[] courseCodes = { "COSC 1111", "COS111" };
	public static String[] dates = { "12/12/2012", "12/a/12" };
	public static String[] times = { "8:30-10.30", "20-30-30-20" };
	@DataPoints 
	public static int[] numOfStaffs = { -1, 0 };
	@DataPoints 
	public static double[] payRates = { -1.0, 0 };

	@Before
	public void setUpValidInput() {
		courseCode = "ISYS1111";
		name = "Assignment Marker";
		start = "24/05/18";
		end = "07/06/18";
		time = "09:00-10:00";
		numOfStaff = 2;
		payRate = 30.00;
	}

	@Test
	public void testValidJob() {
		Boolean valid = HRApplication.checkJob(courseCode, name, start, end,
				time, numOfStaff, payRate);
		assertEquals(true, valid);
	}

	@Theory
	public void testFalseCourseCode(String courseCodes) {
		Boolean valid = HRApplication.checkJob(courseCodes, name, start, end,
				time, numOfStaff, payRate);
		assertEquals(false, valid);
	}

	@Theory
	public void testFalseStart(String dates) {
		Boolean valid = HRApplication.checkJob(courseCode, name, dates, end,
				time, numOfStaff, payRate);
		assertEquals(false, valid);
	}

	@Theory
	public void testFalseEnd(String dates) {
		Boolean valid = HRApplication.checkJob(courseCode, name, start, dates,
				time, numOfStaff, payRate);
		assertEquals(false, valid);
	}

	@Theory
	public void testFalseTime(String times) {
		Boolean valid = HRApplication.checkJob(courseCode, name, start, end,
				times, numOfStaff, payRate);
		assertEquals(false, valid);
	}

	@Theory
	public void testFalseNumOfStaff(int numOfStaffs) {
		Boolean valid = HRApplication.checkJob(courseCode, name, start, end,
				time, numOfStaffs, payRate);
		assertEquals(false, valid);
	}

	@Theory
	public void testFalsePayRate(double payRates) {
		Boolean valid = HRApplication.checkJob(courseCode, name, start, end,
				time, numOfStaff, payRates);
		assertEquals(false, valid);
	}

}
