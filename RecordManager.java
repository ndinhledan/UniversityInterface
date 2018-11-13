import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.Serializable;
import java.io.IOException;

public class RecordManager{
	private List<Record> records = new ArrayList<Record>();

	public Boolean existRecord (String student){
		for (Record r : records){
			if (student.equals(r.getStudent())) return true;
		}
		return false;
	}

	public Record findRecord (String student){
		for (Record r : records){
			if (student.equals(r.getStudent())) return r;
		}
		return null;
	}

	public List getStudent(String course){
		List<String> students = new ArrayList<String>();
		for (Record rec : records){
			if (rec.existCourse(course)) students.add(rec.getStudent());
		}
		return students;
	}

	public List getStudent(String course, String index){
		List<String> students = new ArrayList<String>();
		for (Record rec : records){
			if (rec.existCourse(course, index)) students.add(rec.getStudent());
		}
		return students;
	}

	public List getCourse(String student){
		return findRecord(student).getCourse();
	}

	/*
		*
		@return 0: Success
		@return 1: Student already register
		*
	*/

	public int addRecord (String student, String course){
		Record record = findRecord(student);
		if (record == null) record = new Record(student);
		if (record.existCourse(course)) return 1;
		record.addCourse(course);
		if (!existRecord(student)) records.add(record);	 
		return 0;
	}

	/*
		*
		@return 0: Success
		@return 1: Student already register
		*
	*/

	public int addRecord (String student, String course, String index){
		Record record = findRecord(student);
		if (record == null) record = new Record(student);
		if (record.existCourse(course)) return 1;
		record.addCourse(course, index);
		if (!existRecord(student)) records.add(record);
		return 0;
	}

	public int addExamMark(String student, Course course){
		Scanner sc = new Scanner(System.in);
		Record rec = findRecord(student);
		if (!rec.existCourse(course.getCode())) return 2; //student not register to course
		if (rec.addedExam(course.getCode())) return 3; // exam added already
		if (course.getExamWeightage() == 0) return 4; //course does not have exam
		int grade = 0;
		try {
			System.out.print("Enter exam mark for " + course.getCode() + " /100: ");
			grade = sc.nextInt();
			if (grade < 1 || grade > 100){
				throw new InputMismatchException(">>>>>>>>>>Exam mark cannot be negative or larger than 100<<<<<<<<<");
			}
		}catch(InputMismatchException e){
			System.out.println(e.getMessage());
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		rec.addExam(course.getCode(), grade);
		return 0;
	}

	public int addCourseworkMark(String student, Course course){
		Record rec = findRecord(student);
		if (!rec.existCourse(course.getCode())) return 2; //student not register to course
		if (rec.addedCoursework(course.getCode())) return 3; // coursework added already
		if (course.getExamWeightage() == 100) return 4;//course does not have coursework
		Map<String, Integer> cw = new HashMap<String, Integer>(); //new dictionary to store {component coursework: mark}
		Scanner sc = new Scanner(System.in);
		
		for (String c : course.getCourseworkWeightage().keySet()){ //get component name e.g. Essay
			try {
				System.out.printf("Enter mark for %s/100: ", c);
				int mark = sc.nextInt();
				if (mark < 1 || mark > 100){
					throw new InputMismatchException(">>>>>>>>>>Component marks cannot be negative or larger than 100!<<<<<<<<<<\n\n\n");
				}
				cw.put(c, mark);
			} catch(InputMismatchException e){
				System.out.println(e.getMessage());
				return 1; //invalid input
			} catch(Exception e){
				e.printStackTrace();
				return -1;
			}
		}
		rec.addCoursework(course.getCode(), cw);
		return 0;
	}

	public double getGrade(String student, Course course){
		Record rec = findRecord(student);
		Map<String, Integer> courseworkw = (HashMap<String, Integer>) course.getCourseworkWeightage();
		if (!rec.existCourse(course.getCode())) return -1;
		if (!rec.addedCoursework(course.getCode()) || !rec.addedExam(course.getCode())) return -1;
		double result = 0;
		if (course.getExamWeightage() != 0){
			result += rec.getExam(course.getCode())*course.getExamWeightage()/100;	
		}
		if (courseworkw.size() != 0){ // if coursework has weightage 
			Map<String, Integer> courseworkg = (HashMap<String, Integer>) rec.getMarks(course.getCode());
			Iterator itw = courseworkw.entrySet().iterator();
			while (itw.hasNext()){
				Map.Entry pair = (Map.Entry) itw.next();
				String component = (String) pair.getKey();
				if (component.equals("Exam")) continue;
				Integer cgrade = courseworkg.get(component);
				Integer w = (Integer) pair.getValue();
				result += (cgrade*w)/100;
			}
		}
		return result/20;
	}

	public Map getStats(Course course){
		Map<String, Map<String, Integer>> stats = new HashMap<String, Map<String, Integer>>();
		for (Record rec : records){
			if (!rec.existCourse(course.getCode())) continue;
			if (!rec.addedCoursework(course.getCode()) || !rec.addedExam(course.getCode())) continue;
			Map<String, Integer> courseworkg = (HashMap<String, Integer>) rec.getMarks(course.getCode());
			stats.put(rec.getStudent(), courseworkg);
		}
		return stats;
	}

	public void read(String file){
		try { // reading in data from files
			records = (ArrayList<Record>) SerializeDB.readSerializedObject(file);
			System.out.println("Records read succssfullly");
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
			SerializeDB.writeSerializedObject(file, records);
			System.out.println("Records saved successfully");
		}catch(IOException e){
			System.out.println(">>>>>>>>>>File Records Error<<<<<<<<<<");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}