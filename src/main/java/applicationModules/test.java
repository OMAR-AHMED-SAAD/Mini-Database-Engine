package applicationModules;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

import application.DBApp;
import exceptions.DBAppException;

public class test {
	public static void main(String[] args) throws DBAppException, IOException {
		DBApp db=new DBApp();
		db.init();
		  String tableName = "students";

	        Hashtable<String, String> htblColNameType = new Hashtable<String, String>();
	        htblColNameType.put("id", "java.lang.String");
	        htblColNameType.put("first_name", "java.lang.String");
	        htblColNameType.put("last_name", "java.lang.String");
	        htblColNameType.put("dob", "java.util.Date");
	        htblColNameType.put("gpa", "java.lang.Double");

	        Hashtable<String, String> minValues = new Hashtable<>();
	        minValues.put("id", "43-0000");
	        minValues.put("first_name", "AAAAAA");
	        minValues.put("last_name", "AAAAAA");
	        minValues.put("dob", "1990-01-01");
	        minValues.put("gpa", "0.7");

	        Hashtable<String, String> maxValues = new Hashtable<>();
	        maxValues.put("id", "99-9999");
	        maxValues.put("first_name", "zzzzzz");
	        maxValues.put("last_name", "zzzzzz");
	        maxValues.put("dob", "2000-12-31");
	        maxValues.put("gpa", "5.0");

	       // db.createTable(tableName, "id", htblColNameType, minValues, maxValues);
        
	        BufferedReader studentsTable = new BufferedReader(new FileReader("src/main/resources/students_table.csv"));
	        String record;
	     

	        Hashtable<String, Object> row = new Hashtable<>();
	        while ((record = studentsTable.readLine()) != null ) {
	            String[] fields = record.split(",");

	            row.put("id", fields[0]);
	            row.put("first_name", fields[1]);
	            row.put("last_name", fields[2]);

	            int year = Integer.parseInt(fields[3].trim().substring(0, 4));
	            int month = Integer.parseInt(fields[3].trim().substring(5, 7));
	            int day = Integer.parseInt(fields[3].trim().substring(8));

	            Date dob = new Date(year - 1900, month - 1, day);
	            row.put("dob", dob);

	            double gpa = Double.parseDouble(fields[4].trim());

	            row.put("gpa", gpa);
	            //long start = System.currentTimeMillis();
	            db.insertIntoTable("students", row);
	            //long end = System.currentTimeMillis();
	            //long elapsedTime = end - start;
	            //System.out.println("Elapsed Time : "+ elapsedTime);
	            row.clear();
	           
	            }
	        
	        studentsTable.close();
	}
}
