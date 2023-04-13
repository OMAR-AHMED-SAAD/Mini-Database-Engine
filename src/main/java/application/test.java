package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import exceptions.DBAppException;

public class test {
	public static void main(String[] args) throws FileNotFoundException, IOException, DBAppException {
		String tblName="Student";
		Hashtable<String, String> NameType = new Hashtable<String, String>();
		NameType.put("id", "java.lang.Integer");
		NameType.put("name", "java.lang.String");
		NameType.put("gpa", "java.lang.double");
		
		Hashtable<String, String> min = new Hashtable<String, String>();
		min.put("id", "0");
		min.put("name", "A");
		min.put("gpa", "0");

		Hashtable<String, String> max = new Hashtable<String, String>();
		max.put("id", "1000");
		max.put("name", "ZZZZZZZZZZZZ");
		max.put("gpa", "1000");

		DBApp db=new DBApp(); 
		
		db.createTable(tblName, "id", NameType, min, max);
		
	}
}
