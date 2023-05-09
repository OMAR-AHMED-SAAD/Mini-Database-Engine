package applicationModules;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

import applicationModules.Octree.Node;
import exceptions.DBAppException;

public class looojiiiii {
	public static int compare(Object One, Object Two) {
		if (One instanceof Integer && Two instanceof Integer)
			return ((Integer) One).compareTo((Integer) Two);
		else if (One instanceof java.lang.String && Two instanceof java.lang.String)
			return ((java.lang.String) One).compareTo((java.lang.String) Two);
		else if (One instanceof java.lang.Double && Two instanceof java.lang.Double)
			return ((java.lang.Double) One).compareTo((java.lang.Double) Two);
		else if (One instanceof java.util.Date && Two instanceof java.util.Date)
			return ((java.util.Date) One).compareTo((java.util.Date) Two);
		return 0;

	}
	private static boolean CheckDateFormat(String date) {
		SimpleDateFormat DF=new SimpleDateFormat("yyyy-MM-dd");
		DF.setLenient(false);	
		try {
			DF.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static void main(String[] args) {

		System.out.println(CheckDateFormat("2022-12-21"));
		
//		try {
//			Properties Prop = new Properties();
//			FileInputStream inputStream = new FileInputStream("src/Resources/DBApp.config.properties");
//			Prop.load(inputStream);
//			int MaxRowCount = Integer.parseInt(Prop.getProperty("MaximumRowsCountinTablePage"));
//			System.out.println(MaxRowCount);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		Vector<Integer> vec=new Vector<Integer>();
//		vec.add(1);
//		vec.add(2);
//		vec.add(4);
//		int j=0;
//		for(int i=0;i<vec.size();i++) {
//			if(i==1)
//				vec.remove(j);
//		
//		}
////		
////		for(int i=0;i<3;i++) {
////			if(((Integer)vec.elementAt(i))>3)
////				vec.insertElementAt(3, i);
////		}
//		for(int i=0;i<vec.size();i++) {
//			System.out.println(vec.elementAt(i));
//				
//		}
		
//		Object a=new String("3");
//		Object b=new String("4");
//		System.out.println(compare(a,b));
		
//		Page p=LoadPage("src/main/DBFiles/StudentPage0.class");
//		for(Hashtable<String, Object> x: p.getVecPage()) {
//			System.out.println(x.get("id"));
//		}
	
	}

	public static Page LoadPage(String FilePath) {
		Page RestoredPage = null;
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FilePath));
			RestoredPage = (Page) objectInputStream.readObject();
			objectInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RestoredPage;
	}
}

//
//Page InsertionPage = this.LoadPage(PageFilePath.get(Min++));
//Hashtable<String, Object> PageInsertResult = InsertionPage.InsertToPage(ClustKey, ColNameValue);
//if (PageInsertResult == null) {
//	InsertionPage.UnLoadPage();
//} else {
//	//InsertionPage.UnLoadPage();
//	int ComparisonValue = this.compare(ColNameValue.get(ClustKey), PageInsertResult.get(ClustKey));
//	if (ComparisonValue == 0)
//		throw new DBAppException("Can not accept duplicate primary keys");
//	else {
//		while (Min <= PageCount && PageInsertResult != null) {
//			if (Min == PageCount) {
//				Page NewPage = new Page(PageCount, this.TblName);
//				this.PageFilePath.put(NewPage.getPageId(), NewPage.getFilePath());
//				PageInsertResult = NewPage.InsertToPage(ClustKey, PageInsertResult);
//				this.PageMaxClustKey.add(NewPage.getCurrMax());
//				PageCount++;
//				NewPage.UnLoadPage();
//				Min++;
//			} else {
//				InsertionPage = this.LoadPage(PageFilePath.get(++Min));
//				PageInsertResult = InsertionPage.InsertToPage(ClustKey, PageInsertResult);
//				InsertionPage.UnLoadPage();
//			}
//		}
//	}
//}


