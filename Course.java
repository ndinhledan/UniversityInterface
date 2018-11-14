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
		*@return number of index
		*
	*/

	public int getSize(){
		return lessonManager.getSize();
	}

	
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

	public void addWeightage(int exam, Map coursework){
		this.exam = exam;
		this.coursework = (HashMap<String, Integer>) coursework;
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

}