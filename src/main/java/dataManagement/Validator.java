package dataManagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import exceptions.DBAppException;

public class Validator implements ComparatorI {

	private static String[] SupportedTypes = new String[] { "java.lang.Integer", "java.lang.String", "java.util.Date",
			"java.util.Double" };

	public void checkTypeSupport(String type) throws DBAppException {
		boolean found = false;
		for (String SuppType : SupportedTypes)
			if (SuppType.equals(type))
				found = true;
		if (!found)
			throw new DBAppException(type + " is not currently supported");
	}

	// given Keys (ColumnNames) and Hahstable of Columns,check no column is invalid
	// (not in the table)
	public void ValidateColumnsE(Enumeration<String> ColNameKeys, Hashtable<String, String> ColumnNameType)
			throws DBAppException {
		while (ColNameKeys.hasMoreElements()) {
			String Key = ColNameKeys.nextElement();
			if (ColumnNameType.get(Key) == null)
				throw new DBAppException("Column " + Key + " does not exist");
		}
	}

	// takes Object and a type and check if object is of that type

	public void ValidateObjectType(Object Obj, String Type) throws DBAppException {
		switch (Type) {
		case "java.util.Date":
			if (!(Obj instanceof java.util.Date))
				throw new DBAppException("Invalid Type for Date");
		case "java.lang.Integer":
			if (!(Obj instanceof java.lang.Integer))
				return;
			throw new DBAppException("Invalid Type for Integer");
		case "java.lang.String":
			if (!(Obj instanceof java.lang.String))
				return;
			throw new DBAppException("Invalid Type for String");
		case "java.lang.Double":
			if (!(Obj instanceof java.lang.Double))
				return;
			throw new DBAppException("Invalid Type for Double");
		}
	}

	public void ValidateBounds(String Key, Object Obj, Hashtable<String, String> ColumnNameType,
			Hashtable<String, String> ColumnNameMin, Hashtable<String, String> ColumnNameMax)
			throws ParseException, DBAppException {
		Object Min = Parsing(ColumnNameMin.get(Key), ColumnNameType.get(Key));
		Object Max = Parsing(ColumnNameMax.get(Key), ColumnNameType.get(Key));
		if (C.compare(Obj, Min) < 0 || C.compare(Obj, Max) > 0)
			throw new DBAppException("Entry causes Bounds Violation");
	}

	public void tryParse(String Value, String Type) throws DBAppException {
		try {
			Parsing(Value, Type);
		} catch (ParseException e) {
			throw new DBAppException(Value + " is Invalid for type " + Type);
		}
	}

	// takes string and a type and parse object to that type
	public Object Parsing(String Value, String Type) throws ParseException {
		switch (Type) {
		case "java.lang.Integer":
			return new Integer(Integer.parseInt(Value));
		case "java.lang.String":
			return new String(Value);
		case "java.util.Date":
			SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");
			DF.setLenient(false);
			Date date = DF.parse(Value);
			return date;
		case "java.lang.Double":
			return new Double(Double.parseDouble(Value));
		default:
			return new String(Value);
		}
	}
}
