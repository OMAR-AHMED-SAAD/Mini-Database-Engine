// Generated from sql.g4 by ANTLR 4.9.2

package parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link sqlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface sqlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link sqlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(sqlParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#select}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect(sqlParser.SelectContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#select_conditions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_conditions(sqlParser.Select_conditionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(sqlParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#select_condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_condition(sqlParser.Select_conditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#select_condition_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_condition_name(sqlParser.Select_condition_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#select_condition_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect_condition_value(sqlParser.Select_condition_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#oper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOper(sqlParser.OperContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#create_index}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_index(sqlParser.Create_indexContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#index_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex_name(sqlParser.Index_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#index_columns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex_columns(sqlParser.Index_columnsContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#index_column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex_column(sqlParser.Index_columnContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#create_table}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate_table(sqlParser.Create_tableContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#creation_columns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreation_columns(sqlParser.Creation_columnsContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#creation_column}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreation_column(sqlParser.Creation_columnContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#pk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPk(sqlParser.PkContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(sqlParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#data_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_type(sqlParser.Data_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(sqlParser.IntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#update}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate(sqlParser.UpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#update_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_list(sqlParser.Update_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#update_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_item(sqlParser.Update_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#update_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_name(sqlParser.Update_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#update_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_value(sqlParser.Update_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#update_condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_condition(sqlParser.Update_conditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#update_condition_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_condition_name(sqlParser.Update_condition_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#update_condition_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate_condition_value(sqlParser.Update_condition_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#delete}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete(sqlParser.DeleteContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#delete_conditions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete_conditions(sqlParser.Delete_conditionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#delete_condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete_condition(sqlParser.Delete_conditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#delete_condition_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete_condition_name(sqlParser.Delete_condition_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#delete_condition_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete_condition_value(sqlParser.Delete_condition_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#insert}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert(sqlParser.InsertContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#column_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_list(sqlParser.Column_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#values_clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValues_clause(sqlParser.Values_clauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(sqlParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#table_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable_name(sqlParser.Table_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link sqlParser#column_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_name(sqlParser.Column_nameContext ctx);
}