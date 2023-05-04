package applicationModules;

import java.io.Serializable;

public class Element implements Serializable {
	private static final long serialVersionUID = 1L;
	Object attribute1;
	Object attribute2;
	Object attribute3;
	String pointer;

	public Element(Object attribute1, Object attribute2, Object attribute3, String filePath) {
		this.attribute1 = attribute1;
		this.attribute2 = attribute2;
		this.attribute3 = attribute3;
		this.pointer = filePath;
	}
}
