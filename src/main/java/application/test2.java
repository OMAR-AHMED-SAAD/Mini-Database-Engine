package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

import exceptions.DBAppException;

public class test2 {
	public static void main(String[] args) throws FileNotFoundException, IOException, DBAppException {
		String tblName = "Student3";
		Hashtable<String, String> NameType = new Hashtable<String, String>();
		NameType.put("id", "java.lang.Integer");
		NameType.put("name", "java.lang.String");
		NameType.put("gpa", "java.lang.double");
		NameType.put("birth", "java.util.date");
		Hashtable<String, String> min = new Hashtable<String, String>();
		min.put("id1", "0");
		min.put("name", "A");
		min.put("gpa", "0");
		min.put("birth", "2000-11-01");
		Hashtable<String, String> max = new Hashtable<String, String>();
		max.put("id", "1000");
		max.put("name", "ZZZZZZZZZZZZ");
		max.put("gpa", "1000");
		max.put("birth", "2023-10-01");
//
		DBApp db = new DBApp();
//
		db.createTable(tblName, "id", NameType, min, max);
//
		Hashtable<String, Object> htblColNameValue1 = new Hashtable<String, Object>();
		htblColNameValue1.put("id", new Integer(2));
		htblColNameValue1.put("name", new String("Ahmed"));
		htblColNameValue1.put("gpa", new Double(0.69));
		htblColNameValue1.put("birth", new Date(2002 - 14 - 01));
//
		Hashtable<String, Object> htblColNameValue2 = new Hashtable<String, Object>();
		htblColNameValue2.put("id", new Integer(2));
		htblColNameValue2.put("name", new String("Loji"));
		htblColNameValue2.put("gpa", new Double(1.0));
//
		Hashtable<String, Object> htblColNameValue3 = new Hashtable<String, Object>();
		htblColNameValue3.put("id", new Integer(7));
		htblColNameValue3.put("name", new String("Hamada"));
		htblColNameValue3.put("gpa", new Double(0.85));
//
		Hashtable<String, Object> htblColNameValue4 = new Hashtable<String, Object>();
		htblColNameValue4.put("id", new Integer(4));
		htblColNameValue4.put("name", new String("Ali"));
		htblColNameValue4.put("gpa", new Double(1.0));
//
//		Hashtable<String, Object> htblColNameValue5 = new Hashtable<String, Object>();
//		htblColNameValue5.put("id", new Integer(5));
//		htblColNameValue5.put("name", new String("Nelly"));
//		htblColNameValue5.put("gpa", new Double(2.0));
//
		// db.insertIntoTable(tblName,htblColNameValue1);
// db.insertIntoTable(tblName,htblColNameValue2);
// db.insertIntoTable(tblName,htblColNameValue3);
// db.insertIntoTable(tblName,htblColNameValue4);
		// db.insertIntoTable(tblName,htblColNameValue5);
//
//		// delete tuples
////		htblColNameValue2.remove("id");
////		htblColNameValue2.remove("name");
////		htblColNameValue2.remove("gpa");
////		db.deleteFromTable(tblName, htblColNameValue2);
//
		// update tuples
//		htblColNameValue2.remove("id");
//		htblColNameValue2.remove("gpa");
//		htblColNameValue2.remove("name");
//		htblColNameValue2.put("gpa", new Double(0.78));
//		db.updateTable(tblName,"2", htblColNameValue2);

	}
}
