////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
///////Class to implemets University, includes
///////Student Manager which is the control class of student
///////Course Manager which is the control class of course
///////Record Manager which is the control class of record
///////
///////Records hold students' registration of course and their marks
////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.EOFException;

public class University{
	private String name;
	private Boolean isQuit = false; //boolean for controlling the app
	private StudentManager studentManager = new StudentManager();
	private CourseManager courseManager = new CourseManager();
	private RecordManager recordManager = new RecordManager();
	private String studentFile = "Student.dat";
	private String courseFile = "Course.dat";
	private String recordFile = "Record.dat";
		
	
	public University(String name){
		this.name = name;
	}

	public Boolean isQuit(){
		return isQuit;
	}

	public String getName(){
		return name;
	}

	///////////////////////////////////////////////////
	///Add a student
	////////////////////////////////////////////////////
	public void addStudent(){
		int check = studentManager.addStudent();
		if (check ==1){
			System.out.println(">>>>>>>>>>Student already exist!<<<<<<<<<<");
		}
		else if (check ==0){
			System.out.println(">>>>>>>Add student successfully!<<<<<<<<<");
		}
		else if (check ==2){
			System.out.println(">>>>>>>>>>Invalid Matric!<<<<<<<<<<");
			System.out.println(">>>>>>>>>>Please enter matric number of length 9!<<<<<<<<<<");
		}
		studentManager.printStudent();
		System.out.println("\n\n\n\n");
	}


	///////////////////////////////////////////////////
	///Add a course
	////////////////////////////////////////////////////
	public void addCourse(){
		int check = courseManager.addCourse();
		if (check ==1){
			System.out.println(">>>>>>>>>>Course already exist!<<<<<<<<<<");
		}
		else if (check ==0){
			System.out.println(">>>>>>>>>>Add course successfully!<<<<<<<<<<");
		}
		courseManager.printCourse();
		System.out.println("\n\n\n\n");
	}



	///////////////////////////////////////////////////
	///Register a student
	///////////////////////////////////////////////////
	public void registerStudent(){ 
		Scanner sc = new Scanner(System.in);
		if (courseManager.isEmpty()){
			System.out.println(">>>>>>>>>>No course!<<<<<<<<<<\n\n\n");
			return;
		}
		if (studentManager.isEmpty()){
			System.out.println(">>>>>>>>>>No student<<<<<<<<<<\n\n\n");
			return;
		}
		System.out.println("\n\n");
		System.out.println("==========================================");
		System.out.print("Enter student by matric: ");
		String matric = sc.next();//take in matric
		if (!studentManager.existStudent(matric)){
			System.out.println(">>>>>>>>>>No student found!!<<<<<<<<<<");
			System.out.println("\n\n\n\n");
			return;
		}
							
		System.out.println("Adding student " + studentManager.getName(matric) + " to a course\n");
		courseManager.printCourse();
		System.out.println("\n\n");
		System.out.println("==========================================");
		System.out.print("Enter course code: ");						
		String code = sc.next();//take in course code

		if (!courseManager.existCourse(code)){
			System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
			System.out.println("\n\n\n\n");
			return;
		}	

		if (!courseManager.includeTut(code) && !courseManager.includeLab(code)){ // course with no tut and lab
			int check1 = courseManager.checkCourse(code);
			if (check1 == 1){
				System.out.println(">>>>>>>>>>No vacancy left<<<<<<<<<<");
				return;
			}
			else if (check1 == 2){
				System.out.println(">>>>>>>>>>Course has not added coursework weightage!<<<<<<<<<");
				System.out.println(">>>>>>>>>>Please add coursework weightage for this course first!<<<<<<<<<");
				return;
			}

			int check = recordManager.addRecord(matric, code); //register a course to student without index 

			if (check == 0){ //success
				courseManager.regCourse(code);
				System.out.println(">>>>>>>>>>Student " +
		  studentManager.getName(matric) + " added successfully to " + code + "<<<<<<<<<<");
			}
			else if (check == 1){//student already registered
				System.out.println(">>>>>>>>>>Student " + studentManager.getName(matric) +
					" already registered to " + code + " <<<<<<<<<<");
			}
		}
		/*
			*course with tut and lab
			*register index
		*/
		else {
			courseManager.printIndex(code);
			System.out.println("\n\n");
			System.out.println("==========================================");
			System.out.print("Enter index to register: ");
			String index = sc.next();//take in index
			int check1 = courseManager.checkCourse(code, index);
			if (check1 == 1){
				System.out.println(">>>>>>>>>>No vacancy left<<<<<<<<<<");
				return;
			}
			else if (check1 == 3){
				System.out.println(">>>>>>>>>>No index found<<<<<<<<<<");
				return;
			}
			else if (check1 == 2){
				System.out.println(">>>>>>>>>>Course has not added coursework weightage!<<<<<<<<<");
				System.out.println(">>>>>>>>>>Please add coursework weightage for this course first!<<<<<<<<<");
				return;
			}

			int check = recordManager.addRecord(matric, code, index); //register a student with index 

			if (check == 0){ //success
				courseManager.regCourse(code, index);
				System.out.println(">>>>>>>>>>Student " +
		  studentManager.getName(matric) + " added successfully to " + code + "<<<<<<<<<<");
			}
			else if (check == 1){//student already registered
				System.out.println(">>>>>>>>>>Student " + studentManager.getName(matric) +
					" already registered to " + code + " <<<<<<<<<<");
			}
		}
		System.out.println("\n\n\n");
		return;
	}
				
