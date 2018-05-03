package a4;

import java.util.Scanner;

// -----------------------------------------------------
// Assignment 4
// Question: 1
// Written by: Jack Leung - 40061019
// -----------------------------------------------------

//Course class with its methods

public class Course implements DirectlyRelatable {
	private String courseID;
	private String courseName;
	private double credit;
	private String preReqID;
	private String coReqID;

	// start of Getters and Setters
	/**
	 * getCourseID method
	 * @return courseID
	 */
	public String getCourseID() {
		return courseID;
	}

	/**
	 * setCourseID method
	 * @param courseID a String value
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	/**
	 * getCourseName method
	 * @return courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * setCourseName method
	 * @param courseName String value
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * getCredit method
	 * @return credit
	 */
	public double getCredit() {
		return credit;
	}

	/**
	 * setCredit method 
	 * @param credit double value
	 */
	public void setCredit(double credit) {
		this.credit = credit;
	}

	/**
	 * getPreReqID method
	 * @return preReqId
	 */
	public String getPreReqID() {
		return preReqID;
	}

	/**
	 * setPreqReqID method
	 * @param preReqID String value
	 */
	public void setPreReqID(String preReqID) {
		this.preReqID = preReqID;
	}

	/**
	 * getCoReqID method
	 * @return coReqId 
	 */
	public String getCoReqID() {
		return coReqID;
	}
	
	/**
	 * setCoReqID method
	 * @param coReqID String value
	 */
	public void setCoReqID(String coReqID) {
		this.coReqID = coReqID;
	}
	// end of Getters and Setters
	
	/**
	 * Default constructor
	 */
	public Course(){
		this.courseID ="";
		this.courseName ="";
		this.credit=0;
		this.preReqID="";
		this.coReqID="";
	}
	
	/**
	 * Parameterized constructor
	 * @param courseID a String value
	 * @param courseName a String value
	 * @param credit a double value
	 * @param preReqID a String value
	 * @param coReqID a String value
	 */
	public Course(String courseID, String courseName, double credit, String preReqID, String coReqID) {
		this.courseID = courseID;
		this.courseName = courseName;
		this.credit = credit;
		this.preReqID = preReqID;
		this.coReqID = coReqID;
	}

	// Copy constructor
	public Course(Course copy, String value) {
		this.courseID = value;

		this.courseName = copy.courseName;
		this.credit = copy.credit;
		this.preReqID = copy.preReqID;
		this.coReqID = copy.coReqID;
	}

	// clone() method
	public Course clone() {
		String cloneID;

		Scanner kb = new Scanner(System.in);
		System.out.println("\nInitiating clone() method...");
		System.out.println("Please enter new courseID: ");
		cloneID = kb.next();

		return new Course(this, cloneID); // not sure if its correct
	}

	/**
	 * toString() method that display a string
	 */
	public String toString() {
		return /*"courseID=" +*/ courseID; 
				//+ ", courseName=" + courseName + ", credit=" + credit + ", preReqID=" + preReqID + ", coReqID=" + coReqID;
	}

	/**
	 * equals() method that checks if both objects are equal
	 * @param obj an Object value
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Course other = (Course) obj;
		if (coReqID == null) {
			if (other.coReqID != null)
				return false;
		} else if (!coReqID.equals(other.coReqID))
			return false;

		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;

		if (Double.doubleToLongBits(credit) != Double.doubleToLongBits(other.credit))
			return false;

		if (preReqID == null) {
			if (other.preReqID != null)
				return false;
		} else if (!preReqID.equals(other.preReqID))
			return false;

		return true;
	}

	/**
	 * isDirectlyRelated method
	 * @param c a Course value
	 */
	public boolean isDirectlyRelated(Course c) { // need to check returns true if C is prered or coreq of current course
		if (this.preReqID.equals(c.getCourseID())) {
				System.out.println(c.getCourseID()+ " is the pre-requisite of " + this.courseID);
				return true;
		}
		
		else if(this.coReqID.equals(c.getCourseID())) {
			System.out.println(c.getCourseID()+ " is the co-requisite of " + this.courseID);
			return true;
		}
		
		else {
			System.out.println("Both courses are not related to each other.");
			return false;
		}
	}//end of isDirectlyRelated method

}//end of class
