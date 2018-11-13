///////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////
/////Class to implement course manager object to control course object, includes 
/////ArrayList of Courses
/////Methods to add or find or print students
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


public class CourseManager {
	private List<Course> courses = new ArrayList<Course>();

	private ProfessorManager profManager = new ProfessorManager();

	public Boolean isEmpty(){
		if (courses.size() == 0) return true;
		else return false;
	}

	/*
		*
		*method to check if a course existed already or not
		@param code: String, used to determine course
		*
	*/

	public Boolean existCourse(String code){
		for (Course c : courses){
			if (code.equals(c.getCode())) return true;
		}
		return false;
	}

	/*
		*
		*method to find Course inside the list
		@param code: String, used to determine Course
		@return: Course
		*return the found Course
		*if Course not found return null
	*/

	public Course findCourse(String code){
		for (Course c : courses){
			if (code.equals(c.getCode())) return c;
		}
		return null;
	}

	/*
		*
		@return 1: No vacancy
		@return 2: No weightage added
		*
	*/

	public void regCourse(String course){
		findCourse(course).register();
	}

	public int checkCourse(String code){
		Course course = findCourse(code);
		if (course.getExamWeightage() == 0 && course.getCourseworkWeightage().size() == 0) return 2;
		if (course.getVacancy() == 0) return 1;
		return 0;
	}

	

	public void regCourse(String course, String index){
		findCourse(course).register(index);
	}

	/*
		*
		@return 1: No vacancy
		@return 2: No weightage added
		@return 3: No index found
		*
	*/

	public int checkCourse(String code, String index){
		Course course = findCourse(code);
		if (course.getExamWeightage() == 0 && course.getCourseworkWeightage().size() == 0) return 2;
		if (course.getVacancy() == 0) return 1;
		if (!course.existIndex(index)) return 3;
		if (course.getVacancy(index) == 0) return 1;
		return 0;
	}

	public Boolean isRegistered(String code){
		return findCourse(code).isRegistered();
	}

	public Boolean includeTut(String code){
		return findCourse(code).includeTut();
	}

	public Boolean includeLab(String code){
		return findCourse(code).includeLab();
	}

	public int getVacancy(String course){
		return findCourse(course).getVacancy();
	}

	public Boolean existIndex(String course, String index){
		return findCourse(course).existIndex(index);
	}

	/*
		*
		*method for printing all courses 
		*
	*/

	public void printCourse(){
		System.out.println("      =========List of course==========");
		for (Course c : courses){
				System.out.println("Course code: "+c.getCode() + ", Course name: "
					+c.getName() + ", Number of tutorial index(s): " + c.getSize() +", Number of lab index(s): " 
					+ c.getSize() +", Coordinator: " + c.getCoordinator().getName() + ", Vacancy: " + c.getVacancy());
		}
		System.out.printf("\n\n\n");
	}

	public void printCourse(List list){
		if (list.size() == 0) return;
		List<String> courses = (ArrayList<String>) list;
		System.out.println("      =========List of course==========");
		for (String course : courses){
				Course c = findCourse(course);
				System.out.println("Course code: "+c.getCode() + ", Course name: "
					+c.getName() + ", Number of tutorial index(s): " + c.getSize() +", Number of lab index(s): " 
					+ c.getSize() +", Coordinator: " + c.getCoordinator().getName() + ", Vacancy: " + c.getVacancy());
		}
		System.out.printf("\n\n\n");
	}

	public void printIndex(String code3){
		findCourse(code3).printIndex();
	}

	/*
		*
		*method for adding new course into courses
		*
		@return 1: Course already exist
		@return 0: Success
		*
	*/

