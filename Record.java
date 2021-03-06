///////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////
/////Class to implement Record to store records of a student, including which student
/////register for which course, and marks for student 
/////
/////
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

public class Record implements Serializable{
	String student;//matric
	Map<String, String> register = new HashMap<String, String>(); //{course: index}
	Map<String, Map<String, Integer>> marks = new HashMap<String, Map<String, Integer>>(); 
	//{course: {component: mark}}
	private static final long serialVersionUID = -3914670736074682579L;

	public Record(String student){
		this.student = student;
	}

	public String getStudent(){
		return student;
	}

	/*
		*
		*register a course for a student by adding it into register
		*if course has no index then "lecture" is put in index
		*
	*/

	public void addCourse (String course){
		register.put(course, "Lecture");
	}

	public void addCourse(String course, String index){
		register.put(course, index);
	}
	/*
		*check if a student's record has a course registered
		*if index is passed in index is checked to
		*
	*/

	public Boolean existCourse(String course){
		for (String c : register.keySet()){
			if (c.equals(course)) return true;
		}
		return false;
	}

	public Boolean existCourse(String course, String index){
		Iterator it = register.entrySet().iterator();
		while (it.hasNext()){
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getKey().equals(course) && pair.getValue().equals(index)) return true;
		}
		return false;
	}

	/*
		*return a list of course from a record
	*/

	public List getCourse(){
		List<String> tmp = new ArrayList<String>();
		if (register.size() == 0) return tmp;
		for (String course : register.keySet()){
			tmp.add(course);
		}
		return tmp;
	}

	/*
		*add in exam mark for a record
	*/

	public void addExam(String course, int exam){
		if (marks.get(course) == null){
			Map<String, Integer> coursework = new HashMap<String, Integer>();
			coursework.put("Exam", exam);		
			marks.put(course, coursework);
		}
		else{
			Map<String, Integer> coursework = marks.get(course);
			coursework.put("Exam", exam);
			marks.put(course, coursework);
		}
	}
	/*
		*check if exam mark has been added for this record or not
	*/

	public Boolean addedExam(String course){
		Map<String, Integer> tmp = marks.get(course);
		if (tmp == null || tmp.get("Exam") == null) return false;
		else return true;
	}

	/*
		*return exam mark of a course from this record
	*/

	public int getExam(String course){
		Map<String, Integer> tmp = marks.get(course);
		return tmp.get("Exam");
	}

	/*
		*add coursework mark for a course for this record
	*/

	public void addCoursework(String course, Map cw){
		Map<String, Integer> coursework = (HashMap<String, Integer>) cw;
		if (marks.get(course) == null){
			marks.put(course, coursework);
		}
		else{
			coursework.put("Exam", marks.get(course).get("Exam"));
			marks.put(course, coursework);
		}
	}

	/*
		*check if coursework mark for a course has been added or not
	*/

	public Boolean addedCoursework(String course){
		Map<String, Integer> tmp = marks.get(course);
		if (tmp == null) return false;
		if (tmp.size() == 1 && tmp.get("Exam") != null) return false;
		else return true;
	}

	/*	
		*get all marks including exam and coursework
	*/

	public Map getMarks(String course){
		return marks.get(course);
	}
}