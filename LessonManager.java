///////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////
/////Class to implement lesson manager controls lesson, includes
/////Hashmap of lessons
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


public class LessonManager implements Serializable{
	private Map<String, Integer> lessons = new HashMap<String, Integer>(); // {index: vacancy}
	private static final long serialVersionUID = -3914670736074682579L;

	public int getSize(){
		return lessons.size();
	}

	public void printIndex(){
		Iterator it = lessons.entrySet().iterator();
			while (it.hasNext()){
				Map.Entry pair = (Map.Entry) it.next();
				System.out.printf("Index: %s === Vacancy: %d\n", pair.getKey(), pair.getValue());
			}
	}

	public Boolean existIndex(String index){
		if (lessons.get(index) == null) return false;
		else return true;
	}

	public void register(String index){
		lessons.put(index, lessons.get(index) - 1);
	}

	public int getVacancy(String index){
		return lessons.get(index);
	}

	/*
		*
		*method to add index to the course
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

	public int addIndex(Boolean hasTut, Boolean hasLab, String code, int vacancy){
		Scanner sc = new Scanner(System.in);
		Map<String, Integer> tmp = new HashMap<String, Integer>();
		try{ 
			System.out.print("Enter number of index(es) for " + code +": ");
			int num = sc.nextInt();//take in number of index
			if (num <1){
				throw new InputMismatchException(">>>>>>>>>>Number of index(es) cannot be negative<<<<<<<<<");
			}
			System.out.print("Enter vacancy for each index (each index has equal vacancy): ");
			int vacancyi = sc.nextInt();//take in vacancy for each index
			if (vacancyi < 1){
				throw new InputMismatchException(">>>>>>>>>>Negative Vacancy!<<<<<<<<<<<");
			}
			if (vacancyi *num != vacancy) return 2; // number of vacancy does not match		
			for (int i=0; i<num; i++){
				System.out.print("Enter index for index number "+(i+1) + ": ");
				String index = sc.next();//take in index
				tmp.put(index, vacancyi);
				}
		} catch(InputMismatchException e){
			sc.nextLine();//clearing the input buffer
			System.out.println(">>>>>>>>>>>Invalid Input!<<<<<<<<<<<");
			System.out.println(e.getMessage());
			return 1;
		} catch(Exception e){
			sc.nextLine();//clearing the input buffer
			System.out.println(">>>>>>>>>>>Error!!<<<<<<<<<<<");
			e.printStackTrace();
			return -1;
		}
		lessons = tmp;
		return 0;
	}
}