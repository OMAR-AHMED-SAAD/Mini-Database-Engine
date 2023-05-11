package applicationModules;

import java.util.Vector;

public class tester2 {
	private static Vector<OctreeDescription> Octrees = new Vector<OctreeDescription>();
	public static void main(String[] args) {
		Octrees.add(new OctreeDescription("lol", "x", "y", "z"));

		Octrees.add(new OctreeDescription("lol", "A", "B", "C"));
		Octrees.add(new OctreeDescription("lol", "Aa", "Bh", "Cy"));

		Octrees.add(new OctreeDescription("lol", "Ajkhn", "rgB", "Crjknjk"));
		Octrees.add(new OctreeDescription("lol", "Ajknkjr", "Brhkfjb", "Cjkhrbe"));

		Octrees.add(new OctreeDescription("lol", "jknr", "Bkrj", "rejm C"));
		Octrees.add(new OctreeDescription("lol", "Ajr", "hjrfB", "Ckf "));
		Octrees.add(new OctreeDescription("lol", "Afk", "Befrk", "Cfek"));

		Octrees.add(new OctreeDescription("lol", "m", "n", "o"));

		String [] ss={"o","b","a"};
		System.out.println(getMatchingInex(ss));
		long st=System.currentTimeMillis();
		System.out.println(getBestMatch(ss));
		long end=System.currentTimeMillis();
		System.out.println(end-st);
	
	}

	public static Vector<OctreeDescription> getMatchingInex(String[] columns) {
		Vector<OctreeDescription> result = new Vector<OctreeDescription>();
		boolean flag = false;
		for (OctreeDescription od : Octrees)
			for (String existingAtt : od.getAttributes()) {
				for (String col : columns)
					if (col.equalsIgnoreCase(existingAtt)) {
						result.add(od);
						flag = true;
						break;
					}
				if (flag) {
					flag = false;
					break;
				}
			}
		return result;
	}
	public static OctreeDescription getBestMatch(String[] columns) {
		OctreeDescription bestMatch = null;
		Vector<OctreeDescription> allMatches = getMatchingInex(columns);
		int maxCount = 0;
		int count = 0;
		for (OctreeDescription od : Octrees) {
			for (String existingAtt : od.getAttributes()) {
				for (String col : columns)
					if (col.equalsIgnoreCase(existingAtt)) {
						count++;
					}
			}
			if (count > maxCount) {
				maxCount = count;
				bestMatch = od;
			}
			count = 0;
		}
		return bestMatch;
	}
}
