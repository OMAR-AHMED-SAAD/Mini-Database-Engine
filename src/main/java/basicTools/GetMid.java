package basicTools;

import exceptions.DBAppException;

public class GetMid {
	
	public Object getMid(Object min,Object max) throws DBAppException {
		if (min instanceof java.lang.Integer && max instanceof java.lang.Integer)
			return ((java.lang.Integer) min).compareTo((java.lang.Integer) max);
		else if (min instanceof java.lang.String && max instanceof java.lang.String)
			return printMiddleString((String) max, (String)min, ((String)max).length());
		else if (min instanceof java.util.Date && max instanceof java.util.Date)
			return ((java.util.Date) min).compareTo((java.util.Date) max);
		else if (min instanceof java.lang.Double && max instanceof java.lang.Double)
			return ((java.lang.Double) min).compareTo((java.lang.Double) max);
		else
			throw new DBAppException("getMid");
	}
	
	 static String printMiddleString(String S, String T, int N)
	    {
	        // Stores the base 26 digits after addition
	        int[] a1 = new int[N + 1];
	        
	        int min= Math.min(S.length(),T.length());
	 
	        for (int i = 0; i < N; i++) {
	            a1[i + 1] = (int)S.charAt(i) - 97
	                        + (int)T.charAt(i) - 97;
	        }
//	        if(S.length()>T.length()) {
//	        	for(int i=min;i<N;i++)
//	        		a1[i + 1] = (int)S.charAt(i) - 97;
//	        }
//	        else if (S.length()<T.length()) {
//	        	for(int i=min;i<N;i++)
//	        		a1[i + 1] = (int)T.charAt(i) - 97;
//	        }
	        	
	        // Iterate from right to left
	        // and add carry to next position
	        for (int i = N; i >= 1; i--) {
	            a1[i - 1] += (int)a1[i] / 26;
	            a1[i] %= 26;
	        }
	 
	        // Reduce the number to find the middle
	        // string by dividing each position by 2
	        for (int i = 0; i <= N; i++) {
	 
	            // If current value is odd,
	            // carry 26 to the next index value
	            if ((a1[i] & 1) != 0) {
	 
	                if (i + 1 <= N) {
	                    a1[i + 1] += 26;
	                }
	            }
	 
	            a1[i] = (int)a1[i] / 2;
	        }
	        String result= "";
	 
	        for (int i = 1; i <= N; i++) {
//	            System.out.print((char)(a1[i] + 97));
	            result+=(char)(a1[i] + 97);
	        }
	        return result;
	    }
	 
	 public static void main(String[]args) {
		 int N = 3;
	        String S = "afo";
	        String T = "asdji";
	        System.out.println(printMiddleString(S, T, N));
	       
	 }

}
