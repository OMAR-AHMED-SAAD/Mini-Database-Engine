package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import applicationModules.Table;
import exceptions.DBAppException;

public class loojiPleaseRead {
	public static void main(String[] args) throws FileNotFoundException, IOException, DBAppException {
		DBApp db = new DBApp();
		db.init();
		System.out.println(db.getCreatedTables().toString());
		String FilePath = db.getCreatedTables().get("students");
		Table table = db.LoadTable(FilePath);
		table.ReadMetaData();
		System.out.println(table.toString());
	}
}
