///////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////
/////Class to implement student manager object to control students
////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.Serializable;
import java.io.IOException;

public class StudentManager extends SerializeDB{
	private List<Student> students = new ArrayList<Student>();

	public Boolean isEmpty(){
		if (students.size() == 0) return true;
		return false;
	}

	/*
		*
		*method for printing all students in university
		*
	*/
	public void printStudent(){
		System.out.println("          =========List of students========");
		System.out.printf("%-15s %30s", "Student Name", "Matric Number\n");
		System.out.println("------------------------------------------------------");
			for (Student s : students){
				System.out.printf("%-15s %27s\n", s.getName(), s.getMatric());
		}
		System.out.printf("\n\n\n");
	}

	public void printStudent(List list){
		if (list.size() == 0) return;
		List<String> students = (ArrayList<String>) list;
		System.out.println("          =========List of students========");
		System.out.printf("%-25s %30s", "Student Name", "Matric Number\n");
		System.out.println("------------------------------------------------------");
		for (String s : students){
			Student student = findStudent(s);
			System.out.printf("%-25 %27s\n", student.getName(), student.getMatric());
		}
	}

	/*
		*
		*method to check if a student exist already or not
		@param matric: String, used to determine student
		*
	*/

	public Boolean existStudent(String matric){
		for (Student s : students){
			if (matric.equals(s.getMatric())) return true;
		}
		return false;
	}

	/*
		*
		*method to find student inside the list
		@param matric: String, used to determine student
		@return: Student
		*return the found student
		*if student not found return null
	*/

	public Student findStudent(String matric){
		for (Student s : students){
			if (matric.equals(s.getMatric())) return s;
		}
		return null;
	}

	public String getName(String matric){
		Student s = findStudent(matric);
		return s.getName();
	}

	/*
		*
		*method for adding new student 
		@return 0: Success
		@return 1: Student already exist
		@return 2: Invalid matric input
		*
	*/

	public int addStudent(){
		Scanner sc = new Scanner(System.in);
		String smatric;
		String sname;
		try{
			System.out.println("\n\n");
			System.out.println("==========================================");
			System.out.print("Enter student matric: ");
			smatric = sc.next(); //take in matric
			if (smatric.length() !=9){
				return 2;//invalid matric (length of matric =9)
			}
			if (students != null){ //check for duplicate student
				if (existStudent(smatric)) return 1; //duplicate student
			}
			sc.nextLine();//clearing input buffer
			System.out.println("\n\n");
			System.out.println("==========================================");	
			System.out.print("Enter student name: ");
			sname = sc.nextLine();//take in student name
		} catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		Student student = new Student(sname, smatric);//create new student
		students.add(student);//add to students
		return 0;
	}

	public void read(String file){
		try{
			students = (ArrayList<Student>) readDB(file);
			System.out.println("Students read succssfullly");
		} catch (IOException e){
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
	}

	public void write(String file){
		try{
			writeDB(file, students);
			System.out.println("Student saved successfully");
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
}