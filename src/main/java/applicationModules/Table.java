package applicationModules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import dataManagement.ComparatorI;
import dataManagement.RowAddress;
import dataManagement.ValidatorI;
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

	public void ReadMetaData() throws IOException {
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

	public void ValidateInsert(Hashtable<String, Object> ColNameValue) throws DBAppException{
		if (ColNameValue.get(CKName) == null)
			throw new DBAppException("Cannot insert without primary key");
		validateHelper(ColNameValue);
	}
	
	public void ValidateDelete(Hashtable<String, Object> ColNameValue) throws DBAppException{
		validateHelper(ColNameValue);
	}
	
	public void ValidateUpdate(String CKValue, Hashtable<String,Object> ColNameValue) throws DBAppException {
		V.tryParse(CKValue,ColumnNameType.get(CKName));
		validateHelper(ColNameValue);
	}
	
	private void validateHelper(Hashtable<String,Object> ColNameValue) throws DBAppException {
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

	private RowAddress SearchByCk(Object CkValObj) throws DBAppException {
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
	}// String badal object parse it

	public void DelFromTbl(Hashtable<String, Object> ColNameVal) throws DBAppException {
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

}