package application;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import basicTools.ValidatorI;
import exceptions.DBAppException;
import parser.*;

public class solveDoubleQuote implements ValidatorI {


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
		System.out.println("tableName" + tableName);
		System.out.println("primaryKey" + primaryKey);
		System.out.println("columnNames" + columnNames);
		System.out.println("columnDatatypes" + columnDatatypes);
		System.out.println("executed successfully");
		return null;
	}

	private Void sqlDelete(ExtractStatement visitor) throws DBAppException {
		String tableName = visitor.getTableName().toLowerCase();
		List<String> columnNames = visitor.getDeleteColumnNames();
		List<String> columnValues = visitor.getDeleteValues();
		System.out.println("tableName" + tableName);
		System.out.println("columnNames" + columnNames);
		System.out.println("columnValues" + columnValues);
		System.out.println("executed successfully");
		return null;
	}

	private Void sqlInsert(ExtractStatement visitor) throws DBAppException {
		String tableName = visitor.getTableName().toLowerCase();
		List<String> columnNames = visitor.getInsertionColumnNames();
		List<String> columnValues = visitor.getInsertionValues();
		System.out.println("tableName" + tableName);
		System.out.println("columnNames" + columnNames);
		System.out.println("columnValues" + columnValues);
		System.out.println("executed successfully");
		return null;
	}

	private Void sqlUpdate(ExtractStatement visitor) throws DBAppException {
		String tableName = visitor.getTableName().toLowerCase();
		String pkName = visitor.getUpdateConditionName();
		String pkValue = visitor.getUpdateConditionValue();
		List<String> columnNames = visitor.getUpdateColumnNames();
		List<String> columnValues = visitor.getUpdateValues();
		System.out.println("tableName" + tableName);
		System.out.println("pkName" + pkName);
		System.out.println("pkValue" + pkValue);
		System.out.println("columnNames" + columnNames);
		System.out.println("columnValues" + columnValues);
		System.out.println("executed successfully");
		System.out.println("executed successfully");
		return null;
	}

	private Void sqlCreateIndex(ExtractStatement visitor) throws DBAppException {
		String tableName = visitor.getTableName().toLowerCase();
		List<String> columnNames = visitor.getIndexColumnNames();
		System.out.println("tableName" + tableName);
		System.out.println("columnNames" + columnNames);
		System.out.println("executed successfully");
		return null;
	}

	@SuppressWarnings("rawtypes")
	private Iterator sqlSelect(ExtractStatement visitor) throws DBAppException {
		String tableName = visitor.getTableName().toLowerCase();
		// SQLTerm[] arrSQLTerms = visitor.getSelectSqlTerms().toArray(new
		// SQLTerm[visitor.getSelectSqlTerms().size()]);
		String[] strarrOperators = visitor.getSelectOperators()
				.toArray(new String[visitor.getSelectOperators().size()]);
		System.out.println("tableName" + tableName);
		System.out.println("arrSQLTerms" + visitor.getSelectSqlTerms());
		System.out.println("strarrOperators" + Arrays.toString(strarrOperators));
		return null;

	}

	public static void main(String[] args) throws DBAppException {
//		new solveDoubleQuote().sqlExecuteParse("SELECT * FROM STUDENT WHERE name=\"ahmed\" OR age=\"12\";");
		new solveDoubleQuote().sqlExecuteParse("SELECT * FROM STUDENT WHERE name='ahmed' OR  age='12';");
//			Scanner sc=new Scanner(System.in);
//			String s=sc.nextLine();
//			System.out.println(s);
	}
}
