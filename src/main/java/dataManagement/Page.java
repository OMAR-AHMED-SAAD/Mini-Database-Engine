package dataManagement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import java.util.Properties;
import java.util.Vector;

import exceptions.DBAppException;

public class Page implements Serializable {
	private static final long serialVersionUID = 1L;
	private String TblName;
	private int PageId;
	private Object CurrMin;
	private Object CurrMax;
	private int CurrRowCount;
	private int MaxRowCount;
	private String FilePath;
	private Vector<Hashtable<String, Object>> VecPage = new Vector<Hashtable<String, Object>>();

	public Page(int id, String TblName) {
		PageId = id;
		this.TblName = TblName;
		FilePath = "src/main/DBFiles/" + this.TblName + "Page" + this.PageId + ".bin";
		CurrRowCount = 0;
		Properties Prop = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream("src/main/resources/DBApp.config.properties");
			Prop.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.MaxRowCount = Integer.parseInt(Prop.getProperty("MaximumRowsCountinTablePage"));

	}

	public int getPageId() {
		return PageId;
	}

	public Object getCurrMax() {
		return CurrMax;
	}

	public Object getCurrMin() {
		return CurrMin;
	}

	public int getCurrRowCount() {
		return CurrRowCount;
	}

	public String getFilePath() {
		return FilePath;
	}

	public void UnLoadPage() {
		try {
			ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(new FileOutputStream(FilePath));
			ObjectOutputStream.writeObject(this);
			ObjectOutputStream.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public int compare(Object One, Object Two) {
		if (One instanceof java.lang.Integer && Two instanceof java.lang.Integer)
			return ((java.lang.Integer) One).compareTo((java.lang.Integer) Two);
		else if (One instanceof java.lang.String && Two instanceof java.lang.String)
			return ((java.lang.String) One).compareTo((java.lang.String) Two);
		else if (One instanceof java.lang.Double && Two instanceof java.lang.Double)
			return ((java.lang.Double) One).compareTo((java.lang.Double) Two);
		else if (One instanceof java.util.Date && Two instanceof java.util.Date)
			return ((java.util.Date) One).compareTo((java.util.Date) Two);
		return 0;
	}

	public Hashtable<String, Object> InsertToPage(String ClustKey, Hashtable<String, Object> ColNameValue)
			throws DBAppException {
		Hashtable<String, Object> Result = null;
		if (CurrRowCount == 0) {
			VecPage.add(ColNameValue);
			CurrRowCount++;
		} else {
			int PageSize = VecPage.size();
			for (int i = 0; i < PageSize; i++) {
				int ComparisonValue = this.compare(ColNameValue.get(ClustKey), VecPage.elementAt(i).get(ClustKey));
				if (ComparisonValue < 0) {
					VecPage.insertElementAt(ColNameValue, i);
					CurrRowCount++;
					break;
				} else if (ComparisonValue == 0)
					throw new DBAppException("Can not accept duplicate primary keys");
				else if (ComparisonValue > 0 && i == (PageSize - 1)) {
					VecPage.insertElementAt(ColNameValue, i + 1);
					CurrRowCount++;
				}
			}
		}
		if (CurrRowCount > MaxRowCount) {
			Result = VecPage.elementAt(CurrRowCount - 1);
			VecPage.remove(--CurrRowCount);

		}
		CurrMax = VecPage.elementAt(CurrRowCount - 1).get(ClustKey);
		CurrMin = VecPage.elementAt(0).get(ClustKey);
		return Result;
	}

	public boolean IsFull() {
		return (CurrRowCount == MaxRowCount);
	}
	public boolean IsEmpty() {
		return (CurrRowCount == 0);
	}
	public int IsRowFound(String CKName, Object CkValO) {
		int Min = 0;
		int Max = VecPage.size() - 1;
		while (Min < Max) {
			int Mid = (Min + Max) / 2;
			Hashtable<String, Object> CurrRow = VecPage.get(Mid);
			int comparisonVal = compare(CkValO, CurrRow.get(CKName));
			if (comparisonVal == 0)
				return Mid;
			else if (comparisonVal < 0)
				Max = Mid - 1;
			else
				Min = Mid + 1;
		}
		return -1;
	}

	public void UpdtRow(int index, Hashtable<String, Object> ColNameVal) {
		ColNameVal.forEach((key, value) -> VecPage.get(index).put(key, value));
	}

	public void DelRows(Hashtable<String, Object> ColNameVal,String CKName) {
		int Index = 0;
		for (Hashtable<String, Object> Tuple : VecPage) {
			boolean flag = true;
			Enumeration<String> ColNameValKeys = ColNameVal.keys();
			while (ColNameValKeys.hasMoreElements()) {
				String Key = ColNameValKeys.nextElement();
				int CompVal = compare(Tuple.get(Key), ColNameVal.get(Key));
				if (CompVal != 0)
					flag = false;
			}
			if (flag) {
				VecPage.remove(Index);
				CurrRowCount--;
				}
			Index++;
		}
		CurrMax = VecPage.elementAt(CurrRowCount - 1).get(CKName);
		CurrMin = VecPage.elementAt(0).get(CKName);
	}

	public Vector<Hashtable<String, Object>> getVecPage() {
		return VecPage;
	}
}
