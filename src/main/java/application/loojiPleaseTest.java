package application;

import java.util.Date;
import java.util.Hashtable;

import exceptions.DBAppException;

public class loojiPleaseTest {
	public static void main(String[] args) throws DBAppException {
		DBApp db=new DBApp();
		db.init();
		//create Index
//		String [] indexColumns= {"gpa","first_name","dob"};
//		db.createIndex("students", indexColumns);
		Hashtable<String, Object> row=new Hashtable<>();
		row.put("id", 3);
		row.put("first_name","Charlotte");
		Date dob = new Date(1999 - 1900, 5 - 1, 10);
		row.put("dob", dob);
		row.put("gpa", 2.8);
		db.insertIntoTable("students", row);
		
				
			
	}
}
