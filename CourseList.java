package a4;

//-----------------------------------------------------
//Assignment 4
//Question: 1
//Written by: Jack Leung - 40061019
//-----------------------------------------------------

//CourseList class with an inner class CourseNode set to private to prevent privacy leak
//Contains different methods of each classes

import java.util.ArrayList;

// Assignment 4
// Question: 1
// Written by: Jack Leung - 40061019
// -----------------------------------------------------

import java.util.NoSuchElementException;

public class CourseList {

	private class CourseNode { // CourseNode is in private so that we can only access the components of the nodes of the list inside this class, which prevents a privacy leak
		private Course course;
		private CourseNode next; // pointer to CourseNode object

		/**
		 * Default constructor
		 */
		public CourseNode() {
			this.course = null;
			this.next = null;
		}

		/**
		 * Parameterized constructor
		 * @param course a Course value
		 * @param node a CourseNode value
		 */
		public CourseNode(Course course, CourseNode node) {
			this.course = course;
			this.next = node;
		}

		/**
		 * Copy constructor
		 * @param copy a CourseNode value
		 */
		public CourseNode(CourseNode copy) {
			this.course = copy.course.clone(); 
			this.next = copy.next;
		}
		
		/**
		 * clone method
		 */
		public CourseNode clone(){
			return new CourseNode(this);
		}

		/**
		 * Get the course value
		 * @return course value
		 */
		public Course getCourse() {
			return course;
		}

		/**
		 * Set the course value
		 * @param course a Course value
		 */
		public void setCourse(Course course) {
			this.course = course;
		}

		/**
		 * Get the next value
		 * @return next value
		 */
		public CourseNode getNext() {
			return next;
		}

		/**
		 * Set the next value
		 * @param next a CourseNode value
		 */
		public void setNext(CourseNode next) {
			this.next = next;
		}
	} // end of CourseNode class

	private CourseNode head;
	private int size;

	/**
	 * Default constructor
	 */
	public CourseList() {
		head = null;
		size = 0;
	}

	/**
	 * Copy constructor
	 * @param copy a CourseList value
	 */
	public CourseList(CourseList copy) {
		this.head = copy.head.clone();
		this.size = copy.size;
	}

	/**
	 * addToStart method
	 * @param x a Course value
	 */
	public void addToStart(Course x) {
		head = new CourseNode(x, head);
		size++;
	}

	/**
	 * insertAtIndex method
	 * @param x a Course value
	 * @param index a integer value
	 * @return true or false
	 */
	public boolean insertAtIndex(Course x, int index) {
		if (index > size - 1 || index < 0) {
			System.out.println("ERROR: Given index is out of range! Program will terminate. \n");
			throw new NoSuchElementException();
		}

		System.out.println("\nInserting new node at index #" + index);
		int i = 0;
		CourseNode t = head;

		if (index == 0) {
			CourseNode newNode = new CourseNode(x, head);
			head = newNode;
			newNode = null; // why?? copying from linkedlist10.doc
		}

		else {
			while (i != index - 1) {
				t = t.next;
				i++;
			}
			t.next = new CourseNode(x, t.next);
		}

		size++;
		return true;
	}// end of insertAtIndex method

	/**
	 * deleteFromIndex method
	 * @param index an integer value
	 * @return true or false
	 */
	public boolean deleteFromIndex(int index) {
		if (index > size - 1 || index < 0) {
			System.out.println("ERROR: Given index is out of range! Program will terminate. \n");
			throw new NoSuchElementException(); // does it terminate program??
		}

		int i = 0;
		CourseNode t = head;

		if (size == 1) {
			System.out.println("\nDeleting the only node of the list at index #0.");

			head = null;
			size--;
			return true;
		}

		if (index == 0) {
			System.out.println("\nDeleting node with value " + head.course + " from index #" + index + ".");
			head = head.next; // should be pointing to null??
		} else {
			while (i != index - 1) {
				t = t.next;
				i++;
			}
			System.out.println("\nDeleting node with value " + t.next.course + " from index #" + index);
			t.next = t.next.next;

		}
		size--;
		return true;
	}// end of deleteFromIndex method

	/**
	 * deleteFromStart method
	 * @return true or false
	 */
	public boolean deleteFromStart() {
		if (head == null) {
			System.out.println("\nList is already empty.");
			return false;
		}

		head = head.next;

		size--;
		return true;
	}// end of deleteFromStart

