////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
/////Class to implement course inside university, includes,
/////String name
/////String code
/////Boolean indicates whether course has tut and lab or not
/////Professor as coordinator
/////An int exam indicates the exam weightage for this course
/////A dictionary contains coursework weightage for this course
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

	public class Course implements Serializable{
		private String code; //course code e.g CZ2002
		private String name; //course name
		private boolean hasTut;//indicate whether course has tut
		private boolean hasLab;//indicate whether course has lab
		private Professor coordinator;
		private int exam; //exam weightage
		private Map<String, Integer> coursework = new HashMap<String, Integer>();//{component: weightage} coursework weightage
		private int vacancy;
		private LessonManager lessonManager = new LessonManager();
		private boolean registered = false;
		//private List<Tutorial> tuts = new ArrayList<Tutorial>();//tuts of course
		//private List<Lab> labs = new ArrayList<Lab>();//labs of course
		//private Map<Student, String> students = new HashMap<Student, String>(); //{Student: Index}, index "Lecture" = no tut/lab
		private static final long serialVersionUID = -3914670736074682579L;

		
	//////////////////////////////////////////////////////////////////////////////////////
	//Constructor
	////////////////////////////////////////////////////////////////////////////////////

	public Course(String code, String name, Boolean hasTut, Boolean hasLab, Professor coordinator, int vacancy){
		this.code = code;
		this.name = name;
		this.hasTut = hasTut;
		this.hasLab = hasLab;
		this.coordinator = coordinator;
		this.vacancy = vacancy;
		/*if (hasTut || hasLab){ // course has tut or lab
			int check = addIndex();
			while (check != 0){
				if (check == 2) System.out.println("Vacancy for course and index(es) does not match");
				if (check == 0) break;
				check = addIndex();
			}
		}*/

		if (hasTut || hasLab){
			int check = this.lessonManager.addIndex(hasTut, hasLab, code, vacancy);
			while (check != 0){
				if (check == 2) System.out.println("Vacancy for course and index(es) does not match");
				if (check == 0) break;
				check = this.lessonManager.addIndex(hasTut, hasLab, code, vacancy);
			}
		}
	}

	public Course(){};

	//////////////////////////////////////////////////////////////////////////////////////////////////
	//Methods
	/////////////////////////////////////////////////////////////////////////////////////////////////

	/*
		*
		*@return code of this course
		*
	*/

	public String getCode(){
		return code;
	}

	/*
		*
		*@return name of this course
		*
	*/

	public String getName(){
		return name;
	}

	/*
		*
		*@return Boolean if this course has tut
		*
	*/

	public boolean includeTut(){
		return hasTut;
	}

	/*
		*
		*@return Boolean if this course has lab
		*
	*/

	public boolean includeLab(){
		return hasLab;
	}

	/*
		*
		*@return coordinator for this course
		*
	*/

	public Professor getCoordinator(){
		return coordinator;
	}

	public Boolean isRegistered(){
		return registered;
	}

	/*
		*
		*@return exam weightage for this course
		*
	*/

	public int getExamWeightage(){
		return exam;
	}

	/*
		*
		*@return coursework weightage map of this course
		*
	*/

	public Map<String, Integer> getCourseworkWeightage(){
		return coursework;
	}

	/*
		*print all weightage 
		*include exam and all coursework
	*/
	
	public void printWeightage(){
		System.out.printf("Exam: %d percent\n", exam);
		Iterator it = coursework.entrySet().iterator();
		while (it.hasNext()){
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + " :" + pair.getValue() + " percent");
		}
	}

	/*
		*
		*@return vacancy for this course
		*
	*/

	public int getVacancy(){
		return vacancy;
	}

	public int getVacancy(String index){
		return lessonManager.getVacancy(index);
	}

	public void register(){
		registered = true;
		vacancy--;
	}

	public void register(String index){
		registered = true;
		vacancy--;
		lessonManager.register(index);
	}
	/*
		*
		*method for adding a tutorial to the tutrial arraylist
		*@param tut: add to arraylist tuts 
		*
	*/

	/*public void addTut(Tutorial tut){
		tuts.add(tut);
	}
*/
	/*
		*
		*@return number of index
		*
	*/

	public int getSize(){
		return lessonManager.getSize();
	}

	/*
		*print all the index and its vacancy
	*/

	/*public void printIndex(){
		lessonManager.printLesson();
	}*/

	/*
		*
		*method to return if this course has this tutorial or not
		@param: String index of tutorial
		*
	*/

	/*public Boolean existTutorial(String index){
		for (Tutorial t : tuts){
			if (index.equals(t.getIndex())) return true;
		}
		return false;
	}*/

	/*
		*
		*method to find tutorial
		@param: String index used to find tutorial
		@return Tutorial object found inside Tutorials
		@return null if not found
		*
	*/

	/*public Tutorial findTutorial(String index){
		for (Tutorial t : tuts){
			if (index.equals(t.getIndex())) return t;
		}
		return null;
	}
*/
	/*
		*
		*add lab to the labs arraylist
		*@param lab
		*
	*/

	/*public void addLab(Lab lab){
		labs.add(lab);
	}
*/
	/*
		*
		*@return number of students inside registered to a lab
		*
	*/

	/*public int getSizeLab(){
		return labs.size();
	}*/

	/*
		*print index of a lab and its student
	*/

	/*public void printLab(){
		for (Lab l : labs){
			System.out.printf("Index: %s === Vacancy: %d\n", l.getIndex(), l.getVacancy());
		}
	}*/

	/*
		*
		*method to return if this course has this lab or not
		@param: String index of lab
		*
	*/

	/*public Boolean existLab(String index){
		for (Lab l : labs){
			if (index.equals(l.getIndex())) return true;
		}
		return false;
	}*/

	/*
		*
		*method to find Lab
		@param: String index used to find Lab
		@return Lab object found inside Labs
		@return null if not found
		*
	*/

	/*public Lab findLab(String index){
		for (Lab l : labs){
			if (index.equals(l.getIndex())) return l;
		}
		return null;
	}*/


	/*
		*
		*add student to course without tut and lab
		*@param stu: add to arraylist students 
		@return exit value:
		0: success
		2: no vacancy
		3: student already registered
		4: course cannot be registered because it has not added coursework weightage
		*
	*/

	/*public int addStudent(Student student){
		if (getExamWeightage() == 0 && getCourseworkWeightage().size() == 0){
			return 4;
		}
		if (vacancy <1 ) { // no vacancy
			return 2;
		}
		if (existStudent(student.getMatric())) return 3;//duplicate student
		vacancy--;
		students.put(student, "Lecture");
		return 0;
	}*/
	
	/*
		*
		*add student to course with tut and lab
		*@param student: add to students arraylist
		*@param index: index for adding student to tut and lab
		*
		@return an exit value:
		0: success
		1: wrong index passed
		2: index has no vacancy
		3: student already registered for this course
		4: course cannot be registered because it has not added coursework weightage
		*
	*/
	/*public int addStudent(Student student, String index){
		if (getExamWeightage() == 0 && getCourseworkWeightage().size() == 0){
			return 4;
		}
		Tutorial ttemp = findTutorial(index);
		Lab ltemp = findLab(index);

		if (ttemp == null && ltemp == null){ //either tut or lab cannot be found i.e wrong index
			return 1;
		}
		if (vacancy <1 || (ttemp.getVacancy() <1 && ltemp.getVacancy() <1)) { //no vacancy
			return 2;
		}
		if (existStudent(student.getMatric())) return 3;//student already registered
		vacancy--;
		ttemp.regTut(student);
		if (ltemp != null) ltemp.regLab(student);
		students.put(student, index);
		return 0;
	}
*/
	/*
		*
		*method to return if student has registered to this course or not
		@param: String matric of student
		*
	*/

	/*public Boolean existStudent(String matric){
		for (Student s : students.keySet()){
			if (matric.equals(s.getMatric())) return true;
		}
		return false;
	}*/

	/*
		*
		*method to find student
		@param: String matric used to find student
		@return Student object found inside students
		@return null if not found
		*
	*/

	/*public Student findStudent(String matric){
		for (Student s : students.keySet()){
			if (matric.equals(s.getMatric())) return s;
		}
		return null;
	}*/

	/*
		*
		*return number of student registered to a course
		*
	*/

	/*public int getSizeStudent(){
		return students.size();
	}*/

	/*
		*
		*print all student registered to this course
		*
	*/

	/*public void printStudent(){
		System.out.println("=========================================================");
		if (students.size() == 0){
			System.out.println(">>>>>>>>>>No student has registered for this course yet!<<<<<<<<<<");
			return;
		}
		if (!includeTut() && !includeLab()){
			for (Student s : students.keySet()){
				System.out.println("Student name: " + s.getName() +", Matric: " + s.getMatric());
			}
		}
		else {
			Iterator it = students.entrySet().iterator();
			while (it.hasNext()){
				Map.Entry pair = (Map.Entry) it.next();
				Student tmp = (Student) pair.getKey();
				System.out.println("Student name: " + tmp.getName() +", Matric: " + tmp.getMatric()
				 + ", Index: " + pair.getValue());
			}
		}
	}*/

	/*
		*
		*print all student registered to a course with a specific index
		*@param index: index for which student are registered to
		*
	*/

	/*public void printStudent(String index){
		System.out.println("=========================================================");
		if (students.size() == 0){
			System.out.println(">>>>>>>>>>No student has registered for this course yet!<<<<<<<<<<");
			return;
		}
		Tutorial ttemp = findTutorial(index);
		if (ttemp == null){ //neither tut or lab cannot be found i.e wrong index
			System.out.println(">>>>>>>>>>No index found<<<<<<<<<<");
			return;
		}
		else {
			System.out.println("=========================================================");
			ttemp.printStudent();
		}	
	}*/

	/*
		*
		*print out all indexes and their respective vancancy
		*
	*/

	public void printIndex(){
		if (!hasTut && !hasLab) return;
		lessonManager.printIndex();
	}

	public Boolean existIndex(String index){
		return lessonManager.existIndex(index);
	}

	/*
		*
		*print stats of this course
		*include all students registered to this course and their marks/5
		*
	*/

	/*public void printStats(){
		if (students.size() == 0){
			System.out.println(">>>>>>>>>>No student registered for this course yet!<<<<<<<<<<");
			return;
		}
		System.out.println("       ====================Course Statistics========================");
		for (Student s : students.keySet()){
			System.out.print("Student name: " + s.getName() + ", Mark for this course: " + s.getMark(this)/20 + "/5");

			//printing parts of grades
			if (s.getExam().get(this.getCode()) != null){
					System.out.print(", Exam: " + s.getExam().get(this.getCode()));
				}
			if (s.getCoursework().get(this.getCode()) != null){
				Map <String, Integer> cwg = (Map <String, Integer>) s.getCoursework().get(this.getCode());
				Iterator it = cwg.entrySet().iterator();
				while (it.hasNext()){
					Map.Entry pair = (Map.Entry) it.next();
					System.out.print(", " + pair.getKey() + ": " + pair.getValue());
				}
			}
			System.out.println();
		}
	}*/

	/*
		*
		*method to add index to this course
		*each index has the same vacancy
		*each index include at least 1 tut; lab is not compulsory
		*1 index corresponds with 1 tut (and lab if this course has lab) inside the tuts (and labs) arraylist
		*
		*@return an exit value:
		*0: success
		*1: Invalid input
		*2: number of vacancy for indexes and vacancy for course don't match
		*-1: unknown error
	*/

	/*public int addIndex(){
		Scanner sc = new Scanner(System.in);
		try{
			System.out.print("Enter number of index(es) for " +getCode() +": ");
			int num = sc.nextInt();//take in number of index
			if (num <1){
				throw new InputMismatchException(">>>>>>>>>>Number of index(es) cannot be negative<<<<<<<<<");
			} 
			System.out.print("Enter vacancy for each index (each index has equal vacancy): ");
			int vacancy = sc.nextInt();//take in vacancy for each index
			if (vacancy < 1){
				throw new InputMismatchException(">>>>>>>>>>Negative Vacancy!<<<<<<<<<<<");
			}
			if (vacancy *num != getVacancy()) return 2; // number of vacancy does not match		
			for (int i=0; i<num; i++){
				System.out.print("Enter index for index number "+(i+1) + ": ");
				String index = sc.next();//take in index
				if (hasTut) addTut(new Tutorial(index, vacancy));//adding tut 
				if (hasLab) addLab(new Lab(index, vacancy));//adding lab
			}
		} catch(InputMismatchException e){
			sc.nextLine();//clearing the input buffer
			System.out.println(">>>>>>>>>>>Invalid Input!<<<<<<<<<<<");
			System.out.println(e.getMessage());
			return 1;
		} catch(Exception e){
			System.out.println(">>>>>>>>>>>Error!!<<<<<<<<<<<");
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
*/
	/*
		*
		*method for adding weightage to this course
		*including exam weightage and coursework weightage
		*allow exam to have 100% weightage or coursework to have 100% wieghtage
		*allow to change weightage when there is no student registered yet
		*
		*@return an exit value
		*0: sucessful
		*1: Input mismatch or invalid input
		*2: weightages not add up to 100
		*3: Not update
		*-1: unknown error
		*
	*/

	public int addWeightage(){
		Scanner sc = new Scanner(System.in);
		int sum =0;
		int tmp =0;
		Map<String, Integer> courseworktmp = new HashMap<String, Integer>();
		String choice = "";
		try {
			if (getCourseworkWeightage().size() > 0 || getExamWeightage() > 0){ //coursework weightage already added
					System.out.print("Coursework weightage for this course has already been added.\nDo you want to update it? (y/n): ");
					choice = sc.nextLine();
					if (choice.equals("n") || choice.equals("N")) return 3;
					else if (choice.equals("y") || choice.equals("Y"));
					else throw new InputMismatchException(">>>>>>>>>>>Please only type y or n!<<<<<<<<<<<\n\n\n");
			}

			System.out.print("Enter exam weightage(%): ");
			tmp = sc.nextInt();//take in exam weightage
			if (tmp <0 || tmp >100){
				throw new InputMismatchException(">>>>>>>>>>>Exam weightage cannot be negative or larger than 100!<<<<<<<<<<\n\n\n");
			}
			System.out.print("Enter number of component in coursework: ");
			int compo = sc.nextInt();
			if (compo < 0){
				throw new InputMismatchException(">>>>>>>>>>Number of component cannot be negative!<<<<<<<<<<\n\n\n");
			}
			for (int i =0; i<compo; i++){
				System.out.print("Enter component name: ");
				String component = sc.next();//take in component of coursework name
				System.out.print("Enter component weightage(%): ");
				int weight = sc.nextInt();//take in the component's weightage
				if (weight < 1 || weight > 100){
					throw new InputMismatchException(">>>>>>>>>>>Component weightage cannot be negative or larger than 100!<<<<<<<<<<\n\n\n");
				}
				courseworktmp.put(component, weight);
			}
		} catch(InputMismatchException e){
			System.out.println(">>>>>>>>>>Invalic Input!<<<<<<<<<<");
			System.out.println(e.getMessage());
			return 1;
		} catch(Exception e){
			System.out.println(">>>>>>>>>>Error!<<<<<<<<<<");
			return -1;
		} 
		// check if weighatge add up to 100%
		for (int v : courseworktmp.values()){
			sum += v;
		}

		if (tmp + sum != 100){
			return 2;
		}
		exam = tmp;
		coursework = courseworktmp;
		return 0;
	}

	/*
		*
		*method for checking if 2 course objects is the same based on their course code
		*
	*/

	public boolean equals(Object o){
		if (o instanceof Course){
			Course p = (Course)o;
			return (getCode().equals(p.getCode()));
		}
		return false;
	}

/*
	*
	*method is called whenever a student is modified (adding exam and/or coursework mark)
	*@param student: student who is recently modified
	*
	*For some reasons when using serialize to write to files and read back,
	*students object inside students arraylist in university and student object inside students dictionary in course
	*point to different objects after reading back, even though they point to the same previously
	*
	*So there's a case when a student is added to a course
	*Then the app is saved and exit
	*After that the marks of that student is updated, but the mark of student inside course is not i.e no mark
	*
	*Because the mark updated is of the student in university, not the student inside course
	*
	*This won't happen if adding the student to a course and adding marks of that student for that course 
	*happen in one instance without exitting the app
	*
*/

	/*public void updateStudent(Student student){
		String index ="";
		for (Student s : students.keySet()){
			if (s.equals(student)){
				index = students.get(s);
				students.remove(s);
				students.put(student, index);
				break;
			}
		}
		if (includeTut()){
			for (Tutorial t : tuts){
				if (t.getIndex().equals(index)){
					t.updateStudent(student);
					break;
				}
			}
		}
		if (includeLab()){
			for (Lab l : labs){
				if (l.getIndex().equals(index)){
					l.updateStudent(student);
					break;
				}
			}
		}
	}*/

}