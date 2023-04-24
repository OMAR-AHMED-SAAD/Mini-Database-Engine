// Generated from sql.g4 by ANTLR 4.9.2

package parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class sqlLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"CREATE", "TABLE", "PRIMARY", "KEY", "INT", "INTEGER", "VARCHAR", "DATE", 
			"DOUBLE", "UPDATE", "SET", "WHERE", "INSERT", "INTO", "VALUES", "DELETE", 
			"FROM", "AND", "ID", "INTEG", "DOUB", "DAT", "MONTH", "DAY", "DASH", 
			"POINT", "SEMICOL", "STRING", "DOUBLE_QUOTE", "EQUAL", "LPRAN", "RPRAN", 
			"COMMA", "WS", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", 
			"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", 
			"Z"
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


	public sqlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "sql.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2$\u0181\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\7\24\u00eb\n\24\f\24\16\24\u00ee\13\24\3\25\6\25\u00f1\n\25\r\25\16\25"+
		"\u00f2\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\30\3\30\3\30\3\30\5\30\u0109\n\30\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\5\31\u0111\n\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\5\35\u011a"+
		"\n\35\3\35\3\35\3\35\3\35\7\35\u0120\n\35\f\35\16\35\u0123\13\35\3\35"+
		"\3\35\5\35\u0127\n\35\3\35\3\35\3\35\3\35\7\35\u012d\n\35\f\35\16\35\u0130"+
		"\13\35\3\35\3\35\3\35\5\35\u0135\n\35\3\35\3\35\3\35\3\35\5\35\u013b\n"+
		"\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\6#\u0148\n#\r#\16#\u0149"+
		"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3"+
		".\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65"+
		"\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\2\2>\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C"+
		"#E$G\2I\2K\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\2_\2a\2c\2e\2g\2i\2k\2m\2o\2q\2"+
		"s\2u\2w\2y\2\3\2(\5\2C\\aac|\6\2\62;C\\aac|\3\2\62;\3\2\62\62\3\2\63;"+
		"\3\2\63\63\3\2\62\64\3\2\63\64\3\2\65\65\3\2\62\63\3\2))\5\2\13\f\17\17"+
		"\"\"\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4"+
		"\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSs"+
		"s\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2"+
		"\\\\||\2\u0175\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\3{\3\2\2\2\5\u0082\3\2\2\2\7\u0088\3\2\2\2\t\u0090\3\2\2\2\13\u0094"+
		"\3\2\2\2\r\u0098\3\2\2\2\17\u00a0\3\2\2\2\21\u00a8\3\2\2\2\23\u00ad\3"+
		"\2\2\2\25\u00b4\3\2\2\2\27\u00bb\3\2\2\2\31\u00bf\3\2\2\2\33\u00c5\3\2"+
		"\2\2\35\u00cc\3\2\2\2\37\u00d1\3\2\2\2!\u00d8\3\2\2\2#\u00df\3\2\2\2%"+
		"\u00e4\3\2\2\2\'\u00e8\3\2\2\2)\u00f0\3\2\2\2+\u00f4\3\2\2\2-\u00f8\3"+
		"\2\2\2/\u0108\3\2\2\2\61\u0110\3\2\2\2\63\u0112\3\2\2\2\65\u0114\3\2\2"+
		"\2\67\u0116\3\2\2\29\u013a\3\2\2\2;\u013c\3\2\2\2=\u013e\3\2\2\2?\u0140"+
		"\3\2\2\2A\u0142\3\2\2\2C\u0144\3\2\2\2E\u0147\3\2\2\2G\u014d\3\2\2\2I"+
		"\u014f\3\2\2\2K\u0151\3\2\2\2M\u0153\3\2\2\2O\u0155\3\2\2\2Q\u0157\3\2"+
		"\2\2S\u0159\3\2\2\2U\u015b\3\2\2\2W\u015d\3\2\2\2Y\u015f\3\2\2\2[\u0161"+
		"\3\2\2\2]\u0163\3\2\2\2_\u0165\3\2\2\2a\u0167\3\2\2\2c\u0169\3\2\2\2e"+
		"\u016b\3\2\2\2g\u016d\3\2\2\2i\u016f\3\2\2\2k\u0171\3\2\2\2m\u0173\3\2"+
		"\2\2o\u0175\3\2\2\2q\u0177\3\2\2\2s\u0179\3\2\2\2u\u017b\3\2\2\2w\u017d"+
		"\3\2\2\2y\u017f\3\2\2\2{|\5K&\2|}\5i\65\2}~\5O(\2~\177\5G$\2\177\u0080"+
		"\5m\67\2\u0080\u0081\5O(\2\u0081\4\3\2\2\2\u0082\u0083\5m\67\2\u0083\u0084"+
		"\5G$\2\u0084\u0085\5I%\2\u0085\u0086\5]/\2\u0086\u0087\5O(\2\u0087\6\3"+
		"\2\2\2\u0088\u0089\5e\63\2\u0089\u008a\5i\65\2\u008a\u008b\5W,\2\u008b"+
		"\u008c\5_\60\2\u008c\u008d\5G$\2\u008d\u008e\5i\65\2\u008e\u008f\5w<\2"+
		"\u008f\b\3\2\2\2\u0090\u0091\5[.\2\u0091\u0092\5O(\2\u0092\u0093\5w<\2"+
		"\u0093\n\3\2\2\2\u0094\u0095\5W,\2\u0095\u0096\5a\61\2\u0096\u0097\5m"+
		"\67\2\u0097\f\3\2\2\2\u0098\u0099\5W,\2\u0099\u009a\5a\61\2\u009a\u009b"+
		"\5m\67\2\u009b\u009c\5O(\2\u009c\u009d\5S*\2\u009d\u009e\5O(\2\u009e\u009f"+
		"\5i\65\2\u009f\16\3\2\2\2\u00a0\u00a1\5q9\2\u00a1\u00a2\5G$\2\u00a2\u00a3"+
		"\5i\65\2\u00a3\u00a4\5K&\2\u00a4\u00a5\5U+\2\u00a5\u00a6\5G$\2\u00a6\u00a7"+
		"\5i\65\2\u00a7\20\3\2\2\2\u00a8\u00a9\5M\'\2\u00a9\u00aa\5G$\2\u00aa\u00ab"+
		"\5m\67\2\u00ab\u00ac\5O(\2\u00ac\22\3\2\2\2\u00ad\u00ae\5M\'\2\u00ae\u00af"+
		"\5c\62\2\u00af\u00b0\5o8\2\u00b0\u00b1\5I%\2\u00b1\u00b2\5]/\2\u00b2\u00b3"+
		"\5O(\2\u00b3\24\3\2\2\2\u00b4\u00b5\5o8\2\u00b5\u00b6\5e\63\2\u00b6\u00b7"+
		"\5M\'\2\u00b7\u00b8\5G$\2\u00b8\u00b9\5m\67\2\u00b9\u00ba\5O(\2\u00ba"+
		"\26\3\2\2\2\u00bb\u00bc\5k\66\2\u00bc\u00bd\5O(\2\u00bd\u00be\5m\67\2"+
		"\u00be\30\3\2\2\2\u00bf\u00c0\5s:\2\u00c0\u00c1\5U+\2\u00c1\u00c2\5O("+
		"\2\u00c2\u00c3\5i\65\2\u00c3\u00c4\5O(\2\u00c4\32\3\2\2\2\u00c5\u00c6"+
		"\5W,\2\u00c6\u00c7\5a\61\2\u00c7\u00c8\5k\66\2\u00c8\u00c9\5O(\2\u00c9"+
		"\u00ca\5i\65\2\u00ca\u00cb\5m\67\2\u00cb\34\3\2\2\2\u00cc\u00cd\5W,\2"+
		"\u00cd\u00ce\5a\61\2\u00ce\u00cf\5m\67\2\u00cf\u00d0\5c\62\2\u00d0\36"+
		"\3\2\2\2\u00d1\u00d2\5q9\2\u00d2\u00d3\5G$\2\u00d3\u00d4\5]/\2\u00d4\u00d5"+
		"\5o8\2\u00d5\u00d6\5O(\2\u00d6\u00d7\5k\66\2\u00d7 \3\2\2\2\u00d8\u00d9"+
		"\5M\'\2\u00d9\u00da\5O(\2\u00da\u00db\5]/\2\u00db\u00dc\5O(\2\u00dc\u00dd"+
		"\5m\67\2\u00dd\u00de\5O(\2\u00de\"\3\2\2\2\u00df\u00e0\5Q)\2\u00e0\u00e1"+
		"\5i\65\2\u00e1\u00e2\5c\62\2\u00e2\u00e3\5_\60\2\u00e3$\3\2\2\2\u00e4"+
		"\u00e5\5G$\2\u00e5\u00e6\5a\61\2\u00e6\u00e7\5M\'\2\u00e7&\3\2\2\2\u00e8"+
		"\u00ec\t\2\2\2\u00e9\u00eb\t\3\2\2\u00ea\u00e9\3\2\2\2\u00eb\u00ee\3\2"+
		"\2\2\u00ec\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed(\3\2\2\2\u00ee\u00ec"+
		"\3\2\2\2\u00ef\u00f1\t\4\2\2\u00f0\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2"+
		"\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3*\3\2\2\2\u00f4\u00f5\5)\25\2"+
		"\u00f5\u00f6\5\65\33\2\u00f6\u00f7\5)\25\2\u00f7,\3\2\2\2\u00f8\u00f9"+
		"\7)\2\2\u00f9\u00fa\t\4\2\2\u00fa\u00fb\t\4\2\2\u00fb\u00fc\t\4\2\2\u00fc"+
		"\u00fd\t\4\2\2\u00fd\u00fe\5\63\32\2\u00fe\u00ff\5/\30\2\u00ff\u0100\5"+
		"\63\32\2\u0100\u0101\5\61\31\2\u0101\u0102\3\2\2\2\u0102\u0103\7)\2\2"+
		"\u0103.\3\2\2\2\u0104\u0105\t\5\2\2\u0105\u0109\t\6\2\2\u0106\u0107\t"+
		"\7\2\2\u0107\u0109\t\b\2\2\u0108\u0104\3\2\2\2\u0108\u0106\3\2\2\2\u0109"+
		"\60\3\2\2\2\u010a\u010b\t\5\2\2\u010b\u0111\t\6\2\2\u010c\u010d\t\t\2"+
		"\2\u010d\u0111\t\4\2\2\u010e\u010f\t\n\2\2\u010f\u0111\t\13\2\2\u0110"+
		"\u010a\3\2\2\2\u0110\u010c\3\2\2\2\u0110\u010e\3\2\2\2\u0111\62\3\2\2"+
		"\2\u0112\u0113\7/\2\2\u0113\64\3\2\2\2\u0114\u0115\7\60\2\2\u0115\66\3"+
		"\2\2\2\u0116\u0117\7=\2\2\u01178\3\2\2\2\u0118\u011a\7p\2\2\u0119\u0118"+
		"\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u0121\7)\2\2\u011c"+
		"\u011d\7)\2\2\u011d\u0120\7)\2\2\u011e\u0120\n\f\2\2\u011f\u011c\3\2\2"+
		"\2\u011f\u011e\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122"+
		"\3\2\2\2\u0122\u0124\3\2\2\2\u0123\u0121\3\2\2\2\u0124\u013b\7)\2\2\u0125"+
		"\u0127\7p\2\2\u0126\u0125\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0128\3\2"+
		"\2\2\u0128\u012e\5;\36\2\u0129\u012a\7)\2\2\u012a\u012d\7)\2\2\u012b\u012d"+
		"\n\f\2\2\u012c\u0129\3\2\2\2\u012c\u012b\3\2\2\2\u012d\u0130\3\2\2\2\u012e"+
		"\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0131\3\2\2\2\u0130\u012e\3\2"+
		"\2\2\u0131\u0132\5;\36\2\u0132\u013b\3\2\2\2\u0133\u0135\7p\2\2\u0134"+
		"\u0133\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0137\5;"+
		"\36\2\u0137\u0138\59\35\2\u0138\u0139\5;\36\2\u0139\u013b\3\2\2\2\u013a"+
		"\u0119\3\2\2\2\u013a\u0126\3\2\2\2\u013a\u0134\3\2\2\2\u013b:\3\2\2\2"+
		"\u013c\u013d\7$\2\2\u013d<\3\2\2\2\u013e\u013f\7?\2\2\u013f>\3\2\2\2\u0140"+
		"\u0141\7*\2\2\u0141@\3\2\2\2\u0142\u0143\7+\2\2\u0143B\3\2\2\2\u0144\u0145"+
		"\7.\2\2\u0145D\3\2\2\2\u0146\u0148\t\r\2\2\u0147\u0146\3\2\2\2\u0148\u0149"+
		"\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014b\3\2\2\2\u014b"+
		"\u014c\b#\2\2\u014cF\3\2\2\2\u014d\u014e\t\16\2\2\u014eH\3\2\2\2\u014f"+
		"\u0150\t\17\2\2\u0150J\3\2\2\2\u0151\u0152\t\20\2\2\u0152L\3\2\2\2\u0153"+
		"\u0154\t\21\2\2\u0154N\3\2\2\2\u0155\u0156\t\22\2\2\u0156P\3\2\2\2\u0157"+
		"\u0158\t\23\2\2\u0158R\3\2\2\2\u0159\u015a\t\24\2\2\u015aT\3\2\2\2\u015b"+
		"\u015c\t\25\2\2\u015cV\3\2\2\2\u015d\u015e\t\26\2\2\u015eX\3\2\2\2\u015f"+
		"\u0160\t\27\2\2\u0160Z\3\2\2\2\u0161\u0162\t\30\2\2\u0162\\\3\2\2\2\u0163"+
		"\u0164\t\31\2\2\u0164^\3\2\2\2\u0165\u0166\t\32\2\2\u0166`\3\2\2\2\u0167"+
		"\u0168\t\33\2\2\u0168b\3\2\2\2\u0169\u016a\t\34\2\2\u016ad\3\2\2\2\u016b"+
		"\u016c\t\35\2\2\u016cf\3\2\2\2\u016d\u016e\t\36\2\2\u016eh\3\2\2\2\u016f"+
		"\u0170\t\37\2\2\u0170j\3\2\2\2\u0171\u0172\t \2\2\u0172l\3\2\2\2\u0173"+
		"\u0174\t!\2\2\u0174n\3\2\2\2\u0175\u0176\t\"\2\2\u0176p\3\2\2\2\u0177"+
		"\u0178\t#\2\2\u0178r\3\2\2\2\u0179\u017a\t$\2\2\u017at\3\2\2\2\u017b\u017c"+
		"\t%\2\2\u017cv\3\2\2\2\u017d\u017e\t&\2\2\u017ex\3\2\2\2\u017f\u0180\t"+
		"\'\2\2\u0180z\3\2\2\2\20\2\u00ec\u00f2\u0108\u0110\u0119\u011f\u0121\u0126"+
		"\u012c\u012e\u0134\u013a\u0149\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}