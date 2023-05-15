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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import application.SQLTerm;
import applicationModules.Octree.Element;
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
	private Vector<OctreeDescription> octrees = new Vector<OctreeDescription>();

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
			throw new DBAppException(e.getMessage());
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

	public String InsertInTable(Hashtable<String, Object> ColNameValue) throws DBAppException {
		Object CK = ColNameValue.get(this.CKName);
		String resultFilePath = "";
		boolean isInserted = true;
		if (TablePages.size() == 0)
			resultFilePath = CreateAddNewPage(ColNameValue);
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
					if (!ColNameValue.equals(PgInstRes))
						isInserted = false;
					resultFilePath = InstPg.getFilePath();
					OverflowSolver(PgInstRes, resultFilePath);
					InstPg = null;
					break;
				} else if (C.compare(CK, PageMaxVal) > 0 && IsPgF && IsLastPg) {
					resultFilePath = CreateAddNewPage(ColNameValue);
					break;
				} else if (C.compare(CK, PageMaxVal) > 0 && IsPgF && !IsLastPg) {
					continue;
				} else if (C.compare(CK, PageMaxVal) > 0 && !IsPgF && IsLastPg) {
					InstPg = LoadPage(this.PageFilePath.get(Pid));
					PgInstRes = InstPg.InsertToPage(this.CKName, ColNameValue);
					UpTblData(InstPg);
					if (!ColNameValue.equals(PgInstRes))
						isInserted = false;
					resultFilePath = InstPg.getFilePath();
					OverflowSolver(PgInstRes, resultFilePath);
					InstPg = null;
					break;
				} else if (C.compare(CK, PageMaxVal) > 0 && !IsPgF && !IsLastPg) {
					int NxtPid = TablePages.get(i + 1);
					Object NxtPageMinVal = MinPage.get(NxtPid);
					if (C.compare(CK, NxtPageMinVal) < 0) {
						InstPg = LoadPage(this.PageFilePath.get(Pid));
						PgInstRes = InstPg.InsertToPage(this.CKName, ColNameValue);
						UpTblData(InstPg);
						if (!ColNameValue.equals(PgInstRes))
							isInserted = false;
						resultFilePath = InstPg.getFilePath();
						OverflowSolver(PgInstRes, resultFilePath);
						InstPg = null;
						break;
					} else
						continue;
				} else
					throw new DBAppException("Can not accept duplicate primary keys");
			}
		}
		if (isInserted)
			insertInOctree(ColNameValue, resultFilePath);
		return resultFilePath;
	}

	private void insertInOctree(Hashtable<String, Object> tuple, String FilePath) throws DBAppException { // needs
																											// testing
		for (OctreeDescription od : octrees) {
			Hashtable<String, Object> insertTuple = new Hashtable<String, Object>();
			for (String attribute : od.getAttributes()) {
				Object value = tuple.get(attribute);
				if (value != null)
					insertTuple.put(attribute, value);
			}
			Octree oct = this.LoadOctree(od.getFilePath());
			Element insertelememt = oct.new Element(insertTuple, FilePath);
			oct.insert(insertelememt);
			oct.UnLoadTree();
			oct = null;
		}
	}

	private String CreateAddNewPage(Hashtable<String, Object> ColNameValue) throws DBAppException {
		Page CreatedPage = new Page(PageIdInc, this.TblName);
		TablePages.add(CreatedPage.getPageId());
		this.PageFilePath.put(CreatedPage.getPageId(), CreatedPage.getFilePath());
		CreatedPage.InsertToPage(CKName, ColNameValue);
		UpTblData(CreatedPage);
		String pageFilePath = CreatedPage.getFilePath();
		CreatedPage = null;
		PageIdInc++;
		return pageFilePath;
	}

	private void UpTblData(Page Pg) throws DBAppException {
		MaxPage.put(Pg.getPageId(), Pg.getCurrMax());
		MinPage.put(Pg.getPageId(), Pg.getCurrMin());
		IsPgFull.put(Pg.getPageId(), Pg.IsFull());
		Pg.UnLoadPage();
		Pg = null;
	}

	private void OverflowSolver(Hashtable<String, Object> PgInstRes, String oldFilePath) throws DBAppException {
		if (PgInstRes == null)
			return;
		else {
			String newFilePath = InsertInTable(PgInstRes);
			OverFlowSolverOctree(PgInstRes, oldFilePath, newFilePath);
		}
	}

	// needs testing
	private void OverFlowSolverOctree(Hashtable<String, Object> tuple, String oldFilePath, String newFilePath)
			throws DBAppException {
		if (tuple == null)
			return;
		for (OctreeDescription od : octrees) {
			Hashtable<String, Object> searchTuple = new Hashtable<String, Object>();
			for (String attribute : od.getAttributes()) {
				Object value = tuple.get(attribute);
				if (value != null)
					searchTuple.put(attribute, value);
			}
			Octree oct = this.LoadOctree(od.getFilePath());
			oct.updatePageRef(searchTuple, oldFilePath, newFilePath);
			oct.UnLoadTree();
			oct = null;
		}
	}

	private RowAddress SearchByCkWithoutIndex(Object CkValObj) throws DBAppException {
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

	// need to test later on
	private RowAddress SearchByCkWithIndex(Object CkValObj) throws DBAppException {
		Boolean IsPgFound = false;
		Octree oct = this.LoadOctree(getBestMatch(new String[] { CKName }).getFilePath());
		Hashtable<String, Object> serachHtbl = new Hashtable<String, Object>();
		serachHtbl.put(CKName, CkValObj);
		ArrayList<String> pages = oct.search(serachHtbl);
		if (pages.size() != 0)
			IsPgFound = true;
		oct.UnLoadTree();
		oct = null;
		if (!IsPgFound)
			return null;
		else {
			Page page = LoadPage(pages.get(0));
			int RowId = page.IsRowFound(CKName, CkValObj);
			int PgId = page.getPageId();
			page.UnLoadPage();
			page = null;
			if (RowId == -1)
				return null;
			else
				return new RowAddress(PgId, RowId);
		}
	}

	// need to test later on
	public void UpdateTbl(String CKVal, Hashtable<String, Object> ColNameVal) throws DBAppException {
		Vector<OctreeDescription> existingOctrees = this.getMatchingIndex(new String[] { CKName });
		if (existingOctrees.size() == 0)
			updateWithoutIndex(CKVal, ColNameVal);
		else
			updateWithIndex(CKVal, ColNameVal);
	}

	private void updateWithoutIndex(String CKVal, Hashtable<String, Object> ColNameVal) throws DBAppException {
		RowAddress RowAdrs = SearchByCkWithoutIndex(V.tryParse(CKVal, ColumnNameType.get(CKName)));
		if (RowAdrs == null)
			return;
		Page updatePg = LoadPage(PageFilePath.get(RowAdrs.getPageId()));
		Vector<Hashtable<String, Object>> oldAndNewValues = updatePg.UpdtRow(RowAdrs.getRowIndex(), ColNameVal);
		updateOctrees(oldAndNewValues.firstElement(), oldAndNewValues.lastElement(), ColNameVal,
				updatePg.getFilePath());
		updatePg.UnLoadPage();
		updatePg = null;
	}

	// need to test later on
	private void updateWithIndex(String CKVal, Hashtable<String, Object> ColNameVal) throws DBAppException {
		RowAddress RowAdrs = SearchByCkWithIndex(V.tryParse(CKVal, ColumnNameType.get(CKName)));
		if (RowAdrs == null)
			return;
		Page updatePg = LoadPage(PageFilePath.get(RowAdrs.getPageId()));
		Vector<Hashtable<String, Object>> oldAndNewValues = updatePg.UpdtRow(RowAdrs.getRowIndex(), ColNameVal);
		updateOctrees(oldAndNewValues.firstElement(), oldAndNewValues.lastElement(), ColNameVal,
				updatePg.getFilePath());
		updatePg.UnLoadPage();
		updatePg = null;
	}

	// need to test later on
	private void updateOctrees(Hashtable<String, Object> oldTuple, Hashtable<String, Object> newTuple,
			Hashtable<String, Object> updateValues, String pageFilePath) throws DBAppException {
		Vector<OctreeDescription> updatedOctrees = this
				.getMatchingIndex(updateValues.keySet().toArray(new String[updateValues.keySet().size()]));
		for (OctreeDescription od : updatedOctrees) {
			Hashtable<String, Object> deleteTuple = new Hashtable<String, Object>();
			Hashtable<String, Object> insertTuple = new Hashtable<String, Object>();
			for (String attribute : od.getAttributes()) {
				Object value = oldTuple.get(attribute);
				if (value != null)
					deleteTuple.put(attribute, value);
				value = newTuple.get(attribute);
				if (value != null)
					insertTuple.put(attribute, value);
			}
			Octree oct = this.LoadOctree(od.getFilePath());
			oct.delete(deleteTuple, pageFilePath);
			Element insertelememt = oct.new Element(insertTuple, pageFilePath);
			oct.insert(insertelememt);
			oct.UnLoadTree();
			oct = null;
		}

	}

	// need to test later on
	public void DelFromTbl(Hashtable<String, Object> ColNameVal) throws DBAppException {
		Vector<OctreeDescription> existingOctrees = this
				.getMatchingIndex(ColNameVal.keySet().toArray(new String[ColNameVal.keySet().size()]));
		if (existingOctrees.size() == 0)
			deleteWithoutIndex(ColNameVal);
		else
			deleteWithIndex(ColNameVal);
	}

	private void deleteWithoutIndex(Hashtable<String, Object> ColNameVal) throws DBAppException {
		if (ColNameVal.containsKey(CKName)) {
			RowAddress RowAdrs = SearchByCkWithoutIndex(ColNameVal.get(CKName));
			if (RowAdrs == null)
				return;
			int PgId = RowAdrs.getPageId();
			Page DelPg = LoadPage(PageFilePath.get(PgId));
			Vector<Hashtable<String, Object>> deletedRows = DelPg.DelRows(ColNameVal, CKName, RowAdrs.getRowIndex());
			deleteFromOctrees(deletedRows, DelPg.getFilePath());
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
				Vector<Hashtable<String, Object>> deletedRows = DelPg.DelRows(ColNameVal, CKName);
				deleteFromOctrees(deletedRows, DelPg.getFilePath());
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

	// need to test later on
	private void deleteWithIndex(Hashtable<String, Object> ColNameVal) throws DBAppException {
		if (ColNameVal.containsKey(CKName) && getMatchingIndex(new String[] { CKName }) != null) {
			RowAddress RowAdrs = SearchByCkWithIndex(ColNameVal.get(CKName));
			if (RowAdrs == null)
				return;
			int PgId = RowAdrs.getPageId();
			Page DelPg = LoadPage(PageFilePath.get(PgId));
			Vector<Hashtable<String, Object>> deletedRows = DelPg.DelRows(ColNameVal, CKName, RowAdrs.getRowIndex());
			deleteFromOctrees(deletedRows, DelPg.getFilePath());
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
			OctreeDescription od = getBestMatch(ColNameVal.keySet().toArray(new String[ColNameVal.keySet().size()]));
			Octree oct = this.LoadOctree(od.getFilePath());
			Hashtable<String, Object> searchTuple = new Hashtable<String, Object>();
			for (String attribute : od.getAttributes()) {
				Object value = ColNameVal.get(attribute);
				if (value != null)
					searchTuple.put(attribute, value);
			}
			ArrayList<String> filePaths = oct.search(searchTuple);
			oct.UnLoadTree();
			oct = null;
			for (String pageFilePath : filePaths) {
				Page DelPg = LoadPage(pageFilePath);
				int PgId = DelPg.getPageId();
				Vector<Hashtable<String, Object>> deletedRows = DelPg.DelRows(ColNameVal, CKName);
				deleteFromOctrees(deletedRows, DelPg.getFilePath());
				if (DelPg.IsEmpty()) {
					TablePages.remove((Object) PgId);
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

	private void deleteFromOctrees(Vector<Hashtable<String, Object>> deletedRows, String pageFilePath)
			throws DBAppException {
		for (Hashtable<String, Object> tuple : deletedRows) {
			for (OctreeDescription od : octrees) {
				Hashtable<String, Object> deleteTuple = new Hashtable<String, Object>();
				for (String attribute : od.getAttributes()) {
					Object value = tuple.get(attribute);
					if (value != null)
						deleteTuple.put(attribute, value);
				}
				Octree oct = this.LoadOctree(od.getFilePath());
				oct.delete(deleteTuple, pageFilePath);
				oct.UnLoadTree();
				oct = null;
			}
		}
	}

	public void createIndex(String[] columns) throws DBAppException {
		String col0 = columns[0];
		String col1 = columns[1];
		String col2 = columns[2];

		OctreeDescription od = new OctreeDescription(TblName, col0, col1, col2);
		this.octrees.add(od);
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

	private Vector<OctreeDescription> getMatchingIndex(String[] columns) {
		Vector<OctreeDescription> result = new Vector<OctreeDescription>();
		boolean flag = false;
		for (OctreeDescription od : this.octrees)
			for (String existingAtt : od.getAttributes()) {
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

	private OctreeDescription getBestMatch(String[] columns) {
		OctreeDescription bestMatch = null;
		int maxCount = 0;
		int count = 0;
		for (OctreeDescription od : octrees) {
			for (String existingAtt : od.getAttributes()) {
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

	public OctreeDescription getFullMatch(String[] columns) {
		OctreeDescription bestMatch = null;
		int maxCount = 0;
		int count = 0;
		for (OctreeDescription od : octrees) {
			for (String existingAtt : od.getAttributes()) {
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
		if (count == 3)
			return bestMatch;
		return null;
	}

	public void validateCreateIndex(String[] columns) throws DBAppException {
		if (columns.length != 3)
			throw new DBAppException("Cannot create octree for anyhing other than THREE!! columns");
		String col0 = columns[0];
		String col1 = columns[1];
		String col2 = columns[2];
		for (OctreeDescription octDesc : octrees) {
			String att0 = octDesc.getAttributes()[0];
			String att1 = octDesc.getAttributes()[1];
			String att2 = octDesc.getAttributes()[2];
			if (col0.equalsIgnoreCase(att0) || col0.equalsIgnoreCase(att1) || col0.equalsIgnoreCase(att2)
					|| col1.equalsIgnoreCase(att0) || col1.equalsIgnoreCase(att1) || col1.equalsIgnoreCase(att2)
					|| col2.equalsIgnoreCase(att0) || col2.equalsIgnoreCase(att1) || col2.equalsIgnoreCase(att2))
				throw new DBAppException("Octree already exists on one of the specified columns");
		}
		if (ColumnNameType.get(col0) == null || ColumnNameType.get(col1) == null || ColumnNameType.get(col2) == null)
			throw new DBAppException("Cannot create octree for columns THAT DO NOT EXIST!!");
	}

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
					sb.deleteCharAt(sb.length() - 1);
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
			throw new DBAppException(e.getMessage());
		}

		new File(oldFilePath).delete();
		new File(newFilePath).renameTo(new File(oldFilePath));
	}

	// test later on
	public Vector<Hashtable<String, Object>> selectLinear(String colName, Object colValue, String operator)
			throws DBAppException {
		Vector<Hashtable<String, Object>> Result = new Vector<Hashtable<String, Object>>();
		for (int Index = 0; Index < TablePages.size(); Index++) {
			int PgId = TablePages.get(Index);
			Page currPg = LoadPage(PageFilePath.get(PgId));
			Vector<Hashtable<String, Object>> rows = currPg.getVecPage();
			for (Hashtable<String, Object> currRow : rows) {
				Object currColValue = currRow.get(colName);
				if (C.compareWithOperator(currColValue, colValue, operator) == true)
					Result.add(currRow);
			}
			currPg.UnLoadPage();
			currPg = null;
		}

		return Result;
	}

	// testLaterOn
	public Vector<Hashtable<String, Object>> selectWithIndex(SQLTerm[] sqlTerms, OctreeDescription od)
			throws DBAppException {
		Vector<Hashtable<String, Object>> result = new Vector<Hashtable<String, Object>>();
		Octree oct = this.LoadOctree(od.getFilePath());
		ArrayList<String> octreeAtt = new ArrayList<>(Arrays.asList(od.getAttributes()));
		SQLTerm[] searchTerms = new SQLTerm[3];
		int i = 0;
		for (SQLTerm term : sqlTerms)
			if (octreeAtt.contains(term.get_strColumnName()))
				searchTerms[i++] = term;
		ArrayList<String> pagePaths = oct.searchRange(searchTerms);
		for (String pagePath : pagePaths) {
			Page currPg = LoadPage(pagePath);
			Vector<Hashtable<String, Object>> rows = currPg.getVecPage();
			for (Hashtable<String, Object> currRow : rows) {
				for (SQLTerm term : sqlTerms) {
					Object colValue = term.get_objValue();
					String colName = term.get_strColumnName();
					Object currColValue = currRow.get(colName);
					String operator = term.get_strOperator();
					if (C.compareWithOperator(currColValue, colValue, operator) == true)
						result.add(currRow);
				}
			}
			currPg.UnLoadPage();
			currPg = null;
		}
		return result;
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

	public Hashtable<String, String> getColumnNameMin() {
		return ColumnNameMin;
	}

	public Hashtable<String, String> getColumnNameMax() {
		return ColumnNameMax;
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

	public Vector<OctreeDescription> getOctrees() {
		return octrees;
	}

}