	///////////////////////////////////////////////////
	///Check available slots
	////////////////////////////////////////////////////
	public void checkAvailableSlots(){
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\n");
		if (courseManager.isEmpty()) {
			System.out.println(">>>>>>>>>>No courses<<<<<<<<<<");
			System.out.println("\n\n\n\n");
			return;
		}
		courseManager.printCourse();
		System.out.println("\n\n");
		System.out.println("==========================================");
		System.out.print("Enter course code: ");						
		String code4 = sc.next();//take in course code
		if (!courseManager.existCourse(code4)){
			System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
			System.out.println("\n\n\n\n");
			return;
		}
		System.out.println("Course " + code4 +" have " + courseManager.getVacancy(code4) + " free slots!");
		courseManager.printIndex(code4);//print indexes and their respective vacancy
		System.out.println("\n\n\n\n");
	}

	///////////////////////////////////////////////////
	///Print student list
	////////////////////////////////////////////////////
	public void printStudentList(){
		Scanner sc = new Scanner(System.in);
		int choice5 =0;
		System.out.println("\n\n");
		if (courseManager.isEmpty()){ //check if there is any course exist
			System.out.println(">>>>>>>>>>No courses<<<<<<<<<<");
			System.out.println("\n\n\n\n");
			return;
		}
		courseManager.printCourse();
		System.out.println("\n\n");
		System.out.println("==========================================");
		System.out.print("Enter course code: ");						
		String code5 = sc.next();//take in course code
					
		if (!courseManager.existCourse(code5)){
			System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
			System.out.println("\n\n\n\n");
			return;
		}
			
		if (!courseManager.includeTut(code5) && !courseManager.includeLab(code5)){//course printed doesn't have tut and lab
			if (recordManager.getStudent(code5).size() == 0){
				System.out.println(">>>>>>>>>>No student have registerd for this course yet<<<<<<<<<<");
				System.out.println("\n\n\n");
				return;
			}
			studentManager.printStudent(recordManager.getStudent(code5));
			System.out.println("\n\n\n");
			return;
		}
		try {
			System.out.println("1.Print students by lecture");
			System.out.println("2.Print students by tutorial/lab");
			System.out.println("==========================================");
			System.out.print("Enter choice: ");
			choice5 = sc.nextInt();
		} catch(InputMismatchException e){
			System.out.println(">>>>>>>>>>Invalid Input!<<<<<<<<<");
			return;
		} catch(Exception e){
			System.out.println(">>>>>>>>>>Error!<<<<<<<<<<");
			return;
		}
		switch (choice5){
			case 1://print all student not specific to index
				studentManager.printStudent(recordManager.getStudent(code5));
				break;
			case 2://print student according to index
				courseManager.printIndex(code5);
				System.out.println("\n\n");
				System.out.println("==========================================");
				System.out.print("Enter index to print students: ");
				String indextmp5 = sc.next();
				if (!courseManager.existIndex(code5, indextmp5)){
				System.out.println(">>>>>>>>>>Index not found!<<<<<<<<<<\n\n\n");
					break;
				}
				if (recordManager.getStudent(code5, indextmp5).size() == 0){
					System.out.println(">>>>>>>>>>No student have registerd for this index yet!<<<<<<<<<<");
					System.out.println("\n\n\n");
					break;
				}
				studentManager.printStudent(recordManager.getStudent(code5, indextmp5));
				break;
			default:
				System.out.println(">>>>>>>>>>Invalid choice!<<<<<<<<<<");
				break;
			}
					System.out.println("\n\n\n");
	}
	///////////////////////////////////////////////////
	///Enter coursework weightage
	////////////////////////////////////////////////////
	public void enterCourseworkWeightage(){ 
		Scanner sc = new Scanner(System.in);
		if (courseManager.isEmpty()){//check if any course exist
			System.out.println(">>>>>>>>>>No courses<<<<<<<<<<");
			System.out.println("\n\n\n\n");
			return;
		}
		courseManager.printCourse();
		System.out.println("\n\n");
		System.out.println("==========================================");		
		System.out.print("Enter course code: ");						
		String code = sc.next();//take in course code
		int check;

					
		if (!courseManager.existCourse(code)){
			System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
			System.out.println("\n\n\n\n");
			return;
		}

		if (courseManager.isRegistered(code)){
			System.out.println(">>>>>>>>>>Coursework weightage for " + code +" cannot be updated because there are students registered already<<<<<<<<<<");
			System.out.println("\n\n\n\n");
			return;
		}
			
		do {
			check = courseManager.addWeightage(code);//adding weightage
			if (check == 2) System.out.println(">>>>>>>>>>Coursework do not add up to 100 percent<<<<<<<<<<<");
		} while (check !=0 && check != 3);
		if (check == 0) {
			System.out.println(">>>>>>>>>>Course assesment components weightage added successfully!<<<<<<<<<<");
		}
		if (check == 3) System.out.println(">>>>>>>>>>Coursework weightage for "+ code +" was not updated!<<<<<<<<<<");
				
		System.out.println("\n\n\n\n");
	}	

