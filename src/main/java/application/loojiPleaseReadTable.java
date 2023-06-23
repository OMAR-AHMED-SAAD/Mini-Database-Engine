package application;
//package TRASH;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import applicationModules.Table;
import exceptions.DBAppException;

public class loojiPleaseReadTable {
	public static void main(String[] args) throws FileNotFoundException, IOException, DBAppException {
		DBApp db = new DBApp();
		db.init();
		System.out.println(db.getCreatedTables().toString());
		String FilePath = db.getCreatedTables().get("employees2");
		Table table =loojiPleaseReadTable.LoadTable(FilePath);
		table.ReadMetaData();
		System.out.println(table.toString());
	}

	public static Table LoadTable(String FilePath) throws DBAppException {
	    Table RestoredTable = null;
	    try {
	        InputStream inputStream = DBApp.class.getClassLoader().getResourceAsStream("Tables/employees2.bin");
	        if (inputStream == null) {
	            throw new FileNotFoundException("Resource not found: " + FilePath);
	        }
	        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
	        RestoredTable = (Table) objectInputStream.readObject();
	        objectInputStream.close();
	    } catch (Exception e) {
	        throw new DBAppException(e.getMessage());
	    }
	    return RestoredTable;
	}

}
