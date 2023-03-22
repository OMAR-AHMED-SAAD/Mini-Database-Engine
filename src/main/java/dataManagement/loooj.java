package dataManagement;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Hashtable;

public class loooj {
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

	public static void main(String[] args) {

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

//		Vector<Integer> vec=new Vector<Integer>();
//		vec.add(1);
//		vec.add(2);
//		vec.add(4);
//		
//		for(int i=0;i<3;i++) {
//			if(((Integer)vec.elementAt(i))>3)
//				vec.insertElementAt(3, i);
//		}
//		for(int i=0;i<vec.size();i++) {
//			System.out.println(vec.elementAt(i));
//				
//		}
//		
//		Object a=new String("3");
//		Object b=new String("4");
//		System.out.println(compare(a,b));
		
		Page p=LoadPage("src/main/DBFiles/StudentPage0.class");
		for(Hashtable<String, Object> x: p.getVecPage()) {
			System.out.println(x.get("id"));
		}
		
		
		
		
		
		

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