	///////////////////////////////////////////////////
	///Enter coursework marks
	////////////////////////////////////////////////////	
	public void enterCourseworkMark(){
		Scanner sc = new Scanner(System.in);
		if (courseManager.isEmpty()){//check if any course exist
			System.out.println(">>>>>>>>>>No course<<<<<<<<<<\n\n\n");
			return;
		}
		if (studentManager.isEmpty()){
			System.out.println(">>>>>>>>>>No student<<<<<<<<<<\n\n\n");
			return;
		}
		System.out.println("\n\n");
		System.out.println("==========================================");
		System.out.print("Enter student by matric: ");
		String matric = sc.next();//take in student matric
		
		if (!studentManager.existStudent(matric)){
			System.out.println(">>>>>>>>>>No student found!!<<<<<<<<<<");
			System.out.println("\n\n\n\n");
			return;
		}

		if (recordManager.getCourse(matric).size() == 0){
			System.out.println(">>>>>>>>>>>Student " + studentManager.getName(matric) 
				+ " has not registered for any course yet!<<<<<<<<<<<\n\n\n\n");
			return;
		}
		System.out.println("Student " + studentManager.getName(matric) + "\n");
		courseManager.printCourse(recordManager.getCourse(matric));//print courses registered to student

		System.out.println("\n\n");
		System.out.println("==========================================");
		System.out.print("Enter course code: ");						
		String code = sc.next();//take in course code
					
		if (!courseManager.existCourse(code)){
			System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
			System.out.println("\n\n\n");
			return;
		}

		int check7 = recordManager.addCourseworkMark(matric, courseManager.findCourse(code));//adding coursework mark
		if (check7 == 0){ //success
			System.out.println(">>>>>>>>>>Coursework marks of student " + studentManager.getName(matric) 
				+ " added successfully for course " 
				+ code + "<<<<<<<<<<");
		}
		else if (check7 == 1){
			System.out.println(">>>>>>>>>>>Invalid Input!<<<<<<<<<<<");
		}
		else if (check7 == 2){ //student not registered to course
			System.out.println(">>>>>>>>>>Student " + studentManager.getName(matric) + " not registered to course " 
				+ code + "<<<<<<<<<<");
		}
		else if (check7 == 3){ // mark already added
			System.out.println(">>>>>>>>>>Coursework marks of student " + studentManager.getName(matric) + " already added to course " +
				code);
		}
		else if (check7 == -1){//unknown error
			System.out.println(">>>>>>>>>>Error!<<<<<<<<<<<<");
		}
		System.out.println("\n\n\n");
	}

