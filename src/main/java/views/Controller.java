package views;

import java.awt.Color;

import java.util.Enumeration;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import application.DBApp;
import applicationModules.Table;
import exceptions.DBAppException;

public class Controller {
	private DBApp db;
	private boolean changed = true;
	private View view;

	public Controller(View view) {
		db = new DBApp();
		db.init();
		this.view = view;
	}

	public void GetCurrTables() throws DBAppException {
		if (!changed)
			return;
		view.leftPanel.setText("");
		StyledDocument doc = view.leftPanel.getStyledDocument();

		// Create some styles for the text
		Style style = doc.addStyle("regular", null);
		StyleConstants.setFontFamily(style, "Segoe UI Emoji");
		StyleConstants.setForeground(style, Color.ORANGE);
		StyleConstants.setFontSize(style, 17);
		style = doc.addStyle("bold", style);
		StyleConstants.setBold(style, true);
		style = doc.addStyle("header", style);
		StyleConstants.setFontSize(style, 22);

		StringBuilder sb = new StringBuilder();
		sb.append("Tables").append("\n");
		Enumeration<String> keys = db.getCreatedTables().keys();
		if (!keys.hasMoreElements()) {
			try {
				doc.insertString(doc.getLength(), " No Tables \n Created Yet !", doc.getStyle("header"));
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		while (keys.hasMoreElements()) {
			String tableName = keys.nextElement();
			String FilePath = db.getCreatedTables().get(tableName);
			Table table = db.LoadTable(FilePath);
			table.ReadMetaData();
			try {
				String pin = "\uD83D\uDCBE";
				String k = "\uD83D\uDD11";
				String c = "\u270E";

				doc.insertString(doc.getLength(), " " + pin + " " + tableName + "\n", doc.getStyle("header"));
				if (table.getCreationOrder().isEmpty())
					doc.insertString(doc.getLength(), "       No Columns" + "\n", doc.getStyle("regular"));
				for (String s : table.getCreationOrder()) {
					if (s.equals(table.getCKName()))
						doc.insertString(doc.getLength(), "       " + k + " " + s + "\n", doc.getStyle("regular"));
					else
						doc.insertString(doc.getLength(), "       " + c + " " + s + "\n", doc.getStyle("regular"));
				}

			} catch (BadLocationException e) {
				e.printStackTrace();
			}

			db.UnLoadTable(table, FilePath);
		}
		changed = false;

	}

	public void run(String s) {
		if (s == null)
			return;
		changed = true;
		StringBuffer sb = new StringBuffer(s);
		try {
			if (db.parseSQL(sb) == null) {
				view.consoleJPanel.setForeground(Color.GREEN);
				view.consoleJPanel.setText(" "+s + "\nexecuted succesfully");
			}
			else {
				//print iterator result of select statement
			}
		} catch (DBAppException e) {
			// TODO Auto-generated catch block
			view.consoleJPanel.setForeground(Color.RED);
			view.consoleJPanel.setText(e.getMessage());
		}
	}

}