	/**
	 * replaceAtIndex method
	 * @param c a Course value
	 * @param index an integer value
	 */
	public void replaceAtIndex(Course c, int index) {
		if (index < 0 || index > size)
			return;

		int i = 0;
		CourseNode t = head;

		while (i != index) {
			t = t.next;
			i++;
		}

		System.out.println("Replacing value at index #" + index + " from " + t.course + " to " + c + ".\n");
		t.course = c;
	}// end of replaceAtIndex

	/**
	 * find method
	 * @param id a String value
	 * @return a pointer of that CourseNode
	 */
	public CourseNode find(String id) {
		CourseNode t = head;
		
		int iteration = 1;

		while (t != null) {
			if (t.course.getCourseID().equals(id)) {
				System.out.println("Found same course ID after " + iteration + " iterations.");
				return t; // returns the clone instead of the original node so that there is no privacy
								// leak?
			}

			t = t.next;
			iteration++;
		}
		System.out.println("The course " +id+ " was not found in the list.");
		return null;
	}// end of find method

	/**
	 * contains method
 	 * @param id a String value
	 * @return true or false
	 */
	public boolean contains(String id) {
		if (find(id) != null)
			return true;
		else
			return false;
	}// end of contains method

	/**
	 * equals method
 	 * @param list2 a CourseList value
	 * @return true or false
	 */
	public boolean equals(CourseList list2) { // NOT SURE IF CORRECT
		CourseNode t1 = head;
		CourseNode t2 = list2.head;
		
		if (size != list2.size)
			return false;
		else if(head == null && list2.head == null)
			return true;
		
		while (t1 != null) {
			if (t1.course.equals(t2.course))
				return true;
			
			t1 = t1.next;
			t2 = t2.next;
		}
		
		
		if (!t1.course.equals(t2.course))
			return false;

		for (int i = 0; i < size; i++) {
			if (t1.course.equals(t2.course)) {
				t1 = t1.next;
				t2 = t2.next;
			}

			else
				return false;
		}

		return true;
	}// end of equals method

	/**
	 * showContents method
	 */
	public void showContents() {
		CourseNode temp = head;
		if (temp == null)
			System.out.println("\bThere is nothing to display; list is empty.");
		else
			while (temp != null) {
				System.out.print(temp.course + " ---> ");
				temp = temp.next;
			}
	}// end of showContents method
	
	private static ArrayList<String> enrolled = new ArrayList<String>();
	
	/**
	 * enrolling method
	 * @param finished an ArrayList<String> value
	 * @param requested a String value
	 */
	public void enrolling(ArrayList<String> finished, String requested){
		CourseNode t = null;
		t = find(requested);
		
		if (t.course.getCourseID().equals(requested)){
			
			if (finished.contains(t.course.getPreReqID()) && finished.contains(t.course.getCoReqID())){
				System.out.println("Student can enroll in " +requested+ " course as he/she has completed the pre-requisite " +t.course.getPreReqID() + " and co-requisite " + t.course.getCoReqID()+".");
				enrolled.add(requested);
			}
				
			else if (finished.contains(t.course.getPreReqID()) && enrolled.contains(t.course.getCoReqID())){
				System.out.println("Student can enroll in " + requested+ " course as he/she has completed the pre-requisite " + t.course.getPreReqID() + " and is enrolled in" + t.course.getCoReqID()+".");
				enrolled.add(requested);
			}
			
			else if (finished.contains(t.course.getPreReqID())){
				System.out.println("Student can enroll in " + requested + " as he/she has completed the pre-requisite " + t.course.getPreReqID()+".");
				enrolled.add(requested);
			}
			
			else if (enrolled.contains(t.course.getCoReqID())){
				System.out.println("Student can enroll in " + requested + " as he/she is enrolled in the co-requisite " + t.course.getCoReqID()+".");
				enrolled.add(requested);
			}
			
			else if (finished.contains(t.course.getCoReqID())){
				System.out.println("Student can enroll in " + requested + " as he/she has completed the co-requisite " + t.course.getCoReqID()+".");
				enrolled.add(requested);
			}	
			
			else if (finished.isEmpty() && t.course.getCourseID().equals(requested) && t.course.getPreReqID().isEmpty() && t.course.getCoReqID().isEmpty())
				System.out.println("Student can enroll in " + requested + " as it does not require any pre-requisite or co-requisite.");
			
			else
				System.out.println("Student can't enroll in " + requested + " as he/she doesn't have sufficient background needed.");
		
		}//end of big if
		
		else 
			System.out.println("The course requested is not valid.");
		
	}//end of enrolling method

}// end of CourseList class
