package dataManagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;
import exceptions.DBAppException;

public class Table {

	private String TblName;
	private String CKName;
	private Hashtable<Integer, String> PageFilePath = new Hashtable<Integer, String>();
	private Hashtable<Integer, Object> MaxPage = new Hashtable<Integer, Object>();
	private Hashtable<Integer, Object> MinPage = new Hashtable<Integer, Object>();
	private Hashtable<Integer, Boolean> IsPgFull = new Hashtable<Integer, Boolean>();
	private Vector<Integer> TablePages = new Vector<Integer>();
	private int PageIdInc;

	public Table(String name, String ClustKey) {
		this.TblName = name;
		this.CKName = ClustKey;

		PageIdInc = 0;

	}

	private Page LoadPage(String FilePath) {
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
				(key, value) -> pw.println(this.TblName + "," + key + "," + value + "," + key.equals(this.CKName) + ","
						+ "null" + "," + "null" + "," + ColNameMin.get(key) + "," + ColNameMax.get(key)));
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

	private int compare(Object One, Object Two) {
		if (One instanceof java.lang.Integer && Two instanceof java.lang.Integer)
			return ((java.lang.Integer) One).compareTo((java.lang.Integer) Two);
		else if (One instanceof java.lang.String && Two instanceof java.lang.String)
			return ((java.lang.String) One).compareTo((java.lang.String) Two);
		else if (One instanceof java.util.Date && Two instanceof java.util.Date)
			return ((java.util.Date) One).compareTo((java.util.Date) Two);
		else
			return ((java.lang.Double) One).compareTo((java.lang.Double) Two);

	}

//	private Object ParsingCk(String CkVal) throws ParseException {
//
//		switch (CkType) {
//		case "java.lang.Integer":
//			return new Integer(Integer.parseInt(CkVal));
//		case "java.lang.String":
//			return new String(CkVal);
//		case "java.util.Date":
//			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(CkVal);
//			return date;
//		case "java.lang.Double":
//			return new Double(Double.parseDouble(CkVal));
//		default:
//			return new String(CkVal);
//		}
//	}

	private RowAddress SearchByCk(Object CkValObj) {
		Boolean IsPgFound = false;
		int PgId = 0;
		int Min = 0;
		int Max = TablePages.size() - 1;
		while (Min <= Max) {
			int Mid = (Min + Max) / 2;
			Object MinVal = MinPage.get(TablePages.get(Mid));
			Object MaxVal = MaxPage.get(TablePages.get(Mid));
			if (compare(CkValObj, MinVal) >= 0 && compare(CkValObj, MaxVal) <= 0) {
				PgId = Mid;
				IsPgFound = true;
				break;
			} else if (compare(CkValObj, MinVal) < 0)
				Max = Mid - 1;
			else if (compare(CkValObj, MaxVal) > 0)
				Min = Mid + 1;
		}
		if (!IsPgFound)
			return null;
		else {
			Page Pg = LoadPage(PageFilePath.get(PgId));
			int RowId = Pg.IsRowFound(CKName, CkValObj);
			Pg.UnLoadPage();
			if (RowId == -1)
				return null;
			else
				return new RowAddress(PgId, RowId);
		}
	}

	public void UpdtTbl(Object CKVal, Hashtable<String, Object> ColNameVal) throws DBAppException {
		RowAddress RowAdrs = SearchByCk(CKVal);
		if (RowAdrs == null)
			throw new DBAppException("Tuple not found");
		Page UpdatePg = LoadPage(PageFilePath.get(RowAdrs.getPageId()));
		UpdatePg.UpdtRow(RowAdrs.getRowIndex(), ColNameVal);
		UpdatePg.UnLoadPage();
	}

	public void DelFromTbl(Hashtable<String, Object> ColNameVal) {

		for (int Index = 0; Index < TablePages.size(); Index++) {
			int PgId = TablePages.get(Index);
			Page DelPg = LoadPage(PageFilePath.get(PgId));
			DelPg.DelRows(ColNameVal, CKName);
			if (DelPg.IsEmpty()) {
				TablePages.remove(Index--);
				MaxPage.remove(PgId);
				MinPage.remove(PgId);
				IsPgFull.remove(PgId);
				new File(PageFilePath.get(PgId)).delete();
				PageFilePath.remove(PgId);
			} else
				UpTblData(DelPg);

		}
	}

	public static void main(String[] args) throws IOException, DBAppException {

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

		Table t = new Table("Student", "id");

		t.AddMetaData(NameType, min, max);
		t.AddMetaData(NameType, min, max);

		Hashtable<String, Object> htblColNameValue1 = new Hashtable<String, Object>();
		htblColNameValue1.put("id", new Integer(1));
		htblColNameValue1.put("name", new String("Ahmed"));
		htblColNameValue1.put("gpa", new Double(0.69));

		Hashtable<String, Object> htblColNameValue2 = new Hashtable<String, Object>();
		htblColNameValue2.put("id", new Integer(2));
		htblColNameValue2.put("name", new String("Loji"));
		htblColNameValue2.put("gpa", new Double(1.0));

		Hashtable<String, Object> htblColNameValue3 = new Hashtable<String, Object>();
		htblColNameValue3.put("id", new Integer(3));
		htblColNameValue3.put("name", new String("Hamada"));
		htblColNameValue3.put("gpa", new Double(0.85));
		// insert tuples
		t.InsertInTable(htblColNameValue1);
		t.InsertInTable(htblColNameValue2);
		t.InsertInTable(htblColNameValue3);

		// delete tuples
	//	htblColNameValue3.remove("id");
//		htblColNameValue3.remove("gpa");
//		t.DelFromTbl(htblColNameValue3);
		
		//update tuples
	htblColNameValue2.remove("id");
	t.UpdtTbl(new Integer(1), htblColNameValue2);

//		System.out.println(t.SearchByCk(new Integer (2)));
		
		try {
			Page p = t.LoadPage("src/main/DBFiles/StudentPage0.bin");
			for (Hashtable<String, Object> x : p.getVecPage())
				System.out.println(x.get("id") + " " + x.get("name")+ " "+ x.get("gpa"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Page p = t.LoadPage("src/main/DBFiles/StudentPage1.bin");
			for (Hashtable<String, Object> x : p.getVecPage())
				System.out.println(x.get("id") + " " + x.get("name")+ " "+ x.get("gpa"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
