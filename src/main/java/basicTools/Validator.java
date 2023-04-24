package basicTools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import exceptions.DBAppException;

public class Validator implements ComparatorI {

	private static String[] SupportedTypes = new String[] { "java.lang.integer", "java.lang.string", "java.util.date",
			"java.lang.double" };

	public void checkTypeSupport(String type) throws DBAppException {
		boolean found = false;
		for (String SuppType : SupportedTypes)
			if (SuppType.equals(type.toLowerCase()))
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
		switch (Type.toLowerCase()) {
		case "java.util.date":
			if (!(Obj instanceof java.util.Date))
				throw new DBAppException("Invalid Type for Date");
		case "java.lang.integer":
			if (!(Obj instanceof java.lang.Integer))
				return;
			throw new DBAppException("Invalid Type for Integer");
		case "java.lang.string":
			if (!(Obj instanceof java.lang.String))
				return;
			throw new DBAppException("Invalid Type for String");
		case "java.lang.double":
			if (!(Obj instanceof java.lang.Double))
				return;
			throw new DBAppException("Invalid Type for Double");
		}
	}

	public void ValidateBounds(String Key, Object Obj, Hashtable<String, String> ColumnNameType,
			Hashtable<String, String> ColumnNameMin, Hashtable<String, String> ColumnNameMax)
			throws DBAppException {
		Object Min = tryParse(ColumnNameMin.get(Key), ColumnNameType.get(Key));
		Object Max = tryParse(ColumnNameMax.get(Key), ColumnNameType.get(Key));
		if (C.compare(Obj, Min) < 0 || C.compare(Obj, Max) > 0)
			throw new DBAppException("Entry causes Bounds Violation");
	}

	public Object tryParse(String Value, String Type) throws DBAppException {
		try {
			return Parsing(Value, Type);
		} catch (ParseException e) {
			throw new DBAppException(Value + " is Invalid for type " + Type);
		}
	}

	// takes string and a type and parse object to that type
	@SuppressWarnings("removal")
	public Object Parsing(String Value, String Type) throws ParseException {
		switch (Type.toLowerCase()) {
		case "java.lang.integer":
			return new Integer(Integer.parseInt(Value));
		case "java.lang.string":
			return new String(Value);
		case "java.util.date":
			SimpleDateFormat DF = new SimpleDateFormat("yyyy-MM-dd");
			DF.setLenient(false);
			Date date = DF.parse(Value);
			return date;
		case "java.lang.double":
			return new Double(Double.parseDouble(Value));
		default:
			return new String(Value);
		}
	}
}
