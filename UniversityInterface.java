////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
///////Class to implemets University interface
////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////


import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.EOFException;

public class UniversityInterface{
	private University university;

	public UniversityInterface(String name){
		this.university = new University(name);
		university.init();
	}

	public Boolean isQuit(){
		return university.isQuit();
	}
		
	public void go() throws InputMismatchException{
		Scanner sc = new Scanner(System.in);
		int choice;//variable for switch
		System.out.printf("========Welcome to the %s University SCRAME=======\n", university.getName());
		System.out.println("1.Add a student");
		System.out.println("2.Add a course");
		System.out.println("3.Register student for a course");
		System.out.println("4.Check available slot in a class");
		System.out.println("5.Print student list by lecture, tutorial, lab for a course");
		System.out.println("6.Enter course assesment components weightage");
		System.out.println("7.Enter coursework mark - inclusize of its components");
		System.out.println("8.Enter exam mark");
		System.out.println("9.Print course stats");
		System.out.println("10.Print student transcript");
		System.out.println("11.Save");
		System.out.println("12.Save & Exit");
		System.out.println("13.Exit without saving");
		System.out.print("Enter choice: ");
		try {
			choice = sc.nextInt();
		} catch (InputMismatchException e){
			throw new InputMismatchException(">>>>>>>>>>Invalid Input!<<<<<<<<<<<");
		} catch (Exception e){
			throw e;
		}
		switch (choice){

			///////////////////////////////////////////////////
			///Add a student
			////////////////////////////////////////////////////
			case 1:
				university.addStudent();
				break;

			///////////////////////////////////////////////////
			///Add a course
			////////////////////////////////////////////////////
			case 2:
				university.addCourse();
				break;

			///////////////////////////////////////////////////
			///Register a student
			////////////////////////////////////////////////////
			case 3: 
				university.registerStudent();
				break;

			///////////////////////////////////////////////////
			///Check available slots
			////////////////////////////////////////////////////
			case 4:
				university.checkAvailableSlots();
				break;

			///////////////////////////////////////////////////
			///Print student list
			////////////////////////////////////////////////////
			case 5:
				university.printStudentList();
				break;

			///////////////////////////////////////////////////
			///Enter coursework weightage
			////////////////////////////////////////////////////
			case 6: 
				university.enterCourseworkWeightage();
				break;	

			///////////////////////////////////////////////////
			///Enter coursework marks
			////////////////////////////////////////////////////	
			case 7:
				university.enterCourseworkMark();
				break;

			///////////////////////////////////////////////////
			///Enter exam mark
			////////////////////////////////////////////////////
			case 8:
				university.enterExamMark();
				break;

			///////////////////////////////////////////////////
			///Print course stats
			////////////////////////////////////////////////////	
			case 9:
				university.printCourseStats();
				break;

			///////////////////////////////////////////////////
			///Print student transcript
			////////////////////////////////////////////////////
			case 10:
				university.printStudentTranscript();
				break;

			///////////////////////////////////////////////////
			///Save
			////////////////////////////////////////////////////
			case 11:
				university.save();
				break;

			///////////////////////////////////////////////////
			///Save and exit
			////////////////////////////////////////////////////
			case 12:
				university.saveAndExit();
				break;
		
			case 13://exit without saving
				university.exit();
				break;
			default:
				System.out.println(">>>>>>>>>>Invalid Input!<<<<<<<<<<");
				break;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	
		UniversityInterface uniInterface = new UniversityInterface("NTU");	
		while (!uniInterface.isQuit()){
			try{
				uniInterface.go();
			}
			catch(InputMismatchException e){
				System.out.println(e.getMessage());
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		sc.close();
	}
}