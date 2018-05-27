package Controller;

import Models.*;
import java.util.*;

public class Driver {
	public static void main(String[] args) {
		Staff user = HRApplication.login();
		HRApplication.importCourseInfo();
		while (true) {
			HRApplication.selectMenu(user);
		}
	}
}
