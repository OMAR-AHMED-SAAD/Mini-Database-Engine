package applicationModules;

import java.io.Serializable;


public class OctreeDescription implements Serializable {
	private static final long serialVersionUID = 1L;
	private String FilePath;
	private String[] attributes;

	
	
	public OctreeDescription(String tblName, String A1, String A2, String A3 ) {
		attributes = new String[3];
		attributes[0] = A1;
		attributes[1] = A2;
		attributes[2] = A3;
		FilePath = "src/main/resources/Indices/" + tblName + A1 + A2 + A3 + ".bin";
		
	}
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append("{").append(attributes[0]+",").append(attributes[1]+",").append(attributes[2]).append("}");
		return sb.toString();
	}
	public String getFilePath() {
		return FilePath;
	}
	public String[] getAttributes() {
		return attributes;
	}
	
}
