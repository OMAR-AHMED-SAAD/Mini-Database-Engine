package applicationModules;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import basicTools.ComparatorI;
import basicTools.GetMidI;
import exceptions.DBAppException;

public class OctreeOLD implements Serializable, ComparatorI, GetMidI {
	private static final long serialVersionUID = 1L;
	private Node root;
	private String FilePath;
	private String[] attributes;
	private int maxElements;

	public OctreeOLD(String tblName, String A1, String A2, String A3, Hashtable<String, Object> max,
			Hashtable<String, Object> min) throws DBAppException {
		attributes = new String[3];
		attributes[0] = A1;
		attributes[1] = A2;
		attributes[2] = A3;
		FilePath = "src/main/DBFiles/Indices/" + tblName + A1 + A2 + A3 + ".bin";
		root = new Node(max, min);
		Properties Prop = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream("src/main/resources/DBApp.config");
			Prop.load(inputStream);
		} catch (Exception e) {
			throw new DBAppException();
		}
		this.maxElements = Integer.parseInt(Prop.getProperty("MaximumEntriesinOctreeNode"));

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

	public boolean populate(Table tbl) throws DBAppException {
		for (int pageId : tbl.getTablePages()) {
			String filePath = tbl.getPageFilePath().get(pageId);
			Page pg = tbl.LoadPage(filePath);
			for (Hashtable<String, Object> row : pg.getVecPage()) {
				Object attribute1 = row.get(attributes[0]);
				Object attribute2 = row.get(attributes[1]);
				Object attribute3 = row.get(attributes[2]);
				if (attribute1 == null || attribute2 == null || attribute3 == null)
					return false;
				ElementOLD element = new ElementOLD(attribute1, attribute2, attribute3, filePath);
				insert(element);
			}
		}
		return true;
	}

	public void insert(ElementOLD element) throws DBAppException {
		insert(root, element);
	}

	private void insert(Node node, ElementOLD element) throws DBAppException {
		if (node.elements != null && node.elements.size() < this.maxElements && node.children == null) {
			insertElement(node.elements, element);
			return;
		} else if (node.elements != null && node.elements.size() == this.maxElements && node.children == null)
			if (!insertElement(node.elements, element))
				split(node);
			else
				return;
		for (Node child : node.children)
			if (isRightNode(child, element)) {
				insert(child, element);
				break;
			}
	}