	///////////////////////////////////////////////////
	///Enter exam mark
	////////////////////////////////////////////////////
	public void enterExamMark(){
		Scanner sc = new Scanner(System.in);
		if (courseManager.isEmpty()){
			System.out.println(">>>>>>>>>>No course<<<<<<<<<<\n\n\n");
			return;
		}
		if (studentManager.isEmpty()){
			System.out.println(">>>>>>>>>>No student<<<<<<<<<<\n\n\n");
			return;
		}
		System.out.print("Enter student by matric: ");
		String matric = sc.next();//take in matric
					
		if (!studentManager.existStudent(matric)){
			System.out.println(">>>>>>>>>>No student found!!<<<<<<<<<<");
			System.out.println("\n\n\n\n");
			return;
		}
		if (recordManager.getCourse(matric).size() == 0){
			System.out.println(">>>>>>>>>>>Student " + studentManager.getName(matric) 
				+ " has not registered for any course yet!<<<<<<<<<<<\n\n\n\n");
			return;
		}
		System.out.println("Student " + studentManager.getName(matric) + "\n");
		courseManager.printCourse(recordManager.getCourse(matric));//print courses registered to student
		System.out.print("Enter course code: ");						
		String code = sc.next();
					
					
		if (!courseManager.existCourse(code)){
			System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
			System.out.println("\n\n\n");
			return;
		}
		int check = recordManager.addExamMark(matric, courseManager.findCourse(code));//adding exam mark
		if (check == 0){ //success
			System.out.println(">>>>>>>>>>Exam marks of student " + studentManager.getName(matric) 
				+ " added successfully for course " 
				+ code + "<<<<<<<<<<");
		}
		else if (check == 1){
			System.out.println(">>>>>>>>>>>Invalid Input!<<<<<<<<<<<");
		}
		else if (check == 2){//student not regitered to course
			System.out.println(">>>>>>>>>>Student " + studentManager.getName(matric) + " not registered to course " 
				+ code + "<<<<<<<<<<");
		}
		else if (check == 3){//exam mark already added
			System.out.println(">>>>>>>>>>Exam marks of student " + studentManager.getName(matric) + " already added to course " +
				code + "<<<<<<<<<<");
		}
		else if (check == 4){ //course does not have exam
			System.out.println(">>>>>>>>>>Course " + code + " does not have exam " +
			"<<<<<<<<<<");	
		}
		else if (check == -1){//unknown error
			System.out.println(">>>>>>>>>>Error!<<<<<<<<<<<<");
		}
		System.out.println("\n\n\n");
	}

