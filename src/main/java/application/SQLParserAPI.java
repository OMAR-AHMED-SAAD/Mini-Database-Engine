package application;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import applicationModules.Table;
import basicTools.ValidatorI;
import exceptions.DBAppException;
import parser.*;

public class SQLParserAPI implements ValidatorI {
	private DBApp db;

	public SQLParserAPI(DBApp db) {
		this.db = db;
	}

	public Object sqlExecuteParse(String statement) throws DBAppException {
		try {
			CharStream cs = CharStreams.fromString(statement);

			sqlLexer lexer = new sqlLexer(cs);
			lexer.removeErrorListeners();
			lexer.addErrorListener(new ErrorListener());
			CommonTokenStream tokens = new CommonTokenStream(lexer);

			sqlParser parser = new sqlParser(tokens);
			parser.removeErrorListeners();
			parser.addErrorListener(new ErrorListener());

			ExtractStatement visitor = new ExtractStatement();
			visitor.visit(parser.statement());

			String type = visitor.getStatementType();
			switch (type) {
			case "insert":
				return sqlInsert(visitor);
			case "update":
				return sqlUpdate(visitor);
			case "createTable":
				return sqlCreate(visitor);
			case "delete":
				return sqlDelete(visitor);
			case "createIndex":
				return sqlCreateIndex(visitor);
			case "select":
				return sqlSelect(visitor);
			default:
				throw new DBAppException("INVALID STATEMENT");
			}
		} catch (Exception e) {

			throw new DBAppException(e.getMessage());
		}
	}

	private Void sqlCreate(ExtractStatement visitor) throws DBAppException {
		String tableName = visitor.getTableName();
		String primaryKey = visitor.getPrimaryKey();
		List<String> columnNames = visitor.getCreationColumnNames();
		List<String> columnDatatypes = visitor.getCreationDatatypes();
		if (!columnNames.contains(primaryKey))
			throw new DBAppException("Primary Key should be one of the table columns");
		else if (columnDatatypes.size() != columnDatatypes.size())
			throw new DBAppException("Ensure specifying data type for each column");
		Hashtable<String, String> NameType = new Hashtable<String, String>();
		Hashtable<String, String> min = new Hashtable<String, String>();
		Hashtable<String, String> max = new Hashtable<String, String>();
		for (int i = 0; i < columnNames.size(); i++) {
			if (columnDatatypes.get(i).toLowerCase().startsWith("varchar")
					|| columnDatatypes.get(i).toLowerCase().startsWith("char")) {
				NameType.put(columnNames.get(i), "java.lang.string");
				int maxChar = extractMax(columnDatatypes.get(i));
				max.put(columnNames.get(i), String.valueOf("Z").repeat(maxChar));
				min.put(columnNames.get(i), "A");
			} else if (columnDatatypes.get(i).toLowerCase().startsWith("int")) {
				NameType.put(columnNames.get(i), "java.lang.integer");
				int maxChar = extractMax(columnDatatypes.get(i));
				if (maxChar == -1)
					max.put(columnNames.get(i), "10000");
				else
					max.put(columnNames.get(i), maxChar + "");
				min.put(columnNames.get(i), "0");
			} else if (columnDatatypes.get(i).toLowerCase().startsWith("double")) {
				NameType.put(columnNames.get(i), "java.lang.double");
				int[] maxChar = extractDoubleMax(columnDatatypes.get(i));
				if (maxChar.length == 0)
					max.put(columnNames.get(i), "1000000");
				else
					max.put(columnNames.get(i), "1" + String.valueOf("0").repeat(maxChar[0] - 1) + "."
							+ String.valueOf("0").repeat(maxChar[1]));
				min.put(columnNames.get(i), "0");
			} else if (columnDatatypes.get(i).toLowerCase().startsWith("date")) {
				NameType.put(columnNames.get(i), "java.util.date");
				max.put(columnNames.get(i), "9998-12-31");
				min.put(columnNames.get(i), "1753-01-01");
			} else
				throw new DBAppException("Inavlid type input");
		}
		db.createTable(tableName, primaryKey, NameType, min, max);
		String FilePath = db.getCreatedTables().get(tableName);
		Table table = db.LoadTable(FilePath);
		table.ReadMetaData();
		for (String col : columnNames)
			table.getCreationOrder().add(col);
		db.UnLoadTable(table, FilePath);
		System.out.println("executed successfully");
		return null;
	}

