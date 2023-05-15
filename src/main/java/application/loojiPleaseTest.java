package application;

import exceptions.DBAppException;

public class loojiPleaseTest {
	public static void main(String[] args) throws DBAppException {
		DBApp db=new DBApp();
		db.init();
		//create Index
//		String [] indexColumns= {"gpa","first_name","dob"};
//		db.createIndex("students", indexColumns);
	}
}
