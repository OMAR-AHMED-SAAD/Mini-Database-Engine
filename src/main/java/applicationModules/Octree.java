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

import application.SQLTerm;
import basicTools.ComparatorI;
import basicTools.GetMidI;
import exceptions.DBAppException;

public class Octree implements Serializable, ComparatorI, GetMidI {
	private static final long serialVersionUID = 1L;
	private Node root;
	private String FilePath;
	private String[] attributes;
	private int maxElements;

	public Octree(String tblName, String A1, String A2, String A3, Hashtable<String, Object> min,
			Hashtable<String, Object> max) throws DBAppException {
		attributes = new String[3];
		attributes[0] = A1;
		attributes[1] = A2;
		attributes[2] = A3;
		FilePath = "src/main/resources/Indices/" + tblName + A1 + A2 + A3 + ".bin";
		root = new Node(max, min);
		Properties Prop = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream("src/main/resources/DBApp.config");
			Prop.load(inputStream);
		} catch (Exception e) {
			throw new DBAppException(e.getMessage());
		}
		this.maxElements = Integer.parseInt(Prop.getProperty("MaximumEntriesinOctreeNode"));

	}

	public void UnLoadTree() throws DBAppException {
		try {
			ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(new FileOutputStream(FilePath));
			ObjectOutputStream.writeObject(this);
			ObjectOutputStream.close();
		} catch (Exception e) {
			throw new DBAppException(e.getMessage());
		}
	}

	public void populate(Table tbl) throws DBAppException {
		for (int pageId : tbl.getTablePages()) {
			String filePath = tbl.getPageFilePath().get(pageId);
			Page pg = tbl.LoadPage(filePath);
			for (Hashtable<String, Object> row : pg.getVecPage()) {
				Enumeration<String> keys = row.keys();
				while (keys.hasMoreElements()) {
					String key = keys.nextElement();
					if (!attributes[0].equals(key) && !attributes[1].equals(key) && !attributes[2].equals(key))
						row.remove(key);
				}
				Element element = new Element(row, filePath);
				insert(element);
			}
		}
	}

	public void insert(Element element) throws DBAppException {
		insert(root, element);
	}

	private void insert(Node node, Element element) throws DBAppException {
		if (node.elements != null && node.elements.size() < this.maxElements && node.children == null) {
			insertElement(node.elements, element);
			return;
		} else if (node.elements != null && node.elements.size() == this.maxElements && node.children == null)
			if (!insertElement(node.elements, element))
				split(node);
			else
				return;
		for (Node child : node.children)
			if (isRightNode(child, element.htblAttributes)) {
				insert(child, element);
				break;
			}
	}

	// when we want to check duplicates we used compareWNull instead of
	// fullnullsupport as we don't want to consider nulls duplicates to each other
	private boolean insertElement(Vector<Vector<Element>> elements, Element element) throws DBAppException {
		boolean inserted = false;
		for (Vector<Element> vec : elements) {
			for (Element existingElement : vec) {
				int comparison1 = C.compareWNull(existingElement.htblAttributes.get(attributes[0]),
						element.htblAttributes.get(attributes[0]));
				int comparison2 = C.compareWNull(existingElement.htblAttributes.get(attributes[1]),
						element.htblAttributes.get(attributes[1]));
				int comparison3 = C.compareWNull(existingElement.htblAttributes.get(attributes[2]),
						element.htblAttributes.get(attributes[2]));
				if (comparison1 == 0 || comparison2 == 0 || comparison3 == 0) { // change to and if we don't want to
																				// support null values & throw exception
																				// in table
					vec.add(element);
					inserted = true;
					return true;
				}
			}
		}
		if (!inserted && elements.size() == this.maxElements) {
			return false;
		}
		if (elements.size() < this.maxElements && !inserted) {
			Vector<Element> elementsVector = new Vector<Element>();
			elementsVector.add(element);
			elements.add(elementsVector);
		}
		return true;
	}

	private void split(Node node) throws DBAppException {
		node.children = new Node[8];
		createChildren(node);
		for (Vector<Element> vec : node.elements)
			for (Element element : vec)
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

	// searches for all elements that matches & matches on null
	public ArrayList<String> search(Hashtable<String, Object> tuple) throws DBAppException {
		ArrayList<String> pagePaths = new ArrayList<>();
		searchHelper(root, tuple, pagePaths);
		return pagePaths;
	}

	private void searchHelper(Node node, Hashtable<String, Object> tuple, ArrayList<String> pagePaths)
			throws DBAppException {
		if (node.children == null) {
			for (Vector<Element> vec : node.elements)
				for (Element element : vec) {
					if (isRightElement(element, tuple))
						pagePaths.add(element.pointer);
				}
		} else {
			for (Node child : node.children)
				if (isRightNode(child, tuple)) {
					searchHelper(child, tuple, pagePaths);
					if (tuple.size() == 3)//// check risk!!!
						break;
				}
		}
	}

	public boolean isRightElement(Element element, Hashtable<String, Object> tuple) throws DBAppException {
		boolean flag = false;
		Enumeration<String> keys = tuple.keys();
		while (keys.hasMoreElements()) {
			String currKey = keys.nextElement();
			int comp = C.compareNullSupport(tuple.get(currKey), element.htblAttributes.get(currKey));
			if (comp == 0)
				flag = true;
			else
				return false;
		}

		return flag;
	}

	// deletes one element & matches on null
	public void delete(Hashtable<String, Object> tuple, String filePath) throws DBAppException {
		deleteHelper(root, tuple, filePath);
	}

	private void deleteHelper(Node node, Hashtable<String, Object> tuple, String filePath) throws DBAppException {
		if (node.children == null) {
			for (Vector<Element> vec : node.elements)
				for (Element element : vec)
					if (isRightElement(element, tuple) && element.pointer.equals(filePath)) {
						vec.removeElement(element);
						if (vec.isEmpty())
							node.elements.remove(vec);
						return;
					}
		} else {
			for (Node child : node.children)
				if (isRightNode(child, tuple)) {
					deleteHelper(child, tuple, filePath);
					if (tuple.size() == 3)//// check risk!!!
						break;
				}
		}
	}

	// updates one element & matches null
	public void updatePageRef(Hashtable<String, Object> tuple, String oldFilePath, String newFilePath)
			throws DBAppException {
		updtPgRef(root, tuple, oldFilePath, newFilePath);
	}

	private void updtPgRef(Node node, Hashtable<String, Object> tuple, String oldFilePath, String newFilePath)
			throws DBAppException {
		if (node.children == null) {
			for (Vector<Element> vec : node.elements)
				for (Element element : vec)
					if (isRightElement(element, tuple) && element.pointer.equals(oldFilePath)) {
						element.pointer = newFilePath;
						return;
					}
		} else {
			for (Node child : node.children)
				if (isRightNode(child, tuple)) {
					updtPgRef(child, tuple, oldFilePath, newFilePath);
					if (tuple.size() == 3)//// check risk!!!
						break;
				}
		}
	}

	// search with support for range queries
	public ArrayList<String> searchRange(SQLTerm[] sqlterms) throws DBAppException {
		ArrayList<String> pagePaths = new ArrayList<>();
		searchRangeHelper(root, sqlterms, pagePaths);
		return pagePaths;
	}

	private void searchRangeHelper(Node node, SQLTerm[] sqlterms, ArrayList<String> pagePaths) throws DBAppException {
		if (node.children == null) {
			for (Vector<Element> vec : node.elements)
				for (Element element : vec)
					if (isRightElement(element, sqlterms))
						pagePaths.add(element.pointer);
		} else
			for (Node child : node.children)
				if (isRightNode(child, sqlterms))
					searchRangeHelper(child, sqlterms, pagePaths);
	}

	private boolean isRightNode(Node node, SQLTerm[] sqlterms) throws DBAppException {
		boolean result = true;
		for (SQLTerm term : sqlterms) {
			Object min = node.min.get(term.get_strColumnName());
			Object max = node.max.get(term.get_strColumnName());
			boolean isMAxIncluded = max.equals(root.max.get(term.get_strColumnName()));
			boolean comparison = C.compareWithOperator(min, max, term.get_strOperator(), term.get_objValue(),
					isMAxIncluded);
			result &= comparison;
		}
		return result;
	}

	public boolean isRightElement(Element element, SQLTerm[] sqlterms) throws DBAppException {
		boolean result = true;
		for (SQLTerm term : sqlterms) {
			Object currvalue = element.htblAttributes.get(term.get_strColumnName());
			boolean comparison = C.compareWithOperator(currvalue, term.get_objValue(), term.get_strOperator());
			result &= comparison;
		}
		return result;

	}

	public String toString() {

		return root.toString(0) + "\n" + Element.count;// delete

	}

	public class Node implements Serializable {
		private static final long serialVersionUID = 1L;
		private Hashtable<String, Object> max = new Hashtable<String, Object>();
		private Hashtable<String, Object> min = new Hashtable<String, Object>();
		private Vector<Vector<Element>> elements = new Vector<Vector<Element>>();
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
				for (Vector<Element> e : elements) {
					sb.append(getIndent(level)).append(e.toString()).append("\n");
				}
			} else {
				for (int i = 0; i < 8; i++) {
					sb.append(getIndent(level)).append("Child ").append(i).append(": ");
					if (children[i] == null) {
						sb.append("null\n");
					} else {
						sb.append("\n");
						sb.append(getIndent(level + 1)).append("Min: ").append(children[i].printMin()).append("\n");
						sb.append(getIndent(level + 1)).append("Max: ").append(children[i].printMax()).append("\n");
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

		public String toString() {
			StringBuffer sb = new StringBuffer();
			// sb.append("max"+getMax()).append("\n").append("min"+getMin());
			// sb.append("\n");
			if (this.elements != null) {
				for (Vector<Element> vec : elements) {
					for (Element e : vec) {
						sb.append(e).append(" ");
						sb.append("\n");
					}
				}
			}
			if (this.children != null) {
				for (Node c : children)
					sb.append(c.toString());
			}

			return sb.toString();
		}

		public String printMax() {
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

		public String printMin() {
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

	public class Element implements Serializable {
		private static final long serialVersionUID = 1L;
		Hashtable<String, Object> htblAttributes = new Hashtable<String, Object>();
		String pointer;
		static int count = 0;// delete

		public Element(Hashtable<String, Object> att, String filePath) {
			Enumeration<String> keys = att.keys();
			while (keys.hasMoreElements()) {
				String key = (String) keys.nextElement();
				htblAttributes.put(key, att.get(key));
			}
			this.pointer = filePath;
		}

		public String toString() {
			count++;// delete
			Object att1 = htblAttributes.get(attributes[0]);
			Object att2 = htblAttributes.get(attributes[1]);
			Object att3 = htblAttributes.get(attributes[2]);
			if (att1 instanceof Date)
				att1 = new SimpleDateFormat("yyyy-MM-dd").format(att1);
			if (att2 instanceof Date)
				att2 = new SimpleDateFormat("yyyy-MM-dd").format(att2);
			if (att3 instanceof Date)
				att3 = new SimpleDateFormat("yyyy-MM-dd").format(att3);

			return "Element{" + "att1=" + att1 + ", att2=" + att2 + ", att3=" + att3 + ", ptr='" + pointer.substring(25)
					+ '\'' + '}';
		}
	}
}
