package application;

import java.util.Hashtable;
import java.util.Iterator;

import exceptions.DBAppException;

public class LojiPleaseSelect {
	public static void main(String[] args) throws DBAppException {
		DBApp db=new DBApp();
		db.init();
		Iterator Iter=db.parseSQL(new StringBuffer("Select * from Students Where id=7 AND first_name='Liam' AND dob='1996-01-10';"));
while (Iter.hasNext()) {
	System.out.println(Iter.next());
	
}
		//create Index
//		String [] indexColumns= {"id","first_name","dob"};
//		db.createIndex("students", indexColumns);
	}
}
