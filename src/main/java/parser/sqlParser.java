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
		DELETE=16, FROM=17, AND=18, ID=19, INTEG=20, DOUB=21, DAT=22, MONTH=23, 
		DAY=24, DASH=25, POINT=26, SEMICOL=27, STRING=28, DOUBLE_QUOTE=29, EQUAL=30, 
		LPRAN=31, RPRAN=32, COMMA=33, WS=34;
	public static final int
		RULE_statement = 0, RULE_create_table = 1, RULE_creation_columns = 2, 
		RULE_creation_column = 3, RULE_pk = 4, RULE_name = 5, RULE_data_type = 6, 
		RULE_integer = 7, RULE_update = 8, RULE_update_list = 9, RULE_update_item = 10, 
		RULE_update_name = 11, RULE_update_value = 12, RULE_update_condition = 13, 
		RULE_update_condition_name = 14, RULE_update_condition_value = 15, RULE_delete = 16, 
		RULE_delete_conditions = 17, RULE_delete_condition = 18, RULE_delete_condition_name = 19, 
		RULE_delete_condition_value = 20, RULE_insert = 21, RULE_column_list = 22, 
		RULE_values_clause = 23, RULE_value = 24, RULE_table_name = 25, RULE_column_name = 26;
	private static String[] makeRuleNames() {
		return new String[] {
			"statement", "create_table", "creation_columns", "creation_column", "pk", 
			"name", "data_type", "integer", "update", "update_list", "update_item", 
			"update_name", "update_value", "update_condition", "update_condition_name", 
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
			null, "'-'", "'.'", "';'", null, "'\"'", "'='", "'('", "')'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CREATE", "TABLE", "PRIMARY", "KEY", "INT", "INTEGER", "VARCHAR", 
			"DATE", "DOUBLE", "UPDATE", "SET", "WHERE", "INSERT", "INTO", "VALUES", 
			"DELETE", "FROM", "AND", "ID", "INTEG", "DOUB", "DAT", "MONTH", "DAY", 
			"DASH", "POINT", "SEMICOL", "STRING", "DOUBLE_QUOTE", "EQUAL", "LPRAN", 
			"RPRAN", "COMMA", "WS"
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
			setState(58);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INSERT:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				insert();
				}
				break;
			case UPDATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				update();
				}
				break;
			case CREATE:
				enterOuterAlt(_localctx, 3);
				{
				setState(56);
				create_table();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 4);
				{
				setState(57);
				delete();
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
		enterRule(_localctx, 2, RULE_create_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(CREATE);
			setState(61);
			match(TABLE);
			setState(62);
			table_name();
			setState(63);
			creation_columns();
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOL) {
				{
				setState(64);
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
		enterRule(_localctx, 4, RULE_creation_columns);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(LPRAN);
			setState(68);
			creation_column();
			setState(73);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(69);
					match(COMMA);
					setState(70);
					creation_column();
					}
					} 
				}
				setState(75);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(76);
			match(COMMA);
			setState(77);
			match(PRIMARY);
			setState(78);
			match(KEY);
			setState(79);
			match(LPRAN);
			setState(80);
			pk();
			setState(81);
			match(RPRAN);
			setState(82);
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
		enterRule(_localctx, 6, RULE_creation_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			name();
			setState(85);
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
		enterRule(_localctx, 8, RULE_pk);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
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
		enterRule(_localctx, 10, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
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
		enterRule(_localctx, 12, RULE_data_type);
		int _la;
		try {
			setState(108);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				integer();
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPRAN) {
					{
					setState(92);
					match(LPRAN);
					setState(93);
					match(INTEG);
					setState(94);
					match(RPRAN);
					}
				}

				}
				break;
			case VARCHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(97);
				match(VARCHAR);
				setState(98);
				match(LPRAN);
				setState(99);
				match(INTEG);
				setState(100);
				match(RPRAN);
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 3);
				{
				setState(101);
				match(DOUBLE);
				setState(102);
				match(LPRAN);
				setState(103);
				match(INTEG);
				setState(104);
				match(COMMA);
				setState(105);
				match(INTEG);
				setState(106);
				match(RPRAN);
				}
				break;
			case DATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(107);
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
		enterRule(_localctx, 14, RULE_integer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
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
		enterRule(_localctx, 16, RULE_update);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(UPDATE);
			setState(113);
			table_name();
			setState(114);
			match(SET);
			setState(115);
			update_list();
			setState(116);
			match(WHERE);
			setState(117);
			update_condition();
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOL) {
				{
				setState(118);
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
		enterRule(_localctx, 18, RULE_update_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			update_item();
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(122);
				match(COMMA);
				setState(123);
				update_item();
				}
				}
				setState(128);
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
		enterRule(_localctx, 20, RULE_update_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			update_name();
			setState(130);
			match(EQUAL);
			setState(131);
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
		enterRule(_localctx, 22, RULE_update_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
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
		enterRule(_localctx, 24, RULE_update_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
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
		enterRule(_localctx, 26, RULE_update_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			update_condition_name();
			setState(138);
			match(EQUAL);
			setState(139);
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
		enterRule(_localctx, 28, RULE_update_condition_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
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
		enterRule(_localctx, 30, RULE_update_condition_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
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
		enterRule(_localctx, 32, RULE_delete);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(DELETE);
			setState(146);
			match(FROM);
			setState(147);
			table_name();
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(148);
				match(WHERE);
				setState(149);
				delete_conditions();
				}
			}

			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOL) {
				{
				setState(152);
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
		enterRule(_localctx, 34, RULE_delete_conditions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			delete_condition();
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(156);
				match(AND);
				setState(157);
				delete_condition();
				}
				}
				setState(162);
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
		enterRule(_localctx, 36, RULE_delete_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			delete_condition_name();
			setState(164);
			match(EQUAL);
			setState(165);
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
		enterRule(_localctx, 38, RULE_delete_condition_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
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
		enterRule(_localctx, 40, RULE_delete_condition_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
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
		enterRule(_localctx, 42, RULE_insert);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(INSERT);
			setState(172);
			match(INTO);
			setState(173);
			table_name();
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPRAN) {
				{
				setState(174);
				column_list();
				}
			}

			setState(177);
			values_clause();
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOL) {
				{
				setState(178);
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
		enterRule(_localctx, 44, RULE_column_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			match(LPRAN);
			setState(182);
			column_name();
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(183);
				match(COMMA);
				setState(184);
				column_name();
				}
				}
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(190);
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
		enterRule(_localctx, 46, RULE_values_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(VALUES);
			setState(193);
			match(LPRAN);
			setState(194);
			value();
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(195);
				match(COMMA);
				setState(196);
				value();
				}
				}
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(202);
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
		enterRule(_localctx, 48, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
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
		enterRule(_localctx, 50, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
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
		enterRule(_localctx, 52, RULE_column_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3$\u00d5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\5\2=\n\2\3\3\3\3\3\3\3"+
		"\3\3\3\5\3D\n\3\3\4\3\4\3\4\3\4\7\4J\n\4\f\4\16\4M\13\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\5\bb\n"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bo\n\b\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\5\nz\n\n\3\13\3\13\3\13\7\13\177\n\13\f\13\16\13"+
		"\u0082\13\13\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\5\22\u0099\n\22\3\22\5\22\u009c"+
		"\n\22\3\23\3\23\3\23\7\23\u00a1\n\23\f\23\16\23\u00a4\13\23\3\24\3\24"+
		"\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\27\5\27\u00b2\n\27\3\27"+
		"\3\27\5\27\u00b6\n\27\3\30\3\30\3\30\3\30\7\30\u00bc\n\30\f\30\16\30\u00bf"+
		"\13\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\7\31\u00c8\n\31\f\31\16\31\u00cb"+
		"\13\31\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\2\2\35\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66\2\5\3\2\7\b\4\2\26\30"+
		"\36\36\3\2\3\25\2\u00cb\2<\3\2\2\2\4>\3\2\2\2\6E\3\2\2\2\bV\3\2\2\2\n"+
		"Y\3\2\2\2\f[\3\2\2\2\16n\3\2\2\2\20p\3\2\2\2\22r\3\2\2\2\24{\3\2\2\2\26"+
		"\u0083\3\2\2\2\30\u0087\3\2\2\2\32\u0089\3\2\2\2\34\u008b\3\2\2\2\36\u008f"+
		"\3\2\2\2 \u0091\3\2\2\2\"\u0093\3\2\2\2$\u009d\3\2\2\2&\u00a5\3\2\2\2"+
		"(\u00a9\3\2\2\2*\u00ab\3\2\2\2,\u00ad\3\2\2\2.\u00b7\3\2\2\2\60\u00c2"+
		"\3\2\2\2\62\u00ce\3\2\2\2\64\u00d0\3\2\2\2\66\u00d2\3\2\2\28=\5,\27\2"+
		"9=\5\22\n\2:=\5\4\3\2;=\5\"\22\2<8\3\2\2\2<9\3\2\2\2<:\3\2\2\2<;\3\2\2"+
		"\2=\3\3\2\2\2>?\7\3\2\2?@\7\4\2\2@A\5\64\33\2AC\5\6\4\2BD\7\35\2\2CB\3"+
		"\2\2\2CD\3\2\2\2D\5\3\2\2\2EF\7!\2\2FK\5\b\5\2GH\7#\2\2HJ\5\b\5\2IG\3"+
		"\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2LN\3\2\2\2MK\3\2\2\2NO\7#\2\2OP\7"+
		"\5\2\2PQ\7\6\2\2QR\7!\2\2RS\5\n\6\2ST\7\"\2\2TU\7\"\2\2U\7\3\2\2\2VW\5"+
		"\f\7\2WX\5\16\b\2X\t\3\2\2\2YZ\7\25\2\2Z\13\3\2\2\2[\\\7\25\2\2\\\r\3"+
		"\2\2\2]a\5\20\t\2^_\7!\2\2_`\7\26\2\2`b\7\"\2\2a^\3\2\2\2ab\3\2\2\2bo"+
		"\3\2\2\2cd\7\t\2\2de\7!\2\2ef\7\26\2\2fo\7\"\2\2gh\7\13\2\2hi\7!\2\2i"+
		"j\7\26\2\2jk\7#\2\2kl\7\26\2\2lo\7\"\2\2mo\7\n\2\2n]\3\2\2\2nc\3\2\2\2"+
		"ng\3\2\2\2nm\3\2\2\2o\17\3\2\2\2pq\t\2\2\2q\21\3\2\2\2rs\7\f\2\2st\5\64"+
		"\33\2tu\7\r\2\2uv\5\24\13\2vw\7\16\2\2wy\5\34\17\2xz\7\35\2\2yx\3\2\2"+
		"\2yz\3\2\2\2z\23\3\2\2\2{\u0080\5\26\f\2|}\7#\2\2}\177\5\26\f\2~|\3\2"+
		"\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\25\3\2"+
		"\2\2\u0082\u0080\3\2\2\2\u0083\u0084\5\30\r\2\u0084\u0085\7 \2\2\u0085"+
		"\u0086\5\32\16\2\u0086\27\3\2\2\2\u0087\u0088\5\66\34\2\u0088\31\3\2\2"+
		"\2\u0089\u008a\5\62\32\2\u008a\33\3\2\2\2\u008b\u008c\5\36\20\2\u008c"+
		"\u008d\7 \2\2\u008d\u008e\5 \21\2\u008e\35\3\2\2\2\u008f\u0090\5\66\34"+
		"\2\u0090\37\3\2\2\2\u0091\u0092\5\62\32\2\u0092!\3\2\2\2\u0093\u0094\7"+
		"\22\2\2\u0094\u0095\7\23\2\2\u0095\u0098\5\64\33\2\u0096\u0097\7\16\2"+
		"\2\u0097\u0099\5$\23\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b"+
		"\3\2\2\2\u009a\u009c\7\35\2\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2"+
		"\u009c#\3\2\2\2\u009d\u00a2\5&\24\2\u009e\u009f\7\24\2\2\u009f\u00a1\5"+
		"&\24\2\u00a0\u009e\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2"+
		"\u00a3\3\2\2\2\u00a3%\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a6\5(\25\2"+
		"\u00a6\u00a7\7 \2\2\u00a7\u00a8\5*\26\2\u00a8\'\3\2\2\2\u00a9\u00aa\5"+
		"\66\34\2\u00aa)\3\2\2\2\u00ab\u00ac\5\62\32\2\u00ac+\3\2\2\2\u00ad\u00ae"+
		"\7\17\2\2\u00ae\u00af\7\20\2\2\u00af\u00b1\5\64\33\2\u00b0\u00b2\5.\30"+
		"\2\u00b1\u00b0\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b5"+
		"\5\60\31\2\u00b4\u00b6\7\35\2\2\u00b5\u00b4\3\2\2\2\u00b5\u00b6\3\2\2"+
		"\2\u00b6-\3\2\2\2\u00b7\u00b8\7!\2\2\u00b8\u00bd\5\66\34\2\u00b9\u00ba"+
		"\7#\2\2\u00ba\u00bc\5\66\34\2\u00bb\u00b9\3\2\2\2\u00bc\u00bf\3\2\2\2"+
		"\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00c0\3\2\2\2\u00bf\u00bd"+
		"\3\2\2\2\u00c0\u00c1\7\"\2\2\u00c1/\3\2\2\2\u00c2\u00c3\7\21\2\2\u00c3"+
		"\u00c4\7!\2\2\u00c4\u00c9\5\62\32\2\u00c5\u00c6\7#\2\2\u00c6\u00c8\5\62"+
		"\32\2\u00c7\u00c5\3\2\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9"+
		"\u00ca\3\2\2\2\u00ca\u00cc\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc\u00cd\7\""+
		"\2\2\u00cd\61\3\2\2\2\u00ce\u00cf\t\3\2\2\u00cf\63\3\2\2\2\u00d0\u00d1"+
		"\7\25\2\2\u00d1\65\3\2\2\2\u00d2\u00d3\t\4\2\2\u00d3\67\3\2\2\2\20<CK"+
		"any\u0080\u0098\u009b\u00a2\u00b1\u00b5\u00bd\u00c9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}