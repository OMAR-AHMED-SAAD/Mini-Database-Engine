package dataManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import exceptions.DBAppException;

public class Table implements Serializable,ComparatorI,ValidatorI {
	private static final long serialVersionUID = 1L;
	private String TblName;
	private transient String CKName;
	private Hashtable<Integer, String> PageFilePath = new Hashtable<Integer, String>();
	private Hashtable<Integer, Object> MaxPage = new Hashtable<Integer, Object>();
	private Hashtable<Integer, Object> MinPage = new Hashtable<Integer, Object>();
	private Hashtable<Integer, Boolean> IsPgFull = new Hashtable<Integer, Boolean>();
	private Vector<Integer> TablePages = new Vector<Integer>();
	private int PageIdInc;
	private transient Hashtable<String, String> ColumnNameType;
	private transient Hashtable<String, String> ColumnNameMin;
	private transient Hashtable<String, String> ColumnNameMax;

	public Table(String name) {
		this.TblName = name;
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

	private void ReadMetaData() throws IOException {
		ColumnNameType = new Hashtable<String, String>();
		ColumnNameMin = new Hashtable<String, String>();
		ColumnNameMax = new Hashtable<String, String>();
		String FilePath = "src/main/DBFiles/metadata.csv";
		FileReader fileReader = new FileReader(FilePath);
		BufferedReader bufferedreader = new BufferedReader(fileReader);
		String Line = bufferedreader.readLine();
		while (Line != null) {
			String[] content = Line.split(",");
			if (content[0].equals(TblName)) {
				String ColName = content[1];
				String ColType = content[2];
				String IsPrimaryKey = content[3];
				String ColMin = content[6];
				String ColMax = content[7];
				ColumnNameType.put(ColName, ColType);
				ColumnNameMin.put(ColName, ColMin);
				ColumnNameMax.put(ColName, ColMax);
				if (IsPrimaryKey.toLowerCase().equals("true"))
					CKName = ColName;
			}
			Line = bufferedreader.readLine();
		}
		bufferedreader.close();
	}

	public void ValidateInsert(Hashtable<String, Object> ColNameValue) throws DBAppException, ParseException {
		if (ColNameValue.get(CKName) == null)
			throw new DBAppException("Cannot insert without primary key");
		Enumeration<String> ColNameValKeys = ColNameValue.keys();
		V.ValidateColumnsE(ColNameValKeys, ColumnNameType);
		while (ColNameValKeys.hasMoreElements()) {
			String Key = ColNameValKeys.nextElement();
			V.ValidateObjectType(ColNameValue.get(Key), ColumnNameType.get(Key));
			V.ValidateBounds(Key, ColNameValue.get(Key), ColumnNameType, ColumnNameMin, ColumnNameMax);
		}
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
				if (C.compare(CK, PageMinVal) < 0 || (C.compare(CK, PageMinVal) > 0 && C.compare(CK, PageMaxVal) < 0)) {
					InstPg = LoadPage(this.PageFilePath.get(Pid));
					PgInstRes = InstPg.InsertToPage(this.CKName, ColNameValue);
					UpTblData(InstPg);
					OverflowSolver(PgInstRes);
					break;
				} else if (C.compare(CK, PageMaxVal) > 0 && IsPgF && IsLastPg) {
					CreateAddNewPage(ColNameValue);
					break;
				} else if (C.compare(CK, PageMaxVal) > 0 && IsPgF && !IsLastPg) {
					continue;
				} else if (C.compare(CK, PageMaxVal) > 0 && !IsPgF && IsLastPg) {
					InstPg = LoadPage(this.PageFilePath.get(Pid));
					PgInstRes = InstPg.InsertToPage(this.CKName, ColNameValue);
					UpTblData(InstPg);
					OverflowSolver(PgInstRes);
					break;
				} else if (C.compare(CK, PageMaxVal) > 0 && !IsPgF && !IsLastPg) {
					int NxtPid = TablePages.get(i);
					Object NxtPageMinVal = MinPage.get(NxtPid);
					if (C.compare(CK, NxtPageMinVal) < 0) {
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

	private RowAddress SearchByCk(Object CkValObj) {
		Boolean IsPgFound = false;
		int PgId = 0;
		int Min = 0;
		int Max = TablePages.size() - 1;
		while (Min <= Max) {
			int Mid = (Min + Max) / 2;
			Object MinVal = MinPage.get(TablePages.get(Mid));
			Object MaxVal = MaxPage.get(TablePages.get(Mid));
			if (C.compare(CkValObj, MinVal) >= 0 && C.compare(CkValObj, MaxVal) <= 0) {
				PgId = Mid;
				IsPgFound = true;
				break;
			} else if (C.compare(CkValObj, MinVal) < 0)
				Max = Mid - 1;
			else if (C.compare(CkValObj, MaxVal) > 0)
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

	public void UpdateTbl(Object CKVal, Hashtable<String, Object> ColNameVal) throws DBAppException {
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

		Table t = new Table("Student");

		//t.AddMetaData("id",NameType, min, max);
		

		t.ReadMetaData();
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
		// htblColNameValue3.remove("id");
//		htblColNameValue3.remove("gpa");
//		t.DelFromTbl(htblColNameValue3);

		// update tuples
		htblColNameValue2.remove("id");
		t.UpdateTbl(new Integer(1), htblColNameValue2);

//		System.out.println(t.SearchByCk(new Integer (2)));

		try {
			Page p = t.LoadPage("src/main/DBFiles/Pages/StudentPage0.bin");
			for (Hashtable<String, Object> x : p.getVecPage())
				System.out.println(x.get("id") + " " + x.get("name") + " " + x.get("gpa"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Page p = t.LoadPage("src/main/DBFiles/Pages/StudentPage1.bin");
			for (Hashtable<String, Object> x : p.getVecPage())
				System.out.println(x.get("id") + " " + x.get("name") + " " + x.get("gpa"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
