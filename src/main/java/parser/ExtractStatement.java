package parser;

import java.util.ArrayList;
import java.util.List;

import application.SQLTerm;

public class ExtractStatement extends sqlBaseVisitor<Void> {
	private String statementType;
	private String tableName;
	private List<String> creationColumnNames;
	private List<String> creationDatatypes;
	private String primaryKey;
	private List<String> indexColumnNames;
	private List<String> insertionColumnNames;
	private List<String> insertionValues;
	private List<String> updateColumnNames;
	private List<String> updateValues;
	private String updateConditionName;
	private String updateConditionValue;
	private List<String> deleteColumnNames;
	private List<String> deleteValues;
	private List<SQLTerm> selectSqlTerms;
	private int countSelectColumnNames = 0;
	private int countSelectColumnOperators = 0;
	private int countSelectColumnValues = 0;
	private List<String> selectOperators;

	public ExtractStatement() {
		statementType = "";
		tableName = "";
		creationColumnNames = new ArrayList<String>();
		creationDatatypes = new ArrayList<String>();
		primaryKey = "";
		indexColumnNames = new ArrayList<String>();
		insertionValues = new ArrayList<String>();
		insertionColumnNames = new ArrayList<String>();
		updateColumnNames = new ArrayList<String>();
		updateValues = new ArrayList<String>();
		updateConditionName = "";
		updateConditionValue = "";
		deleteColumnNames = new ArrayList<String>();
		deleteValues = new ArrayList<String>();
		selectSqlTerms = new ArrayList<SQLTerm>();
		selectOperators = new ArrayList<String>();
	}

	@Override
	public Void visitTable_name(sqlParser.Table_nameContext ctx) {
		// Get the text of the table name node and assign it to the field
		tableName = ctx.getText();
		// Visit the children of the node
		return super.visitTable_name(ctx);
	}

	@Override
	public Void visitSelect(sqlParser.SelectContext ctx) {
		statementType = "select";
		return visitChildren(ctx);
	}

	@Override
	public Void visitOperator(sqlParser.OperatorContext ctx) {
		selectOperators.add(ctx.getText());
		return visitChildren(ctx);
	}

	@Override
	public Void visitSelect_condition_name(sqlParser.Select_condition_nameContext ctx) {
		if (countSelectColumnNames < selectSqlTerms.size()) {
			SQLTerm temp = selectSqlTerms.get(countSelectColumnNames);
			temp.set_strColumnName(ctx.getText());
			//temp.set_strTableName(tableName);
		} else {
			SQLTerm term = new SQLTerm();
			term.set_strColumnName(ctx.getText());
			term.set_strTableName(tableName);
			selectSqlTerms.add(countSelectColumnNames, term);
		}
		countSelectColumnNames++;
		return visitChildren(ctx);
	}

	@Override
	public Void visitSelect_condition_value(sqlParser.Select_condition_valueContext ctx) {
		String value=ctx.getText();
		if (value.startsWith("'") || value.startsWith("\""))
			value = value.substring(1, value.length() - 1);
		if (countSelectColumnValues < selectSqlTerms.size()) {
			SQLTerm temp = selectSqlTerms.get(countSelectColumnValues);
			temp.set_objValue(value);
			//temp.set_strTableName(tableName);
		} else {
			SQLTerm term = new SQLTerm();
			term.set_objValue(value);
			term.set_strTableName(tableName);
			selectSqlTerms.add(countSelectColumnValues, term);
		}
		countSelectColumnValues++;
		return visitChildren(ctx);
	}

	@Override
	public Void visitOper(sqlParser.OperContext ctx) {
		if (countSelectColumnOperators < selectSqlTerms.size()) {
			SQLTerm temp = selectSqlTerms.get(countSelectColumnOperators);
			temp.set_strOperator(ctx.getText());
			//temp.set_strTableName(tableName);
		} else {
			SQLTerm term = new SQLTerm();
			term.set_strOperator(ctx.getText());
			term.set_strTableName(tableName);
			selectSqlTerms.add(countSelectColumnOperators, term);
		}
		countSelectColumnOperators++;
		return visitChildren(ctx);
	}

	@Override
	public Void visitCreate_table(sqlParser.Create_tableContext ctx) {
		statementType = "createTable";
		return visitChildren(ctx);
	}

	@Override
	public Void visitName(sqlParser.NameContext ctx) {
		creationColumnNames.add(ctx.getText());
		return visitChildren(ctx);
	}

