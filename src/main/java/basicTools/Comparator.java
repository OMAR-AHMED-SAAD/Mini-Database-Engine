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
		if (One==null || Two==null)
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
	//full null support
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
}
