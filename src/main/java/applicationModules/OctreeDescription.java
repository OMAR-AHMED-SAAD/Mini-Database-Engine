package applicationModules;

import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Properties;

import applicationModules.Octree.Node;
import exceptions.DBAppException;

public class OctreeDescription {
	
	private String FilePath;
	private String[] attributes; //ghayarha

	
	
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