	private boolean insertElement(Vector<Vector<ElementOLD>> elements, ElementOLD element) throws DBAppException {
		boolean inserted = false;
		for (Vector<ElementOLD> vec : elements) {
			for (ElementOLD existingElement : vec) {
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
		if (!inserted && elements.size() == this.maxElements) {
			return false;
		}
		if (elements.size() < this.maxElements && !inserted) {
			Vector<ElementOLD> elementsVector = new Vector<ElementOLD>();
			elementsVector.add(element);
			elements.add(elementsVector);
		}
		return true;
	}

	private void split(Node node) throws DBAppException {
		node.children = new Node[8];
		createChildren(node);
		for (Vector<ElementOLD> vec : node.elements)
			for (ElementOLD element : vec)
				insert(node, element);
		node.elements = null;
	}

	private void createChildren(Node node) throws DBAppException {
		Object att1Min = node.min.get(attributes[0]);
		Object att2Min = node.min.get(attributes[1]);
		Object att3Min = node.min.get(attributes[2]);
		Object att1Mid = GM.getMid(node.min.get(attributes[0]), node.max.get(attributes[0]));
		Object att2Mid = GM.getMid(node.min.get(attributes[1]), node.max.get(attributes[1]));
		Object att3Mid = GM.getMid(node.min.get(attributes[2]), node.max.get(attributes[2]));
		Object att1Max = node.max.get(attributes[0]);
		Object att2Max = node.max.get(attributes[1]);
		Object att3Max = node.max.get(attributes[2]);
		node.children[0] = new Node(att1Min, att2Min, att3Min, att1Mid, att2Mid, att3Mid);
		node.children[1] = new Node(att1Min, att2Mid, att3Min, att1Mid, att2Max, att3Mid);
		node.children[2] = new Node(att1Min, att2Mid, att3Mid, att1Mid, att2Max, att3Max);
		node.children[3] = new Node(att1Min, att2Min, att3Mid, att1Mid, att2Mid, att3Max);
		node.children[4] = new Node(att1Mid, att2Min, att3Min, att1Max, att2Mid, att3Mid);
		node.children[5] = new Node(att1Mid, att2Mid, att3Min, att1Max, att2Max, att3Mid);
		node.children[6] = new Node(att1Mid, att2Mid, att3Mid, att1Max, att2Max, att3Max);
		node.children[7] = new Node(att1Mid, att2Min, att3Mid, att1Max, att2Mid, att3Max);
	}

	private boolean isRightNode(Node node, ElementOLD element) throws DBAppException {
		int comparison1Min = C.compare(node.min.get(attributes[0]), element.attribute1);
		int comparison2Min = C.compare(node.min.get(attributes[1]), element.attribute2);
		int comparison3Min = C.compare(node.min.get(attributes[2]), element.attribute3);
		int comparison1Max = C.compare(node.max.get(attributes[0]), element.attribute1);
		int comparison2Max = C.compare(node.max.get(attributes[1]), element.attribute2);
		int comparison3Max = C.compare(node.max.get(attributes[2]), element.attribute3);
		if (comparison1Min <= 0 && comparison2Min <= 0 && comparison3Min <= 0 && comparison1Max > 0
				&& comparison2Max > 0 && comparison3Max > 0)
			return true;
		int comparison1MaxRoot = C.compare(node.max.get(attributes[0]), root.max.get(attributes[0]));
		int comparison2MaxRoot = C.compare(node.max.get(attributes[1]), root.max.get(attributes[1]));
		int comparison3MaxRoot = C.compare(node.max.get(attributes[2]), root.max.get(attributes[2]));
		if (comparison1MaxRoot == 0 && comparison2MaxRoot == 0 && comparison3MaxRoot == 0)
			if (comparison1Min <= 0 && comparison2Min <= 0 && comparison3Min <= 0 && comparison1Max >= 0
					&& comparison2Max >= 0 && comparison3Max >= 0)
				return true;
		return false;
	}

	private boolean isRightNode(Node node, Hashtable<String, Object> colNameValues) throws DBAppException {
		boolean flag = false;
		Enumeration<String> keys = colNameValues.keys();
		while (keys.hasMoreElements()) {
			String currKey = keys.nextElement();
			int compareMin = C.compare(node.min.get(currKey), colNameValues.get(currKey));
			int compareMax = C.compare(node.max.get(currKey), colNameValues.get(currKey));
			int compareRoot = C.compare(root.max.get(currKey), colNameValues.get(currKey));
			if ((compareMin <= 0 && compareMax > 0) || (compareRoot == 0 && compareMin <= 0 && compareMax >= 0))
				flag = true;
			else
				return false;
		}

		return flag;
	}

	public boolean isRightElement(ElementOLD element, Hashtable<String, Object> tuple) throws DBAppException {
		Hashtable<String, Object> elementAttributes = new Hashtable<String, Object>();
		elementAttributes.put(attributes[0], element.attribute1);
		elementAttributes.put(attributes[1], element.attribute2);
		elementAttributes.put(attributes[2], element.attribute3);
		boolean flag = false;
		Enumeration<String> keys = tuple.keys();
		while (keys.hasMoreElements()) {
			String currKey = keys.nextElement();
			int comp = C.compare(tuple.get(currKey), elementAttributes.get(currKey));
			if (comp == 0)
				flag = true;
			else
				return false;
		}

		return flag;
	}
//	public boolean isRightElement(Element element, Hashtable<String, Object> tuple) throws DBAppException {
//		Object a0 = tuple.get(attributes[0]);
//		Object a1 = tuple.get(attributes[1]);
//		Object a2 = tuple.get(attributes[2]);
//		int comp0 = C.compareWNull(a0, element.attribute1);
//		int comp1 = C.compareWNull(a1, element.attribute2);
//		int comp2 = C.compareWNull(a2, element.attribute3);
//		if (comp0 == 0 && comp1 == 0 && comp2 == 0)
//			return true;
//		return false;
//	}

	public ArrayList<String> search(Hashtable<String, Object> tuple) throws DBAppException {
		ArrayList<String> pagePaths = new ArrayList<>();
		searchHelper(root, tuple, pagePaths);
		return pagePaths;
	}

	private void searchHelper(Node node, Hashtable<String, Object> tuple, ArrayList<String> pagePaths)
			throws DBAppException {
		if (node.children == null) {
			for (Vector<ElementOLD> vec : node.elements)
				for (ElementOLD element : vec) {
					if (isRightElement(element, tuple))
						pagePaths.add(element.pointer);
				}
		} else {
			for (Node child : node.children)
				if (isRightNode(child, tuple)) {
					searchHelper(child, tuple, pagePaths);
					if (tuple.size() == 3)
						break;
				}
		}
	}

	public void searchByIndex(int X) {

	}

	public String toString() {
		return root.toString(0);
	}

	public class Node implements Serializable {
		private static final long serialVersionUID = 1L;
		private Hashtable<String, Object> max = new Hashtable<String, Object>();
		private Hashtable<String, Object> min = new Hashtable<String, Object>();
		private Vector<Vector<ElementOLD>> elements = new Vector<Vector<ElementOLD>>();
		private Node[] children;

		public Node(Hashtable<String, Object> max, Hashtable<String, Object> min) throws DBAppException {
			Enumeration<String> minKeys = min.keys();
			while (minKeys.hasMoreElements()) {
				String key = minKeys.nextElement();
				this.min.put(key, min.get(key));
			}
			Enumeration<String> maxKeys = max.keys();
			while (maxKeys.hasMoreElements()) {
				String key = maxKeys.nextElement();
				this.max.put(key, max.get(key));
			}
		}

		public Node(Object attMin1, Object attMin2, Object attMin3, Object attMax1, Object attMax2, Object attMax3) {
			this.min.put(attributes[0], attMin1);
			this.min.put(attributes[1], attMin2);
			this.min.put(attributes[2], attMin3);
			this.max.put(attributes[0], attMax1);
			this.max.put(attributes[1], attMax2);
			this.max.put(attributes[2], attMax3);
		}

		public String toString(int level) {
			StringBuilder sb = new StringBuilder();
			if (children == null) {
				for (Vector<ElementOLD> e : elements) {
					sb.append(getIndent(level)).append(e.toString()).append("\n");
				}
			} else {
				for (int i = 0; i < 8; i++) {
					sb.append(getIndent(level)).append("Child ").append(i).append(": ");
					if (children[i] == null) {
						sb.append("null\n");
					} else {
						sb.append("\n");
						sb.append(getIndent(level + 1)).append("Min: ").append(children[i].getMin()).append("\n");
						sb.append(getIndent(level + 1)).append("Max: ").append(children[i].getMax()).append("\n");
						sb.append(children[i].toString(level + 1));
					}
				}
			}
			return sb.toString();
		}

		private String getIndent(int level) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < level; i++) {
				sb.append("  ");
			}
			return sb.toString();
		}

		public String getMax() {
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			for (String attribute : attributes) {
				Object att = max.get(attribute);
				if (att instanceof Date)
					att = new SimpleDateFormat("yyyy-MM-dd").format(att);
				sb.append(attribute + "=" + att + ", ");
			}
			sb.delete(sb.length() - 2, sb.length());
			sb.append("}");
			return sb.toString();
		}

		public String getMin() {
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			for (String attribute : attributes) {
				Object att = min.get(attribute);
				if (att instanceof Date)
					att = new SimpleDateFormat("yyyy-MM-dd").format(att);
				sb.append(attribute + "=" + att + ", ");
			}
			sb.delete(sb.length() - 2, sb.length());
			sb.append("}");
			return sb.toString();
		}
	}
}
