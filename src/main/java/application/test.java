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
		
		//db.createTable(tblName, "id", NameType, min, max);
		
		Hashtable<String, Object> htblColNameValue1 = new Hashtable<String, Object>();
		htblColNameValue1.put("id", new Integer(1));
		htblColNameValue1.put("name", new String("Ahmed"));
		htblColNameValue1.put("gpa", new Double(0.69));
	
		Hashtable<String, Object> htblColNameValue2 = new Hashtable<String, Object>();
		htblColNameValue2.put("id", new Integer(2));
		htblColNameValue2.put("name", new String("Loji"));
		htblColNameValue2.put("gpa", new Double(1.0));
	
		Hashtable<String, Object> htblColNameValue3 = new Hashtable<String, Object>();
		htblColNameValue3.put("id", new Integer(3));
		htblColNameValue3.put("name", new String("Hamada"));
		htblColNameValue3.put("gpa", new Double(0.85));
		
//		db.insertIntoTable(tblName,htblColNameValue1);
//		db.insertIntoTable(tblName,htblColNameValue2);
//		db.insertIntoTable(tblName,htblColNameValue3);
		
		
//		// delete tuples
//		 htblColNameValue3.remove("id");
//		htblColNameValue3.remove("gpa");
//		db.deleteFromTable(tblName,htblColNameValue3);
//	
		// update tuples
		htblColNameValue2.remove("id");
		htblColNameValue3.remove("gpa");
		htblColNameValue3.put("gpa", new Double(0.75));
		db.updateTable(tblName,"1", htblColNameValue2);
	
	}
}
