///////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////
/////Class to implement student object, includes
/////String Name
/////String Matric
////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////


import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.Serializable;


public class Student implements Serializable{
	private String name;
	private String matric;
	private static final long serialVersionUID = -3914670736074682579L;

	public Student(String name, String matric){
		this.name = name;
		this.matric = matric;
	}

	public Student(){};

	public String getName(){
		return name;
	}

	public String getMatric(){
		return matric;
	}

	public boolean equals(Object o){
		if (o instanceof Student){
			Student p = (Student)o;
			return (getMatric().equals(p.getMatric()));
		}
		return false;
	}
}