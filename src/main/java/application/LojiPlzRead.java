package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import applicationModules.Page;
import applicationModules.Table;

public class LojiPlzRead {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		DBApp db=new DBApp(); 
		System.out.println(db.CreatedTables.toString());
		Table t = new Table("student");
		try {
			Page p = t.LoadPage("src/main/DBFiles/Pages/StudentPage0.bin");
			for (Hashtable<String, Object> x : p.getVecPage())
				System.out.println(x.get("id") + " " + x.get("name") + " " + x.get("gpa"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Page p = t.LoadPage("src/main/DBFiles/Pages/StudentPage1.bin");
			for (Hashtable<String, Object> x : p.getVecPage())
				System.out.println(x.get("id") + " " + x.get("name") + " " + x.get("gpa"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
