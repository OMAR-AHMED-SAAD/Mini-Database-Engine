package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import applicationModules.Table;
import dataManagement.ValidatorI;
import exceptions.DBAppException;

public class DBApp implements ValidatorI {
	private Hashtable<String, String> CreatedTables;

	public DBApp()  {
		init();
	}

	public void init() {
		try{
		ReadCreatedTables();
		}
		catch(DBAppException e) {
			System.out.println("DBAppException");
		}
	}

	@SuppressWarnings("unchecked")
	public void ReadCreatedTables() throws DBAppException {
		String FilePath = "src/main/DBFiles/CreatedTables.bin";
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FilePath));
			CreatedTables = (Hashtable<String, String>) objectInputStream.readObject();
			objectInputStream.close();

		} catch (FileNotFoundException e) {
			CreatedTables = new Hashtable<String, String>();
			WriteCreatedTables();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DBAppException();
		}
	}

	public void WriteCreatedTables() throws DBAppException {
		String FilePath = "src/main/DBFiles/CreatedTables.bin";
		try {
			ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(new FileOutputStream(FilePath));
			ObjectOutputStream.writeObject(CreatedTables);
			ObjectOutputStream.close();
		} catch (Exception e) {
			throw new DBAppException();
		}
	}

	public void createTable(String strTableName, String strClusteringKeyColumn,
			Hashtable<String, String> htblColNameType, Hashtable<String, String> htblColNameMin,
			Hashtable<String, String> htblColNameMax) throws DBAppException {
		if (CreatedTables.get(strTableName) != null)
			throw new DBAppException(strTableName + " already exists");
		else {
			ValidateMetaData(htblColNameType, htblColNameMin, htblColNameMax);
			AddMetaData(strTableName, strClusteringKeyColumn, htblColNameType, htblColNameMin, htblColNameMax);
			Table newTable = new Table(strTableName);
			String FilePath = "src/main/DBFiles/Tables/" + strTableName + ".bin";
			CreatedTables.put(strTableName, FilePath);
			WriteCreatedTables();
			UnLoadTable(newTable, FilePath);
		}
	}

	public void UnLoadTable(Table tbl, String FilePath) throws DBAppException {
		try {
			ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(new FileOutputStream(FilePath));
			ObjectOutputStream.writeObject(tbl);
			ObjectOutputStream.close();
		} catch (Exception e) {
			throw new DBAppException();
		}
	}

	private Table LoadTable(String FilePath) {
		Table RestoredTable = null;
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FilePath));
			RestoredTable = (Table) objectInputStream.readObject();
			objectInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RestoredTable;
	}

	public void ValidateMetaData(Hashtable<String, String> ColNameType, Hashtable<String, String> ColNameMin,
			Hashtable<String, String> ColNameMax) throws DBAppException {
		Enumeration<String> ColNameTypeKeys = ColNameType.keys();
		while (ColNameTypeKeys.hasMoreElements()) {
			String Key = ColNameTypeKeys.nextElement();
			String type = ColNameType.get(Key);
			String Min = ColNameMin.get(Key);
			String Max = ColNameMax.get(Key);
			V.checkTypeSupport(type);
			V.tryParse(Max, type);
			V.tryParse(Min, type);
		}
	}

	public void AddMetaData(String TblName, String ClusteringKey, Hashtable<String, String> ColNameType,
			Hashtable<String, String> ColNameMin, Hashtable<String, String> ColNameMax) throws DBAppException {
		String FilePath = "src/main/resources/metadata.csv";
		FileWriter fw;
		try {
			fw = new FileWriter(FilePath, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DBAppException();
		}
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		if (new File(FilePath).length()==0)
			pw.println("TableName" + "," + "Column Name" + "," + "Column Type" + "," + "ClusteringKey" + ","
					+ "IndexName" + "," + "IndexType" + "," + "min" + "," + "max");
		ColNameType
				.forEach((key, value) -> pw.println(TblName + "," + key + "," + value + "," + ClusteringKey.equals(key)
						+ "," + "null" + "," + "null" + "," + ColNameMin.get(key) + "," + ColNameMax.get(key)));
		pw.flush();
		pw.close();
	}

//following method creates an octree
//depending on the count of column names passed.
//If three column names are passed, create an octree.
//If only one or two column names is passed, throw an Exception.
	public void createIndex(String strTableName, String[] strarrColName) throws DBAppException {

	}

// following method inserts one row only.
//htblColNameValue must include a value for the primary key 
	public void insertIntoTable(String strTableName, Hashtable<String, Object> htblColNameValue) throws DBAppException {
		if (CreatedTables.get(strTableName) == null)
			throw new DBAppException(strTableName + " does not exists");
		String FilePath = CreatedTables.get(strTableName);
		Table Table = LoadTable(FilePath);
		Table.ReadMetaData();
		Table.ValidateInsert(htblColNameValue);
		Table.InsertInTable(htblColNameValue);
		UnLoadTable(Table, FilePath);
	}

//following method updates one row only
//htblColNameValue holds the key and new value
//htblColNameValue will not include clustering key as column name
//strClusteringKeyValue is the value to look for to find the row to update. 
	public void updateTable(String strTableName, String strClusteringKeyValue,
			Hashtable<String, Object> htblColNameValue) throws DBAppException {
		if (CreatedTables.get(strTableName) == null)
			throw new DBAppException(strTableName + " does not exists");
		String FilePath = CreatedTables.get(strTableName);
		Table Table = LoadTable(FilePath);
		Table.ReadMetaData();
		Table.ValidateUpdate(strClusteringKeyValue, htblColNameValue);
		Table.UpdateTbl(strClusteringKeyValue, htblColNameValue);
		UnLoadTable(Table, FilePath);
	}

//following method could be used to delete one or more rows.
//htblColNameValue holds the key and value. This will be used in search // to identify which rows/tuples to delete.
//htblColNameValue enteries are ANDED together
	public void deleteFromTable(String strTableName, Hashtable<String, Object> htblColNameValue) throws DBAppException {
		if (CreatedTables.get(strTableName) == null)
			throw new DBAppException(strTableName + " does not exists");
		String FilePath = CreatedTables.get(strTableName);
		Table Table = LoadTable(FilePath);
		Table.ReadMetaData();
		Table.ValidateDelete(htblColNameValue);
		Table.DelFromTbl(htblColNameValue);
		UnLoadTable(Table, FilePath);
	}

	@SuppressWarnings("rawtypes")
	public Iterator selectFromTable(SQLTerm[] arrSQLTerms, String[] strarrOperators) throws DBAppException {

		return null;
	}
}
