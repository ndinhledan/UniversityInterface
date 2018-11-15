import java.io.IOException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;


public class SerializeDB{
	
	public static List read(String filename) throws IOException, ClassNotFoundException{
		List details = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			details = (ArrayList) in.readObject();
			in.close();
		}catch(EOFException ex){
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new IOException("File error in " + filename);
		} catch (ClassNotFoundException ex) {
			throw new ClassNotFoundException("No class found!");
		}
		return details;
	}

	public static void write(String filename, List list) throws IOException{
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		//	System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new IOException("File error!");
		}
	}

	/*public static void main(String[] args) {
		List list;
		List list1;
		List list2;
		List list3;
		try	{
				list = new ArrayList<Course>();
				list2 = new ArrayList<Student>();
				list3 = new ArrayList<Record>();
				// read from serialized file the list of professors
				//list1 = (ArrayList)SerializeDB.readSerializedObject("Professor.dat");
				//for (int i = 0 ; i < list.size() ; i++) {
				//	Professor p = (Professor)list.get(i);
				//	System.out.println("name is " + p.getName() );
				//	System.out.println("contact is " + p.getContact() );
				//}
				//Professor p1 = (Professor) list1.get(0);
				//Professor p2 = (Professor) list1.get(1);
				//Professor p3 = (Professor) list1.get(2);
				// write to serialized file - update/insert/delete
				// example - add one more professor
				//public Course(String code, String name, Boolean hasTut, Boolean hasLab, Professor coordinator, int vacancy)
				//Course c1 = new Course("CZ2002", "OOP", false, false, p1, 20);
				//Course c2 = new Course("CZ2004", "HCI", true, false, p2, 20);
				//Course c3 = new Course("CZ2005","OS", true, true, p3, 20);

				//Student s1 = new Student("Danny", "U1720241A");
				//Student s2 = new Student("Le Dan", "G1728185U");
				//Student s3 = new Student("DanDanDan", "86713293");
				
				//public Course(String code, String name, Boolean hasTut, Boolean hasLab, Professor coordinator, int vacancy){
				// add to list
				//list.add(c1);
				//list.add(c2);
				//list.add(c3);
				//list2.add(s1);
				//list2.add(s2);
				//list2.add(s3);
				
				// list.remove(p);  // remove if p equals object in the list

				SerializeDB.writeSerializedObject("Course.dat", list);
				SerializeDB.writeSerializedObject("Student.dat", list2);
				SerializeDB.writeSerializedObject("Record.dat", list3);
				


		}  catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
		}
	}*/
} 