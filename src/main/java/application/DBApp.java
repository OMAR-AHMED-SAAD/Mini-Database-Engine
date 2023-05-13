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
import java.util.Set;

import applicationModules.Table;
import basicTools.ValidatorI;
import exceptions.DBAppException;

public class DBApp implements ValidatorI {
	private Hashtable<String, String> CreatedTables;

	public void init() {
		try {
			ReadCreatedTables();
		} catch (DBAppException e) {
			System.out.println("DBAppException");
		}
	}

	@SuppressWarnings("unchecked")
	public void ReadCreatedTables() throws DBAppException {
		String FilePath = "src/main/resources/CreatedTables.bin";
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FilePath));
			CreatedTables = (Hashtable<String, String>) objectInputStream.readObject();
			objectInputStream.close();

		} catch (FileNotFoundException e) {
			CreatedTables = new Hashtable<String, String>();
			WriteCreatedTables();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DBAppException(e.getMessage());
		}
	}

	public void WriteCreatedTables() throws DBAppException {
		String FilePath = "src/main/resources/CreatedTables.bin";
		try {
			ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(new FileOutputStream(FilePath));
			ObjectOutputStream.writeObject(CreatedTables);
			ObjectOutputStream.close();
		} catch (Exception e) {
			throw new DBAppException(e.getMessage());
		}
	}

	public void createTable(String strTableName, String strClusteringKeyColumn,
			Hashtable<String, String> htblColNameType, Hashtable<String, String> htblColNameMin,
			Hashtable<String, String> htblColNameMax) throws DBAppException {
		strTableName = strTableName.toLowerCase();
		strClusteringKeyColumn = strClusteringKeyColumn.toLowerCase();
		htblColNameType = convertToSmallCase(htblColNameType);
		htblColNameMin = convertToSmallCase(htblColNameMin);
		htblColNameMax = convertToSmallCase(htblColNameMax);
		if (CreatedTables.get(strTableName) != null)
			throw new DBAppException(strTableName + " already exists");
		else if (htblColNameType.get(strClusteringKeyColumn) == null)
			throw new DBAppException("You should specify clustering key type ,min and max");
		else {
			ValidateMetaData(htblColNameType, htblColNameMin, htblColNameMax);
			AddMetaData(strTableName, strClusteringKeyColumn, htblColNameType, htblColNameMin, htblColNameMax);
			Table newTable = new Table(strTableName);
			for (String col : htblColNameType.keySet())
				newTable.getCreationOrder().add(col);
			String FilePath = "src/main/resources/Tables/" + strTableName + ".bin";
			CreatedTables.put(strTableName, FilePath);
			WriteCreatedTables();
			UnLoadTable(newTable, FilePath);
			newTable = null;
		}
	}

	private Hashtable<String, String> convertToSmallCase(Hashtable<String, String> htbl) {
		Hashtable<String, String> htblRes = new Hashtable<String, String>();
		Enumeration<String> keys = htbl.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			htblRes.put(key.toLowerCase(), htbl.get(key));
		}
		return htblRes;
	}

	private Hashtable<String, Object> convertToSmallCaseObj(Hashtable<String, Object> htbl) {
		Hashtable<String, Object> htblRes = new Hashtable<String, Object>();
		Enumeration<String> keys = htbl.keys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			htblRes.put(key.toLowerCase(), htbl.get(key));
		}
		return htblRes;
	}

	public void UnLoadTable(Table tbl, String FilePath) throws DBAppException {
		try {
			ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(new FileOutputStream(FilePath));
			ObjectOutputStream.writeObject(tbl);
			ObjectOutputStream.close();
		} catch (Exception e) {
			throw new DBAppException(e.getMessage());
		}
	}

	public Table LoadTable(String FilePath) throws DBAppException {
		Table RestoredTable = null;
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FilePath));
			RestoredTable = (Table) objectInputStream.readObject();
			objectInputStream.close();
		} catch (Exception e) {
			throw new DBAppException(e.getMessage());
		}
		return RestoredTable;
	}

	public void ValidateMetaData(Hashtable<String, String> ColNameType, Hashtable<String, String> ColNameMin,
			Hashtable<String, String> ColNameMax) throws DBAppException {
		Set<String> nameTypeList = ColNameType.keySet();
		Set<String> nameMinList = ColNameMin.keySet();
		Set<String> nameMaxList = ColNameMax.keySet();
		if (!(nameTypeList.size() == nameMinList.size() && nameMinList.size() == nameMaxList.size()))
			throw new DBAppException("You should insert same ids for types ,minimum and maximum");
		Enumeration<String> ColNameTypeKeys = ColNameType.keys();
		while (ColNameTypeKeys.hasMoreElements()) {
			String Key = ColNameTypeKeys.nextElement();
			if (ColNameMin.get(Key) == null || ColNameMax.get(Key) == null)
				throw new DBAppException("You should insert same ids for types ,minimum and maximum");
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
			throw new DBAppException(e.getMessage());
		}
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		if (new File(FilePath).length() == 0)
			pw.println("TableName" + "," + "Column Name" + "," + "Column Type" + "," + "ClusteringKey" + ","
					+ "IndexName" + "," + "IndexType" + "," + "min" + "," + "max");
		ColNameType
				.forEach((key, value) -> pw.println(TblName + "," + key + "," + value + "," + ClusteringKey.equals(key)
						+ "," + "null" + "," + "null" + "," + ColNameMin.get(key) + "," + ColNameMax.get(key)));
		pw.flush();
		pw.close();
	}

	public void createIndex(String strTableName, String[] strarrColName) throws DBAppException {
		strTableName = strTableName.toLowerCase();
		for (int i=0;i<strarrColName.length;i++)
			strarrColName[i]=strarrColName[i].toLowerCase();
		if (CreatedTables.get(strTableName) == null)
			throw new DBAppException(strTableName + " does not exists");
		String FilePath = CreatedTables.get(strTableName);
		Table Table = LoadTable(FilePath);
		Table.ReadMetaData();
		Table.validateCreateIndex(strarrColName);
		Table.createIndex(strarrColName);
		UnLoadTable(Table, FilePath);
		Table = null;
	}

	public void insertIntoTable(String strTableName, Hashtable<String, Object> htblColNameValue) throws DBAppException {
		strTableName = strTableName.toLowerCase();
		htblColNameValue = convertToSmallCaseObj(htblColNameValue);
		if (CreatedTables.get(strTableName) == null)
			throw new DBAppException(strTableName + " does not exists");
		String FilePath = CreatedTables.get(strTableName);
		Table Table = LoadTable(FilePath);
		Table.ReadMetaData();
		Table.ValidateInsert(htblColNameValue);
		Table.InsertInTable(htblColNameValue);
		UnLoadTable(Table, FilePath);
		Table = null;
	}

	public void updateTable(String strTableName, String strClusteringKeyValue,
			Hashtable<String, Object> htblColNameValue) throws DBAppException {
		strTableName = strTableName.toLowerCase();
		htblColNameValue = convertToSmallCaseObj(htblColNameValue);
		if (CreatedTables.get(strTableName) == null)
			throw new DBAppException(strTableName + " does not exists");
		String FilePath = CreatedTables.get(strTableName);
		Table Table = LoadTable(FilePath);
		Table.ReadMetaData();
		Table.ValidateUpdate(strClusteringKeyValue, htblColNameValue);
		Table.UpdateTbl(strClusteringKeyValue, htblColNameValue);
		UnLoadTable(Table, FilePath);
		Table = null;
	}

	public void deleteFromTable(String strTableName, Hashtable<String, Object> htblColNameValue) throws DBAppException {
		strTableName = strTableName.toLowerCase();
		htblColNameValue = convertToSmallCaseObj(htblColNameValue);
		if (CreatedTables.get(strTableName) == null)
			throw new DBAppException(strTableName + " does not exists");
		String FilePath = CreatedTables.get(strTableName);
		Table Table = LoadTable(FilePath);
		Table.ReadMetaData();
		Table.ValidateDelete(htblColNameValue);
		Table.DelFromTbl(htblColNameValue);
		UnLoadTable(Table, FilePath);
		Table = null;
	}
	
	private void validateSelect (SQLTerm[] arrSQLTerms, String[] strarrOperators) throws DBAppException {
		String frstTblName=arrSQLTerms[0].get_strTableName();
		if (CreatedTables.get(frstTblName) == null)
			throw new DBAppException(arrSQLTerms[0].get_strTableName() + " does not exists");
		if((arrSQLTerms.length)-(strarrOperators.length)!=1)
			throw new DBAppException("Length of the terms and operators are incompatible");
		String FilePath = CreatedTables.get(frstTblName);
		Table Table = LoadTable(FilePath);
		Table.ReadMetaData();
		for(SQLTerm term:arrSQLTerms) {
			String currTblName=term.get_strTableName();
			if((currTblName.equals(frstTblName))!=true)
				throw new DBAppException("Cannot select from multiple tables at once");
			String currColName=term.get_strColumnName();
			if(Table.getColumnNameType().get(currColName)==null)
				throw new DBAppException("Column name doesn't exist for table "+ currTblName);
			V.ValidateObjectType(term.get_objValue(), Table.getColumnNameType().get(currColName));
			V.ValidateBounds(currTblName, term.get_objValue(), Table.getColumnNameType(), Table.getColumnNameMin(),
					Table.getColumnNameMax());			
		}
		UnLoadTable(Table, FilePath);
		Table = null;
	}

	@SuppressWarnings("rawtypes")
	public Iterator selectFromTable(SQLTerm[] arrSQLTerms, String[] strarrOperators) throws DBAppException {
		validateSelect(arrSQLTerms,strarrOperators);
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Iterator parseSQL(StringBuffer strbufSQL) throws DBAppException {
		SQLParserAPI sql = new SQLParserAPI(this);
		return (Iterator) sql.sqlExecuteParse(strbufSQL.toString());
	}

	public Hashtable<String, String> getCreatedTables() {
		return CreatedTables;
	}
}
