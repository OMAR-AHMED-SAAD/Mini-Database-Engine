package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import applicationModules.Page;
import applicationModules.Table;
import exceptions.DBAppException;

public class loojiPleaseRead {
	public static void main(String[] args) throws FileNotFoundException, IOException, DBAppException {
		DBApp db=new DBApp(); 
		System.out.println(db.CreatedTables.toString());
		String FilePath = db.CreatedTables.get("Club");
		Table table = db.LoadTable(FilePath);
		table.ReadMetaData();
		System.out.println(table.toString());
		
		for(int i=0;i<table.TablePages.size();i++) {
			 FilePath=table.PageFilePath.get(table.TablePages.get(i));
		Page page=table.LoadPage(FilePath);
		System.out.println(page.toString());
		}
	}
}