	@Override
	public Void visitPk(sqlParser.PkContext ctx) {
		primaryKey = ctx.getText();
		return visitChildren(ctx);
	}

	@Override
	public Void visitData_type(sqlParser.Data_typeContext ctx) {
		creationDatatypes.add(ctx.getText());
		return visitChildren(ctx);
	}

	@Override
	public Void visitCreate_index(sqlParser.Create_indexContext ctx) {
		statementType = "createIndex";
		return visitChildren(ctx);
	}

	@Override
	public Void visitIndex_column(sqlParser.Index_columnContext ctx) {
		indexColumnNames.add(ctx.getText());
		return visitChildren(ctx);
	}

	@Override
	public Void visitUpdate(sqlParser.UpdateContext ctx) {
		statementType = "update";
		return visitChildren(ctx);
	}

	@Override
	public Void visitUpdate_name(sqlParser.Update_nameContext ctx) {
		updateColumnNames.add(ctx.getText());
		return visitChildren(ctx);
	}

	@Override
	public Void visitUpdate_value(sqlParser.Update_valueContext ctx) {
		String s = ctx.getText();
		if (s.startsWith("'") || s.startsWith("\""))
			s = s.substring(1, s.length() - 1);
		updateValues.add(s);
		return visitChildren(ctx);
	}

	@Override
	public Void visitUpdate_condition_name(sqlParser.Update_condition_nameContext ctx) {
		updateConditionName = ctx.getText();
		return visitChildren(ctx);
	}

	@Override
	public Void visitUpdate_condition_value(sqlParser.Update_condition_valueContext ctx) {
		String s = ctx.getText();
		if (s.startsWith("'") || s.startsWith("\""))
			s = s.substring(1, s.length() - 1);
		updateConditionValue = s;
		return visitChildren(ctx);
	}

	@Override
	public Void visitDelete(sqlParser.DeleteContext ctx) {
		statementType = "delete";
		return visitChildren(ctx);
	}

	@Override
	public Void visitDelete_condition_name(sqlParser.Delete_condition_nameContext ctx) {
		deleteColumnNames.add(ctx.getText());
		return visitChildren(ctx);
	}

	@Override
	public Void visitDelete_condition_value(sqlParser.Delete_condition_valueContext ctx) {
		String s = ctx.getText();
		if (s.startsWith("'") || s.startsWith("\""))
			s = s.substring(1, s.length() - 1);
		deleteValues.add(s);
		return visitChildren(ctx);
	}

	@Override
	public Void visitInsert(sqlParser.InsertContext ctx) {
		statementType = "insert";
		return visitChildren(ctx);
	}

	@Override
	public Void visitValue(sqlParser.ValueContext ctx) {
		String s = ctx.getText();
		if (s.startsWith("'") || s.startsWith("\""))
			s = s.substring(1, s.length() - 1);
		insertionValues.add(s);
		return super.visitValue(ctx);
	}

	@Override
	public Void visitColumn_name(sqlParser.Column_nameContext ctx) {
		insertionColumnNames.add(ctx.getText());
		return super.visitColumn_name(ctx);
	}

	public List<String> getCreationColumnNames() {
		return creationColumnNames;
	}

	public List<String> getCreationDatatypes() {
		return creationDatatypes;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public String getStatementType() {
		return statementType;
	}

	// A getter method to return the table name
	public String getTableName() {
		return tableName;
	}

	// A getter method to return the values
	public List<String> getInsertionValues() {
		return insertionValues;
	}

	// A getter method to return the insertion column names
	public List<String> getInsertionColumnNames() {
		return insertionColumnNames;
	}

	public List<String> getUpdateColumnNames() {
		return updateColumnNames;
	}

	public List<String> getUpdateValues() {
		return updateValues;
	}

	public String getUpdateConditionName() {
		return updateConditionName;
	}

	public String getUpdateConditionValue() {
		return updateConditionValue;
	}

	public List<String> getDeleteColumnNames() {
		return deleteColumnNames;
	}

	public List<String> getDeleteValues() {
		return deleteValues;
	}

	public List<String> getIndexColumnNames() {
		return indexColumnNames;
	}

	public List<SQLTerm> getSelectSqlTerms() {
		return selectSqlTerms;
	}

	public List<String> getSelectOperators() {
		return selectOperators;
	}

}