//main table
//public static void main(String[] args) throws IOException, DBAppException {
//
//	Hashtable<String, String> NameType = new Hashtable<String, String>();
//	NameType.put("id", "java.lang.Integer");
//	NameType.put("name", "java.lang.String");
//	NameType.put("gpa", "java.lang.double");
//
//	Hashtable<String, String> min = new Hashtable<String, String>();
//	min.put("id", "0");
//	min.put("name", "A");
//	min.put("gpa", "0");
//
//	Hashtable<String, String> max = new Hashtable<String, String>();
//	max.put("id", "1000");
//	max.put("name", "ZZZZZZZZZZZZ");
//	max.put("gpa", "1000");
//
//	Table t = new Table("Student");
//
//	//t.AddMetaData("id",NameType, min, max);
//	
//
//	t.ReadMetaData();
//	Hashtable<String, Object> htblColNameValue1 = new Hashtable<String, Object>();
//	htblColNameValue1.put("id", new Integer(1));
//	htblColNameValue1.put("name", new String("Ahmed"));
//	htblColNameValue1.put("gpa", new Double(0.69));
//
//	Hashtable<String, Object> htblColNameValue2 = new Hashtable<String, Object>();
//	htblColNameValue2.put("id", new Integer(2));
//	htblColNameValue2.put("name", new String("Loji"));
//	htblColNameValue2.put("gpa", new Double(1.0));
//
//	Hashtable<String, Object> htblColNameValue3 = new Hashtable<String, Object>();
//	htblColNameValue3.put("id", new Integer(3));
//	htblColNameValue3.put("name", new String("Hamada"));
//	htblColNameValue3.put("gpa", new Double(0.85));
//	// insert tuples
//	t.InsertInTable(htblColNameValue1);
//	t.InsertInTable(htblColNameValue2);
//	t.InsertInTable(htblColNameValue3);
//
//	// delete tuples
//	// htblColNameValue3.remove("id");
////	htblColNameValue3.remove("gpa");
////	t.DelFromTbl(htblColNameValue3);
//
//	// update tuples
//	htblColNameValue2.remove("id");
//	t.UpdateTbl(new Integer(1), htblColNameValue2);
//
////	System.out.println(t.SearchByCk(new Integer (2)));
//
//	try {
//		Page p = t.LoadPage("src/main/DBFiles/Pages/StudentPage0.bin");
//		for (Hashtable<String, Object> x : p.getVecPage())
//			System.out.println(x.get("id") + " " + x.get("name") + " " + x.get("gpa"));
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	try {
//		Page p = t.LoadPage("src/main/DBFiles/Pages/StudentPage1.bin");
//		for (Hashtable<String, Object> x : p.getVecPage())
//			System.out.println(x.get("id") + " " + x.get("name") + " " + x.get("gpa"));
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//
//}
//
//package application;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Hashtable;
//
//import applicationModules.Page;
//import applicationModules.Table;
//
//public class LojiPlzRead {
//	public static void main(String[] args) throws FileNotFoundException, IOException {
//		DBApp db=new DBApp(); 
//		System.out.println(db.CreatedTables.toString());
//		Table t = new Table("student");
//		try {
//			Page p = t.LoadPage("src/main/DBFiles/Pages/StudentPage0.bin");
//			for (Hashtable<String, Object> x : p.getVecPage())
//				System.out.println(x.get("id") + " " + x.get("name") + " " + x.get("gpa"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			Page p = t.LoadPage("src/main/DBFiles/Pages/StudentPage3.bin");
//			for (Hashtable<String, Object> x : p.getVecPage())
//				System.out.println(x.get("id") + " " + x.get("name") + " " + x.get("gpa"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			Page p = t.LoadPage("src/main/DBFiles/Pages/StudentPage4.bin");
//			for (Hashtable<String, Object> x : p.getVecPage())
//				System.out.println(x.get("id") + " " + x.get("name") + " " + x.get("gpa"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
//


//package application;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Hashtable;
//
//import exceptions.DBAppException;
//
//public class test {
//	public static void main(String[] args) throws FileNotFoundException, IOException, DBAppException {
//		String tblName = "Student";
//		Hashtable<String, String> NameType = new Hashtable<String, String>();
//		NameType.put("id", "java.lang.Integer");
//		NameType.put("name", "java.lang.String");
//		NameType.put("gpa", "java.lang.double");
//
//		Hashtable<String, String> min = new Hashtable<String, String>();
//		min.put("id", "0");
//		min.put("name", "A");
//		min.put("gpa", "0");
//
//		Hashtable<String, String> max = new Hashtable<String, String>();
//		max.put("id", "1000");
//		max.put("name", "ZZZZZZZZZZZZ");
//		max.put("gpa", "1000");
//
//		DBApp db = new DBApp();
//
////		db.createTable(tblName, "id", NameType, min, max);
//
//		Hashtable<String, Object> htblColNameValue1 = new Hashtable<String, Object>();
//		htblColNameValue1.put("id", new Integer(1));
//		htblColNameValue1.put("name", new String("Ahmed"));
//		htblColNameValue1.put("gpa", new Double(0.69));
//
//		Hashtable<String, Object> htblColNameValue2 = new Hashtable<String, Object>();
//		htblColNameValue2.put("id", new Integer(2));
//		htblColNameValue2.put("name", new String("Loji"));
//		htblColNameValue2.put("gpa", new Double(1.0));
//
//		Hashtable<String, Object> htblColNameValue3 = new Hashtable<String, Object>();
//		htblColNameValue3.put("id", new Integer(7));
//		htblColNameValue3.put("name", new String("Hamada"));
//		htblColNameValue3.put("gpa", new Double(0.85));
//
//		Hashtable<String, Object> htblColNameValue4 = new Hashtable<String, Object>();
//		htblColNameValue4.put("id", new Integer(4));
//		htblColNameValue4.put("name", new String("Ali"));
//		htblColNameValue4.put("gpa", new Double(1.0));
//
//		Hashtable<String, Object> htblColNameValue5 = new Hashtable<String, Object>();
//		htblColNameValue5.put("id", new Integer(5));
//		htblColNameValue5.put("name", new String("Nelly"));
//		htblColNameValue5.put("gpa", new Double(2.0));
//
////		db.insertIntoTable(tblName,htblColNameValue1);
//		// db.insertIntoTable(tblName,htblColNameValue2);
//		// db.insertIntoTable(tblName,htblColNameValue3);
//		// db.insertIntoTable(tblName,htblColNameValue4);
//		// db.insertIntoTable(tblName,htblColNameValue5);
//
//		// delete tuples
////		htblColNameValue2.remove("id");
////		htblColNameValue2.remove("name");
////		htblColNameValue2.remove("gpa");
////		db.deleteFromTable(tblName, htblColNameValue2);
//
//		// update tuples
////		htblColNameValue2.remove("id");
////		htblColNameValue2.remove("gpa");
////		htblColNameValue2.remove("name");
////		htblColNameValue2.put("gpa", new Double(0.75));
////		db.updateTable(tblName,"1", htblColNameValue2);
//
//	}
//}






//public String toString() {
//	StringBuilder sb = new StringBuilder();
//	sb.append("Table Name: ").append(TblName).append("\n");
//	sb.append("Primary Key Name: ").append(CKName).append("\n");
//	sb.append("Page File Paths: ").append(PageFilePath).append("\n");
//	sb.append("Maximum Page Values: ").append(MaxPage).append("\n");
//	sb.append("Minimum Page Values: ").append(MinPage).append("\n");
//	sb.append("Is Page Full: ").append(IsPgFull).append("\n");
//	sb.append("Table Pages: ").append(TablePages).append("\n");
//	sb.append("Page ID Increment: ").append(PageIdInc).append("\n");
//	sb.append("Column Name Type: ").append(ColumnNameType).append("\n");
//	sb.append("Column Name Minimum: ").append(ColumnNameMin).append("\n");
//	sb.append("Column Name Maximum: ").append(ColumnNameMax).append("\n");
//	sb.append("Creation Order: ").append(creationOrder).append("\n");
//	return sb.toString();
//}




//public void createChildren(Node node) throws DBAppException {
//	Hashtable<String, Object> mid = new Hashtable<String, Object>();
//	mid.put(attributes[0], GM.getMid(node.min.get(attributes[0]), node.max.get(attributes[0])));
//	mid.put(attributes[1], GM.getMid(node.min.get(attributes[1]), node.max.get(attributes[1])));
//	mid.put(attributes[2], GM.getMid(node.min.get(attributes[2]), node.max.get(attributes[2])));
//	Hashtable<String, Object> maxNewNd = new Hashtable<String, Object>();
//	Hashtable<String, Object> minNewNd = new Hashtable<String, Object>();
//	minNewNd.put(attributes[0], node.min.get(attributes[0]));
//	minNewNd.put(attributes[1], node.min.get(attributes[1]));
//	minNewNd.put(attributes[2], node.min.get(attributes[2]));
//	maxNewNd.put(attributes[0], mid.get(attributes[0]));
//	maxNewNd.put(attributes[1], mid.get(attributes[1]));
//	maxNewNd.put(attributes[2], mid.get(attributes[2]));
//	node.children[0] = new Node(maxNewNd, minNewNd);
//	minNewNd.put(attributes[1], mid.get(attributes[1]));
//	maxNewNd.put(attributes[1], node.max.get(attributes[1]));
//	node.children[1] = new Node(maxNewNd, minNewNd);
//	minNewNd.put(attributes[2], mid.get(attributes[2]));
//	maxNewNd.put(attributes[2], node.max.get(attributes[2]));
//	node.children[2] = new Node(maxNewNd, minNewNd);
//	minNewNd.put(attributes[1], node.min.get(attributes[1]));
//	maxNewNd.put(attributes[1], mid.get(attributes[1]));
//	node.children[3] = new Node(maxNewNd, minNewNd);
//	minNewNd.put(attributes[0], mid.get(attributes[0]));
//	maxNewNd.put(attributes[0], node.max.get(attributes[0]));
//	minNewNd.put(attributes[2], node.min.get(attributes[2]));
//	maxNewNd.put(attributes[2], mid.get(attributes[2]));
//	node.children[4] = new Node(maxNewNd, minNewNd);
//	minNewNd.put(attributes[1], mid.get(attributes[1]));
//	maxNewNd.put(attributes[1], node.max.get(attributes[1]));
//	node.children[5] = new Node(maxNewNd, minNewNd);
//	minNewNd.put(attributes[2], mid.get(attributes[2]));
//	maxNewNd.put(attributes[2], node.max.get(attributes[2]));
//	node.children[6] = new Node(maxNewNd, minNewNd);
//	minNewNd.put(attributes[1], node.min.get(attributes[1]));
//	maxNewNd.put(attributes[1], mid.get(attributes[1]));
//	node.children[7] = new Node(maxNewNd, minNewNd);
//}


//Test Ranges
//	public static void main(String[] args) throws DBAppException {
//		Hashtable<String, Object> max = new Hashtable<String, Object>();
//		Hashtable<String, Object> min = new Hashtable<String, Object>();
//		min.put("x", 0);
//		min.put("y", 0);
//		min.put("z", 0);
//		max.put("x", 8);
//		max.put("y", 80);
//		max.put("z", 800);
//		Octree o = new Octree("lol", "x", "y", "z", max, min);
//		o.split(o.root);
//		System.out.println(o);
//	}