// Generated from D:/MiUsuario/Escritorio/EclipseStuff/tercero/DLP/_Lab/GitEnabled/src/parser\Cmm.g4 by ANTLR 4.8
package parser;

import ast.*;
import ast.definitions.*;
import ast.definitions.subclasses.*;
import ast.expression.*;
import ast.expression.subclasses.*;
import ast.statement.*;
import ast.statement.subclasses.*;
import ast.type.*;
import ast.type.subclasses.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CmmLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, INT_CONSTANT=38, 
		BOOLEAN_CONSTANT=39, CHAR_CONSTANT=40, REAL_CONSTANT=41, SINGLE_LINE_COMMENT=42, 
		MULTI_LINE_COMMENT=43, ID=44, SPACE=45;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
			"T__33", "T__34", "T__35", "T__36", "INT_CONSTANT", "BOOLEAN_CONSTANT", 
			"CHAR_CONSTANT", "CHAR_CONTENT", "DIGIT", "REAL_CONSTANT", "REAL_MANTISSA", 
			"REAL_EXPONENT", "SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT", "ID", "LETTER", 
			"SPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "','", "';'", "'void'", "'('", "')'", "'main'", "'write'", 
			"'read'", "'='", "'return'", "'if'", "'else'", "'while'", "'['", "']'", 
			"'-'", "'.'", "'*'", "'/'", "'%'", "'+'", "'>'", "'>='", "'<'", "'<='", 
			"'!='", "'=='", "'&&'", "'||'", "'!'", "'struct'", "'int'", "'char'", 
			"'double'", "'bool'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "INT_CONSTANT", "BOOLEAN_CONSTANT", "CHAR_CONSTANT", "REAL_CONSTANT", 
			"SINGLE_LINE_COMMENT", "MULTI_LINE_COMMENT", "ID", "SPACE"
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


	public CmmLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Cmm.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2/\u015f\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\3\2\3\2"+
		"\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25"+
		"\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\33\3\33"+
		"\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 "+
		"\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3"+
		"%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\7\'\u00e7\n\'\f\'\16\'\u00ea"+
		"\13\'\5\'\u00ec\n\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\5(\u00f7\n(\3)\3)\3)\3"+
		")\3*\3*\3*\3*\3*\3*\3*\5*\u0104\n*\3+\3+\3,\3,\5,\u010a\n,\3,\3,\3,\5"+
		",\u010f\n,\3-\5-\u0112\n-\3-\3-\6-\u0116\n-\r-\16-\u0117\3-\3-\3-\7-\u011d"+
		"\n-\f-\16-\u0120\13-\5-\u0122\n-\3.\3.\5.\u0126\n.\3.\3.\3/\3/\3/\3/\7"+
		"/\u012e\n/\f/\16/\u0131\13/\3/\5/\u0134\n/\3/\3/\3/\3/\3\60\3\60\3\60"+
		"\3\60\7\60\u013e\n\60\f\60\16\60\u0141\13\60\3\60\3\60\3\60\3\60\3\60"+
		"\3\61\3\61\5\61\u014a\n\61\3\61\3\61\3\61\7\61\u014f\n\61\f\61\16\61\u0152"+
		"\13\61\3\62\3\62\3\63\3\63\6\63\u0158\n\63\r\63\16\63\u0159\5\63\u015c"+
		"\n\63\3\63\3\63\4\u012f\u013f\2\64\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S\2U\2W+Y"+
		"\2[\2],_-a.c\2e/\3\2\b\3\2\63;\3\2\62;\4\2GGgg\4\2--//\4\2C\\c|\4\2\13"+
		"\f\17\17\2\u016f\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E"+
		"\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2"+
		"\2\2\2W\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2e\3\2\2\2\3g\3\2\2\2"+
		"\5i\3\2\2\2\7k\3\2\2\2\tm\3\2\2\2\13o\3\2\2\2\rt\3\2\2\2\17v\3\2\2\2\21"+
		"x\3\2\2\2\23}\3\2\2\2\25\u0083\3\2\2\2\27\u0088\3\2\2\2\31\u008a\3\2\2"+
		"\2\33\u0091\3\2\2\2\35\u0094\3\2\2\2\37\u0099\3\2\2\2!\u009f\3\2\2\2#"+
		"\u00a1\3\2\2\2%\u00a3\3\2\2\2\'\u00a5\3\2\2\2)\u00a7\3\2\2\2+\u00a9\3"+
		"\2\2\2-\u00ab\3\2\2\2/\u00ad\3\2\2\2\61\u00af\3\2\2\2\63\u00b1\3\2\2\2"+
		"\65\u00b4\3\2\2\2\67\u00b6\3\2\2\29\u00b9\3\2\2\2;\u00bc\3\2\2\2=\u00bf"+
		"\3\2\2\2?\u00c2\3\2\2\2A\u00c5\3\2\2\2C\u00c7\3\2\2\2E\u00ce\3\2\2\2G"+
		"\u00d2\3\2\2\2I\u00d7\3\2\2\2K\u00de\3\2\2\2M\u00eb\3\2\2\2O\u00f6\3\2"+
		"\2\2Q\u00f8\3\2\2\2S\u0103\3\2\2\2U\u0105\3\2\2\2W\u010e\3\2\2\2Y\u0121"+
		"\3\2\2\2[\u0123\3\2\2\2]\u0129\3\2\2\2_\u0139\3\2\2\2a\u0149\3\2\2\2c"+
		"\u0153\3\2\2\2e\u015b\3\2\2\2gh\7}\2\2h\4\3\2\2\2ij\7\177\2\2j\6\3\2\2"+
		"\2kl\7.\2\2l\b\3\2\2\2mn\7=\2\2n\n\3\2\2\2op\7x\2\2pq\7q\2\2qr\7k\2\2"+
		"rs\7f\2\2s\f\3\2\2\2tu\7*\2\2u\16\3\2\2\2vw\7+\2\2w\20\3\2\2\2xy\7o\2"+
		"\2yz\7c\2\2z{\7k\2\2{|\7p\2\2|\22\3\2\2\2}~\7y\2\2~\177\7t\2\2\177\u0080"+
		"\7k\2\2\u0080\u0081\7v\2\2\u0081\u0082\7g\2\2\u0082\24\3\2\2\2\u0083\u0084"+
		"\7t\2\2\u0084\u0085\7g\2\2\u0085\u0086\7c\2\2\u0086\u0087\7f\2\2\u0087"+
		"\26\3\2\2\2\u0088\u0089\7?\2\2\u0089\30\3\2\2\2\u008a\u008b\7t\2\2\u008b"+
		"\u008c\7g\2\2\u008c\u008d\7v\2\2\u008d\u008e\7w\2\2\u008e\u008f\7t\2\2"+
		"\u008f\u0090\7p\2\2\u0090\32\3\2\2\2\u0091\u0092\7k\2\2\u0092\u0093\7"+
		"h\2\2\u0093\34\3\2\2\2\u0094\u0095\7g\2\2\u0095\u0096\7n\2\2\u0096\u0097"+
		"\7u\2\2\u0097\u0098\7g\2\2\u0098\36\3\2\2\2\u0099\u009a\7y\2\2\u009a\u009b"+
		"\7j\2\2\u009b\u009c\7k\2\2\u009c\u009d\7n\2\2\u009d\u009e\7g\2\2\u009e"+
		" \3\2\2\2\u009f\u00a0\7]\2\2\u00a0\"\3\2\2\2\u00a1\u00a2\7_\2\2\u00a2"+
		"$\3\2\2\2\u00a3\u00a4\7/\2\2\u00a4&\3\2\2\2\u00a5\u00a6\7\60\2\2\u00a6"+
		"(\3\2\2\2\u00a7\u00a8\7,\2\2\u00a8*\3\2\2\2\u00a9\u00aa\7\61\2\2\u00aa"+
		",\3\2\2\2\u00ab\u00ac\7\'\2\2\u00ac.\3\2\2\2\u00ad\u00ae\7-\2\2\u00ae"+
		"\60\3\2\2\2\u00af\u00b0\7@\2\2\u00b0\62\3\2\2\2\u00b1\u00b2\7@\2\2\u00b2"+
		"\u00b3\7?\2\2\u00b3\64\3\2\2\2\u00b4\u00b5\7>\2\2\u00b5\66\3\2\2\2\u00b6"+
		"\u00b7\7>\2\2\u00b7\u00b8\7?\2\2\u00b88\3\2\2\2\u00b9\u00ba\7#\2\2\u00ba"+
		"\u00bb\7?\2\2\u00bb:\3\2\2\2\u00bc\u00bd\7?\2\2\u00bd\u00be\7?\2\2\u00be"+
		"<\3\2\2\2\u00bf\u00c0\7(\2\2\u00c0\u00c1\7(\2\2\u00c1>\3\2\2\2\u00c2\u00c3"+
		"\7~\2\2\u00c3\u00c4\7~\2\2\u00c4@\3\2\2\2\u00c5\u00c6\7#\2\2\u00c6B\3"+
		"\2\2\2\u00c7\u00c8\7u\2\2\u00c8\u00c9\7v\2\2\u00c9\u00ca\7t\2\2\u00ca"+
		"\u00cb\7w\2\2\u00cb\u00cc\7e\2\2\u00cc\u00cd\7v\2\2\u00cdD\3\2\2\2\u00ce"+
		"\u00cf\7k\2\2\u00cf\u00d0\7p\2\2\u00d0\u00d1\7v\2\2\u00d1F\3\2\2\2\u00d2"+
		"\u00d3\7e\2\2\u00d3\u00d4\7j\2\2\u00d4\u00d5\7c\2\2\u00d5\u00d6\7t\2\2"+
		"\u00d6H\3\2\2\2\u00d7\u00d8\7f\2\2\u00d8\u00d9\7q\2\2\u00d9\u00da\7w\2"+
		"\2\u00da\u00db\7d\2\2\u00db\u00dc\7n\2\2\u00dc\u00dd\7g\2\2\u00ddJ\3\2"+
		"\2\2\u00de\u00df\7d\2\2\u00df\u00e0\7q\2\2\u00e0\u00e1\7q\2\2\u00e1\u00e2"+
		"\7n\2\2\u00e2L\3\2\2\2\u00e3\u00ec\7\62\2\2\u00e4\u00e8\t\2\2\2\u00e5"+
		"\u00e7\t\3\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6\3\2"+
		"\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb"+
		"\u00e3\3\2\2\2\u00eb\u00e4\3\2\2\2\u00ecN\3\2\2\2\u00ed\u00ee\7v\2\2\u00ee"+
		"\u00ef\7t\2\2\u00ef\u00f0\7w\2\2\u00f0\u00f7\7g\2\2\u00f1\u00f2\7h\2\2"+
		"\u00f2\u00f3\7c\2\2\u00f3\u00f4\7n\2\2\u00f4\u00f5\7u\2\2\u00f5\u00f7"+
		"\7g\2\2\u00f6\u00ed\3\2\2\2\u00f6\u00f1\3\2\2\2\u00f7P\3\2\2\2\u00f8\u00f9"+
		"\7)\2\2\u00f9\u00fa\5S*\2\u00fa\u00fb\7)\2\2\u00fbR\3\2\2\2\u00fc\u0104"+
		"\13\2\2\2\u00fd\u00fe\7^\2\2\u00fe\u0104\5M\'\2\u00ff\u0100\7^\2\2\u0100"+
		"\u0104\7p\2\2\u0101\u0102\7^\2\2\u0102\u0104\7v\2\2\u0103\u00fc\3\2\2"+
		"\2\u0103\u00fd\3\2\2\2\u0103\u00ff\3\2\2\2\u0103\u0101\3\2\2\2\u0104T"+
		"\3\2\2\2\u0105\u0106\t\3\2\2\u0106V\3\2\2\2\u0107\u0109\5Y-\2\u0108\u010a"+
		"\5[.\2\u0109\u0108\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010f\3\2\2\2\u010b"+
		"\u010c\5M\'\2\u010c\u010d\5[.\2\u010d\u010f\3\2\2\2\u010e\u0107\3\2\2"+
		"\2\u010e\u010b\3\2\2\2\u010fX\3\2\2\2\u0110\u0112\5M\'\2\u0111\u0110\3"+
		"\2\2\2\u0111\u0112\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0115\7\60\2\2\u0114"+
		"\u0116\5U+\2\u0115\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0115\3\2\2"+
		"\2\u0117\u0118\3\2\2\2\u0118\u0122\3\2\2\2\u0119\u011a\5M\'\2\u011a\u011e"+
		"\7\60\2\2\u011b\u011d\5U+\2\u011c\u011b\3\2\2\2\u011d\u0120\3\2\2\2\u011e"+
		"\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0122\3\2\2\2\u0120\u011e\3\2"+
		"\2\2\u0121\u0111\3\2\2\2\u0121\u0119\3\2\2\2\u0122Z\3\2\2\2\u0123\u0125"+
		"\t\4\2\2\u0124\u0126\t\5\2\2\u0125\u0124\3\2\2\2\u0125\u0126\3\2\2\2\u0126"+
		"\u0127\3\2\2\2\u0127\u0128\5M\'\2\u0128\\\3\2\2\2\u0129\u012a\7\61\2\2"+
		"\u012a\u012b\7\61\2\2\u012b\u012f\3\2\2\2\u012c\u012e\13\2\2\2\u012d\u012c"+
		"\3\2\2\2\u012e\u0131\3\2\2\2\u012f\u0130\3\2\2\2\u012f\u012d\3\2\2\2\u0130"+
		"\u0133\3\2\2\2\u0131\u012f\3\2\2\2\u0132\u0134\7\17\2\2\u0133\u0132\3"+
		"\2\2\2\u0133\u0134\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0136\7\f\2\2\u0136"+
		"\u0137\3\2\2\2\u0137\u0138\b/\2\2\u0138^\3\2\2\2\u0139\u013a\7\61\2\2"+
		"\u013a\u013b\7,\2\2\u013b\u013f\3\2\2\2\u013c\u013e\13\2\2\2\u013d\u013c"+
		"\3\2\2\2\u013e\u0141\3\2\2\2\u013f\u0140\3\2\2\2\u013f\u013d\3\2\2\2\u0140"+
		"\u0142\3\2\2\2\u0141\u013f\3\2\2\2\u0142\u0143\7,\2\2\u0143\u0144\7\61"+
		"\2\2\u0144\u0145\3\2\2\2\u0145\u0146\b\60\2\2\u0146`\3\2\2\2\u0147\u014a"+
		"\7a\2\2\u0148\u014a\5c\62\2\u0149\u0147\3\2\2\2\u0149\u0148\3\2\2\2\u014a"+
		"\u0150\3\2\2\2\u014b\u014f\7a\2\2\u014c\u014f\5U+\2\u014d\u014f\5c\62"+
		"\2\u014e\u014b\3\2\2\2\u014e\u014c\3\2\2\2\u014e\u014d\3\2\2\2\u014f\u0152"+
		"\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151b\3\2\2\2\u0152"+
		"\u0150\3\2\2\2\u0153\u0154\t\6\2\2\u0154d\3\2\2\2\u0155\u015c\7\"\2\2"+
		"\u0156\u0158\t\7\2\2\u0157\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u0157"+
		"\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u015c\3\2\2\2\u015b\u0155\3\2\2\2\u015b"+
		"\u0157\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u015e\b\63\2\2\u015ef\3\2\2\2"+
		"\26\2\u00e8\u00eb\u00f6\u0103\u0109\u010e\u0111\u0117\u011e\u0121\u0125"+
		"\u012f\u0133\u013f\u0149\u014e\u0150\u0159\u015b\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}