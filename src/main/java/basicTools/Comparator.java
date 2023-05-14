package basicTools;

import exceptions.DBAppException;

public class Comparator {

	public int compare(Object One, Object Two) throws DBAppException {
		if (One instanceof java.lang.Integer && Two instanceof java.lang.Integer)
			return ((java.lang.Integer) One).compareTo((java.lang.Integer) Two);
		else if (One instanceof java.lang.String && Two instanceof java.lang.String)
			return (((java.lang.String) One).toLowerCase()).compareTo(((java.lang.String) Two).toLowerCase());
		else if (One instanceof java.util.Date && Two instanceof java.util.Date)
			return ((java.util.Date) One).compareTo((java.util.Date) Two);
		else if (One instanceof java.lang.Double && Two instanceof java.lang.Double)
			return ((java.lang.Double) One).compareTo((java.lang.Double) Two);
		else
			throw new DBAppException("Comparing two incompatible types");
	}

//partial null support return not equal whenever one of the values is null
	public int compareWNull(Object One, Object Two) throws DBAppException {
		if (One == null || Two == null)
			return -1;
		if (One instanceof java.lang.Integer && Two instanceof java.lang.Integer)
			return ((java.lang.Integer) One).compareTo((java.lang.Integer) Two);
		else if (One instanceof java.lang.String && Two instanceof java.lang.String)
			return (((java.lang.String) One).toLowerCase()).compareTo(((java.lang.String) Two).toLowerCase());
		else if (One instanceof java.util.Date && Two instanceof java.util.Date)
			return ((java.util.Date) One).compareTo((java.util.Date) Two);
		else if (One instanceof java.lang.Double && Two instanceof java.lang.Double)
			return ((java.lang.Double) One).compareTo((java.lang.Double) Two);
		else
			throw new DBAppException("Comparing two incompatible types");
	}

	// full null support
	public int compareNullSupport(Object One, Object Two) throws DBAppException {
		if (One == null && Two == null)
			return 0;
		if ((One == null && Two != null) || (One != null && Two == null))
			return -1;
		if (One instanceof java.lang.Integer && Two instanceof java.lang.Integer)
			return ((java.lang.Integer) One).compareTo((java.lang.Integer) Two);
		else if (One instanceof java.lang.String && Two instanceof java.lang.String)
			return (((java.lang.String) One).toLowerCase()).compareTo(((java.lang.String) Two).toLowerCase());
		else if (One instanceof java.util.Date && Two instanceof java.util.Date)
			return ((java.util.Date) One).compareTo((java.util.Date) Two);
		else if (One instanceof java.lang.Double && Two instanceof java.lang.Double)
			return ((java.lang.Double) One).compareTo((java.lang.Double) Two);
		else
			throw new DBAppException("Comparing two incompatible types");
	}

	// check this later
	public boolean compareWithOperator(Object one, Object two, String operator) throws DBAppException {
		if (one == null && two == null && operator == "=")
			return true;
		if (one != null && two == null && operator == "!=")
			return true;
		if (one == null || two == null)
			return false;
		switch (operator) {
		case "=":
			if (compare(one, two) == 0)
				return true;
			break;
		case ">":
			if (compare(one, two) > 0)
				return true;
			break;
		case "<":
			if (compare(one, two) < 0)
				return true;
			break;
		case ">=":
			if (compare(one, two) >= 0)
				return true;
			break;
		case "<=":
			if (compare(one, two) <= 0)
				return true;
			break;
		case "!=":
			if (compare(one, two) != 0)
				return true;
			break;
		}
		return false;
	}

//test this later
	public boolean compareWithOperator(Object min, Object max, String operator, Object value, boolean isMaxIncluded)
			throws DBAppException {
		switch (operator) {
		case "=":
			if ((compare(min, value) <= 0 && compare(max, value) >= 0 && isMaxIncluded)
					|| (compare(min, value) <= 0 && compare(max, value) > 0))
				return true;
			break;
		case ">":
			if (compare(max, value) > 0)
				return true;
			break;
		case "<":
			if (compare(min, value) < 0)
				return true;
			break;
		case ">=":
			if ((compare(max, value) > 0) || (compare(max, value) >= 0 && isMaxIncluded))
				return true;
			break;
		case "<=":
			if (compare(min, value) <= 0)
				return true;
			break;
		case "!=":
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws DBAppException {

		System.out.println(new Comparator().compareWithOperator(1, 10, "!=", 1, false));
	}
}
