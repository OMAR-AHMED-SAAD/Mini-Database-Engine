package applicationModules;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.Vector;

import basicTools.ComparatorI;
import exceptions.DBAppException;

public class Page implements Serializable, ComparatorI {
	private static final long serialVersionUID = 1L;
	private String TblName;
	private int PageId;
	private Object CurrMin;
	private Object CurrMax;
	private int CurrRowCount;
	private int MaxRowCount;
	private String FilePath;
	private Vector<Hashtable<String, Object>> VecPage = new Vector<Hashtable<String, Object>>();

	public Page(int id, String TblName) throws DBAppException {
		PageId = id;
		this.TblName = TblName;
		FilePath = "src/main/resources/Pages/" + this.TblName + "Page" + this.PageId + ".bin";
		CurrRowCount = 0;
		Properties Prop = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream("src/main/resources/DBApp.config");
			Prop.load(inputStream);
		} catch (Exception e) {
			throw new DBAppException(e.getMessage());
		}
		this.MaxRowCount = Integer.parseInt(Prop.getProperty("MaximumRowsCountinTablePage"));

	}

	public void UnLoadPage() throws DBAppException {
		try {
			ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(new FileOutputStream(FilePath));
			ObjectOutputStream.writeObject(this);
			ObjectOutputStream.close();
		} catch (Exception e) {
			throw new DBAppException(e.getMessage());
		}
	}

	public Hashtable<String, Object> InsertToPage(String ClustKey, Hashtable<String, Object> ColNameValue)
			throws DBAppException {
		Hashtable<String, Object> Result = null;
		if (CurrRowCount == 0) {
			VecPage.add(ColNameValue);
			CurrRowCount++;
		} else {
			VecPage.add(binarySearchInsertionIndex(ClustKey, VecPage, ColNameValue), ColNameValue);
			CurrRowCount++;
		}
		if (CurrRowCount > MaxRowCount) {
			Result = VecPage.elementAt(CurrRowCount - 1);
			VecPage.remove(--CurrRowCount);

		}
		CurrMax = VecPage.elementAt(CurrRowCount - 1).get(ClustKey);
		CurrMin = VecPage.elementAt(0).get(ClustKey);
		return Result;
	}

	private int binarySearchInsertionIndex(String clkName, Vector<Hashtable<String, Object>> vecOfPages,
			Hashtable<String, Object> ColNameValue) throws DBAppException {
		int Min = 0;
		int Max = vecOfPages.size() - 1;
		while (Min <= Max) {
			int Mid = (Min + Max) / 2;
			int ComparisonValue = C.compare(vecOfPages.get(Mid).get(clkName), ColNameValue.get(clkName));
			if (ComparisonValue == 0) {
				throw new DBAppException("Can not accept duplicate primary keys");
			} else if (ComparisonValue < 0) {
				Min = Mid + 1;
			} else {
				Max = Mid - 1;
			}
		}
		return Min;
	}

	public boolean IsFull() {
		return (CurrRowCount == MaxRowCount);
	}

	public boolean IsEmpty() {
		return (CurrRowCount == 0);
	}

	public int IsRowFound(String CKName, Object CkValO) throws DBAppException {
		int Min = 0;
		int Max = VecPage.size() - 1;
		while (Min <= Max) {
			int Mid = (Min + Max) / 2;
			Hashtable<String, Object> CurrRow = VecPage.get(Mid);
			int comparisonVal = C.compare(CkValO, CurrRow.get(CKName));
			if (comparisonVal == 0)
				return Mid;
			else if (comparisonVal < 0)
				Max = Mid - 1;
			else
				Min = Mid + 1;
		}
		return -1;
	}

	public Vector<Hashtable<String, Object>> UpdtRow(int index, Hashtable<String, Object> ColNameVal) {
		Vector<Hashtable<String, Object>> oldAndNewValues=new Vector<Hashtable<String, Object>>();
		oldAndNewValues.add(VecPage.get(index));
		ColNameVal.forEach((key, value) -> VecPage.get(index).put(key, value));
		oldAndNewValues.add(VecPage.get(index));
		return oldAndNewValues;
	}

	public Vector<Hashtable<String, Object>> DelRows(Hashtable<String, Object> ColNameVal, String CKName)
			throws DBAppException {
		Vector<Hashtable<String, Object>> deletedRows = new Vector<Hashtable<String, Object>>();
		for (int Index = 0; Index < VecPage.size(); Index++) {
			boolean flag = true;
			Enumeration<String> ColNameValKeys = ColNameVal.keys();
			while (ColNameValKeys.hasMoreElements()) {
				String Key = ColNameValKeys.nextElement();
				int CompVal = C.compare(VecPage.get(Index).get(Key), ColNameVal.get(Key));
				if (CompVal != 0)
					flag = false;
			}
			if (flag) {
				deletedRows.add(VecPage.get(Index));
				VecPage.remove(Index--);
				CurrRowCount--;
			}

		}
		if (!this.IsEmpty()) {
			CurrMax = VecPage.elementAt(CurrRowCount - 1).get(CKName);
			CurrMin = VecPage.elementAt(0).get(CKName);
		}
		return deletedRows;
	}

	public Vector<Hashtable<String, Object>> DelRows(Hashtable<String, Object> ColNameVal, String CKName, int rowIndex)
			throws DBAppException {
		Vector<Hashtable<String, Object>> deletedRows = new Vector<Hashtable<String, Object>>();
		boolean flag = true;
		Enumeration<String> ColNameValKeys = ColNameVal.keys();
		while (ColNameValKeys.hasMoreElements()) {
			String Key = ColNameValKeys.nextElement();
			int CompVal = C.compare(VecPage.get(rowIndex).get(Key), ColNameVal.get(Key));
			if (CompVal != 0)
				flag = false;
		}
		if (flag) {
			deletedRows.add(VecPage.get(rowIndex));
			VecPage.remove(rowIndex);
			CurrRowCount--;
		}
		if (!this.IsEmpty()) {
			CurrMax = VecPage.elementAt(CurrRowCount - 1).get(CKName);
			CurrMin = VecPage.elementAt(0).get(CKName);
		}
		return deletedRows;
	}

	public Vector<Hashtable<String, Object>> getVecPage() {
		return VecPage;
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

	public String toString(Vector<String> columnNames) {
		StringBuilder sb = new StringBuilder();
		sb.append("Page ID: ").append(PageId).append("\n");
		sb.append("Table Name: ").append(TblName).append("\n");
		sb.append("Current Min: ").append(CurrMin).append("\n");
		sb.append("Current Max: ").append(CurrMax).append("\n");
		sb.append("Current Row Count: ").append(CurrRowCount).append("\n");
		sb.append("Maximum Row Count: ").append(MaxRowCount).append("\n");
		sb.append("File Path: ").append(FilePath).append("\n");

		// Append the table as a string
		sb.append("Table Contents:\n");
		// Get the column names from the first hashtable in the vector
		// Determine the maximum width of each column
		Map<String, Integer> columnWidths = new TreeMap<String, Integer>();
		for (String columnName : columnNames) {
			int maxWidth = columnName.length();
			for (Hashtable<String, Object> row : VecPage) {
				Object value = row.get(columnName);
				if (value != null) {
					if (value instanceof Date) {
						// Format date cells as yyyy-MM-dd
						maxWidth = Math.max(maxWidth, 10);
					} else {
						maxWidth = Math.max(maxWidth, value.toString().length());
					}
				}
			}
			columnWidths.put(columnName, maxWidth);
		}

		// Append the header row
		for (String columnName : columnNames) {
			int width = columnWidths.get(columnName);
			sb.append(String.format("%-" + width + "s", columnName)).append("  ");
		}
		sb.append("\n");

		// Append a separator row
		for (String columnName : columnNames) {
			int width = columnWidths.get(columnName);
			sb.append(String.format("%-" + width + "s", "").replace(' ', '-')).append("--");
		}
		sb.append("\n");

		// Append each row of the table
		for (Hashtable<String, Object> row : VecPage) {
			for (String columnName : columnNames) {
				int width = columnWidths.get(columnName);
				Object value = row.get(columnName);
				if (value instanceof Date) {
					// Format date cells as yyyy-MM-dd
					sb.append(String.format("%-" + width + "s", new SimpleDateFormat("yyyy-MM-dd").format(value)))
							.append("  ");
				} else {
					sb.append(String.format("%-" + width + "s", value)).append("  ");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