	public int extractMax(String input) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(input);
		if (matcher.find())
			return Integer.parseInt(matcher.group());
		return -1;
	}

	public int[] extractDoubleMax(String input) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(input);
		int[] result = new int[2];
		int i = 0;
		while (matcher.find())
			result[i++] = Integer.parseInt(matcher.group());
		return result;
	}

	private Void sqlDelete(ExtractStatement visitor) throws DBAppException {
		String tableName = visitor.getTableName();
		List<String> columnNames = visitor.getDeleteColumnNames();
		List<String> columnValues = visitor.getDeleteValues();
		Hashtable<String, Object> NameValue = new Hashtable<String, Object>();
		for (int i = 0; i < columnNames.size(); i++)
			NameValue.put(columnNames.get(i), getObject(tableName, columnNames.get(i), columnValues.get(i)));
		db.deleteFromTable(tableName, NameValue);
		System.out.println("executed successfully");
		return null;
	}

	public Object getObject(String tableName, String columnName, String input) throws DBAppException {
		if (db.getCreatedTables().get(tableName) == null)
			throw new DBAppException(tableName + " does not exists");
		String FilePath = db.getCreatedTables().get(tableName);
		Table table = db.LoadTable(FilePath);
		table.ReadMetaData();
		Object result = V.tryParse(input, table.getColumnNameType().get(columnName));
		db.UnLoadTable(table, FilePath);
		return result;
	}

	private Void sqlInsert(ExtractStatement visitor) throws DBAppException {
		String tableName = visitor.getTableName();
		List<String> columnNames = visitor.getInsertionColumnNames();
		List<String> columnValues = visitor.getInsertionValues();
		if (db.getCreatedTables().get(tableName) == null)
			throw new DBAppException(tableName + " does not exists");
		String FilePath = db.getCreatedTables().get(tableName);
		Table table = db.LoadTable(FilePath);
		table.ReadMetaData();
		if (columnNames.size() != 0 && !columnNames.contains(table.getCKName()))
			throw new DBAppException("Insertions should always have primary key");
		if (columnNames.size() != 0 && columnNames.size() != columnValues.size())
			throw new DBAppException("You should enter each column name and corresponding value you wan t to insert");
		if (columnNames.size() == 0) {
			if (table.getColumnNameType().size() != columnValues.size())
				throw new DBAppException("You need to add values for all columns with same order of creation ");
			Hashtable<String, Object> NameValue = new Hashtable<String, Object>();
			for (int i = 0; i < columnValues.size(); i++) {
				if (!columnValues.get(i).toLowerCase().equals("null"))
					NameValue.put(table.getCreationOrder().get(i),
							getObject(tableName, table.getCreationOrder().get(i), columnValues.get(i)));
			}
			db.UnLoadTable(table, FilePath);
			db.insertIntoTable(tableName, NameValue);
		} else {
			Hashtable<String, Object> NameValue = new Hashtable<String, Object>();
			for (int i = 0; i < columnNames.size(); i++) {
				if (!columnValues.get(i).toLowerCase().equals("null"))
					NameValue.put(columnNames.get(i), getObject(tableName, columnNames.get(i), columnValues.get(i)));
			}
			db.insertIntoTable(tableName, NameValue);
		}
		System.out.println("executed successfully");
		return null;
	}

	private Void sqlUpdate(ExtractStatement visitor) throws DBAppException {
		String tableName = visitor.getTableName();
		String pkName = visitor.getUpdateConditionName();
		String pkValue = visitor.getUpdateConditionValue();
		List<String> columnNames = visitor.getUpdateColumnNames();
		List<String> columnValues = visitor.getUpdateValues();
		if (db.getCreatedTables().get(tableName) == null)
			throw new DBAppException(tableName + " does not exists");
		String FilePath = db.getCreatedTables().get(tableName);
		Table table = db.LoadTable(FilePath);
		table.ReadMetaData();
		if (!table.getCKName().equals(pkName))
			throw new DBAppException("This DBMS only allows updates row at a time through the primary key value");
		else if (columnNames.contains(pkName))
			throw new DBAppException("This DBMS does not allow altering the primary key value");
		Hashtable<String, Object> NameValue = new Hashtable<String, Object>();
		for (int i = 0; i < columnNames.size(); i++)
			NameValue.put(columnNames.get(i), getObject(tableName, columnNames.get(i), columnValues.get(i)));
		db.UnLoadTable(table, FilePath);
		db.updateTable(tableName, pkValue, NameValue);
		System.out.println("executed successfully");
		return null;
	}

	private Void sqlCreateIndex(ExtractStatement visitor) throws DBAppException {
		String tableName = visitor.getTableName();
		List<String> columnNames = visitor.getIndexColumnNames();
		if (db.getCreatedTables().get(tableName) == null)
			throw new DBAppException(tableName + " does not exists");
		db.createIndex(tableName, columnNames.toArray(new String[columnNames.size()]));
		System.out.println("executed successfully");
		return null;
	}

	private Iterator sqlSelect(ExtractStatement visitor) throws DBAppException {
		String tableName = visitor.getTableName();
//		System.out.println(visitor.getSelectSqlTerms());
//		System.out.println(visitor.getSelectOperators());
//		return null;
		SQLTerm[] arrSQLTerms = visitor.getSelectSqlTerms().toArray(new SQLTerm[visitor.getSelectSqlTerms().size()]);
		String[] strarrOperators = visitor.getSelectOperators()
				.toArray(new String[visitor.getSelectOperators().size()]);
		if (db.getCreatedTables().get(tableName) == null)
			throw new DBAppException(tableName + " does not exists");
		String FilePath = db.getCreatedTables().get(tableName);
		Table table = db.LoadTable(FilePath);
		table.ReadMetaData();
		for (SQLTerm sqlterm : arrSQLTerms) {
			String type = table.getColumnNameType().get(sqlterm.get_strColumnName());
			if (type == null)
				throw new DBAppException(sqlterm.get_strColumnName() + " does not exist in " + tableName);
			else
				sqlterm.set_objValue(V.tryParse(sqlterm.get_objValue().toString(), type));
		}
		db.UnLoadTable(table, FilePath);
		return db.selectFromTable(arrSQLTerms, strarrOperators);

	}

//	public static void main(String[] args) throws DBAppException {
//		new SQLParserAPI(new DBApp()).sqlExecuteParse("SElect * from student where name = 'john' and age>=20 or id=50 xor a>20");
//	}
}
