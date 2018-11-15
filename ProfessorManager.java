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
	private static final String profFile = "Professor.dat";

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
		System.out.printf("%-25s %23s", "Professor Name", "ID\n");
		System.out.println("------------------------------------------------------");
		
		for (Professor p : profs){
			System.out.printf("%-25s %25s \n" ,p.getName(), p.getID());
		}
	}

	public void read(){
		try{	
			profs = (ArrayList<Professor>) readDB(profFile);
			System.out.println("Professors read succssfullly");
		} catch (IOException e){
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
	}
}