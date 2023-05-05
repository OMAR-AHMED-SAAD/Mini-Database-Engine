package applicationModules;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import basicTools.ComparatorI;
import basicTools.GetMidI;
import exceptions.DBAppException;

public class Octree implements Serializable, ComparatorI, GetMidI {
	private static final long serialVersionUID = 1L;
	private Node root;
	private String FilePath;
	private String[] attributes;
	private int maxElements;

	public static void main(String[] args) throws DBAppException {
		Hashtable<String, Object> max = new Hashtable<String, Object>();
		Hashtable<String, Object> min = new Hashtable<String, Object>();
		min.put("x", 0);
		min.put("y", 0);
		min.put("z", 0);
		max.put("x", 8);
		max.put("y", 80);
		max.put("z", 800);
		Octree o = new Octree("lol", "x", "y", "z", max, min);
		o.split(o.root);
		System.out.println(o);
	}

	public Octree(String tblName, String A1, String A2, String A3, Hashtable<String, Object> max,
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
				Element element = new Element(attribute1, attribute2, attribute3, filePath);
				insert(element);
			}
		}
		return true;
	}

	public void insert(Element element) throws DBAppException {
		insert(root, element);
	}

	private void insert(Node node, Element element) throws DBAppException {
		if (node.elements.size() < this.maxElements && node.children == null) {
			insertElement(node.elements, element);
			return;
		} else if (node.elements.size() == this.maxElements && node.children == null)
			if (!insertElement(node.elements, element))
				split(node);
		for (Node child : node.children)
			if (isRightNode(child, element)) {
				insert(child, element);
				break;
			}

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
		if (!inserted && elements.size() == this.maxElements) {
			return false;
		}
		if (elements.isEmpty() && !inserted) {
			Vector<Element> elementsVector = new Vector<Element>();
			elementsVector.add(element);
			elements.add(elementsVector);
		}
		return true;
	}

	private void split(Node node) throws DBAppException {
		node.children = new Node[8];
		createChildren1(node);
		Vector<Vector<Element>> elementsSplit = node.elements;
		node.elements = null;
		for (Vector<Element> vec : elementsSplit)
			for (Element element : vec)
				insert(node, element);
	}

	public void createChildren(Node node) throws DBAppException {
		Hashtable<String, Object> mid = new Hashtable<String, Object>();
		mid.put(attributes[0], GM.getMid(node.min.get(attributes[0]), node.max.get(attributes[0])));
		mid.put(attributes[1], GM.getMid(node.min.get(attributes[1]), node.max.get(attributes[1])));
		mid.put(attributes[2], GM.getMid(node.min.get(attributes[2]), node.max.get(attributes[2])));
		Hashtable<String, Object> maxNewNd = new Hashtable<String, Object>();
		Hashtable<String, Object> minNewNd = new Hashtable<String, Object>();
		minNewNd.put(attributes[0], node.min.get(attributes[0]));
		minNewNd.put(attributes[1], node.min.get(attributes[1]));
		minNewNd.put(attributes[2], node.min.get(attributes[2]));
		maxNewNd.put(attributes[0], mid.get(attributes[0]));
		maxNewNd.put(attributes[1], mid.get(attributes[1]));
		maxNewNd.put(attributes[2], mid.get(attributes[2]));
		node.children[0] = new Node(maxNewNd, minNewNd);
		minNewNd.put(attributes[1], mid.get(attributes[1]));
		maxNewNd.put(attributes[1], node.max.get(attributes[1]));
		node.children[1] = new Node(maxNewNd, minNewNd);
		minNewNd.put(attributes[2], mid.get(attributes[2]));
		maxNewNd.put(attributes[2], node.max.get(attributes[2]));
		node.children[2] = new Node(maxNewNd, minNewNd);
		minNewNd.put(attributes[1], node.min.get(attributes[1]));
		maxNewNd.put(attributes[1], mid.get(attributes[1]));
		node.children[3] = new Node(maxNewNd, minNewNd);
		minNewNd.put(attributes[0], mid.get(attributes[0]));
		maxNewNd.put(attributes[0], node.max.get(attributes[0]));
		minNewNd.put(attributes[2], node.min.get(attributes[2]));
		maxNewNd.put(attributes[2], mid.get(attributes[2]));
		node.children[4] = new Node(maxNewNd, minNewNd);
		minNewNd.put(attributes[1], mid.get(attributes[1]));
		maxNewNd.put(attributes[1], node.max.get(attributes[1]));
		node.children[5] = new Node(maxNewNd, minNewNd);
		minNewNd.put(attributes[2], mid.get(attributes[2]));
		maxNewNd.put(attributes[2], node.max.get(attributes[2]));
		node.children[6] = new Node(maxNewNd, minNewNd);
		minNewNd.put(attributes[1], node.min.get(attributes[1]));
		maxNewNd.put(attributes[1], mid.get(attributes[1]));
		node.children[7] = new Node(maxNewNd, minNewNd);
	}

	public void createChildren1(Node node) throws DBAppException {
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

	public boolean isRightNode(Node node, Element element) throws DBAppException {
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
		int comparison3MaxRoot = C.compare(node.max.get(attributes[2]), root.max.get(attributes[1]));
		if (comparison1MaxRoot == 0 && comparison2MaxRoot == 0 && comparison3MaxRoot == 0)
			if (comparison1Min <= 0 && comparison2Min <= 0 && comparison3Min <= 0 && comparison1Max >= 0
					&& comparison2Max >= 0 && comparison3Max >= 0)
				return true;
		return false;
	}

	public String toString() {
		return root.toString(0);
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
				sb.append(attribute + "=" + max.get(attribute) + ", ");
			}
			sb.delete(sb.length() - 2, sb.length());
			sb.append("}");
			return sb.toString();
		}

		public String getMin() {
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			for (String attribute : attributes) {
				sb.append(attribute + "=" + min.get(attribute) + ", ");
			}
			sb.delete(sb.length() - 2, sb.length());
			sb.append("}");
			return sb.toString();
		}
	}
}
