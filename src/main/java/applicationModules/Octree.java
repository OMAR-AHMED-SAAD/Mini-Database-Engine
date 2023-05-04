package applicationModules;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.lang.model.util.Elements;

import basicTools.ComparatorI;
import exceptions.DBAppException;

public class Octree implements Serializable, ComparatorI {
	private static final long serialVersionUID = 1L;
	private Node root;
	private String FilePath;
	private String[] Attributes;

	public Octree(String tblName, String A1, String A2, String A3, Hashtable<String, Object> max,
			Hashtable<String, Object> min) throws DBAppException {
		Attributes = new String[3];
		Attributes[0] = A1;
		Attributes[1] = A2;
		Attributes[2] = A3;
		FilePath = "src/main/DBFiles/Indices/" + tblName + A1 + A2 + A3 + ".bin";
		root = new Node(max, min);
		// populate();
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

	public void insert(Element element) throws DBAppException {
		if (root.elements.size() < root.maxElements && root.children == null)
			insertElement(root.elements, element);
		else if (root.elements.size() <= root.maxElements && root.children == null)
			if (!insertElement(root.elements, element))

				;
	}

	private boolean insertElement(Vector<Vector<Element>> elements, Element element) throws DBAppException {
		boolean inserted = false;
		for (Vector<Element> vec : elements) {
			for (Element existingElement : vec) {
				int comparison1 = C.compare(existingElement.attribute1, element.attribute1);
				int comparison2 = C.compare(existingElement.attribute2, element.attribute2);
				int comparison3 = C.compare(existingElement.attribute3, element.attribute3);
				if (comparison1 == 0 || comparison2 == 0 || comparison3 == 0) {
					vec.add(element);
					inserted = true;
					break;
				}
			}
		}
		if (!inserted && elements.size() == root.maxElements) {
			return false;
		}
		if (elements.isEmpty() && !inserted) {
			Vector<Element> elementsVector = new Vector<Element>();
			elementsVector.add(element);
			elements.add(elementsVector);
		}
		return true;
	}

	private void split(Node nd) {
		nd.children = new Node[8];
		Hashtable<String, Object> maxNd = new Hashtable<String, Object>();
		Hashtable<String, Object> minNd = new Hashtable<String, Object>();
//		nd.children[0]=new Node(maxNd,minNd);
	}

	class Node implements Serializable {
		private static final long serialVersionUID = 1L;
		private Hashtable<String, Object> max = new Hashtable<String, Object>();
		private Hashtable<String, Object> min = new Hashtable<String, Object>();
		private Vector<Vector<Element>> elements = new Vector<Vector<Element>>();
		private Node[] children;
		private int maxElements;

		public Node(Hashtable<String, Object> max, Hashtable<String, Object> min) throws DBAppException {
			this.max = max;
			this.min = min;
			Properties Prop = new Properties();
			try {
				FileInputStream inputStream = new FileInputStream("src/main/resources/DBApp.config");
				Prop.load(inputStream);
			} catch (Exception e) {
				throw new DBAppException();
			}
			this.maxElements = Integer.parseInt(Prop.getProperty("MaximumEntriesinOctreeNode"));
		}

	}
}
