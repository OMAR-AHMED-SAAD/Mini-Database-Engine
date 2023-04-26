package applicationModules;

import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import exceptions.DBAppException;

public class Node {
	private Hashtable<String, Object> max = new Hashtable<String, Object>();
	private Hashtable<String, Object> min = new Hashtable<String, Object>();
	private Vector<Hashtable<Object, String>> keyPointer = new Vector<Hashtable<Object, String>>();
	private Vector<Node> Children = new Vector<Node>();
	private int maxValueCount;
	
	public Node(Hashtable<String, Object> max,Hashtable<String, Object> min) throws DBAppException {
		this.max=max;
		this.min=min;
		Properties Prop = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream("src/main/resources/DBApp.config");
			Prop.load(inputStream);
		} catch (Exception e) {
			throw new DBAppException();
		}
		this.maxValueCount = Integer.parseInt(Prop.getProperty("MaximumEntriesinOctreeNode"));
	}

}
