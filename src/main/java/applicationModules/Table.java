package applicationModules;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import basicTools.ComparatorI;
import basicTools.RowAddress;
import basicTools.ValidatorI;
import exceptions.DBAppException;

public class Table implements Serializable, ComparatorI, ValidatorI {
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
	private Vector<String> creationOrder = new Vector<String>();
	private Vector<OctreeDescription> Octrees = new Vector<OctreeDescription>();

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

	public Octree LoadOctree(String FilePath) {
		Octree RestoredOctree = null;
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FilePath));
			RestoredOctree = (Octree) objectInputStream.readObject();
			objectInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RestoredOctree;
	}

	public void ReadMetaData() throws DBAppException {
		ColumnNameType = new Hashtable<String, String>();
		ColumnNameMin = new Hashtable<String, String>();
		ColumnNameMax = new Hashtable<String, String>();
		String FilePath = "src/main/resources/metadata.csv";
		try {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DBAppException();
		}
	}

	public void ValidateInsert(Hashtable<String, Object> ColNameValue) throws DBAppException {
		if (ColNameValue.get(CKName) == null)
			throw new DBAppException("Cannot insert without primary key");
		validateHelper(ColNameValue);
	}

	public void ValidateDelete(Hashtable<String, Object> ColNameValue) throws DBAppException {
		validateHelperDelete(ColNameValue);
	}

	private void validateHelperDelete(Hashtable<String, Object> ColNameValue) throws DBAppException {
		Enumeration<String> ColNameValKeys = ColNameValue.keys();
		V.ValidateColumnsE(ColNameValKeys, ColumnNameType);
		ColNameValKeys = ColNameValue.keys();
		while (ColNameValKeys.hasMoreElements()) {
			String Key = ColNameValKeys.nextElement();
			V.ValidateObjectType(ColNameValue.get(Key), ColumnNameType.get(Key));
		}
	}

	public void ValidateUpdate(String CKValue, Hashtable<String, Object> ColNameValue) throws DBAppException {
		if (ColNameValue.get(CKName) != null)
			throw new DBAppException("This DBMS does not allow altering the primary key value");
		V.tryParse(CKValue, ColumnNameType.get(CKName));
		validateHelper(ColNameValue);
	}

	private void validateHelper(Hashtable<String, Object> ColNameValue) throws DBAppException {
		Enumeration<String> ColNameValKeys = ColNameValue.keys();
		V.ValidateColumnsE(ColNameValKeys, ColumnNameType);
		ColNameValKeys = ColNameValue.keys();
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
					InstPg = null;
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
					InstPg = null;
					break;
				} else if (C.compare(CK, PageMaxVal) > 0 && !IsPgF && !IsLastPg) {
					int NxtPid = TablePages.get(i + 1);
					Object NxtPageMinVal = MinPage.get(NxtPid);
					if (C.compare(CK, NxtPageMinVal) < 0) {
						InstPg = LoadPage(this.PageFilePath.get(Pid));
						PgInstRes = InstPg.InsertToPage(this.CKName, ColNameValue);
						UpTblData(InstPg);
						OverflowSolver(PgInstRes);
						InstPg = null;
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
		CreatedPage = null;
		PageIdInc++;
	}

	private void UpTblData(Page Pg) throws DBAppException {
		MaxPage.put(Pg.getPageId(), Pg.getCurrMax());
		MinPage.put(Pg.getPageId(), Pg.getCurrMin());
		IsPgFull.put(Pg.getPageId(), Pg.IsFull());
		Pg.UnLoadPage();
		Pg = null;
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
				PgId = TablePages.get(Mid);
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
			Pg = null;
			if (RowId == -1)
				return null;
			else
				return new RowAddress(PgId, RowId);
		}
	}

	public void UpdateTbl(String CKVal, Hashtable<String, Object> ColNameVal) throws DBAppException {
		RowAddress RowAdrs = SearchByCk(V.tryParse(CKVal, ColumnNameType.get(CKName)));
		if (RowAdrs == null)
			return;
		Page UpdatePg = LoadPage(PageFilePath.get(RowAdrs.getPageId()));
		UpdatePg.UpdtRow(RowAdrs.getRowIndex(), ColNameVal);
		UpdatePg.UnLoadPage();
		UpdatePg = null;
	}

	public void DelFromTbl(Hashtable<String, Object> ColNameVal) throws DBAppException {
		if (ColNameVal.containsKey(CKName)) {
			RowAddress RowAdrs = SearchByCk(ColNameVal.get(CKName));
			if (RowAdrs == null)
				return;
			int PgId = RowAdrs.getPageId();
			Page DelPg = LoadPage(PageFilePath.get(PgId));
			DelPg.DelRows(ColNameVal, CKName, RowAdrs.getRowIndex());
			if (DelPg.IsEmpty()) {
				TablePages.remove(PgId);
				MaxPage.remove(PgId);
				MinPage.remove(PgId);
				IsPgFull.remove(PgId);
				new File(PageFilePath.get(PgId)).delete();
				PageFilePath.remove(PgId);
				DelPg = null;
			} else {
				UpTblData(DelPg);
				DelPg = null;
			}
		} else {
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
					DelPg = null;
				} else {
					UpTblData(DelPg);
					DelPg = null;
				}
			}
		}
	}

	public void createIndex(String[] columns) throws DBAppException {
		String col0 = columns[0];
		String col1 = columns[1];
		String col2 = columns[2];

		OctreeDescription od = new OctreeDescription(TblName, col0, col1, col2);
		this.Octrees.add(od);
		Hashtable<String, Object> min = new Hashtable<String, Object>();
		Hashtable<String, Object> max = new Hashtable<String, Object>();
		for (String s : columns) {
			min.put(s, V.tryParse(ColumnNameMin.get(s), ColumnNameType.get(s)));
			max.put(s, V.tryParse(ColumnNameMin.get(s), ColumnNameType.get(s)));
		}
		Octree o = new Octree(TblName, col0, col1, col2, min, max);
		o.populate(this);
		o.UnLoadTree();
		this.updateMetaDataIndex(col0, col1, col2);
	}

	public Vector<OctreeDescription> getMatchingInex(String[] columns) {
		Vector<OctreeDescription> result = new Vector<OctreeDescription>();
		boolean flag = false;
		for (OctreeDescription od : this.Octrees)
			for (String existingAtt : od.attributes) {
				for (String col : columns)
					if (col.equalsIgnoreCase(existingAtt)) {
						result.add(od);
						flag = true;
						break;
					}
				if (flag) {
					flag = false;
					break;
				}
			}
		return result;
	}

	public OctreeDescription getBestMatch(String[] columns) {
		OctreeDescription bestMatch = null;
		int maxCount = 0;
		int count = 0;
		for (OctreeDescription od : Octrees) {
			for (String existingAtt : od.attributes) {
				for (String col : columns)
					if (col.equalsIgnoreCase(existingAtt)) {
						count++;
					}
			}
			if (count > maxCount) {
				maxCount = count;
				bestMatch = od;
			}
			count = 0;
		}
		return bestMatch;
	}

	public void validateCreateIndex(String[] columns) throws DBAppException {
		if (columns.length != 3)
			throw new DBAppException("Cannot create octree for anyhing other than THREE!! columns");
		String col0 = columns[0];
		String col1 = columns[1];
		String col2 = columns[2];
		for (OctreeDescription octDesc : Octrees) {
			String att0 = octDesc.attributes[0];
			String att1 = octDesc.attributes[1];
			String att2 = octDesc.attributes[2];
			if (col0.equalsIgnoreCase(att0) || col0.equalsIgnoreCase(att1) || col0.equalsIgnoreCase(att2)
					|| col1.equalsIgnoreCase(att0) || col1.equalsIgnoreCase(att1) || col1.equalsIgnoreCase(att2)
					|| col2.equalsIgnoreCase(att0) || col2.equalsIgnoreCase(att1) || col2.equalsIgnoreCase(att2))
				throw new DBAppException("Octree already exists for some column");
		}
		if (ColumnNameType.get(col0) == null || ColumnNameType.get(col1) == null || ColumnNameType.get(col2) == null)
			throw new DBAppException("Cannot create octree for columns THAT DO NOT EXIST!!");
	}

	// need to test later on
	public void updateMetaDataIndex(String col0, String col1, String col2) throws DBAppException {
		String oldFilePath = "src/main/resources/metadata.csv";
		String newFilePath = "src/main/resources/metadata2.csv";
		try {
			FileReader fileReader = new FileReader(oldFilePath);
			BufferedReader bufferedreader = new BufferedReader(fileReader);
			String line = bufferedreader.readLine();
			FileWriter fw = new FileWriter(newFilePath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			while (line != null) {
				String[] content = line.split(",");
				if (content[0].equalsIgnoreCase(TblName) && (col0.equalsIgnoreCase(content[1])
						|| col1.equalsIgnoreCase(content[1]) || col2.equalsIgnoreCase(content[1]))) {
					content[4] = col0 + col1 + col2 + "index";
					content[5] = "Octree";
					StringBuffer sb = new StringBuffer();
					for (String s : content)
						sb.append(s).append(",");
					sb.deleteCharAt(sb.length());
					line = sb.toString();
				}
				pw.println(line);
				line = bufferedreader.readLine();
			}
			bufferedreader.close();
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DBAppException();
		}

		new File(oldFilePath).delete();
		new File(newFilePath).renameTo(new File(oldFilePath));
	}

	public Hashtable<String, String> getColumnNameType() {
		return ColumnNameType;
	}

	public String getCKName() {
		return CKName;
	}

	public Vector<String> getCreationOrder() {
		return creationOrder;
	}

	public Vector<Integer> getTablePages() {
		return TablePages;
	}

	public Hashtable<Integer, String> getPageFilePath() {
		return PageFilePath;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Table Name: ").append(TblName).append("\n");
		sb.append("CK Name: ").append(CKName).append("\n");
		sb.append("Page File Path:\n");
		for (Integer pageId : PageFilePath.keySet()) {
			sb.append("- Page ").append(pageId).append(": ").append(PageFilePath.get(pageId)).append("\n");
		}
		sb.append("Maximum Page:\n");
		for (Integer pageId : MaxPage.keySet()) {
			sb.append("- Page ").append(pageId).append(": ").append(MaxPage.get(pageId)).append("\n");
		}
		sb.append("Minimum Page:\n");
		for (Integer pageId : MinPage.keySet()) {
			sb.append("- Page ").append(pageId).append(": ").append(MinPage.get(pageId)).append("\n");
		}
		sb.append("Is Page Full:\n");
		for (Integer pageId : IsPgFull.keySet()) {
			sb.append("- Page ").append(pageId).append(": ").append(IsPgFull.get(pageId)).append("\n");
		}
		sb.append("Table Pages: ").append(TablePages).append("\n");
		sb.append("Page ID Increment: ").append(PageIdInc).append("\n");
		sb.append("Column Name Type:\n");
		for (String columnName : ColumnNameType.keySet()) {
			sb.append("- ").append(columnName).append(": ").append(ColumnNameType.get(columnName)).append("\n");
		}
		sb.append("Column Name Minimum:\n");
		for (String columnName : ColumnNameMin.keySet()) {
			sb.append("- ").append(columnName).append(": ").append(ColumnNameMin.get(columnName)).append("\n");
		}
		sb.append("Column Name Maximum:\n");
		for (String columnName : ColumnNameMax.keySet()) {
			sb.append("- ").append(columnName).append(": ").append(ColumnNameMax.get(columnName)).append("\n");
		}
		sb.append("Column Creation Order:\n");
		if (creationOrder.isEmpty()) {
			sb.append("- N/A\n");
		} else {
			for (String columnName : creationOrder) {
				sb.append("- ").append(columnName).append("\n");
			}
		}
		for (int i = 0; i < this.TablePages.size(); i++) {
			String FilePath = this.PageFilePath.get(this.TablePages.get(i));
			Page page = LoadPage(FilePath);
			sb.append("\n").append(page.toString());
			try {
				page.UnLoadPage();
			} catch (DBAppException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}