package views;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import application.DBApp;
import applicationModules.Table;
import exceptions.DBAppException;
import parser.ExtractStatement;
import parser.sqlLexer;
import parser.sqlParser;

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
			double executionTime = System.currentTimeMillis();
			Vector<Hashtable<String, Object>> dbResult = db.parseSQL(sb);
			executionTime = System.currentTimeMillis() - executionTime;
			if (dbResult == null) {
				view.consoleJPanel.setFont(view.fontd);
				view.consoleJPanel.setForeground(Color.GREEN);
				view.consoleJPanel.setText("executed succesfully in " + executionTime + " ms");
			} else {
				// print iterator result of select statement
				view.consoleJPanel.setForeground(Color.white);
				view.consoleJPanel.setFont(new Font("Courier New", Font.PLAIN, 14));
				sb = new StringBuffer();
				CharStream cs = CharStreams.fromString(s);
				sqlLexer lexer = new sqlLexer(cs);
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				sqlParser parser = new sqlParser(tokens);
				ExtractStatement es = new ExtractStatement();
				es.visit(parser.statement());
				String tableName = es.getTableName();
				String FilePath = db.getCreatedTables().get(tableName);
				Table table = db.LoadTable(FilePath);
				table.ReadMetaData();
				Vector<String> header = table.getCreationOrder();
				db.UnLoadTable(table, FilePath);
				sb.append(printSelect(dbResult, header));
				sb.append("\nexecuted succesfully in " + executionTime + " ms");
				view.consoleJPanel.setText(sb.toString());
			}
		} catch (DBAppException e) {
			// TODO Auto-generated catch block
			view.consoleJPanel.setFont(view.fontd);
			view.consoleJPanel.setForeground(Color.RED);
			view.consoleJPanel.setText("Error: " + e.getMessage());
		}
	}

	public String printSelect(Vector<Hashtable<String, Object>> data, Vector<String> header) {
		StringBuilder sb = new StringBuilder();

		// Determine the maximum width of each column
		int[] columnWidths = new int[header.size()];
		for (int i = 0; i < header.size(); i++) {
			columnWidths[i] = header.get(i).length();
			for (Hashtable<String, Object> row : data) {
				Object value = row.get(header.get(i));
				String stringValue = value + "";
				if (value instanceof Date)
					stringValue = new SimpleDateFormat("yyyy-MM-dd").format(value);
				if (stringValue.length() > columnWidths[i])
					columnWidths[i] = stringValue.length();
			}
		}

		// Append the header row
		for (int i = 0; i < header.size(); i++) {
			sb.append(String.format("%-" + columnWidths[i] + "s", header.get(i)));
			if (i < header.size() - 1)
				sb.append(" | ");
		}
		sb.append("\n");

		// Append a separator row
		for (int i = 0; i < header.size(); i++) {
			for (int j = 0; j < columnWidths[i]; j++)
				sb.append("-");
			if (i < header.size() - 1)
				sb.append("-+-");

		}
		sb.append("\n");

		// Append the data rows
		for (Hashtable<String, Object> row : data) {
			for (int i = 0; i < header.size(); i++) {
				Object value = row.get(header.get(i));
				String stringValue = value + "";
				if (value instanceof Date)
					stringValue = new SimpleDateFormat("yyyy-MM-dd").format(value);
				sb.append(String.format("%-" + columnWidths[i] + "s", stringValue));
				if (i < header.size() - 1)
					sb.append(" | ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
