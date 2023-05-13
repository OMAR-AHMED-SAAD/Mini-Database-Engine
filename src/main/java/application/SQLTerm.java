package application;

import java.util.ArrayList;
import java.util.Arrays;

import exceptions.DBAppException;

public class SQLTerm {

	private String _strTableName;
	private String _strColumnName;
	private String _strOperator;
	private Object _objValue;
	private static final ArrayList<String> operators = new ArrayList<>(Arrays.asList("<", ">", ">=", "<=", "=", "="));

	public SQLTerm() {
	}

	public SQLTerm(String _strTableName, String _strColumnName, String _strOperator, Object _objValue)
			throws DBAppException {
		if (!operators.contains(_strOperator))
			throw new DBAppException(
					"Invalid operator " + _strOperator + " only valid operators are  >, >=, <, <=, != or = ");
		this._strTableName = _strTableName.toLowerCase();
		this._strColumnName = _strColumnName.toLowerCase();
		this._strOperator = _strOperator;
		this._objValue = _objValue;

	}

	public String get_strTableName() {
		return _strTableName;
	}

	public void set_strTableName(String _strTableName) {
		this._strTableName = _strTableName;
	}

	public String get_strColumnName() {
		return _strColumnName;
	}

	public void set_strColumnName(String _strColumnName) {
		this._strColumnName = _strColumnName;
	}

	public String get_strOperator() {
		return _strOperator;
	}

	public void set_strOperator(String _strOperator) {
		this._strOperator = _strOperator;
	}

	public Object get_objValue() {
		return _objValue;
	}

	public void set_objValue(Object _objValue) {
		this._objValue = _objValue;
	}

	@Override
	public String toString() {
		return "SQLTerm{" + " TableName = " + _strTableName+ ", ColumnName = " + _strColumnName 
				+ ", Operator = '" + _strOperator + '\'' + ", Value = " + _objValue + '}';
	}

}
