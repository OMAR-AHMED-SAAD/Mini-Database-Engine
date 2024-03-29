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
		DELETE=16, FROM=17, AND=18, OR=19, XOR=20, INDEX=21, ON=22, USING=23, 
		OCTREE=24, SELECT=25, ID=26, INTEG=27, DOUB=28, DAT=29, MONTH=30, DAY=31, 
		DASH=32, POINT=33, SEMICOL=34, STRING=35, IDENTIFIER=36, EQUAL=37, SMALLER=38, 
		GREATER=39, SMALLERE=40, GREATERE=41, NOTEQUAL=42, LPRAN=43, RPRAN=44, 
		COMMA=45, STAR=46, WS=47;
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
			"FROM", "AND", "OR", "XOR", "INDEX", "ON", "USING", "OCTREE", "SELECT", 
			"ID", "INTEG", "DOUB", "DAT", "MONTH", "DAY", "DASH", "POINT", "SEMICOL", 
			"STRING", "IDENTIFIER", "EQUAL", "SMALLER", "GREATER", "SMALLERE", "GREATERE", 
			"NOTEQUAL", "LPRAN", "RPRAN", "COMMA", "STAR", "WS", "A", "B", "C", "D", 
			"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", 
			"S", "T", "U", "V", "W", "X", "Y", "Z"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\61\u01d9\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4"+
		"I\tI\4J\tJ\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\7\33\u0129\n\33\f\33"+
		"\16\33\u012c\13\33\3\34\6\34\u012f\n\34\r\34\16\34\u0130\3\35\3\35\3\35"+
		"\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37"+
		"\3\37\3\37\3\37\5\37\u0147\n\37\3 \3 \3 \3 \3 \3 \5 \u014f\n \3!\3!\3"+
		"\"\3\"\3#\3#\3$\5$\u0158\n$\3$\3$\3$\3$\7$\u015e\n$\f$\16$\u0161\13$\3"+
		"$\3$\5$\u0165\n$\3$\3$\3$\3$\7$\u016b\n$\f$\16$\u016e\13$\3$\3$\5$\u0172"+
		"\n$\3$\3$\3$\3$\7$\u0178\n$\f$\16$\u017b\13$\3$\3$\5$\u017f\n$\5$\u0181"+
		"\n$\3%\6%\u0184\n%\r%\16%\u0185\3&\3&\3\'\3\'\3(\3(\3)\3)\3)\3*\3*\3*"+
		"\3+\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\6\60\u01a0\n\60\r\60\16\60\u01a1"+
		"\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66"+
		"\3\67\3\67\38\38\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@\3A\3"+
		"A\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3G\3G\3H\3H\3I\3I\3J\3J\2\2K\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C"+
		"#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\2c\2e\2g\2i\2k\2m\2o\2q\2s\2u\2w\2"+
		"y\2{\2}\2\177\2\u0081\2\u0083\2\u0085\2\u0087\2\u0089\2\u008b\2\u008d"+
		"\2\u008f\2\u0091\2\u0093\2\3\2*\5\2C\\aac|\6\2\62;C\\aac|\3\2\62;\3\2"+
		"\62\62\3\2\63;\3\2\63\63\3\2\62\64\3\2\63\64\3\2\65\65\3\2\62\63\5\2\f"+
		"\f\17\17))\5\2\f\f\17\17$$\4\2C\\c|\5\2\13\f\17\17\"\"\4\2CCcc\4\2DDd"+
		"d\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2"+
		"MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4"+
		"\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\2\u01d1\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2"+
		"\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I"+
		"\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2"+
		"\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\3\u0095\3"+
		"\2\2\2\5\u009c\3\2\2\2\7\u00a2\3\2\2\2\t\u00aa\3\2\2\2\13\u00ae\3\2\2"+
		"\2\r\u00b2\3\2\2\2\17\u00ba\3\2\2\2\21\u00c2\3\2\2\2\23\u00c7\3\2\2\2"+
		"\25\u00ce\3\2\2\2\27\u00d5\3\2\2\2\31\u00d9\3\2\2\2\33\u00df\3\2\2\2\35"+
		"\u00e6\3\2\2\2\37\u00eb\3\2\2\2!\u00f2\3\2\2\2#\u00f9\3\2\2\2%\u00fe\3"+
		"\2\2\2\'\u0102\3\2\2\2)\u0105\3\2\2\2+\u0109\3\2\2\2-\u010f\3\2\2\2/\u0112"+
		"\3\2\2\2\61\u0118\3\2\2\2\63\u011f\3\2\2\2\65\u0126\3\2\2\2\67\u012e\3"+
		"\2\2\29\u0132\3\2\2\2;\u0136\3\2\2\2=\u0146\3\2\2\2?\u014e\3\2\2\2A\u0150"+
		"\3\2\2\2C\u0152\3\2\2\2E\u0154\3\2\2\2G\u0180\3\2\2\2I\u0183\3\2\2\2K"+
		"\u0187\3\2\2\2M\u0189\3\2\2\2O\u018b\3\2\2\2Q\u018d\3\2\2\2S\u0190\3\2"+
		"\2\2U\u0193\3\2\2\2W\u0196\3\2\2\2Y\u0198\3\2\2\2[\u019a\3\2\2\2]\u019c"+
		"\3\2\2\2_\u019f\3\2\2\2a\u01a5\3\2\2\2c\u01a7\3\2\2\2e\u01a9\3\2\2\2g"+
		"\u01ab\3\2\2\2i\u01ad\3\2\2\2k\u01af\3\2\2\2m\u01b1\3\2\2\2o\u01b3\3\2"+
		"\2\2q\u01b5\3\2\2\2s\u01b7\3\2\2\2u\u01b9\3\2\2\2w\u01bb\3\2\2\2y\u01bd"+
		"\3\2\2\2{\u01bf\3\2\2\2}\u01c1\3\2\2\2\177\u01c3\3\2\2\2\u0081\u01c5\3"+
		"\2\2\2\u0083\u01c7\3\2\2\2\u0085\u01c9\3\2\2\2\u0087\u01cb\3\2\2\2\u0089"+
		"\u01cd\3\2\2\2\u008b\u01cf\3\2\2\2\u008d\u01d1\3\2\2\2\u008f\u01d3\3\2"+
		"\2\2\u0091\u01d5\3\2\2\2\u0093\u01d7\3\2\2\2\u0095\u0096\5e\63\2\u0096"+
		"\u0097\5\u0083B\2\u0097\u0098\5i\65\2\u0098\u0099\5a\61\2\u0099\u009a"+
		"\5\u0087D\2\u009a\u009b\5i\65\2\u009b\4\3\2\2\2\u009c\u009d\5\u0087D\2"+
		"\u009d\u009e\5a\61\2\u009e\u009f\5c\62\2\u009f\u00a0\5w<\2\u00a0\u00a1"+
		"\5i\65\2\u00a1\6\3\2\2\2\u00a2\u00a3\5\177@\2\u00a3\u00a4\5\u0083B\2\u00a4"+
		"\u00a5\5q9\2\u00a5\u00a6\5y=\2\u00a6\u00a7\5a\61\2\u00a7\u00a8\5\u0083"+
		"B\2\u00a8\u00a9\5\u0091I\2\u00a9\b\3\2\2\2\u00aa\u00ab\5u;\2\u00ab\u00ac"+
		"\5i\65\2\u00ac\u00ad\5\u0091I\2\u00ad\n\3\2\2\2\u00ae\u00af\5q9\2\u00af"+
		"\u00b0\5{>\2\u00b0\u00b1\5\u0087D\2\u00b1\f\3\2\2\2\u00b2\u00b3\5q9\2"+
		"\u00b3\u00b4\5{>\2\u00b4\u00b5\5\u0087D\2\u00b5\u00b6\5i\65\2\u00b6\u00b7"+
		"\5m\67\2\u00b7\u00b8\5i\65\2\u00b8\u00b9\5\u0083B\2\u00b9\16\3\2\2\2\u00ba"+
		"\u00bb\5\u008bF\2\u00bb\u00bc\5a\61\2\u00bc\u00bd\5\u0083B\2\u00bd\u00be"+
		"\5e\63\2\u00be\u00bf\5o8\2\u00bf\u00c0\5a\61\2\u00c0\u00c1\5\u0083B\2"+
		"\u00c1\20\3\2\2\2\u00c2\u00c3\5g\64\2\u00c3\u00c4\5a\61\2\u00c4\u00c5"+
		"\5\u0087D\2\u00c5\u00c6\5i\65\2\u00c6\22\3\2\2\2\u00c7\u00c8\5g\64\2\u00c8"+
		"\u00c9\5}?\2\u00c9\u00ca\5\u0089E\2\u00ca\u00cb\5c\62\2\u00cb\u00cc\5"+
		"w<\2\u00cc\u00cd\5i\65\2\u00cd\24\3\2\2\2\u00ce\u00cf\5\u0089E\2\u00cf"+
		"\u00d0\5\177@\2\u00d0\u00d1\5g\64\2\u00d1\u00d2\5a\61\2\u00d2\u00d3\5"+
		"\u0087D\2\u00d3\u00d4\5i\65\2\u00d4\26\3\2\2\2\u00d5\u00d6\5\u0085C\2"+
		"\u00d6\u00d7\5i\65\2\u00d7\u00d8\5\u0087D\2\u00d8\30\3\2\2\2\u00d9\u00da"+
		"\5\u008dG\2\u00da\u00db\5o8\2\u00db\u00dc\5i\65\2\u00dc\u00dd\5\u0083"+
		"B\2\u00dd\u00de\5i\65\2\u00de\32\3\2\2\2\u00df\u00e0\5q9\2\u00e0\u00e1"+
		"\5{>\2\u00e1\u00e2\5\u0085C\2\u00e2\u00e3\5i\65\2\u00e3\u00e4\5\u0083"+
		"B\2\u00e4\u00e5\5\u0087D\2\u00e5\34\3\2\2\2\u00e6\u00e7\5q9\2\u00e7\u00e8"+
		"\5{>\2\u00e8\u00e9\5\u0087D\2\u00e9\u00ea\5}?\2\u00ea\36\3\2\2\2\u00eb"+
		"\u00ec\5\u008bF\2\u00ec\u00ed\5a\61\2\u00ed\u00ee\5w<\2\u00ee\u00ef\5"+
		"\u0089E\2\u00ef\u00f0\5i\65\2\u00f0\u00f1\5\u0085C\2\u00f1 \3\2\2\2\u00f2"+
		"\u00f3\5g\64\2\u00f3\u00f4\5i\65\2\u00f4\u00f5\5w<\2\u00f5\u00f6\5i\65"+
		"\2\u00f6\u00f7\5\u0087D\2\u00f7\u00f8\5i\65\2\u00f8\"\3\2\2\2\u00f9\u00fa"+
		"\5k\66\2\u00fa\u00fb\5\u0083B\2\u00fb\u00fc\5}?\2\u00fc\u00fd\5y=\2\u00fd"+
		"$\3\2\2\2\u00fe\u00ff\5a\61\2\u00ff\u0100\5{>\2\u0100\u0101\5g\64\2\u0101"+
		"&\3\2\2\2\u0102\u0103\5}?\2\u0103\u0104\5\u0083B\2\u0104(\3\2\2\2\u0105"+
		"\u0106\5\u008fH\2\u0106\u0107\5}?\2\u0107\u0108\5\u0083B\2\u0108*\3\2"+
		"\2\2\u0109\u010a\5q9\2\u010a\u010b\5{>\2\u010b\u010c\5g\64\2\u010c\u010d"+
		"\5i\65\2\u010d\u010e\5\u008fH\2\u010e,\3\2\2\2\u010f\u0110\5}?\2\u0110"+
		"\u0111\5{>\2\u0111.\3\2\2\2\u0112\u0113\5\u0089E\2\u0113\u0114\5\u0085"+
		"C\2\u0114\u0115\5q9\2\u0115\u0116\5{>\2\u0116\u0117\5m\67\2\u0117\60\3"+
		"\2\2\2\u0118\u0119\5}?\2\u0119\u011a\5e\63\2\u011a\u011b\5\u0087D\2\u011b"+
		"\u011c\5\u0083B\2\u011c\u011d\5i\65\2\u011d\u011e\5i\65\2\u011e\62\3\2"+
		"\2\2\u011f\u0120\5\u0085C\2\u0120\u0121\5i\65\2\u0121\u0122\5w<\2\u0122"+
		"\u0123\5i\65\2\u0123\u0124\5e\63\2\u0124\u0125\5\u0087D\2\u0125\64\3\2"+
		"\2\2\u0126\u012a\t\2\2\2\u0127\u0129\t\3\2\2\u0128\u0127\3\2\2\2\u0129"+
		"\u012c\3\2\2\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b\66\3\2\2"+
		"\2\u012c\u012a\3\2\2\2\u012d\u012f\t\4\2\2\u012e\u012d\3\2\2\2\u012f\u0130"+
		"\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u01318\3\2\2\2\u0132"+
		"\u0133\5\67\34\2\u0133\u0134\5C\"\2\u0134\u0135\5\67\34\2\u0135:\3\2\2"+
		"\2\u0136\u0137\7)\2\2\u0137\u0138\t\4\2\2\u0138\u0139\t\4\2\2\u0139\u013a"+
		"\t\4\2\2\u013a\u013b\t\4\2\2\u013b\u013c\5A!\2\u013c\u013d\5=\37\2\u013d"+
		"\u013e\5A!\2\u013e\u013f\5? \2\u013f\u0140\3\2\2\2\u0140\u0141\7)\2\2"+
		"\u0141<\3\2\2\2\u0142\u0143\t\5\2\2\u0143\u0147\t\6\2\2\u0144\u0145\t"+
		"\7\2\2\u0145\u0147\t\b\2\2\u0146\u0142\3\2\2\2\u0146\u0144\3\2\2\2\u0147"+
		">\3\2\2\2\u0148\u0149\t\5\2\2\u0149\u014f\t\6\2\2\u014a\u014b\t\t\2\2"+
		"\u014b\u014f\t\4\2\2\u014c\u014d\t\n\2\2\u014d\u014f\t\13\2\2\u014e\u0148"+
		"\3\2\2\2\u014e\u014a\3\2\2\2\u014e\u014c\3\2\2\2\u014f@\3\2\2\2\u0150"+
		"\u0151\7/\2\2\u0151B\3\2\2\2\u0152\u0153\7\60\2\2\u0153D\3\2\2\2\u0154"+
		"\u0155\7=\2\2\u0155F\3\2\2\2\u0156\u0158\7p\2\2\u0157\u0156\3\2\2\2\u0157"+
		"\u0158\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015f\7)\2\2\u015a\u015b\7)\2"+
		"\2\u015b\u015e\7)\2\2\u015c\u015e\n\f\2\2\u015d\u015a\3\2\2\2\u015d\u015c"+
		"\3\2\2\2\u015e\u0161\3\2\2\2\u015f\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160"+
		"\u0162\3\2\2\2\u0161\u015f\3\2\2\2\u0162\u0181\7)\2\2\u0163\u0165\7p\2"+
		"\2\u0164\u0163\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u016c"+
		"\7$\2\2\u0167\u0168\7)\2\2\u0168\u016b\7)\2\2\u0169\u016b\n\r\2\2\u016a"+
		"\u0167\3\2\2\2\u016a\u0169\3\2\2\2\u016b\u016e\3\2\2\2\u016c\u016a\3\2"+
		"\2\2\u016c\u016d\3\2\2\2\u016d\u016f\3\2\2\2\u016e\u016c\3\2\2\2\u016f"+
		"\u0181\7$\2\2\u0170\u0172\7p\2\2\u0171\u0170\3\2\2\2\u0171\u0172\3\2\2"+
		"\2\u0172\u0173\3\2\2\2\u0173\u0179\7$\2\2\u0174\u0175\7)\2\2\u0175\u0178"+
		"\7)\2\2\u0176\u0178\n\r\2\2\u0177\u0174\3\2\2\2\u0177\u0176\3\2\2\2\u0178"+
		"\u017b\3\2\2\2\u0179\u0177\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u017c\3\2"+
		"\2\2\u017b\u0179\3\2\2\2\u017c\u017e\7$\2\2\u017d\u017f\5I%\2\u017e\u017d"+
		"\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u0181\3\2\2\2\u0180\u0157\3\2\2\2\u0180"+
		"\u0164\3\2\2\2\u0180\u0171\3\2\2\2\u0181H\3\2\2\2\u0182\u0184\t\16\2\2"+
		"\u0183\u0182\3\2\2\2\u0184\u0185\3\2\2\2\u0185\u0183\3\2\2\2\u0185\u0186"+
		"\3\2\2\2\u0186J\3\2\2\2\u0187\u0188\7?\2\2\u0188L\3\2\2\2\u0189\u018a"+
		"\7>\2\2\u018aN\3\2\2\2\u018b\u018c\7@\2\2\u018cP\3\2\2\2\u018d\u018e\7"+
		">\2\2\u018e\u018f\7?\2\2\u018fR\3\2\2\2\u0190\u0191\7@\2\2\u0191\u0192"+
		"\7?\2\2\u0192T\3\2\2\2\u0193\u0194\7#\2\2\u0194\u0195\7?\2\2\u0195V\3"+
		"\2\2\2\u0196\u0197\7*\2\2\u0197X\3\2\2\2\u0198\u0199\7+\2\2\u0199Z\3\2"+
		"\2\2\u019a\u019b\7.\2\2\u019b\\\3\2\2\2\u019c\u019d\7,\2\2\u019d^\3\2"+
		"\2\2\u019e\u01a0\t\17\2\2\u019f\u019e\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1"+
		"\u019f\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01a4\b\60"+
		"\2\2\u01a4`\3\2\2\2\u01a5\u01a6\t\20\2\2\u01a6b\3\2\2\2\u01a7\u01a8\t"+
		"\21\2\2\u01a8d\3\2\2\2\u01a9\u01aa\t\22\2\2\u01aaf\3\2\2\2\u01ab\u01ac"+
		"\t\23\2\2\u01ach\3\2\2\2\u01ad\u01ae\t\24\2\2\u01aej\3\2\2\2\u01af\u01b0"+
		"\t\25\2\2\u01b0l\3\2\2\2\u01b1\u01b2\t\26\2\2\u01b2n\3\2\2\2\u01b3\u01b4"+
		"\t\27\2\2\u01b4p\3\2\2\2\u01b5\u01b6\t\30\2\2\u01b6r\3\2\2\2\u01b7\u01b8"+
		"\t\31\2\2\u01b8t\3\2\2\2\u01b9\u01ba\t\32\2\2\u01bav\3\2\2\2\u01bb\u01bc"+
		"\t\33\2\2\u01bcx\3\2\2\2\u01bd\u01be\t\34\2\2\u01bez\3\2\2\2\u01bf\u01c0"+
		"\t\35\2\2\u01c0|\3\2\2\2\u01c1\u01c2\t\36\2\2\u01c2~\3\2\2\2\u01c3\u01c4"+
		"\t\37\2\2\u01c4\u0080\3\2\2\2\u01c5\u01c6\t \2\2\u01c6\u0082\3\2\2\2\u01c7"+
		"\u01c8\t!\2\2\u01c8\u0084\3\2\2\2\u01c9\u01ca\t\"\2\2\u01ca\u0086\3\2"+
		"\2\2\u01cb\u01cc\t#\2\2\u01cc\u0088\3\2\2\2\u01cd\u01ce\t$\2\2\u01ce\u008a"+
		"\3\2\2\2\u01cf\u01d0\t%\2\2\u01d0\u008c\3\2\2\2\u01d1\u01d2\t&\2\2\u01d2"+
		"\u008e\3\2\2\2\u01d3\u01d4\t\'\2\2\u01d4\u0090\3\2\2\2\u01d5\u01d6\t("+
		"\2\2\u01d6\u0092\3\2\2\2\u01d7\u01d8\t)\2\2\u01d8\u0094\3\2\2\2\24\2\u012a"+
		"\u0130\u0146\u014e\u0157\u015d\u015f\u0164\u016a\u016c\u0171\u0177\u0179"+
		"\u017e\u0180\u0185\u01a1\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}