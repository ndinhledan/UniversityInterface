import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.Serializable;
import java.io.IOException;

public class ProfessorManager extends SerializeDB{
	private List<Professor> profs = new ArrayList<Professor>();

	/*
		*
		*method to check if a professor existed or not
		@param ID: String, used to determine professor
		*
	*/

	public Boolean existProfessor(String id){
		for (Professor p : profs){
			if (id.equals(p.getID())) return true;
		}
		return false;
	}

	/*
		*
		*method to find Professor inside the list
		@param id: String, used to determine Professor
		@return: Professor
		*return the found Professor
		*if Professor not found return null
	*/

	public Professor findProfessor(String id){
		for (Professor p : profs){
			if (id.equals(p.getID())) return p;
		}
		return null;
	}

	/*
		*
		*method for printing all professors in university
		*
	*/

	public void printProfessor(){
		System.out.println("          ==========Professors=========");
		for (Professor p : profs){
			System.out.println("Name: "+p.getName() + ", ID: "+p.getID());
		}
	}

	public void read(){
		String profFile = "Professor.dat";
			profs = (ArrayList<Professor>) super(profFile);
			System.out.println("Professors read succssfullly");
	}
}