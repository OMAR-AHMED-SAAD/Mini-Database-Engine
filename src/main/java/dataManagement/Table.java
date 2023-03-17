package dataManagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import exceptions.DBAppException;

public class Table {

	private String TblName;
	private String ClustKey;
	private Hashtable<Integer, String> PageFilePath = new Hashtable<Integer, String>();
	ArrayList<Object> PageMaxClustKey = new ArrayList<Object>();
	private int PageCount;

	public Table(String name, String ClustKey) {
		this.TblName = name;
		this.ClustKey = ClustKey;
		PageCount = 0;

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
				(key, value) -> pw.println(this.TblName + "," + key + "," + value + "," + key.equals(this.ClustKey)
						+ "," + "null" + "," + "null" + "," + ColNameMin.get(key) + "," + ColNameMax.get(key)));
		pw.flush();
		pw.close();
	}

	public void InsertInTable(Hashtable<String, Object> ColNameValue) throws DBAppException {
		if (PageCount == 0)
			CreateAddNewRow(ColNameValue);
		else if (PageCount > 0) {
			int Min = 0;
			int Max = PageCount - 1;
			while (Min != Max) {
				int Mid = (Max + Min) / 2;
				int ComparisonValue = this.compare(ColNameValue.get(ClustKey), PageMaxClustKey.get(Mid));
				if (ComparisonValue == 0)
					throw new DBAppException("Can not accept duplicate primary keys");
				else if (ComparisonValue < 0)
					Max = Mid;
				else
					Min = Mid + 1;
			}
			int ComparisonValue = this.compare(ColNameValue.get(ClustKey), PageMaxClustKey.get(Min));
			Page InsertionPage = this.LoadPage(PageFilePath.get(Min));
			if (Min == (PageCount - 1) && ComparisonValue > 0 && InsertionPage.IsFull()) {
				CreateAddNewRow(ColNameValue);
				InsertionPage.UnLoadPage();
				}
			else {
				Hashtable<String, Object> PageInsertResult = InsertionPage.InsertToPage(ClustKey, ColNameValue);
				InsertionPage.UnLoadPage();
				if (PageInsertResult == null)
					return;
				ComparisonValue = this.compare(ColNameValue.get(ClustKey), PageInsertResult.get(ClustKey));
				if (ComparisonValue == 0)
					throw new DBAppException("Can not accept duplicate primary keys");
				else
					OverflowSolver(PageInsertResult, Min + 1);
			}

		}
	}

	private void CreateAddNewRow(Hashtable<String, Object> ColNameValue) {

		Page NewPage = new Page(PageCount, this.TblName);
		this.PageFilePath.put(NewPage.getPageId(), NewPage.getFilePath());
		NewPage.InsertToPage(ClustKey, ColNameValue);
		this.PageMaxClustKey.add(NewPage.getCurrMax());
		PageCount++;
		NewPage.UnLoadPage();

	}

	private void OverflowSolver(Hashtable<String, Object> PageInsertResult, int CurrPagePtr) throws DBAppException {

		if (PageInsertResult == null)
			return;
		if (CurrPagePtr == PageCount) {
			CreateAddNewRow(PageInsertResult);
			return;
		} else {
			Page InsertionPage = this.LoadPage(PageFilePath.get(CurrPagePtr));
			PageInsertResult = InsertionPage.InsertToPage(ClustKey, PageInsertResult);
			InsertionPage.UnLoadPage();
			OverflowSolver(PageInsertResult, CurrPagePtr + 1);
		}
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

	public String getName() {
		return TblName;
	}

	public String getClusteringKey() {
		return ClustKey;
	}

	public static void main(String[] args) throws IOException {

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
	//	Table t2 = new Table("Doctor", "id");

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
		try {
			t.InsertInTable(htblColNameValue);
			t.InsertInTable(htblColNameValue1);
			t.InsertInTable(htblColNameValue);
			//t.InsertInTable(htblColNameValue2);
			//t.InsertInTable(htblColNameValue3);
			//t.InsertInTable(htblColNameValue4);
		} catch (DBAppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
		// t.AddMetaData(NameType, min, max);
//			 t2.AddMetaData(NameType, min, max);
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//		}
	}

}
