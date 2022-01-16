// Generated from /user/8/.base/kloua/home/Projet_GL/src/main/antlr4/fr/ensimag/deca/syntax/DecaLexer.g4 by ANTLR 4.8
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DecaLexer extends AbstractDecaLexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SEPARATEUR=1, INCLUDE=2, ASM=3, CLASS=4, ELSEIF=5, ELSE=6, IF=7, NEW=8, 
		NULL=9, READINT=10, READFLOAT=11, PROTECTED=12, RETURN=13, THIS=14, INSTANCEOF=15, 
		EXTENDS=16, PRINTLN=17, PRINTLNX=18, PRINT=19, PRINTX=20, FALSE=21, TRUE=22, 
		WHILE=23, IDENT=24, CPARENT=25, OPARENT=26, SEMI=27, OBRACE=28, CBRACE=29, 
		COMMA=30, EQUALS=31, AND=32, DOT=33, EXCLAM=34, OR=35, NEQ=36, EQEQ=37, 
		INT=38, PLUS=39, MINUS=40, GEQ=41, LEQ=42, GT=43, LT=44, TIMES=45, SLASH=46, 
		PERCENT=47, FLOAT=48, STRING=49, MULTI_LINE_STRING=50, WS=51, DEFAULT=52;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"COMMENT", "ESPACE", "TAB", "EOL", "SEPARATEUR", "LETTER", "DIGIT", "NOF", 
			"INCLUDE", "ASM", "CLASS", "ELSEIF", "ELSE", "IF", "NEW", "NULL", "READINT", 
			"READFLOAT", "PROTECTED", "RETURN", "THIS", "INSTANCEOF", "EXTENDS", 
			"PRINTLN", "PRINTLNX", "PRINT", "PRINTX", "FALSE", "TRUE", "WHILE", "IDENT", 
			"CPARENT", "OPARENT", "SEMI", "OBRACE", "CBRACE", "COMMA", "EQUALS", 
			"AND", "DOT", "EXCLAM", "OR", "NEQ", "EQEQ", "POSITIVE_DIGIT", "INT", 
			"PLUS", "MINUS", "GEQ", "LEQ", "GT", "LT", "TIMES", "SLASH", "PERCENT", 
			"STRING_CAR", "NUM", "SIGN", "EXP", "DEC", "FLOATDEC", "DIGITHEX", "NUMHEX", 
			"FLOATHEX", "FLOAT", "STRING", "MULTI_LINE_STRING", "WS", "DEFAULT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'asm'", "'class'", "'elseif'", "'else'", "'if'", "'new'", 
			"'null'", "'readInt'", "'readFloat'", "'protected'", "'return'", "'this'", 
			"'instanceof'", "'extends'", "'println'", "'printlnx'", "'print'", "'printx'", 
			"'false'", "'true'", "'while'", null, "')'", "'('", "';'", "'{'", "'}'", 
			"','", "'='", "'&&'", "'.'", "'!'", "'||'", "'!='", "'=='", null, "'+'", 
			"'-'", "'>='", "'<='", "'>'", "'<'", "'*'", "'/'", "'%'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SEPARATEUR", "INCLUDE", "ASM", "CLASS", "ELSEIF", "ELSE", "IF", 
			"NEW", "NULL", "READINT", "READFLOAT", "PROTECTED", "RETURN", "THIS", 
			"INSTANCEOF", "EXTENDS", "PRINTLN", "PRINTLNX", "PRINT", "PRINTX", "FALSE", 
			"TRUE", "WHILE", "IDENT", "CPARENT", "OPARENT", "SEMI", "OBRACE", "CBRACE", 
			"COMMA", "EQUALS", "AND", "DOT", "EXCLAM", "OR", "NEQ", "EQEQ", "INT", 
			"PLUS", "MINUS", "GEQ", "LEQ", "GT", "LT", "TIMES", "SLASH", "PERCENT", 
			"FLOAT", "STRING", "MULTI_LINE_STRING", "WS", "DEFAULT"
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




	public DecaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DecaLexer.g4"; }

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

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 4:
			SEPARATEUR_action((RuleContext)_localctx, actionIndex);
			break;
		case 8:
			INCLUDE_action((RuleContext)_localctx, actionIndex);
			break;
		case 67:
			WS_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void SEPARATEUR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 skip(); 
			break;
		}
	}
	private void INCLUDE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:

			   doInclude(getText());

			break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:

			              skip(); // avoid producing a token
			          
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\66\u0202\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\3\2\3\2\3\2\3"+
		"\2\7\2\u0092\n\2\f\2\16\2\u0095\13\2\3\2\5\2\u0098\n\2\3\2\3\2\3\2\3\2"+
		"\7\2\u009e\n\2\f\2\16\2\u00a1\13\2\3\2\3\2\5\2\u00a5\n\2\3\3\3\3\3\4\3"+
		"\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\5\6\u00b2\n\6\3\6\3\6\3\7\3\7\3\b\3\b\3"+
		"\t\3\t\3\t\6\t\u00bd\n\t\r\t\16\t\u00be\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\7\n\u00cb\n\n\f\n\16\n\u00ce\13\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3"+
		"\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3"+
		"\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \5 \u0163\n"+
		" \3 \3 \3 \7 \u0168\n \f \16 \u016b\13 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3"+
		"%\3&\3&\3\'\3\'\3(\3(\3(\3)\3)\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3-\3.\3."+
		"\3/\3/\3/\7/\u0190\n/\f/\16/\u0193\13/\5/\u0195\n/\3\60\3\60\3\61\3\61"+
		"\3\62\3\62\3\62\3\63\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67"+
		"\38\38\39\39\3:\6:\u01ae\n:\r:\16:\u01af\3;\3;\5;\u01b4\n;\3<\3<\3<\3"+
		"<\3=\3=\3=\3=\3>\3>\3>\3>\5>\u01c2\n>\3>\3>\5>\u01c6\n>\3?\3?\3@\6@\u01cb"+
		"\n@\r@\16@\u01cc\3A\3A\3A\3A\5A\u01d3\nA\3A\3A\3A\3A\3A\3A\3A\3A\5A\u01dd"+
		"\nA\3B\3B\5B\u01e1\nB\3C\3C\3C\3C\3C\3C\7C\u01e9\nC\fC\16C\u01ec\13C\3"+
		"C\3C\3D\3D\3D\3D\3D\3D\3D\7D\u01f7\nD\fD\16D\u01fa\13D\3D\3D\3E\3E\3E"+
		"\3F\3F\4\u0093\u009f\2G\3\2\5\2\7\2\t\2\13\3\r\2\17\2\21\2\23\4\25\5\27"+
		"\6\31\7\33\b\35\t\37\n!\13#\f%\r\'\16)\17+\20-\21/\22\61\23\63\24\65\25"+
		"\67\269\27;\30=\31?\32A\33C\34E\35G\36I\37K M!O\"Q#S$U%W&Y\'[\2](_)a*"+
		"c+e,g-i.k/m\60o\61q\2s\2u\2w\2y\2{\2}\2\177\2\u0081\2\u0083\62\u0085\63"+
		"\u0087\64\u0089\65\u008b\66\3\2\f\3\3\f\f\4\2C\\c|\4\2&&aa\5\2\f\f$$^"+
		"^\4\2--//\4\2GGgg\4\2HHhh\5\2\62;CHch\4\2RRrr\5\2\13\f\17\17\"\"\2\u0210"+
		"\2\13\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2"+
		"\2\2\2Y\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2"+
		"\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2\u0083\3\2\2"+
		"\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\3\u00a4"+
		"\3\2\2\2\5\u00a6\3\2\2\2\7\u00a8\3\2\2\2\t\u00aa\3\2\2\2\13\u00b1\3\2"+
		"\2\2\r\u00b5\3\2\2\2\17\u00b7\3\2\2\2\21\u00bc\3\2\2\2\23\u00c0\3\2\2"+
		"\2\25\u00d4\3\2\2\2\27\u00d8\3\2\2\2\31\u00de\3\2\2\2\33\u00e5\3\2\2\2"+
		"\35\u00ea\3\2\2\2\37\u00ed\3\2\2\2!\u00f1\3\2\2\2#\u00f6\3\2\2\2%\u00fe"+
		"\3\2\2\2\'\u0108\3\2\2\2)\u0112\3\2\2\2+\u0119\3\2\2\2-\u011e\3\2\2\2"+
		"/\u0129\3\2\2\2\61\u0131\3\2\2\2\63\u0139\3\2\2\2\65\u0142\3\2\2\2\67"+
		"\u0148\3\2\2\29\u014f\3\2\2\2;\u0155\3\2\2\2=\u015a\3\2\2\2?\u0162\3\2"+
		"\2\2A\u016c\3\2\2\2C\u016e\3\2\2\2E\u0170\3\2\2\2G\u0172\3\2\2\2I\u0174"+
		"\3\2\2\2K\u0176\3\2\2\2M\u0178\3\2\2\2O\u017a\3\2\2\2Q\u017d\3\2\2\2S"+
		"\u017f\3\2\2\2U\u0181\3\2\2\2W\u0184\3\2\2\2Y\u0187\3\2\2\2[\u018a\3\2"+
		"\2\2]\u0194\3\2\2\2_\u0196\3\2\2\2a\u0198\3\2\2\2c\u019a\3\2\2\2e\u019d"+
		"\3\2\2\2g\u01a0\3\2\2\2i\u01a2\3\2\2\2k\u01a4\3\2\2\2m\u01a6\3\2\2\2o"+
		"\u01a8\3\2\2\2q\u01aa\3\2\2\2s\u01ad\3\2\2\2u\u01b3\3\2\2\2w\u01b5\3\2"+
		"\2\2y\u01b9\3\2\2\2{\u01c1\3\2\2\2}\u01c7\3\2\2\2\177\u01ca\3\2\2\2\u0081"+
		"\u01d2\3\2\2\2\u0083\u01e0\3\2\2\2\u0085\u01e2\3\2\2\2\u0087\u01ef\3\2"+
		"\2\2\u0089\u01fd\3\2\2\2\u008b\u0200\3\2\2\2\u008d\u008e\7\61\2\2\u008e"+
		"\u008f\7\61\2\2\u008f\u0093\3\2\2\2\u0090\u0092\13\2\2\2\u0091\u0090\3"+
		"\2\2\2\u0092\u0095\3\2\2\2\u0093\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094"+
		"\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0098\t\2\2\2\u0097\u0096\3\2"+
		"\2\2\u0098\u00a5\3\2\2\2\u0099\u009a\7\61\2\2\u009a\u009b\7,\2\2\u009b"+
		"\u009f\3\2\2\2\u009c\u009e\13\2\2\2\u009d\u009c\3\2\2\2\u009e\u00a1\3"+
		"\2\2\2\u009f\u00a0\3\2\2\2\u009f\u009d\3\2\2\2\u00a0\u00a2\3\2\2\2\u00a1"+
		"\u009f\3\2\2\2\u00a2\u00a3\7,\2\2\u00a3\u00a5\7\61\2\2\u00a4\u008d\3\2"+
		"\2\2\u00a4\u0099\3\2\2\2\u00a5\4\3\2\2\2\u00a6\u00a7\7\"\2\2\u00a7\6\3"+
		"\2\2\2\u00a8\u00a9\7\13\2\2\u00a9\b\3\2\2\2\u00aa\u00ab\7\f\2\2\u00ab"+
		"\n\3\2\2\2\u00ac\u00b2\5\3\2\2\u00ad\u00b2\5\5\3\2\u00ae\u00b2\5\t\5\2"+
		"\u00af\u00b2\5\7\4\2\u00b0\u00b2\7\17\2\2\u00b1\u00ac\3\2\2\2\u00b1\u00ad"+
		"\3\2\2\2\u00b1\u00ae\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b0\3\2\2\2\u00b2"+
		"\u00b3\3\2\2\2\u00b3\u00b4\b\6\2\2\u00b4\f\3\2\2\2\u00b5\u00b6\t\3\2\2"+
		"\u00b6\16\3\2\2\2\u00b7\u00b8\4\62;\2\u00b8\20\3\2\2\2\u00b9\u00bd\5\r"+
		"\7\2\u00ba\u00bd\7\60\2\2\u00bb\u00bd\5\17\b\2\u00bc\u00b9\3\2\2\2\u00bc"+
		"\u00ba\3\2\2\2\u00bc\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bc\3\2"+
		"\2\2\u00be\u00bf\3\2\2\2\u00bf\22\3\2\2\2\u00c0\u00c1\7%\2\2\u00c1\u00c2"+
		"\7k\2\2\u00c2\u00c3\7p\2\2\u00c3\u00c4\7e\2\2\u00c4\u00c5\7n\2\2\u00c5"+
		"\u00c6\7w\2\2\u00c6\u00c7\7f\2\2\u00c7\u00c8\7g\2\2\u00c8\u00cc\3\2\2"+
		"\2\u00c9\u00cb\5\5\3\2\u00ca\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca"+
		"\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00cf\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf"+
		"\u00d0\7$\2\2\u00d0\u00d1\5\21\t\2\u00d1\u00d2\7$\2\2\u00d2\u00d3\b\n"+
		"\3\2\u00d3\24\3\2\2\2\u00d4\u00d5\7c\2\2\u00d5\u00d6\7u\2\2\u00d6\u00d7"+
		"\7o\2\2\u00d7\26\3\2\2\2\u00d8\u00d9\7e\2\2\u00d9\u00da\7n\2\2\u00da\u00db"+
		"\7c\2\2\u00db\u00dc\7u\2\2\u00dc\u00dd\7u\2\2\u00dd\30\3\2\2\2\u00de\u00df"+
		"\7g\2\2\u00df\u00e0\7n\2\2\u00e0\u00e1\7u\2\2\u00e1\u00e2\7g\2\2\u00e2"+
		"\u00e3\7k\2\2\u00e3\u00e4\7h\2\2\u00e4\32\3\2\2\2\u00e5\u00e6\7g\2\2\u00e6"+
		"\u00e7\7n\2\2\u00e7\u00e8\7u\2\2\u00e8\u00e9\7g\2\2\u00e9\34\3\2\2\2\u00ea"+
		"\u00eb\7k\2\2\u00eb\u00ec\7h\2\2\u00ec\36\3\2\2\2\u00ed\u00ee\7p\2\2\u00ee"+
		"\u00ef\7g\2\2\u00ef\u00f0\7y\2\2\u00f0 \3\2\2\2\u00f1\u00f2\7p\2\2\u00f2"+
		"\u00f3\7w\2\2\u00f3\u00f4\7n\2\2\u00f4\u00f5\7n\2\2\u00f5\"\3\2\2\2\u00f6"+
		"\u00f7\7t\2\2\u00f7\u00f8\7g\2\2\u00f8\u00f9\7c\2\2\u00f9\u00fa\7f\2\2"+
		"\u00fa\u00fb\7K\2\2\u00fb\u00fc\7p\2\2\u00fc\u00fd\7v\2\2\u00fd$\3\2\2"+
		"\2\u00fe\u00ff\7t\2\2\u00ff\u0100\7g\2\2\u0100\u0101\7c\2\2\u0101\u0102"+
		"\7f\2\2\u0102\u0103\7H\2\2\u0103\u0104\7n\2\2\u0104\u0105\7q\2\2\u0105"+
		"\u0106\7c\2\2\u0106\u0107\7v\2\2\u0107&\3\2\2\2\u0108\u0109\7r\2\2\u0109"+
		"\u010a\7t\2\2\u010a\u010b\7q\2\2\u010b\u010c\7v\2\2\u010c\u010d\7g\2\2"+
		"\u010d\u010e\7e\2\2\u010e\u010f\7v\2\2\u010f\u0110\7g\2\2\u0110\u0111"+
		"\7f\2\2\u0111(\3\2\2\2\u0112\u0113\7t\2\2\u0113\u0114\7g\2\2\u0114\u0115"+
		"\7v\2\2\u0115\u0116\7w\2\2\u0116\u0117\7t\2\2\u0117\u0118\7p\2\2\u0118"+
		"*\3\2\2\2\u0119\u011a\7v\2\2\u011a\u011b\7j\2\2\u011b\u011c\7k\2\2\u011c"+
		"\u011d\7u\2\2\u011d,\3\2\2\2\u011e\u011f\7k\2\2\u011f\u0120\7p\2\2\u0120"+
		"\u0121\7u\2\2\u0121\u0122\7v\2\2\u0122\u0123\7c\2\2\u0123\u0124\7p\2\2"+
		"\u0124\u0125\7e\2\2\u0125\u0126\7g\2\2\u0126\u0127\7q\2\2\u0127\u0128"+
		"\7h\2\2\u0128.\3\2\2\2\u0129\u012a\7g\2\2\u012a\u012b\7z\2\2\u012b\u012c"+
		"\7v\2\2\u012c\u012d\7g\2\2\u012d\u012e\7p\2\2\u012e\u012f\7f\2\2\u012f"+
		"\u0130\7u\2\2\u0130\60\3\2\2\2\u0131\u0132\7r\2\2\u0132\u0133\7t\2\2\u0133"+
		"\u0134\7k\2\2\u0134\u0135\7p\2\2\u0135\u0136\7v\2\2\u0136\u0137\7n\2\2"+
		"\u0137\u0138\7p\2\2\u0138\62\3\2\2\2\u0139\u013a\7r\2\2\u013a\u013b\7"+
		"t\2\2\u013b\u013c\7k\2\2\u013c\u013d\7p\2\2\u013d\u013e\7v\2\2\u013e\u013f"+
		"\7n\2\2\u013f\u0140\7p\2\2\u0140\u0141\7z\2\2\u0141\64\3\2\2\2\u0142\u0143"+
		"\7r\2\2\u0143\u0144\7t\2\2\u0144\u0145\7k\2\2\u0145\u0146\7p\2\2\u0146"+
		"\u0147\7v\2\2\u0147\66\3\2\2\2\u0148\u0149\7r\2\2\u0149\u014a\7t\2\2\u014a"+
		"\u014b\7k\2\2\u014b\u014c\7p\2\2\u014c\u014d\7v\2\2\u014d\u014e\7z\2\2"+
		"\u014e8\3\2\2\2\u014f\u0150\7h\2\2\u0150\u0151\7c\2\2\u0151\u0152\7n\2"+
		"\2\u0152\u0153\7u\2\2\u0153\u0154\7g\2\2\u0154:\3\2\2\2\u0155\u0156\7"+
		"v\2\2\u0156\u0157\7t\2\2\u0157\u0158\7w\2\2\u0158\u0159\7g\2\2\u0159<"+
		"\3\2\2\2\u015a\u015b\7y\2\2\u015b\u015c\7j\2\2\u015c\u015d\7k\2\2\u015d"+
		"\u015e\7n\2\2\u015e\u015f\7g\2\2\u015f>\3\2\2\2\u0160\u0163\5\r\7\2\u0161"+
		"\u0163\t\4\2\2\u0162\u0160\3\2\2\2\u0162\u0161\3\2\2\2\u0163\u0169\3\2"+
		"\2\2\u0164\u0168\5\r\7\2\u0165\u0168\5\17\b\2\u0166\u0168\t\4\2\2\u0167"+
		"\u0164\3\2\2\2\u0167\u0165\3\2\2\2\u0167\u0166\3\2\2\2\u0168\u016b\3\2"+
		"\2\2\u0169\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a@\3\2\2\2\u016b\u0169"+
		"\3\2\2\2\u016c\u016d\7+\2\2\u016dB\3\2\2\2\u016e\u016f\7*\2\2\u016fD\3"+
		"\2\2\2\u0170\u0171\7=\2\2\u0171F\3\2\2\2\u0172\u0173\7}\2\2\u0173H\3\2"+
		"\2\2\u0174\u0175\7\177\2\2\u0175J\3\2\2\2\u0176\u0177\7.\2\2\u0177L\3"+
		"\2\2\2\u0178\u0179\7?\2\2\u0179N\3\2\2\2\u017a\u017b\7(\2\2\u017b\u017c"+
		"\7(\2\2\u017cP\3\2\2\2\u017d\u017e\7\60\2\2\u017eR\3\2\2\2\u017f\u0180"+
		"\7#\2\2\u0180T\3\2\2\2\u0181\u0182\7~\2\2\u0182\u0183\7~\2\2\u0183V\3"+
		"\2\2\2\u0184\u0185\7#\2\2\u0185\u0186\7?\2\2\u0186X\3\2\2\2\u0187\u0188"+
		"\7?\2\2\u0188\u0189\7?\2\2\u0189Z\3\2\2\2\u018a\u018b\4\63;\2\u018b\\"+
		"\3\2\2\2\u018c\u0195\7\62\2\2\u018d\u0191\5[.\2\u018e\u0190\5\17\b\2\u018f"+
		"\u018e\3\2\2\2\u0190\u0193\3\2\2\2\u0191\u018f\3\2\2\2\u0191\u0192\3\2"+
		"\2\2\u0192\u0195\3\2\2\2\u0193\u0191\3\2\2\2\u0194\u018c\3\2\2\2\u0194"+
		"\u018d\3\2\2\2\u0195^\3\2\2\2\u0196\u0197\7-\2\2\u0197`\3\2\2\2\u0198"+
		"\u0199\7/\2\2\u0199b\3\2\2\2\u019a\u019b\7@\2\2\u019b\u019c\7?\2\2\u019c"+
		"d\3\2\2\2\u019d\u019e\7>\2\2\u019e\u019f\7?\2\2\u019ff\3\2\2\2\u01a0\u01a1"+
		"\7@\2\2\u01a1h\3\2\2\2\u01a2\u01a3\7>\2\2\u01a3j\3\2\2\2\u01a4\u01a5\7"+
		",\2\2\u01a5l\3\2\2\2\u01a6\u01a7\7\61\2\2\u01a7n\3\2\2\2\u01a8\u01a9\7"+
		"\'\2\2\u01a9p\3\2\2\2\u01aa\u01ab\n\5\2\2\u01abr\3\2\2\2\u01ac\u01ae\5"+
		"\17\b\2\u01ad\u01ac\3\2\2\2\u01ae\u01af\3\2\2\2\u01af\u01ad\3\2\2\2\u01af"+
		"\u01b0\3\2\2\2\u01b0t\3\2\2\2\u01b1\u01b4\t\6\2\2\u01b2\u01b4\3\2\2\2"+
		"\u01b3\u01b1\3\2\2\2\u01b3\u01b2\3\2\2\2\u01b4v\3\2\2\2\u01b5\u01b6\t"+
		"\7\2\2\u01b6\u01b7\5u;\2\u01b7\u01b8\5s:\2\u01b8x\3\2\2\2\u01b9\u01ba"+
		"\5s:\2\u01ba\u01bb\7\60\2\2\u01bb\u01bc\5s:\2\u01bcz\3\2\2\2\u01bd\u01c2"+
		"\5y=\2\u01be\u01bf\5y=\2\u01bf\u01c0\5w<\2\u01c0\u01c2\3\2\2\2\u01c1\u01bd"+
		"\3\2\2\2\u01c1\u01be\3\2\2\2\u01c2\u01c5\3\2\2\2\u01c3\u01c6\t\b\2\2\u01c4"+
		"\u01c6\3\2\2\2\u01c5\u01c3\3\2\2\2\u01c5\u01c4\3\2\2\2\u01c6|\3\2\2\2"+
		"\u01c7\u01c8\t\t\2\2\u01c8~\3\2\2\2\u01c9\u01cb\5}?\2\u01ca\u01c9\3\2"+
		"\2\2\u01cb\u01cc\3\2\2\2\u01cc\u01ca\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cd"+
		"\u0080\3\2\2\2\u01ce\u01cf\7\62\2\2\u01cf\u01d3\7z\2\2\u01d0\u01d1\7\62"+
		"\2\2\u01d1\u01d3\7Z\2\2\u01d2\u01ce\3\2\2\2\u01d2\u01d0\3\2\2\2\u01d3"+
		"\u01d4\3\2\2\2\u01d4\u01d5\5\177@\2\u01d5\u01d6\7\60\2\2\u01d6\u01d7\5"+
		"\177@\2\u01d7\u01d8\t\n\2\2\u01d8\u01d9\5u;\2\u01d9\u01dc\5s:\2\u01da"+
		"\u01dd\t\b\2\2\u01db\u01dd\3\2\2\2\u01dc\u01da\3\2\2\2\u01dc\u01db\3\2"+
		"\2\2\u01dd\u0082\3\2\2\2\u01de\u01e1\5{>\2\u01df\u01e1\5\u0081A\2\u01e0"+
		"\u01de\3\2\2\2\u01e0\u01df\3\2\2\2\u01e1\u0084\3\2\2\2\u01e2\u01ea\7$"+
		"\2\2\u01e3\u01e9\5q9\2\u01e4\u01e5\7^\2\2\u01e5\u01e9\7$\2\2\u01e6\u01e7"+
		"\7^\2\2\u01e7\u01e9\7^\2\2\u01e8\u01e3\3\2\2\2\u01e8\u01e4\3\2\2\2\u01e8"+
		"\u01e6\3\2\2\2\u01e9\u01ec\3\2\2\2\u01ea\u01e8\3\2\2\2\u01ea\u01eb\3\2"+
		"\2\2\u01eb\u01ed\3\2\2\2\u01ec\u01ea\3\2\2\2\u01ed\u01ee\7$\2\2\u01ee"+
		"\u0086\3\2\2\2\u01ef\u01f8\7$\2\2\u01f0\u01f7\5q9\2\u01f1\u01f7\7\f\2"+
		"\2\u01f2\u01f3\7^\2\2\u01f3\u01f7\7$\2\2\u01f4\u01f5\7^\2\2\u01f5\u01f7"+
		"\7^\2\2\u01f6\u01f0\3\2\2\2\u01f6\u01f1\3\2\2\2\u01f6\u01f2\3\2\2\2\u01f6"+
		"\u01f4\3\2\2\2\u01f7\u01fa\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f8\u01f9\3\2"+
		"\2\2\u01f9\u01fb\3\2\2\2\u01fa\u01f8\3\2\2\2\u01fb\u01fc\7$\2\2\u01fc"+
		"\u0088\3\2\2\2\u01fd\u01fe\t\13\2\2\u01fe\u01ff\bE\4\2\u01ff\u008a\3\2"+
		"\2\2\u0200\u0201\13\2\2\2\u0201\u008c\3\2\2\2\34\2\u0093\u0097\u009f\u00a4"+
		"\u00b1\u00bc\u00be\u00cc\u0162\u0167\u0169\u0191\u0194\u01af\u01b3\u01c1"+
		"\u01c5\u01cc\u01d2\u01dc\u01e0\u01e8\u01ea\u01f6\u01f8\5\3\6\2\3\n\3\3"+
		"E\4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}