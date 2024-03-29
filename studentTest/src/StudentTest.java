/*
 * File: StudentTest.java
 * ----------------------
 * Simple test of the Student class.
 */

public class StudentTest {

	public static void main(String[] args) {
		Student chosenOne = new Student("Harry Potter", 123456);
		System.out.println("Chosen One = " + chosenOne.getName());
		Student topStudent = new Student("Hermione Granger", 314159);
		topStudent.setUnits(263);
		topStudent.setPaidUp(true);
		Student weakStudent = new Student("Vincent Crabbe", 271828);
		weakStudent.setUnits(125);
		weakStudent.setPaidUp(true);
		Student impoverishedStudent = new Student("Ron Weasley", 161803);
		impoverishedStudent.setUnits(32);
		//testEligibility(topStudent);
		//testEligibility(weakStudent);
		//testEligibility(impoverishedStudent);
	}

	private void testEligibility(Student student) {
		String verb = (isEligibleToGraduate(student)) ? "is" : "is not";
		System.out.println(student + " " + verb + " eligible to graduate.");
	}

	private boolean isEligibleToGraduate(Student student) {
		return student.getUnits() >= Student.UNITS_TO_GRADUATE
				&& student.isPaidUp();
	}

}
