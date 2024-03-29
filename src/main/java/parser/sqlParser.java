// Generated from sql.g4 by ANTLR 4.9.2

package parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class sqlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CREATE=1, TABLE=2, PRIMARY=3, KEY=4, INT=5, INTEGER=6, VARCHAR=7, DATE=8, 
		DOUBLE=9, UPDATE=10, SET=11, WHERE=12, INSERT=13, INTO=14, VALUES=15, 
		DELETE=16, FROM=17, AND=18, OR=19, XOR=20, INDEX=21, ON=22, USING=23, 
		OCTREE=24, SELECT=25, ID=26, INTEG=27, DOUB=28, DAT=29, MONTH=30, DAY=31, 
		DASH=32, POINT=33, SEMICOL=34, STRING=35, IDENTIFIER=36, EQUAL=37, SMALLER=38, 
		GREATER=39, SMALLERE=40, GREATERE=41, NOTEQUAL=42, LPRAN=43, RPRAN=44, 
		COMMA=45, STAR=46, WS=47;
	public static final int
		RULE_statement = 0, RULE_select = 1, RULE_select_conditions = 2, RULE_operator = 3, 
		RULE_select_condition = 4, RULE_select_condition_name = 5, RULE_select_condition_value = 6, 
		RULE_oper = 7, RULE_create_index = 8, RULE_index_name = 9, RULE_index_columns = 10, 
		RULE_index_column = 11, RULE_create_table = 12, RULE_creation_columns = 13, 
		RULE_creation_column = 14, RULE_pk = 15, RULE_name = 16, RULE_data_type = 17, 
		RULE_integer = 18, RULE_update = 19, RULE_update_list = 20, RULE_update_item = 21, 
		RULE_update_name = 22, RULE_update_value = 23, RULE_update_condition = 24, 
		RULE_update_condition_name = 25, RULE_update_condition_value = 26, RULE_delete = 27, 
		RULE_delete_conditions = 28, RULE_delete_condition = 29, RULE_delete_condition_name = 30, 
		RULE_delete_condition_value = 31, RULE_insert = 32, RULE_column_list = 33, 
		RULE_values_clause = 34, RULE_value = 35, RULE_table_name = 36, RULE_column_name = 37;
	private static String[] makeRuleNames() {
		return new String[] {
			"statement", "select", "select_conditions", "operator", "select_condition", 
			"select_condition_name", "select_condition_value", "oper", "create_index", 
			"index_name", "index_columns", "index_column", "create_table", "creation_columns", 
			"creation_column", "pk", "name", "data_type", "integer", "update", "update_list", 
			"update_item", "update_name", "update_value", "update_condition", "update_condition_name", 
			"update_condition_value", "delete", "delete_conditions", "delete_condition", 
			"delete_condition_name", "delete_condition_value", "insert", "column_list", 
			"values_clause", "value", "table_name", "column_name"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "'-'", "'.'", "';'", 
			null, null, "'='", "'<'", "'>'", "'<='", "'>='", "'!='", "'('", "')'", 
			"','", "'*'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CREATE", "TABLE", "PRIMARY", "KEY", "INT", "INTEGER", "VARCHAR", 
			"DATE", "DOUBLE", "UPDATE", "SET", "WHERE", "INSERT", "INTO", "VALUES", 
			"DELETE", "FROM", "AND", "OR", "XOR", "INDEX", "ON", "USING", "OCTREE", 
			"SELECT", "ID", "INTEG", "DOUB", "DAT", "MONTH", "DAY", "DASH", "POINT", 
			"SEMICOL", "STRING", "IDENTIFIER", "EQUAL", "SMALLER", "GREATER", "SMALLERE", 
			"GREATERE", "NOTEQUAL", "LPRAN", "RPRAN", "COMMA", "STAR", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "sql.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public sqlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StatementContext extends ParserRuleContext {
		public InsertContext insert() {
			return getRuleContext(InsertContext.class,0);
		}
		public UpdateContext update() {
			return getRuleContext(UpdateContext.class,0);
		}
		public Create_tableContext create_table() {
			return getRuleContext(Create_tableContext.class,0);
		}
		public DeleteContext delete() {
			return getRuleContext(DeleteContext.class,0);
		}
		public Create_indexContext create_index() {
			return getRuleContext(Create_indexContext.class,0);
		}
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_statement);
		try {
			setState(82);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				insert();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				update();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				create_table();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(79);
				delete();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(80);
				create_index();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(81);
				select();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(sqlParser.SELECT, 0); }
		public TerminalNode STAR() { return getToken(sqlParser.STAR, 0); }
		public TerminalNode FROM() { return getToken(sqlParser.FROM, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(sqlParser.WHERE, 0); }
		public TerminalNode EOF() { return getToken(sqlParser.EOF, 0); }
		public Select_conditionsContext select_conditions() {
			return getRuleContext(Select_conditionsContext.class,0);
		}
		public TerminalNode SEMICOL() { return getToken(sqlParser.SEMICOL, 0); }
		public SelectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitSelect(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectContext select() throws RecognitionException {
		SelectContext _localctx = new SelectContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_select);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(SELECT);
			setState(85);
			match(STAR);
			setState(86);
			match(FROM);
			setState(87);
			table_name();
			setState(88);
			match(WHERE);
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CREATE) | (1L << TABLE) | (1L << PRIMARY) | (1L << KEY) | (1L << INT) | (1L << INTEGER) | (1L << VARCHAR) | (1L << DATE) | (1L << DOUBLE) | (1L << UPDATE) | (1L << SET) | (1L << WHERE) | (1L << INSERT) | (1L << INTO) | (1L << VALUES) | (1L << DELETE) | (1L << FROM) | (1L << AND) | (1L << ID))) != 0)) {
				{
				setState(89);
				select_conditions();
				}
			}

			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOL) {
				{
				setState(92);
				match(SEMICOL);
				}
			}

			setState(95);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_conditionsContext extends ParserRuleContext {
		public List<Select_conditionContext> select_condition() {
			return getRuleContexts(Select_conditionContext.class);
		}
		public Select_conditionContext select_condition(int i) {
			return getRuleContext(Select_conditionContext.class,i);
		}
		public List<OperatorContext> operator() {
			return getRuleContexts(OperatorContext.class);
		}
		public OperatorContext operator(int i) {
			return getRuleContext(OperatorContext.class,i);
		}
		public Select_conditionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_conditions; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitSelect_conditions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_conditionsContext select_conditions() throws RecognitionException {
		Select_conditionsContext _localctx = new Select_conditionsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_select_conditions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			select_condition();
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << XOR))) != 0)) {
				{
				{
				setState(98);
				operator();
				setState(99);
				select_condition();
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(sqlParser.AND, 0); }
		public TerminalNode OR() { return getToken(sqlParser.OR, 0); }
		public TerminalNode XOR() { return getToken(sqlParser.XOR, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << XOR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_conditionContext extends ParserRuleContext {
		public Select_condition_nameContext select_condition_name() {
			return getRuleContext(Select_condition_nameContext.class,0);
		}
		public OperContext oper() {
			return getRuleContext(OperContext.class,0);
		}
		public Select_condition_valueContext select_condition_value() {
			return getRuleContext(Select_condition_valueContext.class,0);
		}
		public Select_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_condition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitSelect_condition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_conditionContext select_condition() throws RecognitionException {
		Select_conditionContext _localctx = new Select_conditionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_select_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			select_condition_name();
			setState(109);
			oper();
			setState(110);
			select_condition_value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_condition_nameContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Select_condition_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_condition_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitSelect_condition_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_condition_nameContext select_condition_name() throws RecognitionException {
		Select_condition_nameContext _localctx = new Select_condition_nameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_select_condition_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			column_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_condition_valueContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public Select_condition_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_condition_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitSelect_condition_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_condition_valueContext select_condition_value() throws RecognitionException {
		Select_condition_valueContext _localctx = new Select_condition_valueContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_select_condition_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperContext extends ParserRuleContext {
		public TerminalNode EQUAL() { return getToken(sqlParser.EQUAL, 0); }
		public TerminalNode SMALLER() { return getToken(sqlParser.SMALLER, 0); }
		public TerminalNode GREATER() { return getToken(sqlParser.GREATER, 0); }
		public TerminalNode SMALLERE() { return getToken(sqlParser.SMALLERE, 0); }
		public TerminalNode GREATERE() { return getToken(sqlParser.GREATERE, 0); }
		public TerminalNode NOTEQUAL() { return getToken(sqlParser.NOTEQUAL, 0); }
		public OperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperContext oper() throws RecognitionException {
		OperContext _localctx = new OperContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_oper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUAL) | (1L << SMALLER) | (1L << GREATER) | (1L << SMALLERE) | (1L << GREATERE) | (1L << NOTEQUAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Create_indexContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(sqlParser.CREATE, 0); }
		public TerminalNode INDEX() { return getToken(sqlParser.INDEX, 0); }
		public Index_nameContext index_name() {
			return getRuleContext(Index_nameContext.class,0);
		}
		public TerminalNode ON() { return getToken(sqlParser.ON, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Index_columnsContext index_columns() {
			return getRuleContext(Index_columnsContext.class,0);
		}
		public TerminalNode USING() { return getToken(sqlParser.USING, 0); }
		public TerminalNode OCTREE() { return getToken(sqlParser.OCTREE, 0); }
		public TerminalNode EOF() { return getToken(sqlParser.EOF, 0); }
		public TerminalNode SEMICOL() { return getToken(sqlParser.SEMICOL, 0); }
		public Create_indexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_index; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitCreate_index(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_indexContext create_index() throws RecognitionException {
		Create_indexContext _localctx = new Create_indexContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_create_index);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(CREATE);
			setState(119);
			match(INDEX);
			setState(120);
			index_name();
			setState(121);
			match(ON);
			setState(122);
			table_name();
			setState(123);
			index_columns();
			setState(124);
			match(USING);
			setState(125);
			match(OCTREE);
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOL) {
				{
				setState(126);
				match(SEMICOL);
				}
			}

			setState(129);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Index_nameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(sqlParser.ID, 0); }
		public Index_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitIndex_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Index_nameContext index_name() throws RecognitionException {
		Index_nameContext _localctx = new Index_nameContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_index_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Index_columnsContext extends ParserRuleContext {
		public TerminalNode LPRAN() { return getToken(sqlParser.LPRAN, 0); }
		public List<Index_columnContext> index_column() {
			return getRuleContexts(Index_columnContext.class);
		}
		public Index_columnContext index_column(int i) {
			return getRuleContext(Index_columnContext.class,i);
		}
		public TerminalNode RPRAN() { return getToken(sqlParser.RPRAN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(sqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(sqlParser.COMMA, i);
		}
		public Index_columnsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index_columns; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitIndex_columns(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Index_columnsContext index_columns() throws RecognitionException {
		Index_columnsContext _localctx = new Index_columnsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_index_columns);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(LPRAN);
			setState(134);
			index_column();
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(135);
				match(COMMA);
				setState(136);
				index_column();
				}
				}
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(142);
			match(RPRAN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Index_columnContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(sqlParser.ID, 0); }
		public Index_columnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index_column; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitIndex_column(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Index_columnContext index_column() throws RecognitionException {
		Index_columnContext _localctx = new Index_columnContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_index_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Create_tableContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(sqlParser.CREATE, 0); }
		public TerminalNode TABLE() { return getToken(sqlParser.TABLE, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Creation_columnsContext creation_columns() {
			return getRuleContext(Creation_columnsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(sqlParser.EOF, 0); }
		public TerminalNode SEMICOL() { return getToken(sqlParser.SEMICOL, 0); }
		public Create_tableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_table; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitCreate_table(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_tableContext create_table() throws RecognitionException {
		Create_tableContext _localctx = new Create_tableContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_create_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(CREATE);
			setState(147);
			match(TABLE);
			setState(148);
			table_name();
			setState(149);
			creation_columns();
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOL) {
				{
				setState(150);
				match(SEMICOL);
				}
			}

			setState(153);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Creation_columnsContext extends ParserRuleContext {
		public List<TerminalNode> LPRAN() { return getTokens(sqlParser.LPRAN); }
		public TerminalNode LPRAN(int i) {
			return getToken(sqlParser.LPRAN, i);
		}
		public List<Creation_columnContext> creation_column() {
			return getRuleContexts(Creation_columnContext.class);
		}
		public Creation_columnContext creation_column(int i) {
			return getRuleContext(Creation_columnContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(sqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(sqlParser.COMMA, i);
		}
		public TerminalNode PRIMARY() { return getToken(sqlParser.PRIMARY, 0); }
		public TerminalNode KEY() { return getToken(sqlParser.KEY, 0); }
		public PkContext pk() {
			return getRuleContext(PkContext.class,0);
		}
		public List<TerminalNode> RPRAN() { return getTokens(sqlParser.RPRAN); }
		public TerminalNode RPRAN(int i) {
			return getToken(sqlParser.RPRAN, i);
		}
		public Creation_columnsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creation_columns; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitCreation_columns(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Creation_columnsContext creation_columns() throws RecognitionException {
		Creation_columnsContext _localctx = new Creation_columnsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_creation_columns);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(LPRAN);
			setState(156);
			creation_column();
			setState(161);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(157);
					match(COMMA);
					setState(158);
					creation_column();
					}
					} 
				}
				setState(163);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(164);
			match(COMMA);
			setState(165);
			match(PRIMARY);
			setState(166);
			match(KEY);
			setState(167);
			match(LPRAN);
			setState(168);
			pk();
			setState(169);
			match(RPRAN);
			setState(170);
			match(RPRAN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Creation_columnContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Data_typeContext data_type() {
			return getRuleContext(Data_typeContext.class,0);
		}
		public Creation_columnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creation_column; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitCreation_column(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Creation_columnContext creation_column() throws RecognitionException {
		Creation_columnContext _localctx = new Creation_columnContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_creation_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			name();
			setState(173);
			data_type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PkContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(sqlParser.ID, 0); }
		public PkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pk; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitPk(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PkContext pk() throws RecognitionException {
		PkContext _localctx = new PkContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_pk);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(sqlParser.ID, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Data_typeContext extends ParserRuleContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public TerminalNode LPRAN() { return getToken(sqlParser.LPRAN, 0); }
		public List<TerminalNode> INTEG() { return getTokens(sqlParser.INTEG); }
		public TerminalNode INTEG(int i) {
			return getToken(sqlParser.INTEG, i);
		}
		public TerminalNode RPRAN() { return getToken(sqlParser.RPRAN, 0); }
		public TerminalNode VARCHAR() { return getToken(sqlParser.VARCHAR, 0); }
		public TerminalNode DOUBLE() { return getToken(sqlParser.DOUBLE, 0); }
		public TerminalNode COMMA() { return getToken(sqlParser.COMMA, 0); }
		public TerminalNode DATE() { return getToken(sqlParser.DATE, 0); }
		public Data_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitData_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Data_typeContext data_type() throws RecognitionException {
		Data_typeContext _localctx = new Data_typeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_data_type);
		int _la;
		try {
			setState(196);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				integer();
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPRAN) {
					{
					setState(180);
					match(LPRAN);
					setState(181);
					match(INTEG);
					setState(182);
					match(RPRAN);
					}
				}

				}
				break;
			case VARCHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
				match(VARCHAR);
				setState(186);
				match(LPRAN);
				setState(187);
				match(INTEG);
				setState(188);
				match(RPRAN);
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 3);
				{
				setState(189);
				match(DOUBLE);
				setState(190);
				match(LPRAN);
				setState(191);
				match(INTEG);
				setState(192);
				match(COMMA);
				setState(193);
				match(INTEG);
				setState(194);
				match(RPRAN);
				}
				break;
			case DATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(195);
				match(DATE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(sqlParser.INT, 0); }
		public TerminalNode INTEGER() { return getToken(sqlParser.INTEGER, 0); }
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_integer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==INTEGER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UpdateContext extends ParserRuleContext {
		public TerminalNode UPDATE() { return getToken(sqlParser.UPDATE, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode SET() { return getToken(sqlParser.SET, 0); }
		public Update_listContext update_list() {
			return getRuleContext(Update_listContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(sqlParser.WHERE, 0); }
		public Update_conditionContext update_condition() {
			return getRuleContext(Update_conditionContext.class,0);
		}
		public TerminalNode EOF() { return getToken(sqlParser.EOF, 0); }
		public TerminalNode SEMICOL() { return getToken(sqlParser.SEMICOL, 0); }
		public UpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitUpdate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateContext update() throws RecognitionException {
		UpdateContext _localctx = new UpdateContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_update);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(UPDATE);
			setState(201);
			table_name();
			setState(202);
			match(SET);
			setState(203);
			update_list();
			setState(204);
			match(WHERE);
			setState(205);
			update_condition();
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOL) {
				{
				setState(206);
				match(SEMICOL);
				}
			}

			setState(209);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Update_listContext extends ParserRuleContext {
		public List<Update_itemContext> update_item() {
			return getRuleContexts(Update_itemContext.class);
		}
		public Update_itemContext update_item(int i) {
			return getRuleContext(Update_itemContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(sqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(sqlParser.COMMA, i);
		}
		public Update_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitUpdate_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Update_listContext update_list() throws RecognitionException {
		Update_listContext _localctx = new Update_listContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_update_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			update_item();
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(212);
				match(COMMA);
				setState(213);
				update_item();
				}
				}
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Update_itemContext extends ParserRuleContext {
		public Update_nameContext update_name() {
			return getRuleContext(Update_nameContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(sqlParser.EQUAL, 0); }
		public Update_valueContext update_value() {
			return getRuleContext(Update_valueContext.class,0);
		}
		public Update_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update_item; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitUpdate_item(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Update_itemContext update_item() throws RecognitionException {
		Update_itemContext _localctx = new Update_itemContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_update_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			update_name();
			setState(220);
			match(EQUAL);
			setState(221);
			update_value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Update_nameContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Update_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitUpdate_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Update_nameContext update_name() throws RecognitionException {
		Update_nameContext _localctx = new Update_nameContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_update_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			column_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Update_valueContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public Update_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitUpdate_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Update_valueContext update_value() throws RecognitionException {
		Update_valueContext _localctx = new Update_valueContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_update_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Update_conditionContext extends ParserRuleContext {
		public Update_condition_nameContext update_condition_name() {
			return getRuleContext(Update_condition_nameContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(sqlParser.EQUAL, 0); }
		public Update_condition_valueContext update_condition_value() {
			return getRuleContext(Update_condition_valueContext.class,0);
		}
		public Update_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update_condition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitUpdate_condition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Update_conditionContext update_condition() throws RecognitionException {
		Update_conditionContext _localctx = new Update_conditionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_update_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			update_condition_name();
			setState(228);
			match(EQUAL);
			setState(229);
			update_condition_value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Update_condition_nameContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Update_condition_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update_condition_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitUpdate_condition_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Update_condition_nameContext update_condition_name() throws RecognitionException {
		Update_condition_nameContext _localctx = new Update_condition_nameContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_update_condition_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			column_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Update_condition_valueContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public Update_condition_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update_condition_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitUpdate_condition_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Update_condition_valueContext update_condition_value() throws RecognitionException {
		Update_condition_valueContext _localctx = new Update_condition_valueContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_update_condition_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeleteContext extends ParserRuleContext {
		public TerminalNode DELETE() { return getToken(sqlParser.DELETE, 0); }
		public TerminalNode FROM() { return getToken(sqlParser.FROM, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode EOF() { return getToken(sqlParser.EOF, 0); }
		public TerminalNode WHERE() { return getToken(sqlParser.WHERE, 0); }
		public Delete_conditionsContext delete_conditions() {
			return getRuleContext(Delete_conditionsContext.class,0);
		}
		public TerminalNode SEMICOL() { return getToken(sqlParser.SEMICOL, 0); }
		public DeleteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitDelete(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteContext delete() throws RecognitionException {
		DeleteContext _localctx = new DeleteContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_delete);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(DELETE);
			setState(236);
			match(FROM);
			setState(237);
			table_name();
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(238);
				match(WHERE);
				setState(239);
				delete_conditions();
				}
			}

			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOL) {
				{
				setState(242);
				match(SEMICOL);
				}
			}

			setState(245);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Delete_conditionsContext extends ParserRuleContext {
		public List<Delete_conditionContext> delete_condition() {
			return getRuleContexts(Delete_conditionContext.class);
		}
		public Delete_conditionContext delete_condition(int i) {
			return getRuleContext(Delete_conditionContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(sqlParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(sqlParser.AND, i);
		}
		public Delete_conditionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete_conditions; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitDelete_conditions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Delete_conditionsContext delete_conditions() throws RecognitionException {
		Delete_conditionsContext _localctx = new Delete_conditionsContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_delete_conditions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			delete_condition();
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(248);
				match(AND);
				setState(249);
				delete_condition();
				}
				}
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Delete_conditionContext extends ParserRuleContext {
		public Delete_condition_nameContext delete_condition_name() {
			return getRuleContext(Delete_condition_nameContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(sqlParser.EQUAL, 0); }
		public Delete_condition_valueContext delete_condition_value() {
			return getRuleContext(Delete_condition_valueContext.class,0);
		}
		public Delete_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete_condition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitDelete_condition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Delete_conditionContext delete_condition() throws RecognitionException {
		Delete_conditionContext _localctx = new Delete_conditionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_delete_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			delete_condition_name();
			setState(256);
			match(EQUAL);
			setState(257);
			delete_condition_value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Delete_condition_nameContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Delete_condition_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete_condition_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitDelete_condition_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Delete_condition_nameContext delete_condition_name() throws RecognitionException {
		Delete_condition_nameContext _localctx = new Delete_condition_nameContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_delete_condition_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			column_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Delete_condition_valueContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public Delete_condition_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete_condition_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitDelete_condition_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Delete_condition_valueContext delete_condition_value() throws RecognitionException {
		Delete_condition_valueContext _localctx = new Delete_condition_valueContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_delete_condition_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InsertContext extends ParserRuleContext {
		public TerminalNode INSERT() { return getToken(sqlParser.INSERT, 0); }
		public TerminalNode INTO() { return getToken(sqlParser.INTO, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Values_clauseContext values_clause() {
			return getRuleContext(Values_clauseContext.class,0);
		}
		public TerminalNode EOF() { return getToken(sqlParser.EOF, 0); }
		public Column_listContext column_list() {
			return getRuleContext(Column_listContext.class,0);
		}
		public TerminalNode SEMICOL() { return getToken(sqlParser.SEMICOL, 0); }
		public InsertContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitInsert(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InsertContext insert() throws RecognitionException {
		InsertContext _localctx = new InsertContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_insert);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			match(INSERT);
			setState(264);
			match(INTO);
			setState(265);
			table_name();
			setState(267);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPRAN) {
				{
				setState(266);
				column_list();
				}
			}

			setState(269);
			values_clause();
			setState(271);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOL) {
				{
				setState(270);
				match(SEMICOL);
				}
			}

			setState(273);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_listContext extends ParserRuleContext {
		public TerminalNode LPRAN() { return getToken(sqlParser.LPRAN, 0); }
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public TerminalNode RPRAN() { return getToken(sqlParser.RPRAN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(sqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(sqlParser.COMMA, i);
		}
		public Column_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitColumn_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_listContext column_list() throws RecognitionException {
		Column_listContext _localctx = new Column_listContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_column_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(LPRAN);
			setState(276);
			column_name();
			setState(281);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(277);
				match(COMMA);
				setState(278);
				column_name();
				}
				}
				setState(283);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(284);
			match(RPRAN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Values_clauseContext extends ParserRuleContext {
		public TerminalNode VALUES() { return getToken(sqlParser.VALUES, 0); }
		public TerminalNode LPRAN() { return getToken(sqlParser.LPRAN, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode RPRAN() { return getToken(sqlParser.RPRAN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(sqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(sqlParser.COMMA, i);
		}
		public Values_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_values_clause; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitValues_clause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Values_clauseContext values_clause() throws RecognitionException {
		Values_clauseContext _localctx = new Values_clauseContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_values_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(VALUES);
			setState(287);
			match(LPRAN);
			setState(288);
			value();
			setState(293);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(289);
				match(COMMA);
				setState(290);
				value();
				}
				}
				setState(295);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(296);
			match(RPRAN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode INTEG() { return getToken(sqlParser.INTEG, 0); }
		public TerminalNode STRING() { return getToken(sqlParser.STRING, 0); }
		public TerminalNode DOUB() { return getToken(sqlParser.DOUB, 0); }
		public TerminalNode DAT() { return getToken(sqlParser.DAT, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INTEG) | (1L << DOUB) | (1L << DAT) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_nameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(sqlParser.ID, 0); }
		public Table_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitTable_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_nameContext table_name() throws RecognitionException {
		Table_nameContext _localctx = new Table_nameContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_nameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(sqlParser.ID, 0); }
		public TerminalNode DATE() { return getToken(sqlParser.DATE, 0); }
		public TerminalNode CREATE() { return getToken(sqlParser.CREATE, 0); }
		public TerminalNode TABLE() { return getToken(sqlParser.TABLE, 0); }
		public TerminalNode PRIMARY() { return getToken(sqlParser.PRIMARY, 0); }
		public TerminalNode KEY() { return getToken(sqlParser.KEY, 0); }
		public TerminalNode INT() { return getToken(sqlParser.INT, 0); }
		public TerminalNode INTEGER() { return getToken(sqlParser.INTEGER, 0); }
		public TerminalNode VARCHAR() { return getToken(sqlParser.VARCHAR, 0); }
		public TerminalNode DOUBLE() { return getToken(sqlParser.DOUBLE, 0); }
		public TerminalNode UPDATE() { return getToken(sqlParser.UPDATE, 0); }
		public TerminalNode SET() { return getToken(sqlParser.SET, 0); }
		public TerminalNode WHERE() { return getToken(sqlParser.WHERE, 0); }
		public TerminalNode INSERT() { return getToken(sqlParser.INSERT, 0); }
		public TerminalNode INTO() { return getToken(sqlParser.INTO, 0); }
		public TerminalNode VALUES() { return getToken(sqlParser.VALUES, 0); }
		public TerminalNode DELETE() { return getToken(sqlParser.DELETE, 0); }
		public TerminalNode FROM() { return getToken(sqlParser.FROM, 0); }
		public TerminalNode AND() { return getToken(sqlParser.AND, 0); }
		public Column_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof sqlVisitor ) return ((sqlVisitor<? extends T>)visitor).visitColumn_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_nameContext column_name() throws RecognitionException {
		Column_nameContext _localctx = new Column_nameContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_column_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CREATE) | (1L << TABLE) | (1L << PRIMARY) | (1L << KEY) | (1L << INT) | (1L << INTEGER) | (1L << VARCHAR) | (1L << DATE) | (1L << DOUBLE) | (1L << UPDATE) | (1L << SET) | (1L << WHERE) | (1L << INSERT) | (1L << INTO) | (1L << VALUES) | (1L << DELETE) | (1L << FROM) | (1L << AND) | (1L << ID))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\61\u0133\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\3\2\3\2\3\2\3\2\3\2\5"+
		"\2U\n\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3]\n\3\3\3\5\3`\n\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\7\4h\n\4\f\4\16\4k\13\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3"+
		"\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0082\n\n\3\n\3\n\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\7\f\u008c\n\f\f\f\16\f\u008f\13\f\3\f\3\f\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\3\16\5\16\u009a\n\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\7\17\u00a2\n\17\f\17\16\17\u00a5\13\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\5\23\u00ba\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\5\23\u00c7\n\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u00d2"+
		"\n\25\3\25\3\25\3\26\3\26\3\26\7\26\u00d9\n\26\f\26\16\26\u00dc\13\26"+
		"\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33"+
		"\3\34\3\34\3\35\3\35\3\35\3\35\3\35\5\35\u00f3\n\35\3\35\5\35\u00f6\n"+
		"\35\3\35\3\35\3\36\3\36\3\36\7\36\u00fd\n\36\f\36\16\36\u0100\13\36\3"+
		"\37\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3\"\3\"\5\"\u010e\n\"\3\"\3\"\5"+
		"\"\u0112\n\"\3\"\3\"\3#\3#\3#\3#\7#\u011a\n#\f#\16#\u011d\13#\3#\3#\3"+
		"$\3$\3$\3$\3$\7$\u0126\n$\f$\16$\u0129\13$\3$\3$\3%\3%\3&\3&\3\'\3\'\3"+
		"\'\2\2(\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:"+
		"<>@BDFHJL\2\7\3\2\24\26\3\2\',\3\2\7\b\4\2\35\37%%\4\2\3\24\34\34\2\u0125"+
		"\2T\3\2\2\2\4V\3\2\2\2\6c\3\2\2\2\bl\3\2\2\2\nn\3\2\2\2\fr\3\2\2\2\16"+
		"t\3\2\2\2\20v\3\2\2\2\22x\3\2\2\2\24\u0085\3\2\2\2\26\u0087\3\2\2\2\30"+
		"\u0092\3\2\2\2\32\u0094\3\2\2\2\34\u009d\3\2\2\2\36\u00ae\3\2\2\2 \u00b1"+
		"\3\2\2\2\"\u00b3\3\2\2\2$\u00c6\3\2\2\2&\u00c8\3\2\2\2(\u00ca\3\2\2\2"+
		"*\u00d5\3\2\2\2,\u00dd\3\2\2\2.\u00e1\3\2\2\2\60\u00e3\3\2\2\2\62\u00e5"+
		"\3\2\2\2\64\u00e9\3\2\2\2\66\u00eb\3\2\2\28\u00ed\3\2\2\2:\u00f9\3\2\2"+
		"\2<\u0101\3\2\2\2>\u0105\3\2\2\2@\u0107\3\2\2\2B\u0109\3\2\2\2D\u0115"+
		"\3\2\2\2F\u0120\3\2\2\2H\u012c\3\2\2\2J\u012e\3\2\2\2L\u0130\3\2\2\2N"+
		"U\5B\"\2OU\5(\25\2PU\5\32\16\2QU\58\35\2RU\5\22\n\2SU\5\4\3\2TN\3\2\2"+
		"\2TO\3\2\2\2TP\3\2\2\2TQ\3\2\2\2TR\3\2\2\2TS\3\2\2\2U\3\3\2\2\2VW\7\33"+
		"\2\2WX\7\60\2\2XY\7\23\2\2YZ\5J&\2Z\\\7\16\2\2[]\5\6\4\2\\[\3\2\2\2\\"+
		"]\3\2\2\2]_\3\2\2\2^`\7$\2\2_^\3\2\2\2_`\3\2\2\2`a\3\2\2\2ab\7\2\2\3b"+
		"\5\3\2\2\2ci\5\n\6\2de\5\b\5\2ef\5\n\6\2fh\3\2\2\2gd\3\2\2\2hk\3\2\2\2"+
		"ig\3\2\2\2ij\3\2\2\2j\7\3\2\2\2ki\3\2\2\2lm\t\2\2\2m\t\3\2\2\2no\5\f\7"+
		"\2op\5\20\t\2pq\5\16\b\2q\13\3\2\2\2rs\5L\'\2s\r\3\2\2\2tu\5H%\2u\17\3"+
		"\2\2\2vw\t\3\2\2w\21\3\2\2\2xy\7\3\2\2yz\7\27\2\2z{\5\24\13\2{|\7\30\2"+
		"\2|}\5J&\2}~\5\26\f\2~\177\7\31\2\2\177\u0081\7\32\2\2\u0080\u0082\7$"+
		"\2\2\u0081\u0080\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\3\2\2\2\u0083"+
		"\u0084\7\2\2\3\u0084\23\3\2\2\2\u0085\u0086\7\34\2\2\u0086\25\3\2\2\2"+
		"\u0087\u0088\7-\2\2\u0088\u008d\5\30\r\2\u0089\u008a\7/\2\2\u008a\u008c"+
		"\5\30\r\2\u008b\u0089\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2"+
		"\u008d\u008e\3\2\2\2\u008e\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091"+
		"\7.\2\2\u0091\27\3\2\2\2\u0092\u0093\7\34\2\2\u0093\31\3\2\2\2\u0094\u0095"+
		"\7\3\2\2\u0095\u0096\7\4\2\2\u0096\u0097\5J&\2\u0097\u0099\5\34\17\2\u0098"+
		"\u009a\7$\2\2\u0099\u0098\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\3\2"+
		"\2\2\u009b\u009c\7\2\2\3\u009c\33\3\2\2\2\u009d\u009e\7-\2\2\u009e\u00a3"+
		"\5\36\20\2\u009f\u00a0\7/\2\2\u00a0\u00a2\5\36\20\2\u00a1\u009f\3\2\2"+
		"\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6"+
		"\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\7/\2\2\u00a7\u00a8\7\5\2\2\u00a8"+
		"\u00a9\7\6\2\2\u00a9\u00aa\7-\2\2\u00aa\u00ab\5 \21\2\u00ab\u00ac\7.\2"+
		"\2\u00ac\u00ad\7.\2\2\u00ad\35\3\2\2\2\u00ae\u00af\5\"\22\2\u00af\u00b0"+
		"\5$\23\2\u00b0\37\3\2\2\2\u00b1\u00b2\7\34\2\2\u00b2!\3\2\2\2\u00b3\u00b4"+
		"\7\34\2\2\u00b4#\3\2\2\2\u00b5\u00b9\5&\24\2\u00b6\u00b7\7-\2\2\u00b7"+
		"\u00b8\7\35\2\2\u00b8\u00ba\7.\2\2\u00b9\u00b6\3\2\2\2\u00b9\u00ba\3\2"+
		"\2\2\u00ba\u00c7\3\2\2\2\u00bb\u00bc\7\t\2\2\u00bc\u00bd\7-\2\2\u00bd"+
		"\u00be\7\35\2\2\u00be\u00c7\7.\2\2\u00bf\u00c0\7\13\2\2\u00c0\u00c1\7"+
		"-\2\2\u00c1\u00c2\7\35\2\2\u00c2\u00c3\7/\2\2\u00c3\u00c4\7\35\2\2\u00c4"+
		"\u00c7\7.\2\2\u00c5\u00c7\7\n\2\2\u00c6\u00b5\3\2\2\2\u00c6\u00bb\3\2"+
		"\2\2\u00c6\u00bf\3\2\2\2\u00c6\u00c5\3\2\2\2\u00c7%\3\2\2\2\u00c8\u00c9"+
		"\t\4\2\2\u00c9\'\3\2\2\2\u00ca\u00cb\7\f\2\2\u00cb\u00cc\5J&\2\u00cc\u00cd"+
		"\7\r\2\2\u00cd\u00ce\5*\26\2\u00ce\u00cf\7\16\2\2\u00cf\u00d1\5\62\32"+
		"\2\u00d0\u00d2\7$\2\2\u00d1\u00d0\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3"+
		"\3\2\2\2\u00d3\u00d4\7\2\2\3\u00d4)\3\2\2\2\u00d5\u00da\5,\27\2\u00d6"+
		"\u00d7\7/\2\2\u00d7\u00d9\5,\27\2\u00d8\u00d6\3\2\2\2\u00d9\u00dc\3\2"+
		"\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db+\3\2\2\2\u00dc\u00da"+
		"\3\2\2\2\u00dd\u00de\5.\30\2\u00de\u00df\7\'\2\2\u00df\u00e0\5\60\31\2"+
		"\u00e0-\3\2\2\2\u00e1\u00e2\5L\'\2\u00e2/\3\2\2\2\u00e3\u00e4\5H%\2\u00e4"+
		"\61\3\2\2\2\u00e5\u00e6\5\64\33\2\u00e6\u00e7\7\'\2\2\u00e7\u00e8\5\66"+
		"\34\2\u00e8\63\3\2\2\2\u00e9\u00ea\5L\'\2\u00ea\65\3\2\2\2\u00eb\u00ec"+
		"\5H%\2\u00ec\67\3\2\2\2\u00ed\u00ee\7\22\2\2\u00ee\u00ef\7\23\2\2\u00ef"+
		"\u00f2\5J&\2\u00f0\u00f1\7\16\2\2\u00f1\u00f3\5:\36\2\u00f2\u00f0\3\2"+
		"\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f5\3\2\2\2\u00f4\u00f6\7$\2\2\u00f5"+
		"\u00f4\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8\7\2"+
		"\2\3\u00f89\3\2\2\2\u00f9\u00fe\5<\37\2\u00fa\u00fb\7\24\2\2\u00fb\u00fd"+
		"\5<\37\2\u00fc\u00fa\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe"+
		"\u00ff\3\2\2\2\u00ff;\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0102\5> \2\u0102"+
		"\u0103\7\'\2\2\u0103\u0104\5@!\2\u0104=\3\2\2\2\u0105\u0106\5L\'\2\u0106"+
		"?\3\2\2\2\u0107\u0108\5H%\2\u0108A\3\2\2\2\u0109\u010a\7\17\2\2\u010a"+
		"\u010b\7\20\2\2\u010b\u010d\5J&\2\u010c\u010e\5D#\2\u010d\u010c\3\2\2"+
		"\2\u010d\u010e\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0111\5F$\2\u0110\u0112"+
		"\7$\2\2\u0111\u0110\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"\u0114\7\2\2\3\u0114C\3\2\2\2\u0115\u0116\7-\2\2\u0116\u011b\5L\'\2\u0117"+
		"\u0118\7/\2\2\u0118\u011a\5L\'\2\u0119\u0117\3\2\2\2\u011a\u011d\3\2\2"+
		"\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011e\3\2\2\2\u011d\u011b"+
		"\3\2\2\2\u011e\u011f\7.\2\2\u011fE\3\2\2\2\u0120\u0121\7\21\2\2\u0121"+
		"\u0122\7-\2\2\u0122\u0127\5H%\2\u0123\u0124\7/\2\2\u0124\u0126\5H%\2\u0125"+
		"\u0123\3\2\2\2\u0126\u0129\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2"+
		"\2\2\u0128\u012a\3\2\2\2\u0129\u0127\3\2\2\2\u012a\u012b\7.\2\2\u012b"+
		"G\3\2\2\2\u012c\u012d\t\5\2\2\u012dI\3\2\2\2\u012e\u012f\7\34\2\2\u012f"+
		"K\3\2\2\2\u0130\u0131\t\6\2\2\u0131M\3\2\2\2\25T\\_i\u0081\u008d\u0099"+
		"\u00a3\u00b9\u00c6\u00d1\u00da\u00f2\u00f5\u00fe\u010d\u0111\u011b\u0127";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}