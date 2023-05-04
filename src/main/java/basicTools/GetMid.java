package basicTools;

import java.util.Date;

import exceptions.DBAppException;

public class GetMid {

	public Object getMid(Object min, Object max) throws DBAppException {
		if (min instanceof java.lang.Integer && max instanceof java.lang.Integer)
			return ((java.lang.Integer) min).compareTo((java.lang.Integer) max);
		else if (min instanceof java.lang.String && max instanceof java.lang.String)
			return middleString((String) max, (String) min, ((String) max).length());
		else if (min instanceof java.util.Date && max instanceof java.util.Date)
			return new Date((((java.util.Date) min).getTime() + ((java.util.Date) max).getTime()) / 2);
		else if (min instanceof java.lang.Double && max instanceof java.lang.Double)
			return ((java.lang.Double) min).compareTo((java.lang.Double) max);
		else
			throw new DBAppException("getMid");
	}

	String middleString(String min, String max, int length) {
		min = min.toLowerCase();
		max = max.toLowerCase();

		if (min.length() < max.length()) {
			int diff = max.length() - min.length();
			for (int i = 0; i < diff; i++)
				min += "a";
		} else {
			int diff = min.length() - max.length();
			for (int i = 0; i < diff; i++)
				max += "a";
		}
		// Stores the base 26 digits after addition
		int[] a1 = new int[length + 1];

		for (int i = 0; i < length; i++) {
			a1[i + 1] = (int) min.charAt(i) - 97 + (int) max.charAt(i) - 97;
		}

		// Iterate from right to left
		// and add carry to next position
		for (int i = length; i >= 1; i--) {
			a1[i - 1] += (int) a1[i] / 26;
			a1[i] %= 26;
		}

		// Reduce the number to find the middle
		// string by dividing each position by 2
		for (int i = 0; i <= length; i++) {

			// If current value is odd,
			// carry 26 to the next index value
			if ((a1[i] & 1) != 0) {

				if (i + 1 <= length) {
					a1[i + 1] += 26;
				}
			}

			a1[i] = (int) a1[i] / 2;
		}
		String result = "";

		for (int i = 1; i <= length; i++) {
			result += (char) (a1[i] + 97);
		}
		return result;
	}
}
