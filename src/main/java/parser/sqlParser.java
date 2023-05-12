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
		DELETE=16, FROM=17, AND=18, INDEX=19, ON=20, USING=21, OCTREE=22, ID=23, 
		INTEG=24, DOUB=25, DAT=26, MONTH=27, DAY=28, DASH=29, POINT=30, SEMICOL=31, 
		STRING=32, DOUBLE_QUOTE=33, EQUAL=34, LPRAN=35, RPRAN=36, COMMA=37, WS=38;
	public static final int
		RULE_statement = 0, RULE_create_index = 1, RULE_index_name = 2, RULE_index_columns = 3, 
		RULE_index_column = 4, RULE_create_table = 5, RULE_creation_columns = 6, 
		RULE_creation_column = 7, RULE_pk = 8, RULE_name = 9, RULE_data_type = 10, 
		RULE_integer = 11, RULE_update = 12, RULE_update_list = 13, RULE_update_item = 14, 
		RULE_update_name = 15, RULE_update_value = 16, RULE_update_condition = 17, 
		RULE_update_condition_name = 18, RULE_update_condition_value = 19, RULE_delete = 20, 
		RULE_delete_conditions = 21, RULE_delete_condition = 22, RULE_delete_condition_name = 23, 
		RULE_delete_condition_value = 24, RULE_insert = 25, RULE_column_list = 26, 
		RULE_values_clause = 27, RULE_value = 28, RULE_table_name = 29, RULE_column_name = 30;
	private static String[] makeRuleNames() {
		return new String[] {
			"statement", "create_index", "index_name", "index_columns", "index_column", 
			"create_table", "creation_columns", "creation_column", "pk", "name", 
			"data_type", "integer", "update", "update_list", "update_item", "update_name", 
			"update_value", "update_condition", "update_condition_name", "update_condition_value", 
			"delete", "delete_conditions", "delete_condition", "delete_condition_name", 
			"delete_condition_value", "insert", "column_list", "values_clause", "value", 
			"table_name", "column_name"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "'-'", "'.'", "';'", null, "'\"'", "'='", 
			"'('", "')'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CREATE", "TABLE", "PRIMARY", "KEY", "INT", "INTEGER", "VARCHAR", 
			"DATE", "DOUBLE", "UPDATE", "SET", "WHERE", "INSERT", "INTO", "VALUES", 
			"DELETE", "FROM", "AND", "INDEX", "ON", "USING", "OCTREE", "ID", "INTEG", 
			"DOUB", "DAT", "MONTH", "DAY", "DASH", "POINT", "SEMICOL", "STRING", 
			"DOUBLE_QUOTE", "EQUAL", "LPRAN", "RPRAN", "COMMA", "WS"
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
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				insert();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(63);
				update();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(64);
				create_table();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(65);
				delete();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(66);
				create_index();
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
		enterRule(_localctx, 2, RULE_create_index);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(CREATE);
			setState(70);
			match(INDEX);
			setState(71);
			index_name();
			setState(72);
			match(ON);
			setState(73);
			table_name();
			setState(74);
			index_columns();
			setState(75);
			match(USING);
			setState(76);
			match(OCTREE);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOL) {
				{
				setState(77);
				match(SEMICOL);
				}
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
		enterRule(_localctx, 4, RULE_index_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
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
		enterRule(_localctx, 6, RULE_index_columns);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(LPRAN);
			setState(83);
			index_column();
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(84);
				match(COMMA);
				setState(85);
				index_column();
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(91);
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
		enterRule(_localctx, 8, RULE_index_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
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
		enterRule(_localctx, 10, RULE_create_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(CREATE);
			setState(96);
			match(TABLE);
			setState(97);
			table_name();
			setState(98);
			creation_columns();
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOL) {
				{
				setState(99);
				match(SEMICOL);
				}
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
		enterRule(_localctx, 12, RULE_creation_columns);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(LPRAN);
			setState(103);
			creation_column();
			setState(108);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(104);
					match(COMMA);
					setState(105);
					creation_column();
					}
					} 
				}
				setState(110);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			setState(111);
			match(COMMA);
			setState(112);
			match(PRIMARY);
			setState(113);
			match(KEY);
			setState(114);
			match(LPRAN);
			setState(115);
			pk();
			setState(116);
			match(RPRAN);
			setState(117);
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
		enterRule(_localctx, 14, RULE_creation_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			name();
			setState(120);
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
		enterRule(_localctx, 16, RULE_pk);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
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
		enterRule(_localctx, 18, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
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
		enterRule(_localctx, 20, RULE_data_type);
		int _la;
		try {
			setState(143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				integer();
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPRAN) {
					{
					setState(127);
					match(LPRAN);
					setState(128);
					match(INTEG);
					setState(129);
					match(RPRAN);
					}
				}

				}
				break;
			case VARCHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				match(VARCHAR);
				setState(133);
				match(LPRAN);
				setState(134);
				match(INTEG);
				setState(135);
				match(RPRAN);
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 3);
				{
				setState(136);
				match(DOUBLE);
				setState(137);
				match(LPRAN);
				setState(138);
				match(INTEG);
				setState(139);
				match(COMMA);
				setState(140);
				match(INTEG);
				setState(141);
				match(RPRAN);
				}
				break;
			case DATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(142);
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
		enterRule(_localctx, 22, RULE_integer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
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
		enterRule(_localctx, 24, RULE_update);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(UPDATE);
			setState(148);
			table_name();
			setState(149);
			match(SET);
			setState(150);
			update_list();
			setState(151);
			match(WHERE);
			setState(152);
			update_condition();
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOL) {
				{
				setState(153);
				match(SEMICOL);
				}
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
		enterRule(_localctx, 26, RULE_update_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			update_item();
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(157);
				match(COMMA);
				setState(158);
				update_item();
				}
				}
				setState(163);
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
		enterRule(_localctx, 28, RULE_update_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			update_name();
			setState(165);
			match(EQUAL);
			setState(166);
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
		enterRule(_localctx, 30, RULE_update_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
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
		enterRule(_localctx, 32, RULE_update_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
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
		enterRule(_localctx, 34, RULE_update_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			update_condition_name();
			setState(173);
			match(EQUAL);
			setState(174);
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
		enterRule(_localctx, 36, RULE_update_condition_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
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
		enterRule(_localctx, 38, RULE_update_condition_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
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
		enterRule(_localctx, 40, RULE_delete);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(DELETE);
			setState(181);
			match(FROM);
			setState(182);
			table_name();
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(183);
				match(WHERE);
				setState(184);
				delete_conditions();
				}
			}

			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOL) {
				{
				setState(187);
				match(SEMICOL);
				}
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
		enterRule(_localctx, 42, RULE_delete_conditions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			delete_condition();
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(191);
				match(AND);
				setState(192);
				delete_condition();
				}
				}
				setState(197);
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
		enterRule(_localctx, 44, RULE_delete_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			delete_condition_name();
			setState(199);
			match(EQUAL);
			setState(200);
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
		enterRule(_localctx, 46, RULE_delete_condition_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
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
		enterRule(_localctx, 48, RULE_delete_condition_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
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
		enterRule(_localctx, 50, RULE_insert);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(INSERT);
			setState(207);
			match(INTO);
			setState(208);
			table_name();
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPRAN) {
				{
				setState(209);
				column_list();
				}
			}

			setState(212);
			values_clause();
			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOL) {
				{
				setState(213);
				match(SEMICOL);
				}
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
		enterRule(_localctx, 52, RULE_column_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			match(LPRAN);
			setState(217);
			column_name();
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(218);
				match(COMMA);
				setState(219);
				column_name();
				}
				}
				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(225);
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
		enterRule(_localctx, 54, RULE_values_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			match(VALUES);
			setState(228);
			match(LPRAN);
			setState(229);
			value();
			setState(234);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(230);
				match(COMMA);
				setState(231);
				value();
				}
				}
				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(237);
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
		enterRule(_localctx, 56, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
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
		enterRule(_localctx, 58, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
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
		enterRule(_localctx, 60, RULE_column_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3(\u00f8\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\2\3\2\3\2\5\2F\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3Q\n\3"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\7\5Y\n\5\f\5\16\5\\\13\5\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\5\7g\n\7\3\b\3\b\3\b\3\b\7\bm\n\b\f\b\16\bp\13\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\5\f\u0085\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0092"+
		"\n\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u009d\n\16\3\17\3"+
		"\17\3\17\7\17\u00a2\n\17\f\17\16\17\u00a5\13\17\3\20\3\20\3\20\3\20\3"+
		"\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3"+
		"\26\3\26\3\26\5\26\u00bc\n\26\3\26\5\26\u00bf\n\26\3\27\3\27\3\27\7\27"+
		"\u00c4\n\27\f\27\16\27\u00c7\13\27\3\30\3\30\3\30\3\30\3\31\3\31\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\5\33\u00d5\n\33\3\33\3\33\5\33\u00d9\n\33\3"+
		"\34\3\34\3\34\3\34\7\34\u00df\n\34\f\34\16\34\u00e2\13\34\3\34\3\34\3"+
		"\35\3\35\3\35\3\35\3\35\7\35\u00eb\n\35\f\35\16\35\u00ee\13\35\3\35\3"+
		"\35\3\36\3\36\3\37\3\37\3 \3 \3 \2\2!\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*,.\60\62\64\668:<>\2\5\3\2\7\b\4\2\32\34\"\"\4\2\3\24\31"+
		"\31\2\u00ed\2E\3\2\2\2\4G\3\2\2\2\6R\3\2\2\2\bT\3\2\2\2\n_\3\2\2\2\fa"+
		"\3\2\2\2\16h\3\2\2\2\20y\3\2\2\2\22|\3\2\2\2\24~\3\2\2\2\26\u0091\3\2"+
		"\2\2\30\u0093\3\2\2\2\32\u0095\3\2\2\2\34\u009e\3\2\2\2\36\u00a6\3\2\2"+
		"\2 \u00aa\3\2\2\2\"\u00ac\3\2\2\2$\u00ae\3\2\2\2&\u00b2\3\2\2\2(\u00b4"+
		"\3\2\2\2*\u00b6\3\2\2\2,\u00c0\3\2\2\2.\u00c8\3\2\2\2\60\u00cc\3\2\2\2"+
		"\62\u00ce\3\2\2\2\64\u00d0\3\2\2\2\66\u00da\3\2\2\28\u00e5\3\2\2\2:\u00f1"+
		"\3\2\2\2<\u00f3\3\2\2\2>\u00f5\3\2\2\2@F\5\64\33\2AF\5\32\16\2BF\5\f\7"+
		"\2CF\5*\26\2DF\5\4\3\2E@\3\2\2\2EA\3\2\2\2EB\3\2\2\2EC\3\2\2\2ED\3\2\2"+
		"\2F\3\3\2\2\2GH\7\3\2\2HI\7\25\2\2IJ\5\6\4\2JK\7\26\2\2KL\5<\37\2LM\5"+
		"\b\5\2MN\7\27\2\2NP\7\30\2\2OQ\7!\2\2PO\3\2\2\2PQ\3\2\2\2Q\5\3\2\2\2R"+
		"S\7\31\2\2S\7\3\2\2\2TU\7%\2\2UZ\5\n\6\2VW\7\'\2\2WY\5\n\6\2XV\3\2\2\2"+
		"Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[]\3\2\2\2\\Z\3\2\2\2]^\7&\2\2^\t\3\2\2"+
		"\2_`\7\31\2\2`\13\3\2\2\2ab\7\3\2\2bc\7\4\2\2cd\5<\37\2df\5\16\b\2eg\7"+
		"!\2\2fe\3\2\2\2fg\3\2\2\2g\r\3\2\2\2hi\7%\2\2in\5\20\t\2jk\7\'\2\2km\5"+
		"\20\t\2lj\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2oq\3\2\2\2pn\3\2\2\2qr"+
		"\7\'\2\2rs\7\5\2\2st\7\6\2\2tu\7%\2\2uv\5\22\n\2vw\7&\2\2wx\7&\2\2x\17"+
		"\3\2\2\2yz\5\24\13\2z{\5\26\f\2{\21\3\2\2\2|}\7\31\2\2}\23\3\2\2\2~\177"+
		"\7\31\2\2\177\25\3\2\2\2\u0080\u0084\5\30\r\2\u0081\u0082\7%\2\2\u0082"+
		"\u0083\7\32\2\2\u0083\u0085\7&\2\2\u0084\u0081\3\2\2\2\u0084\u0085\3\2"+
		"\2\2\u0085\u0092\3\2\2\2\u0086\u0087\7\t\2\2\u0087\u0088\7%\2\2\u0088"+
		"\u0089\7\32\2\2\u0089\u0092\7&\2\2\u008a\u008b\7\13\2\2\u008b\u008c\7"+
		"%\2\2\u008c\u008d\7\32\2\2\u008d\u008e\7\'\2\2\u008e\u008f\7\32\2\2\u008f"+
		"\u0092\7&\2\2\u0090\u0092\7\n\2\2\u0091\u0080\3\2\2\2\u0091\u0086\3\2"+
		"\2\2\u0091\u008a\3\2\2\2\u0091\u0090\3\2\2\2\u0092\27\3\2\2\2\u0093\u0094"+
		"\t\2\2\2\u0094\31\3\2\2\2\u0095\u0096\7\f\2\2\u0096\u0097\5<\37\2\u0097"+
		"\u0098\7\r\2\2\u0098\u0099\5\34\17\2\u0099\u009a\7\16\2\2\u009a\u009c"+
		"\5$\23\2\u009b\u009d\7!\2\2\u009c\u009b\3\2\2\2\u009c\u009d\3\2\2\2\u009d"+
		"\33\3\2\2\2\u009e\u00a3\5\36\20\2\u009f\u00a0\7\'\2\2\u00a0\u00a2\5\36"+
		"\20\2\u00a1\u009f\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3"+
		"\u00a4\3\2\2\2\u00a4\35\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\5 \21"+
		"\2\u00a7\u00a8\7$\2\2\u00a8\u00a9\5\"\22\2\u00a9\37\3\2\2\2\u00aa\u00ab"+
		"\5> \2\u00ab!\3\2\2\2\u00ac\u00ad\5:\36\2\u00ad#\3\2\2\2\u00ae\u00af\5"+
		"&\24\2\u00af\u00b0\7$\2\2\u00b0\u00b1\5(\25\2\u00b1%\3\2\2\2\u00b2\u00b3"+
		"\5> \2\u00b3\'\3\2\2\2\u00b4\u00b5\5:\36\2\u00b5)\3\2\2\2\u00b6\u00b7"+
		"\7\22\2\2\u00b7\u00b8\7\23\2\2\u00b8\u00bb\5<\37\2\u00b9\u00ba\7\16\2"+
		"\2\u00ba\u00bc\5,\27\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00be"+
		"\3\2\2\2\u00bd\u00bf\7!\2\2\u00be\u00bd\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"+\3\2\2\2\u00c0\u00c5\5.\30\2\u00c1\u00c2\7\24\2\2\u00c2\u00c4\5.\30\2"+
		"\u00c3\u00c1\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6"+
		"\3\2\2\2\u00c6-\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00c9\5\60\31\2\u00c9"+
		"\u00ca\7$\2\2\u00ca\u00cb\5\62\32\2\u00cb/\3\2\2\2\u00cc\u00cd\5> \2\u00cd"+
		"\61\3\2\2\2\u00ce\u00cf\5:\36\2\u00cf\63\3\2\2\2\u00d0\u00d1\7\17\2\2"+
		"\u00d1\u00d2\7\20\2\2\u00d2\u00d4\5<\37\2\u00d3\u00d5\5\66\34\2\u00d4"+
		"\u00d3\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d8\58"+
		"\35\2\u00d7\u00d9\7!\2\2\u00d8\u00d7\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9"+
		"\65\3\2\2\2\u00da\u00db\7%\2\2\u00db\u00e0\5> \2\u00dc\u00dd\7\'\2\2\u00dd"+
		"\u00df\5> \2\u00de\u00dc\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2\2"+
		"\2\u00e0\u00e1\3\2\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e4"+
		"\7&\2\2\u00e4\67\3\2\2\2\u00e5\u00e6\7\21\2\2\u00e6\u00e7\7%\2\2\u00e7"+
		"\u00ec\5:\36\2\u00e8\u00e9\7\'\2\2\u00e9\u00eb\5:\36\2\u00ea\u00e8\3\2"+
		"\2\2\u00eb\u00ee\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed"+
		"\u00ef\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ef\u00f0\7&\2\2\u00f09\3\2\2\2\u00f1"+
		"\u00f2\t\3\2\2\u00f2;\3\2\2\2\u00f3\u00f4\7\31\2\2\u00f4=\3\2\2\2\u00f5"+
		"\u00f6\t\4\2\2\u00f6?\3\2\2\2\22EPZfn\u0084\u0091\u009c\u00a3\u00bb\u00be"+
		"\u00c5\u00d4\u00d8\u00e0\u00ec";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}