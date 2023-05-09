package applicationModules;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	public String toString() {
		Object att1=attribute1;
		Object att2=attribute2;
		Object att3=attribute3;
		if(attribute1 instanceof Date)
		att1=new SimpleDateFormat("yyyy-MM-dd").format(attribute1);
		if(attribute2 instanceof Date)
			att2=new SimpleDateFormat("yyyy-MM-dd").format(attribute2);
		if(attribute3 instanceof Date)
			att3=new SimpleDateFormat("yyyy-MM-dd").format(attribute3);
			
		return "Element{" + "att1=" + att1 + ", att2=" + att2 + ", att3=" + att3
				+ ", ptr='" + pointer.substring(23) + '\'' + '}';
	}
}
