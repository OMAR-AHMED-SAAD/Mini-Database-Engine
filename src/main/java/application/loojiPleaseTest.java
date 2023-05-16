package application;

import java.util.Date;
import java.util.Hashtable;

import exceptions.DBAppException;

public class loojiPleaseTest {
	public static void main(String[] args) throws DBAppException {
		DBApp db=new DBApp();
		db.init();
		//create Index
//		String [] indexColumns= {"id","first_name","dob"};
//		db.createIndex("students", indexColumns);
		Hashtable<String, Object> row=new Hashtable<>();
//		row.put("id", 3);
//		row.put("first_name","Charlotte");
//		row.put("last_name","Abbas");
//		Date dob = new Date(1999 - 1900, 5 - 1, 10);
//		row.put("dob", dob);
//		row.put("gpa", 2.8);
//		db.insertIntoTable("students", row);
//		
		//row.put("id", 50);
//		row.put("first_name","Nello");
//		row.put("last_name","Hazem");
//		Date dob = new Date(1990 - 1900, 3 - 1, 13);
//		row.put("dob", dob);
		row.put("gpa", 0.7);
		//db.deleteFromTable("students", row);
		long start=System.currentTimeMillis();
		db.updateTable("students", "14", row);
		long end=System.currentTimeMillis();
		System.out.println(end-start);

	}
}
