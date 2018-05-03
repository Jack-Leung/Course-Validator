// -----------------------------------------------------
// Assignment 4
// Question: 1
// Written by: Jack Leung - 40061019
// -----------------------------------------------------

//The driver class where we test part I,II, III, IV
//And also the constructors and methods of each classes

package a4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class EnrolmentResults {

	public static void readFileSyllabus(Scanner input, CourseList list) {
		ArrayList<String> arrList = new ArrayList<String>();

		String s = "";
		String[] arr;

		String courseID = "";
		String courseName = "";
		double credit = 0;
		String preReq = "";
		String coReq = "";
		Course course;

		while (input.hasNextLine()) {//read line by line until end of file
			s = input.nextLine();
			arr = s.split("\t");

			if (arr.length == 3) { //if the line is ID NAME CREDIt
				courseID = arr[0];
				courseName = arr[1];
				credit = Double.parseDouble(arr[2]);

				arrList.add(courseID); //add to array list
			}

			if (arr.length == 2) { // checks if there is something in preReq and coReq
				if (arr[0].equals("P"))
					preReq = arr[1];
				if (arr[0].equals("C"))
					coReq = arr[1];
			}

			if (arr.length == 1) { // checks if there is no courseID on the preReq and/or coReq
				if (arr[0].equals("P"))
					preReq = "";
				if (arr[0].equals("C"))
					coReq = "";
			}

			if (s.equals("") || !input.hasNextLine()) { //add each course in the list when reached the end of the course info
				course = new Course(courseID, courseName, credit, preReq, coReq);
				list.addToStart(course);
			}

		} // end of while

		System.out.println(Arrays.toString(arrList.toArray())); //display the array list that contains the courses

		String[] arrDuplicate = new String[arrList.size()]; // Converting ArrayList to Array
		arrList.toArray(arrDuplicate);

		for (int i = 0; i < arrDuplicate.length; i++) {
			for (int j = i + 1; j < arrDuplicate.length; j++) {
				if (i != j && arrDuplicate[i].equals(arrDuplicate[j])) { //loop until there is a duplicate, and delete it from the list
					list.deleteFromIndex(i - 1); 
				}
			}
		} // end of for loop

		input.close();
	}// end of readFileSyllabus method

	public static void readFileRequest(Scanner input, CourseList list) {
		ArrayList<String> arrList = new ArrayList<String>();
		
		String s = "";

		while (input.hasNextLine()) { //read file until end of file
			s = input.nextLine();

			arrList.add(s); //add each line to array list

		} // end of while

		ArrayList<String> finished = new ArrayList<String>();

		System.out.println("\nFinished classes:");
		for (int i = arrList.indexOf("Finished") + 1; i < arrList.indexOf("Requested"); i++) {
			System.out.println(">>"+arrList.get(i)+"<<"); //display the finished class of student

			finished.add(arrList.get(i)); //add the finished class into array list finished
		} // end of for loop
		
		boolean enrolled = false;
		System.out.println("\nRequested classes:");
		for (int j = arrList.indexOf("Requested")+1; j < arrList.size(); j++) {
			System.out.println(">>"+arrList.get(j)+"<<"); //display the requested class of student
			
			list.enrolling(finished, arrList.get(j)); //check if student can enroll or not
			
			System.out.println();
			enrolled = true;
		}	
		
		if (!enrolled) 
			System.out.println("The course requested is not valid.");
		
		input.close();
	}// end of readFileRequest

	public static void main(String[] args) {
		System.out.println("Welcome to my program.\n\n");
		
		CourseList list1 = new CourseList();
		CourseList list2 = new CourseList();

		Scanner sc = null;
		Scanner kb = new Scanner(System.in);

		String syl = "Syllabus.txt";
		String req = "";

		try {
			sc = new Scanner(new FileInputStream(syl));
			System.out.println("==========Reading " + syl + " file.==========\n\n");
			readFileSyllabus(sc, list1);
			
			System.out.print("Please enter the Request file that needs to be processed: ");
			req = kb.next();
			System.out.println("\n==========Reading "+req+" file.==========\n");
			sc = new Scanner(new FileInputStream(req)); 
			readFileRequest(sc, list1);
			
		} catch (FileNotFoundException e) {
			System.out.println("\n\nCould not open input file " + req + " for reading. Please check if file exists!");
			System.out.println("Program will terminate after closing any opened files.");
			System.exit(0); // exit the program
		} catch (Exception e) {
			System.out.println("\n\nException error. Will terminate.");
			System.exit(0);
		}//end of try and catch
		
		System.out.println();

		
		System.out.println("\n\n-=-=-=-=-=-=-=-=-=-=-TESTING CONSTRUCTORS AND METHODS -=-=-=-=-=-=-=-=-=-=-");
		System.out.println("\n~~TESTING COURSE~~");
		Course c1 = new Course();
		System.out.println("\n********** Testing constructors of Course with toString() **********");
		System.out.println("c1: " +c1);
		Course c2 = new Course("TEST101", "Testing", 1, "TEST100", "TEST102");
		System.out.println("c2: " +c2);
		Course c3 = new Course(c2, "COPY");
		System.out.println("c3: " + c3);
		
		System.out.println("\n********** Testing equals() of Course **********");
		System.out.println("c2 equals c3?: " +c2.equals(c3)); //only different courseID, else same
		Course c5 = new Course();
		c5 = c3.clone();
		System.out.println("Testing clone of c3 --> c5");
		System.out.println("Clone c5: " + c5);
		System.out.println("c5 equals c3?: " +c3.equals(c5));
		
		System.out.println("\n********** Testing isDirectlyRelated() of Course **********");
		Course c4 = new Course("TEST100", null, 0, null, null);
		System.out.println("c3 is directly related to c4?");
		c3.isDirectlyRelated(c4); //check if c4 is a prereq or coreq of c3
		
		System.out.println("\n~~END OF COURSE TESTS~~\n");
		
		System.out.println("\n~~TESTING COURSELIST~~");
		//CourseNode n1 = new CourseNode();		Gives error as CourseNode is in private to prevent privacy leak
		//Its methods are also unreachable outside the CourseList class
		System.out.println("Showing contents of list1");
		list1.showContents();
		
		list2 = new CourseList(list1);
		System.out.println("Showing contents of list2 (copy constructor copying list1)");
		list2.showContents();
		
		System.out.println("\n********** Testing equals() of Course list1 and list2 **********");
		System.out.println(list1.equals(list2));

		System.out.println("\n********** CourseList addtoStart() **********");
		Course c6 = new Course("ENGR251", "Thermodynamics", 3, null, null);
		list2.addToStart(c6);
		System.out.println("\nList1: ");
		list1.showContents();
		System.out.println("\nList2: ");
		list2.showContents();
		System.out.println("\n\nList1 equals List2? " + list1.equals(list2));
		
		System.out.println("\n********** CourseList insertAtIndex(), replaceAtIndex() **********");
		list2.insertAtIndex(c3, 4);
		list2.replaceAtIndex(c2, 8);
		list2.showContents();

		System.out.println("\n\n********** CourseList deleteFromIndex(), deleteFromStart() **********");
		list2.deleteFromIndex(9);
		list2.deleteFromStart();
		list2.showContents();
		
		System.out.println("\n\n********** CourseList replaceAtIndex() **********\n");
		list2.replaceAtIndex(c6, 2);
		list2.showContents();
		
		System.out.println("\n\n********** CourseList find(), contains() **********\n");
		list2.find("ENGR251");
		list2.contains("POTATO");
		list2.showContents();

		System.out.println("\n\n\nThank you for using program.");

	}//end of main
}
