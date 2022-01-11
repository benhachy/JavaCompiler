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
		ASM=1, CLASS=2, ELSE=3, IF=4, NEW=5, NULL=6, READINT=7, READFLOAT=8, PROTECTED=9, 
		RETURN=10, THIS=11, INSTANCEOF=12, EXTENDS=13, PRINTLN=14, PRINTLNX=15, 
		PRINT=16, PRINTX=17, FALSE=18, TRUE=19, WHILE=20, IDENT=21, COMMENT=22, 
		CPARENT=23, OPARENT=24, SEMI=25, OBRACE=26, CBRACE=27, COMMA=28, EQUALS=29, 
		AND=30, DOT=31, EXCLAM=32, OR=33, NEQ=34, EQEQ=35, INT=36, PLUS=37, MINUS=38, 
		GEQ=39, LEQ=40, GT=41, LT=42, TIMES=43, SLASH=44, PERCENT=45, ESPACE=46, 
		TAB=47, EOL=48, STRING_CAR=49, FLOAT=50, STRING=51, MULTI_LINE_STRING=52, 
		WS=53;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"LETTER", "DIGIT", "ASM", "CLASS", "ELSE", "IF", "NEW", "NULL", "READINT", 
			"READFLOAT", "PROTECTED", "RETURN", "THIS", "INSTANCEOF", "EXTENDS", 
			"PRINTLN", "PRINTLNX", "PRINT", "PRINTX", "FALSE", "TRUE", "WHILE", "IDENT", 
			"COMMENT", "CPARENT", "OPARENT", "SEMI", "OBRACE", "CBRACE", "COMMA", 
			"EQUALS", "AND", "DOT", "EXCLAM", "OR", "NEQ", "EQEQ", "POSITIVE_DIGIT", 
			"INT", "PLUS", "MINUS", "GEQ", "LEQ", "GT", "LT", "TIMES", "SLASH", "PERCENT", 
			"ESPACE", "TAB", "EOL", "STRING_CAR", "NUM", "SIGN", "EXP", "DEC", "FLOATDEC", 
			"DIGITHEX", "NUMHEX", "FLOATHEX", "FLOAT", "STRING", "MULTI_LINE_STRING", 
			"WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'asm'", "'class'", "'else'", "'if'", "'new'", "'null'", "'readInt'", 
			"'readFloat'", "'protected'", "'return'", "'this'", "'instanceof'", "'extends'", 
			"'println'", "'printlnx'", "'print'", "'printx'", "'false'", "'true'", 
			"'while'", null, null, "')'", "'('", "';'", "'{'", "'}'", "','", "'='", 
			"'&&'", "'.'", "'!'", "'||'", "'!='", "'=='", null, "'+'", "'-'", "'>='", 
			"'<='", "'>'", "'<'", "'*'", "'/'", "'%'", "' '", "'\t'", "'\n'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ASM", "CLASS", "ELSE", "IF", "NEW", "NULL", "READINT", "READFLOAT", 
			"PROTECTED", "RETURN", "THIS", "INSTANCEOF", "EXTENDS", "PRINTLN", "PRINTLNX", 
			"PRINT", "PRINTX", "FALSE", "TRUE", "WHILE", "IDENT", "COMMENT", "CPARENT", 
			"OPARENT", "SEMI", "OBRACE", "CBRACE", "COMMA", "EQUALS", "AND", "DOT", 
			"EXCLAM", "OR", "NEQ", "EQEQ", "INT", "PLUS", "MINUS", "GEQ", "LEQ", 
			"GT", "LT", "TIMES", "SLASH", "PERCENT", "ESPACE", "TAB", "EOL", "STRING_CAR", 
			"FLOAT", "STRING", "MULTI_LINE_STRING", "WS"
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
		case 23:
			COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 48:
			ESPACE_action((RuleContext)_localctx, actionIndex);
			break;
		case 49:
			TAB_action((RuleContext)_localctx, actionIndex);
			break;
		case 50:
			EOL_action((RuleContext)_localctx, actionIndex);
			break;
		case 63:
			WS_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

			   skip();

			break;
		}
	}
	private void ESPACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			skip();
			break;
		}
	}
	private void TAB_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			skip();
			break;
		}
	}
	private void EOL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			skip();
			break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:

			              skip(); // avoid producing a token
			          
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\67\u01ca\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\4>\t>\4?\t?\4@\t@\4A\tA\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\5\30\u010f\n\30\3\30\3\30\3\30"+
		"\7\30\u0114\n\30\f\30\16\30\u0117\13\30\3\31\3\31\3\31\3\31\7\31\u011d"+
		"\n\31\f\31\16\31\u0120\13\31\3\31\3\31\3\31\3\31\3\31\7\31\u0127\n\31"+
		"\f\31\16\31\u012a\13\31\3\31\3\31\5\31\u012e\n\31\3\31\3\31\3\32\3\32"+
		"\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3\""+
		"\3\"\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3(\3(\3(\7(\u0155\n(\f("+
		"\16(\u0158\13(\5(\u015a\n(\3)\3)\3*\3*\3+\3+\3+\3,\3,\3,\3-\3-\3.\3.\3"+
		"/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\62\3\63\3\63\3\63\3\64\3\64\3\64"+
		"\3\65\3\65\3\66\6\66\u017c\n\66\r\66\16\66\u017d\3\67\3\67\38\38\38\3"+
		"8\39\39\39\39\3:\3:\3:\3:\5:\u018e\n:\3:\3:\5:\u0192\n:\3;\3;\3<\6<\u0197"+
		"\n<\r<\16<\u0198\3=\3=\3=\3=\5=\u019f\n=\3=\3=\3=\3=\3=\3=\3=\3=\3>\3"+
		">\5>\u01ab\n>\3?\3?\3?\3?\3?\3?\7?\u01b3\n?\f?\16?\u01b6\13?\3?\3?\3@"+
		"\3@\3@\3@\3@\3@\3@\7@\u01c1\n@\f@\16@\u01c4\13@\3@\3@\3A\3A\3A\4\u011e"+
		"\u0128\2B\3\2\5\2\7\3\t\4\13\5\r\6\17\7\21\b\23\t\25\n\27\13\31\f\33\r"+
		"\35\16\37\17!\20#\21%\22\'\23)\24+\25-\26/\27\61\30\63\31\65\32\67\33"+
		"9\34;\35=\36?\37A C!E\"G#I$K%M\2O&Q\'S(U)W*Y+[,]-_.a/c\60e\61g\62i\63"+
		"k\2m\2o\2q\2s\2u\2w\2y\2{\64}\65\177\66\u0081\67\3\2\13\4\2C\\c|\4\2&"+
		"&aa\5\2\f\f$$^^\4\2--//\4\2GGgg\4\2HHhh\5\2\62;CHch\4\2RRrr\5\2\13\f\17"+
		"\17\"\"\2\u01d4\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3"+
		"\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2"+
		"=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3"+
		"\2\2\2\2K\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2"+
		"\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2"+
		"e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2"+
		"\u0081\3\2\2\2\3\u0083\3\2\2\2\5\u0085\3\2\2\2\7\u0087\3\2\2\2\t\u008b"+
		"\3\2\2\2\13\u0091\3\2\2\2\r\u0096\3\2\2\2\17\u0099\3\2\2\2\21\u009d\3"+
		"\2\2\2\23\u00a2\3\2\2\2\25\u00aa\3\2\2\2\27\u00b4\3\2\2\2\31\u00be\3\2"+
		"\2\2\33\u00c5\3\2\2\2\35\u00ca\3\2\2\2\37\u00d5\3\2\2\2!\u00dd\3\2\2\2"+
		"#\u00e5\3\2\2\2%\u00ee\3\2\2\2\'\u00f4\3\2\2\2)\u00fb\3\2\2\2+\u0101\3"+
		"\2\2\2-\u0106\3\2\2\2/\u010e\3\2\2\2\61\u012d\3\2\2\2\63\u0131\3\2\2\2"+
		"\65\u0133\3\2\2\2\67\u0135\3\2\2\29\u0137\3\2\2\2;\u0139\3\2\2\2=\u013b"+
		"\3\2\2\2?\u013d\3\2\2\2A\u013f\3\2\2\2C\u0142\3\2\2\2E\u0144\3\2\2\2G"+
		"\u0146\3\2\2\2I\u0149\3\2\2\2K\u014c\3\2\2\2M\u014f\3\2\2\2O\u0159\3\2"+
		"\2\2Q\u015b\3\2\2\2S\u015d\3\2\2\2U\u015f\3\2\2\2W\u0162\3\2\2\2Y\u0165"+
		"\3\2\2\2[\u0167\3\2\2\2]\u0169\3\2\2\2_\u016b\3\2\2\2a\u016d\3\2\2\2c"+
		"\u016f\3\2\2\2e\u0172\3\2\2\2g\u0175\3\2\2\2i\u0178\3\2\2\2k\u017b\3\2"+
		"\2\2m\u017f\3\2\2\2o\u0181\3\2\2\2q\u0185\3\2\2\2s\u018d\3\2\2\2u\u0193"+
		"\3\2\2\2w\u0196\3\2\2\2y\u019e\3\2\2\2{\u01aa\3\2\2\2}\u01ac\3\2\2\2\177"+
		"\u01b9\3\2\2\2\u0081\u01c7\3\2\2\2\u0083\u0084\t\2\2\2\u0084\4\3\2\2\2"+
		"\u0085\u0086\4\62;\2\u0086\6\3\2\2\2\u0087\u0088\7c\2\2\u0088\u0089\7"+
		"u\2\2\u0089\u008a\7o\2\2\u008a\b\3\2\2\2\u008b\u008c\7e\2\2\u008c\u008d"+
		"\7n\2\2\u008d\u008e\7c\2\2\u008e\u008f\7u\2\2\u008f\u0090\7u\2\2\u0090"+
		"\n\3\2\2\2\u0091\u0092\7g\2\2\u0092\u0093\7n\2\2\u0093\u0094\7u\2\2\u0094"+
		"\u0095\7g\2\2\u0095\f\3\2\2\2\u0096\u0097\7k\2\2\u0097\u0098\7h\2\2\u0098"+
		"\16\3\2\2\2\u0099\u009a\7p\2\2\u009a\u009b\7g\2\2\u009b\u009c\7y\2\2\u009c"+
		"\20\3\2\2\2\u009d\u009e\7p\2\2\u009e\u009f\7w\2\2\u009f\u00a0\7n\2\2\u00a0"+
		"\u00a1\7n\2\2\u00a1\22\3\2\2\2\u00a2\u00a3\7t\2\2\u00a3\u00a4\7g\2\2\u00a4"+
		"\u00a5\7c\2\2\u00a5\u00a6\7f\2\2\u00a6\u00a7\7K\2\2\u00a7\u00a8\7p\2\2"+
		"\u00a8\u00a9\7v\2\2\u00a9\24\3\2\2\2\u00aa\u00ab\7t\2\2\u00ab\u00ac\7"+
		"g\2\2\u00ac\u00ad\7c\2\2\u00ad\u00ae\7f\2\2\u00ae\u00af\7H\2\2\u00af\u00b0"+
		"\7n\2\2\u00b0\u00b1\7q\2\2\u00b1\u00b2\7c\2\2\u00b2\u00b3\7v\2\2\u00b3"+
		"\26\3\2\2\2\u00b4\u00b5\7r\2\2\u00b5\u00b6\7t\2\2\u00b6\u00b7\7q\2\2\u00b7"+
		"\u00b8\7v\2\2\u00b8\u00b9\7g\2\2\u00b9\u00ba\7e\2\2\u00ba\u00bb\7v\2\2"+
		"\u00bb\u00bc\7g\2\2\u00bc\u00bd\7f\2\2\u00bd\30\3\2\2\2\u00be\u00bf\7"+
		"t\2\2\u00bf\u00c0\7g\2\2\u00c0\u00c1\7v\2\2\u00c1\u00c2\7w\2\2\u00c2\u00c3"+
		"\7t\2\2\u00c3\u00c4\7p\2\2\u00c4\32\3\2\2\2\u00c5\u00c6\7v\2\2\u00c6\u00c7"+
		"\7j\2\2\u00c7\u00c8\7k\2\2\u00c8\u00c9\7u\2\2\u00c9\34\3\2\2\2\u00ca\u00cb"+
		"\7k\2\2\u00cb\u00cc\7p\2\2\u00cc\u00cd\7u\2\2\u00cd\u00ce\7v\2\2\u00ce"+
		"\u00cf\7c\2\2\u00cf\u00d0\7p\2\2\u00d0\u00d1\7e\2\2\u00d1\u00d2\7g\2\2"+
		"\u00d2\u00d3\7q\2\2\u00d3\u00d4\7h\2\2\u00d4\36\3\2\2\2\u00d5\u00d6\7"+
		"g\2\2\u00d6\u00d7\7z\2\2\u00d7\u00d8\7v\2\2\u00d8\u00d9\7g\2\2\u00d9\u00da"+
		"\7p\2\2\u00da\u00db\7f\2\2\u00db\u00dc\7u\2\2\u00dc \3\2\2\2\u00dd\u00de"+
		"\7r\2\2\u00de\u00df\7t\2\2\u00df\u00e0\7k\2\2\u00e0\u00e1\7p\2\2\u00e1"+
		"\u00e2\7v\2\2\u00e2\u00e3\7n\2\2\u00e3\u00e4\7p\2\2\u00e4\"\3\2\2\2\u00e5"+
		"\u00e6\7r\2\2\u00e6\u00e7\7t\2\2\u00e7\u00e8\7k\2\2\u00e8\u00e9\7p\2\2"+
		"\u00e9\u00ea\7v\2\2\u00ea\u00eb\7n\2\2\u00eb\u00ec\7p\2\2\u00ec\u00ed"+
		"\7z\2\2\u00ed$\3\2\2\2\u00ee\u00ef\7r\2\2\u00ef\u00f0\7t\2\2\u00f0\u00f1"+
		"\7k\2\2\u00f1\u00f2\7p\2\2\u00f2\u00f3\7v\2\2\u00f3&\3\2\2\2\u00f4\u00f5"+
		"\7r\2\2\u00f5\u00f6\7t\2\2\u00f6\u00f7\7k\2\2\u00f7\u00f8\7p\2\2\u00f8"+
		"\u00f9\7v\2\2\u00f9\u00fa\7z\2\2\u00fa(\3\2\2\2\u00fb\u00fc\7h\2\2\u00fc"+
		"\u00fd\7c\2\2\u00fd\u00fe\7n\2\2\u00fe\u00ff\7u\2\2\u00ff\u0100\7g\2\2"+
		"\u0100*\3\2\2\2\u0101\u0102\7v\2\2\u0102\u0103\7t\2\2\u0103\u0104\7w\2"+
		"\2\u0104\u0105\7g\2\2\u0105,\3\2\2\2\u0106\u0107\7y\2\2\u0107\u0108\7"+
		"j\2\2\u0108\u0109\7k\2\2\u0109\u010a\7n\2\2\u010a\u010b\7g\2\2\u010b."+
		"\3\2\2\2\u010c\u010f\5\3\2\2\u010d\u010f\t\3\2\2\u010e\u010c\3\2\2\2\u010e"+
		"\u010d\3\2\2\2\u010f\u0115\3\2\2\2\u0110\u0114\5\3\2\2\u0111\u0114\5\5"+
		"\3\2\u0112\u0114\t\3\2\2\u0113\u0110\3\2\2\2\u0113\u0111\3\2\2\2\u0113"+
		"\u0112\3\2\2\2\u0114\u0117\3\2\2\2\u0115\u0113\3\2\2\2\u0115\u0116\3\2"+
		"\2\2\u0116\60\3\2\2\2\u0117\u0115\3\2\2\2\u0118\u0119\7\61\2\2\u0119\u011a"+
		"\7\61\2\2\u011a\u011e\3\2\2\2\u011b\u011d\13\2\2\2\u011c\u011b\3\2\2\2"+
		"\u011d\u0120\3\2\2\2\u011e\u011f\3\2\2\2\u011e\u011c\3\2\2\2\u011f\u0121"+
		"\3\2\2\2\u0120\u011e\3\2\2\2\u0121\u012e\7\f\2\2\u0122\u0123\7\61\2\2"+
		"\u0123\u0124\7,\2\2\u0124\u0128\3\2\2\2\u0125\u0127\13\2\2\2\u0126\u0125"+
		"\3\2\2\2\u0127\u012a\3\2\2\2\u0128\u0129\3\2\2\2\u0128\u0126\3\2\2\2\u0129"+
		"\u012b\3\2\2\2\u012a\u0128\3\2\2\2\u012b\u012c\7,\2\2\u012c\u012e\7\61"+
		"\2\2\u012d\u0118\3\2\2\2\u012d\u0122\3\2\2\2\u012e\u012f\3\2\2\2\u012f"+
		"\u0130\b\31\2\2\u0130\62\3\2\2\2\u0131\u0132\7+\2\2\u0132\64\3\2\2\2\u0133"+
		"\u0134\7*\2\2\u0134\66\3\2\2\2\u0135\u0136\7=\2\2\u01368\3\2\2\2\u0137"+
		"\u0138\7}\2\2\u0138:\3\2\2\2\u0139\u013a\7\177\2\2\u013a<\3\2\2\2\u013b"+
		"\u013c\7.\2\2\u013c>\3\2\2\2\u013d\u013e\7?\2\2\u013e@\3\2\2\2\u013f\u0140"+
		"\7(\2\2\u0140\u0141\7(\2\2\u0141B\3\2\2\2\u0142\u0143\7\60\2\2\u0143D"+
		"\3\2\2\2\u0144\u0145\7#\2\2\u0145F\3\2\2\2\u0146\u0147\7~\2\2\u0147\u0148"+
		"\7~\2\2\u0148H\3\2\2\2\u0149\u014a\7#\2\2\u014a\u014b\7?\2\2\u014bJ\3"+
		"\2\2\2\u014c\u014d\7?\2\2\u014d\u014e\7?\2\2\u014eL\3\2\2\2\u014f\u0150"+
		"\4\63;\2\u0150N\3\2\2\2\u0151\u015a\7\62\2\2\u0152\u0156\5M\'\2\u0153"+
		"\u0155\5\5\3\2\u0154\u0153\3\2\2\2\u0155\u0158\3\2\2\2\u0156\u0154\3\2"+
		"\2\2\u0156\u0157\3\2\2\2\u0157\u015a\3\2\2\2\u0158\u0156\3\2\2\2\u0159"+
		"\u0151\3\2\2\2\u0159\u0152\3\2\2\2\u015aP\3\2\2\2\u015b\u015c\7-\2\2\u015c"+
		"R\3\2\2\2\u015d\u015e\7/\2\2\u015eT\3\2\2\2\u015f\u0160\7@\2\2\u0160\u0161"+
		"\7?\2\2\u0161V\3\2\2\2\u0162\u0163\7>\2\2\u0163\u0164\7?\2\2\u0164X\3"+
		"\2\2\2\u0165\u0166\7@\2\2\u0166Z\3\2\2\2\u0167\u0168\7>\2\2\u0168\\\3"+
		"\2\2\2\u0169\u016a\7,\2\2\u016a^\3\2\2\2\u016b\u016c\7\61\2\2\u016c`\3"+
		"\2\2\2\u016d\u016e\7\'\2\2\u016eb\3\2\2\2\u016f\u0170\7\"\2\2\u0170\u0171"+
		"\b\62\3\2\u0171d\3\2\2\2\u0172\u0173\7\13\2\2\u0173\u0174\b\63\4\2\u0174"+
		"f\3\2\2\2\u0175\u0176\7\f\2\2\u0176\u0177\b\64\5\2\u0177h\3\2\2\2\u0178"+
		"\u0179\n\4\2\2\u0179j\3\2\2\2\u017a\u017c\5\5\3\2\u017b\u017a\3\2\2\2"+
		"\u017c\u017d\3\2\2\2\u017d\u017b\3\2\2\2\u017d\u017e\3\2\2\2\u017el\3"+
		"\2\2\2\u017f\u0180\t\5\2\2\u0180n\3\2\2\2\u0181\u0182\t\6\2\2\u0182\u0183"+
		"\5m\67\2\u0183\u0184\5k\66\2\u0184p\3\2\2\2\u0185\u0186\5k\66\2\u0186"+
		"\u0187\7\60\2\2\u0187\u0188\5k\66\2\u0188r\3\2\2\2\u0189\u018e\5q9\2\u018a"+
		"\u018b\5q9\2\u018b\u018c\5o8\2\u018c\u018e\3\2\2\2\u018d\u0189\3\2\2\2"+
		"\u018d\u018a\3\2\2\2\u018e\u0191\3\2\2\2\u018f\u0192\t\7\2\2\u0190\u0192"+
		"\3\2\2\2\u0191\u018f\3\2\2\2\u0191\u0190\3\2\2\2\u0192t\3\2\2\2\u0193"+
		"\u0194\t\b\2\2\u0194v\3\2\2\2\u0195\u0197\5u;\2\u0196\u0195\3\2\2\2\u0197"+
		"\u0198\3\2\2\2\u0198\u0196\3\2\2\2\u0198\u0199\3\2\2\2\u0199x\3\2\2\2"+
		"\u019a\u019b\7\62\2\2\u019b\u019f\7z\2\2\u019c\u019d\7\62\2\2\u019d\u019f"+
		"\7Z\2\2\u019e\u019a\3\2\2\2\u019e\u019c\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0"+
		"\u01a1\5w<\2\u01a1\u01a2\7\60\2\2\u01a2\u01a3\5w<\2\u01a3\u01a4\t\t\2"+
		"\2\u01a4\u01a5\5m\67\2\u01a5\u01a6\5k\66\2\u01a6\u01a7\t\7\2\2\u01a7z"+
		"\3\2\2\2\u01a8\u01ab\5s:\2\u01a9\u01ab\5y=\2\u01aa\u01a8\3\2\2\2\u01aa"+
		"\u01a9\3\2\2\2\u01ab|\3\2\2\2\u01ac\u01b4\7$\2\2\u01ad\u01b3\5i\65\2\u01ae"+
		"\u01af\7^\2\2\u01af\u01b3\7$\2\2\u01b0\u01b1\7^\2\2\u01b1\u01b3\7^\2\2"+
		"\u01b2\u01ad\3\2\2\2\u01b2\u01ae\3\2\2\2\u01b2\u01b0\3\2\2\2\u01b3\u01b6"+
		"\3\2\2\2\u01b4\u01b2\3\2\2\2\u01b4\u01b5\3\2\2\2\u01b5\u01b7\3\2\2\2\u01b6"+
		"\u01b4\3\2\2\2\u01b7\u01b8\7$\2\2\u01b8~\3\2\2\2\u01b9\u01c2\7$\2\2\u01ba"+
		"\u01c1\5i\65\2\u01bb\u01c1\7\f\2\2\u01bc\u01bd\7^\2\2\u01bd\u01c1\7$\2"+
		"\2\u01be\u01bf\7^\2\2\u01bf\u01c1\7^\2\2\u01c0\u01ba\3\2\2\2\u01c0\u01bb"+
		"\3\2\2\2\u01c0\u01bc\3\2\2\2\u01c0\u01be\3\2\2\2\u01c1\u01c4\3\2\2\2\u01c2"+
		"\u01c0\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c5\3\2\2\2\u01c4\u01c2\3\2"+
		"\2\2\u01c5\u01c6\7$\2\2\u01c6\u0080\3\2\2\2\u01c7\u01c8\t\n\2\2\u01c8"+
		"\u01c9\bA\6\2\u01c9\u0082\3\2\2\2\25\2\u010e\u0113\u0115\u011e\u0128\u012d"+
		"\u0156\u0159\u017d\u018d\u0191\u0198\u019e\u01aa\u01b2\u01b4\u01c0\u01c2"+
		"\7\3\31\2\3\62\3\3\63\4\3\64\5\3A\6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}