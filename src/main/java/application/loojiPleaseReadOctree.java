package application;

import applicationModules.OctreeDescription;
import applicationModules.Table;
import exceptions.DBAppException;

public class loojiPleaseReadOctree {
	public static void main(String[] args) throws DBAppException {
		DBApp db = new DBApp();
		db.init();
		System.out.println(db.getCreatedTables().toString());
		String FilePath = db.getCreatedTables().get("students");
		Table table = db.LoadTable(FilePath);
		table.ReadMetaData();
		for(OctreeDescription od:table.getOctrees()) 
			System.out.println(table.LoadOctree(od.getFilePath()));
	}
}
