package dataManagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Date;

import exceptions.DBAppException;

public class Table {

	private String TblName;
	private String CKName;
	private String CkType;
	private Hashtable<Integer, String> PageFilePath = new Hashtable<Integer, String>();
	private Hashtable<Integer, Object> MaxPage = new Hashtable<Integer, Object>();
	private Hashtable<Integer, Object> MinPage = new Hashtable<Integer, Object>();
	private Hashtable<Integer, Boolean> IsPgFull = new Hashtable<Integer, Boolean>();
	private Vector<Integer> TablePages = new Vector<Integer>();
	private int PageIdInc;

	public Table(String name, String ClustKey, String CkType) {
		this.TblName = name;
		this.CKName = ClustKey;
		this.CkType = CkType;
		PageIdInc = 0;

	}

	public Page LoadPage(String FilePath) {
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

	public void AddMetaData(Hashtable<String, String> ColNameType, Hashtable<String, String> ColNameMin,
			Hashtable<String, String> ColNameMax) throws IOException {
		String FilePath = "src/main/DBFiles/metadata.csv";
		boolean FileExist = false;
		if (new File(FilePath).exists()) {
			FileExist = true;
		}
		FileWriter fw = new FileWriter(FilePath, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		if (!FileExist)
			pw.println("TableName" + "," + "Column Name" + "," + "Column Type" + "," + "ClusteringKey" + ","
					+ "IndexName" + "," + "IndexType" + "," + "min" + "," + "max");
		ColNameType.forEach(
				(key, value) -> pw.println(this.TblName + "," + key + "," + value + "," + key.equals(this.CKName)
						+ "," + "null" + "," + "null" + "," + ColNameMin.get(key) + "," + ColNameMax.get(key)));
		pw.flush();
		pw.close();
	}

	public void InsertInTable(Hashtable<String, Object> ColNameValue) throws DBAppException {
		Object CK = ColNameValue.get(this.CKName);
		if (TablePages.size() == 0)
			CreateAddNewPage(ColNameValue);
		else if (TablePages.size() > 0) {
			for (int i = 0; i < TablePages.size(); i++) {
				int Pid = TablePages.get(i);
				Object PageMinVal = MinPage.get(Pid);
				Object PageMaxVal = MaxPage.get(Pid);
				Boolean IsPgF = IsPgFull.get(Pid);
				Boolean IsLastPg = (i == (TablePages.size() - 1));
				Page InstPg;
				Hashtable<String, Object> PgInstRes;
				if (compare(CK, PageMinVal) < 0 || (compare(CK, PageMinVal) > 0 && compare(CK, PageMaxVal) < 0)) {
					InstPg = LoadPage(this.PageFilePath.get(Pid));
					PgInstRes = InstPg.InsertToPage(this.CKName, ColNameValue);
					UpTblData(InstPg);
					OverflowSolver(PgInstRes);
					break;
				} else if (compare(CK, PageMaxVal) > 0 && IsPgF && IsLastPg) {
					CreateAddNewPage(ColNameValue);
					break;
				} else if (compare(CK, PageMaxVal) > 0 && IsPgF && !IsLastPg) {
					continue;
				} else if (compare(CK, PageMaxVal) > 0 && !IsPgF && IsLastPg) {
					InstPg = LoadPage(this.PageFilePath.get(Pid));
					PgInstRes = InstPg.InsertToPage(this.CKName, ColNameValue);
					UpTblData(InstPg);
					OverflowSolver(PgInstRes);
					break;
				} else if (compare(CK, PageMaxVal) > 0 && !IsPgF && !IsLastPg) {
					int NxtPid = TablePages.get(i);
					Object NxtPageMinVal = MinPage.get(NxtPid);
					if (compare(CK, NxtPageMinVal) < 0) {
						InstPg = LoadPage(this.PageFilePath.get(Pid));
						PgInstRes = InstPg.InsertToPage(this.CKName, ColNameValue);
						UpTblData(InstPg);
						OverflowSolver(PgInstRes);
						break;
					} else
						continue;
				} else
					throw new DBAppException("Can not accept duplicate primary keys");
			}
		}
	}

	private void CreateAddNewPage(Hashtable<String, Object> ColNameValue) throws DBAppException {
		Page CreatedPage = new Page(PageIdInc, this.TblName);
		TablePages.add(CreatedPage.getPageId());
		this.PageFilePath.put(CreatedPage.getPageId(), CreatedPage.getFilePath());
		CreatedPage.InsertToPage(CKName, ColNameValue);
		UpTblData(CreatedPage);
		PageIdInc++;

	}

	private void UpTblData(Page Pg) {
		MaxPage.put(Pg.getPageId(), Pg.getCurrMax());
		MinPage.put(Pg.getPageId(), Pg.getCurrMin());
		IsPgFull.put(Pg.getPageId(), Pg.IsFull());
		Pg.UnLoadPage();
	}

	private void OverflowSolver(Hashtable<String, Object> PgInstRes) throws DBAppException {

		if (PgInstRes == null)
			return;
		else
			InsertInTable(PgInstRes);

	}

	public int compare(Object One, Object Two) {
		if (One instanceof java.lang.Integer && Two instanceof java.lang.Integer)
			return ((java.lang.Integer) One).compareTo((java.lang.Integer) Two);
		else if (One instanceof java.lang.String && Two instanceof java.lang.String)
			return ((java.lang.String) One).compareTo((java.lang.String) Two);
		else if (One instanceof java.util.Date && Two instanceof java.util.Date)
			return ((java.util.Date) One).compareTo((java.util.Date) Two);
		else
			return ((java.lang.Double) One).compareTo((java.lang.Double) Two);

	}

	public Object ParsingCk(String CkVal) throws ParseException {

		switch (CkType) {
		case "java.lang.Integer":
			return new Integer(Integer.parseInt(CkVal));
		case "java.lang.String":
			return new String(CkVal);
		case "java.util.Date":
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(CkVal);
			return date;
		case "java.lang.Double":
			return new Double(Double.parseDouble(CkVal));
		default:
			return new String(CkVal);
		}
	}

	public Page SearchByCk(String CkVal) throws ParseException {
		Object CkValO = ParsingCk(CkVal);
		Boolean IsPgFound = false;
		int PgId = 0;
		int Min = 0;
		int Max = TablePages.size() - 1;
		while (Min < Max) {
			int Mid = (Min + Max) / 2;
			Object MinVal = MinPage.get(TablePages.get(Mid));
			Object MaxVal = MaxPage.get(TablePages.get(Mid));
			if (compare(CkValO, MinVal) >= 0 && compare(CkValO, MaxVal) <= 0) {
				PgId = Mid;
				IsPgFound = true;
				break;
			} else if (compare(CkValO, MinVal) < 0)
				Max = Mid - 1;
			else if (compare(CkValO, MaxVal) > 0)
				Min = Mid + 1;
		}
		if (!IsPgFound)
			return null;
		else {
			Page Pg = LoadPage(PageFilePath.get(PgId));
			if (Pg.IsRowFound(CKName,CkValO))
				return Pg;
			else
				return null;
		}
	}

	public static void main(String[] args) throws IOException, ParseException {

		Hashtable<String, String> NameType = new Hashtable<String, String>();
		NameType.put("id", "java.lang.Integer");
		NameType.put("name", "java.lang.String");
		NameType.put("gpa", "java.lang.double");

		Hashtable<String, String> min = new Hashtable<String, String>();
		min.put("id", "0");
		min.put("name", "A");
		min.put("gpa", "0");

		Hashtable<String, String> max = new Hashtable<String, String>();
		max.put("id", "1000");
		max.put("name", "ZZZZZZZZZZZZ");
		max.put("gpa", "1000");

		Table t = new Table("Student", "id", "java.util.Date");
		// Table t2 = new Table("Doctor", "id");

		Hashtable<String, Object> htblColNameValue = new Hashtable<String, Object>();
		htblColNameValue.put("id", new Integer(1));
		htblColNameValue.put("name", new String("Ahmed Noor"));
		htblColNameValue.put("gpa", new Double(0.95));

		Hashtable<String, Object> htblColNameValue1 = new Hashtable<String, Object>();
		htblColNameValue1.put("id", new Integer(2));
		htblColNameValue1.put("name", new String("Ahmed Noor"));
		htblColNameValue1.put("gpa", new Double(0.95));

		Hashtable<String, Object> htblColNameValue2 = new Hashtable<String, Object>();
		htblColNameValue2.put("id", new Integer(3));
		htblColNameValue2.put("name", new String("Ahmed Noor"));
		htblColNameValue2.put("gpa", new Double(0.95));

		Hashtable<String, Object> htblColNameValue3 = new Hashtable<String, Object>();
		htblColNameValue3.put("id", new Integer(4));
		htblColNameValue3.put("name", new String("Ahmed Noor"));
		htblColNameValue3.put("gpa", new Double(0.95));

		Hashtable<String, Object> htblColNameValue4 = new Hashtable<String, Object>();
		htblColNameValue4.put("id", new Integer(5));
		htblColNameValue4.put("name", new String("Ahmed Noor"));
		htblColNameValue4.put("gpa", new Double(0.95));
//		try {
//			t.InsertInTable(htblColNameValue4);
//			t.InsertInTable(htblColNameValue1);
//			t.InsertInTable(htblColNameValue2);
//			t.InsertInTable(htblColNameValue3);
//			t.InsertInTable(htblColNameValue);
//		//	 t.InsertInTable(htblColNameValue);
//		} catch (DBAppException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//	}
//		try {
//		 t.AddMetaData(NameType, min, max);
//			 t2.AddMetaData(NameType, min, max);
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//		}

//		try {
//			Page p = t.LoadPage("src/main/DBFiles/StudentPage0.class");
//			for (Hashtable<String, Object> x : p.getVecPage())
//				System.out.println(x.get("id"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			Page p = t.LoadPage("src/main/DBFiles/StudentPage1.class");
//			for (Hashtable<String, Object> x : p.getVecPage())
//				System.out.println(x.get("id"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			Page p = t.LoadPage("src/main/DBFiles/StudentPage2.class");
//			for (Hashtable<String, Object> x : p.getVecPage())
//				System.out.println(x.get("id"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		System.out.println(t.ParsingCk("2002-11-5"));

	}

}