	public int addCourse(){
		Scanner sc = new Scanner(System.in);
		String ccode = "";
		String cname = "";
		Boolean tut = true;
		Boolean lab = true;
		Professor prof = new Professor();
		int choice1;
		int choice2;
		int vacancy = 0;
		Boolean vflag = false;

		try{
			System.out.println("\n\n");
			System.out.println("==========================================");
			System.out.print("Enter course code: ");
			ccode = sc.next();//take in course code
			/*
				*check if course exist
			*/
			if (courses != null){
				if (existCourse(ccode)) return 1;//course existed already
			}
			sc.nextLine(); //clearing input buffer;
			System.out.println("\n\n");
			System.out.println("==========================================");
			System.out.print("Enter course name: ");
			cname = sc.nextLine();//take in course name
		} catch(Exception e){
			e.printStackTrace();
		}

		try {
			do {
				System.out.println("\n\n");
				System.out.println("==========================================");
				System.out.printf("Does %s have tutorial?\n", ccode);
				System.out.println("1.Yes");
				System.out.println("2.No");
				System.out.print("Choice: ");
				choice1 = sc.nextInt();
				switch(choice1){
					case 1:
						tut = true;
						break;
					case 2:
						tut = false;
						break;
					default:
						System.out.println("Invalid choice!");
						break;
				}
			} while (choice1 <1 || choice1 >2);
			do {
				System.out.println("\n\n");
				System.out.println("==========================================");
				System.out.printf("Does %s have lab?\n", ccode);
				System.out.println("1.Yes");
				System.out.println("2.No");
				System.out.print("Choice: ");
				choice2 = sc.nextInt();
				switch(choice2){
					case 1:
						lab = true;
						break;
					case 2:
						lab = false;
						break;
					default:
						System.out.println(">>>>>>>>>>Invalid choice!<<<<<<<<<<");
						break;
				}
			} while (choice2 <1 || choice2 >2);
		} catch(InputMismatchException e){
			System.out.println(">>>>>>>>>>Invalid Input!<<<<<<<<<");
			return -1;
		} catch(Exception e){
			System.out.println(">>>>>>>>>>Error!<<<<<<<<<<");
			return -1;
		}
		profManager.printProfessor();
		System.out.println("Choose the course coordinator");
		do{
			try{ 
				System.out.println("\n\n");
				System.out.println("==========================================");
				System.out.print("Enter the coordinator of your choice by ID: ");
				String pid = sc.next(); //take in professor id
				prof = profManager.findProfessor(pid);
			} catch(InputMismatchException e){
				System.out.println(">>>>>>>>>>Invalid Input!<<<<<<<<<<");
			} catch(Exception e){
				System.out.println(">>>>>>>>>>Error!<<<<<<<<<<");
			}
			if (prof == null) System.out.println(">>>>>>>>>>No match found!<<<<<<<<<<");
		} while (prof == null);
		System.out.println("Professor " + prof.getName());
		do {
			try { 
				System.out.println("\n\n");
				System.out.println("==========================================");
				System.out.print("Enter vacancy for the course: ");
				vacancy = sc.nextInt();//take in vacancy
				if (vacancy < 1){
					throw new InputMismatchException("Negative vacancy");
				}
				vflag = true;
			} catch(InputMismatchException e){
				sc.nextLine();
				System.out.println(">>>>>>>>>>Invalid Input!<<<<<<<<<<");
			} catch (Exception e){
				e.printStackTrace();
			}
		} while (!vflag);
		Course cour = new Course(ccode, cname, tut, lab, prof, vacancy); //create new course
		courses.add(cour); // add to courses
		return 0;
	}

	/*
		*
		*method for adding weightage to course
		*including exam weightage and coursework weightage
		*allow exam to have 100% weightage or coursework to have 100% wieghtage
		*call add exam and add weightage of course
		*
		*@return an exit value
		*0: sucessful
		*1: Input mismatch or invalid input
		*2: weightages not add up to 100
		*3: No update
		*-1: unknown error
		*
	*/

	public int addWeightage(String course){
		return findCourse(course).addWeightage();			
	}

	public void read(String file){
		try { // reading in data from files
			courses = (ArrayList<Course>) SerializeDB.readSerializedObject(file);
			profManager.read();
			System.out.println("Courses read succssfullly");
		} catch (IOException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
			e.printStackTrace();	
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public void write(String file){
		try {
			SerializeDB.writeSerializedObject(file, courses);
			System.out.println("Course saved successfully");
		}catch(IOException e){
			System.out.println(">>>>>>>>>>File Course Error<<<<<<<<<<");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}