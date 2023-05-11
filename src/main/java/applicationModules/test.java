package applicationModules;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

import application.DBApp;
import basicTools.ValidatorI;
import exceptions.DBAppException;

public class test implements ValidatorI {
	public static void main(String[] args) throws DBAppException {
		DBApp db = new DBApp();
		db.init();
		Hashtable<String, String> htblColNameType = new Hashtable<String, String>();
		htblColNameType.put("id", "java.lang.String");
		htblColNameType.put("first_name", "java.lang.String");
		htblColNameType.put("last_name", "java.lang.String");
		htblColNameType.put("dob", "java.util.Date");
		htblColNameType.put("gpa", "java.lang.Double");
		Hashtable<String, Object> minValues = new Hashtable<>();
		minValues.put("id", V.tryParse("43-0000", htblColNameType.get("id")));
		minValues.put("dob", V.tryParse("1990-01-01", htblColNameType.get("dob")));
		minValues.put("gpa", V.tryParse("0.7", htblColNameType.get("gpa")));

		Hashtable<String, Object> maxValues = new Hashtable<>();
		maxValues.put("id", V.tryParse("99-9999", htblColNameType.get("id")));
		maxValues.put("dob", V.tryParse("2000-12-31", htblColNameType.get("dob")));
		maxValues.put("gpa", V.tryParse("5.0", htblColNameType.get("gpa")));

		Octree o = new Octree("students2", "id", "dob", "gpa", minValues,maxValues);
		String FilePath = db.getCreatedTables().get("students");
		Table table = db.LoadTable(FilePath);
		table.ReadMetaData();
		Hashtable<String, Object> row = new Hashtable<>();
		row.put("id", "46-5041");
		row.put("dob", new Date(1990 - 1900, 12 - 1, 18));
		// db.insertIntoTable("students2", row);
		row = new Hashtable<>();
		row.put("id", "49-5041");
		// db.insertIntoTable("students2", row);
		o.populate(table);
		Hashtable<String, Object> tuple = new Hashtable<>();

		tuple.put("id", "52-3564");// "64-2646");
		tuple.put("dob", new Date(1991 - 1900, 8 - 1, 3));
		tuple.put("gpa", new Double(4.15));

//		tuple.put("id", "49-5041");// "64-2646");
//		tuple.put("dob", new Date(1994 - 1900, 12 - 1, 18));
//		tuple.put("gpa", new Double(2.54));
//		o.delete(o, tuple, "src/main/DBFiles/Pages/students2Page32.bin");

		// o.updatePageRef(tuple, "src/main/DBFiles/Pages/students2Page32.bin",
		// "src/main/DBFiles/Pages/ashdbjasb.bin");
//		System.out.println(o.search(tuple));
		long x = System.currentTimeMillis();
		System.out.println(o);
		long y = System.currentTimeMillis();
		System.out.println(y - x);
	}

//	public static void main(String[] args) throws DBAppException, IOException {
//		DBApp db=new DBApp();
//		db.init();
//		  String tableName = "students2";
//
//	        Hashtable<String, String> htblColNameType = new Hashtable<String, String>();
//	        htblColNameType.put("id", "java.lang.String");
//	        htblColNameType.put("first_name", "java.lang.String");
//	        htblColNameType.put("last_name", "java.lang.String");
//	        htblColNameType.put("dob", "java.util.Date");
//	        htblColNameType.put("gpa", "java.lang.Double");
//
//	        Hashtable<String, String> minValues = new Hashtable<>();
//	        minValues.put("id", "43-0000");
//	        minValues.put("first_name", "AAAAAA");
//	        minValues.put("last_name", "AAAAAA");
//	        minValues.put("dob", "1990-01-01");
//	        minValues.put("gpa", "0.7");
//
//	        Hashtable<String, String> maxValues = new Hashtable<>();
//	        maxValues.put("id", "99-9999");
//	        maxValues.put("first_name", "zzzzzz");
//	        maxValues.put("last_name", "zzzzzz");
//	        maxValues.put("dob", "2000-12-31");
//	        maxValues.put("gpa", "5.0");
//
//	       db.createTable(tableName, "id", htblColNameType, minValues, maxValues);
//        
//	        BufferedReader studentsTable = new BufferedReader(new FileReader("src/main/resources/students_table 2.csv"));
//	        String record;
//	     
//
//	        Hashtable<String, Object> row = new Hashtable<>();
//	        while ((record = studentsTable.readLine()) != null ) {
//	            String[] fields = record.split(",");
//
//	            row.put("id", fields[0]);
//	            row.put("first_name", fields[1]);
//	            row.put("last_name", fields[2]);
//
//	            int year = Integer.parseInt(fields[3].trim().substring(0, 4));
//	            int month = Integer.parseInt(fields[3].trim().substring(5, 7));
//	            int day = Integer.parseInt(fields[3].trim().substring(8));
//
//	            Date dob = new Date(year - 1900, month - 1, day);
//	            row.put("dob", dob);
//
//	            double gpa = Double.parseDouble(fields[4].trim());
//
//	            row.put("gpa", gpa);
//	            //long start = System.currentTimeMillis();
//	            db.insertIntoTable("students2", row);
//	            //long end = System.currentTimeMillis();
//	            //long elapsedTime = end - start;
//	            //System.out.println("Elapsed Time : "+ elapsedTime);
//	            row.clear();
//	           
//	            }
//	        
//	        studentsTable.close();
//	}
}
