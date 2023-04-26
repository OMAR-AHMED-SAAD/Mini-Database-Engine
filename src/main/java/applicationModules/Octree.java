package applicationModules;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

import exceptions.DBAppException;

public class Octree {
	private Node root;
	private String FilePath;
	private String[]Attributes;
	
	public Octree(String tblName,String A1, String A2, String A3,Hashtable<String, Object> max,Hashtable<String, Object> min) throws DBAppException {
		Attributes= new String[3];
		Attributes[0]=A1;
		Attributes[1]=A2;
		Attributes[2]=A3;		
		FilePath = "src/main/DBFiles/Indices/" +tblName+ A1+A2+A3 + ".bin";
		root = new Node(max,min);
		//populate();
	}
	
	public void UnLoadTree() throws DBAppException {
		try {
			ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(new FileOutputStream(FilePath));
			ObjectOutputStream.writeObject(this);
			ObjectOutputStream.close();
		} catch (Exception e) {
			throw new DBAppException();
		}
	}

}