	///////////////////////////////////////////////////
	///Print course stats
	////////////////////////////////////////////////////	
	public void printCourseStats(){
		Scanner sc = new Scanner(System.in);
		if (courseManager.isEmpty()){
			System.out.println(">>>>>>>>>>No course<<<<<<<<<<");
			return;
		}
		courseManager.printCourse();
		System.out.println("=================================");
		System.out.print("Enter course code: ");						
		String code = sc.next();//take in course code

		if (!courseManager.existCourse(code)){
			System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
			System.out.println("\n\n\n");
			return;
		}

		if (!courseManager.isRegistered(code)){
			System.out.println(">>>>>>>>>>>Course does not have any student yet!<<<<<<<<<<\n\n\n");
			return;
		}
		Map<String, Map<String, Integer>> stats = (HashMap<String, Map<String, Integer>>) recordManager.getStats(courseManager.findCourse(code));

		Iterator it = stats.entrySet().iterator();
		while (it.hasNext()){
			Map.Entry pair = (Map.Entry) it.next();
			String student = (String) pair.getKey();
			Map<String, Integer> grades = (HashMap<String, Integer>) pair.getValue();
			System.out.printf("Student %-10s , Grade: %.2f/5, ", studentManager.getName(student), recordManager.getGrade(student, courseManager.findCourse(code)));
			Iterator itg = grades.entrySet().iterator();
			while (itg.hasNext()){
				Map.Entry pairg = (Map.Entry) itg.next();	
				System.out.print(pairg.getKey() + ": " + pairg.getValue() +", ");
			}
			System.out.println();
		}
		System.out.println("\n\n\n\n");
	}

	///////////////////////////////////////////////////
	///Print student transcript
	////////////////////////////////////////////////////
	public void printStudentTranscript(){
		Scanner sc = new Scanner(System.in);
		if (studentManager.isEmpty()){
			System.out.println(">>>>>>>>>>No student<<<<<<<<<<\n\n\n");
			return;
		}
		System.out.print("Enter student by matric: ");
		String matric = sc.next();//take in student matric
		
		/*
			*check if student exist
		*/
		if (!studentManager.existStudent(matric)){
			System.out.println(">>>>>>>>>>No student found!!<<<<<<<<<<");
			System.out.println("\n\n\n\n");
			return;
		}
		List<String> courseList = (ArrayList<String>)recordManager.getCourse(matric);
		System.out.println("==========Transcript of student " + studentManager.getName(matric) + " =========");
		for (String c : courseList){
			if (recordManager.getGrade(matric, courseManager.findCourse(c)) != -1)
				System.out.printf("Course %-10s Grade %.2f\n", c, recordManager.getGrade(matric, courseManager.findCourse(c)));
		}
		System.out.println("\n\n\n\n");
	}

	///////////////////////////////////////////////////
	///Save
	////////////////////////////////////////////////////
	public void save(){
			System.out.println("Saving data ......");
			studentManager.write(studentFile);
			courseManager.write(courseFile);
			recordManager.write(recordFile);
			System.out.println("Data saved successfully");
			System.out.println("\n\n\n\n");
	}

	///////////////////////////////////////////////////
	///Save and exit
	////////////////////////////////////////////////////
	public void saveAndExit(){
		System.out.println("Saving data ......");
		studentManager.write(studentFile);
		courseManager.write(courseFile);
		recordManager.write(recordFile);
		System.out.println("Data saved successfully");
		System.out.println("\n\n\n\n");
		isQuit = true;//stopping program
		System.out.println("SCRAME closing ......");
		System.out.println("\n\n\n\n");
	}

	public void exit(){
		isQuit = true;//stopping program
		System.out.println("SCRAME closing ......");
		System.out.println("\n\n\n\n");
	}

	public void init(){
		System.out.println("Reading data......");
		studentManager.read(studentFile);
		courseManager.read(courseFile);
		recordManager.read(recordFile);
		System.out.println("Data read successfully!");
		System.out.println("==========================================================================");
		System.out.println("\n\n\n");
	}
}