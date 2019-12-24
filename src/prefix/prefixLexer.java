// Generated from prefix.g4 by ANTLR 4.7.2
package prefix;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class prefixLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, PASS=2, BOTH=3, PRINT=4, PLUS=5, MINUS=6, TIMES=7, DIV=8, NOT=9, 
		XOR=10, AND=11, OR=12, GE=13, GT=14, LE=15, LT=16, EQ=17, ASSIGN=18, WHITESPACE=19, 
		NUMBER=20, VAR=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "PASS", "BOTH", "PRINT", "PLUS", "MINUS", "TIMES", "DIV", "NOT", 
			"XOR", "AND", "OR", "GE", "GT", "LE", "LT", "EQ", "ASSIGN", "LETTER", 
			"START", "DIGIT", "SIGN", "WHITESPACE", "NUMBER", "VAR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'pass'", "'both'", "'print'", "'+'", "'-'", "'*'", "'/'", 
			"'!'", "'^'", "'&'", "'|'", "'>='", "'>'", "'<='", "'<'", "'=='", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "PASS", "BOTH", "PRINT", "PLUS", "MINUS", "TIMES", "DIV", 
			"NOT", "XOR", "AND", "OR", "GE", "GT", "LE", "LT", "EQ", "ASSIGN", "WHITESPACE", 
			"NUMBER", "VAR"
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


	public prefixLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "prefix.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u0084\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3"+
		"\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21"+
		"\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\5\25l\n\25\3\26\3\26\3\27"+
		"\3\27\5\27r\n\27\3\30\3\30\3\30\3\30\3\31\6\31y\n\31\r\31\16\31z\3\32"+
		"\3\32\3\32\7\32\u0080\n\32\f\32\16\32\u0083\13\32\2\2\33\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\2)\2+\2-\2/\25\61\26\63\27\3\2\5\4\2C\\c|\3\2\62;\5\2\13\f\17\17"+
		"\"\"\2\u0084\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\3\65\3"+
		"\2\2\2\58\3\2\2\2\7=\3\2\2\2\tB\3\2\2\2\13H\3\2\2\2\rJ\3\2\2\2\17L\3\2"+
		"\2\2\21N\3\2\2\2\23P\3\2\2\2\25R\3\2\2\2\27T\3\2\2\2\31V\3\2\2\2\33X\3"+
		"\2\2\2\35[\3\2\2\2\37]\3\2\2\2!`\3\2\2\2#b\3\2\2\2%e\3\2\2\2\'g\3\2\2"+
		"\2)k\3\2\2\2+m\3\2\2\2-q\3\2\2\2/s\3\2\2\2\61x\3\2\2\2\63|\3\2\2\2\65"+
		"\66\7k\2\2\66\67\7h\2\2\67\4\3\2\2\289\7r\2\29:\7c\2\2:;\7u\2\2;<\7u\2"+
		"\2<\6\3\2\2\2=>\7d\2\2>?\7q\2\2?@\7v\2\2@A\7j\2\2A\b\3\2\2\2BC\7r\2\2"+
		"CD\7t\2\2DE\7k\2\2EF\7p\2\2FG\7v\2\2G\n\3\2\2\2HI\7-\2\2I\f\3\2\2\2JK"+
		"\7/\2\2K\16\3\2\2\2LM\7,\2\2M\20\3\2\2\2NO\7\61\2\2O\22\3\2\2\2PQ\7#\2"+
		"\2Q\24\3\2\2\2RS\7`\2\2S\26\3\2\2\2TU\7(\2\2U\30\3\2\2\2VW\7~\2\2W\32"+
		"\3\2\2\2XY\7@\2\2YZ\7?\2\2Z\34\3\2\2\2[\\\7@\2\2\\\36\3\2\2\2]^\7>\2\2"+
		"^_\7?\2\2_ \3\2\2\2`a\7>\2\2a\"\3\2\2\2bc\7?\2\2cd\7?\2\2d$\3\2\2\2ef"+
		"\7?\2\2f&\3\2\2\2gh\t\2\2\2h(\3\2\2\2il\5\'\24\2jl\7a\2\2ki\3\2\2\2kj"+
		"\3\2\2\2l*\3\2\2\2mn\t\3\2\2n,\3\2\2\2or\5\13\6\2pr\5\r\7\2qo\3\2\2\2"+
		"qp\3\2\2\2r.\3\2\2\2st\t\4\2\2tu\3\2\2\2uv\b\30\2\2v\60\3\2\2\2wy\5+\26"+
		"\2xw\3\2\2\2yz\3\2\2\2zx\3\2\2\2z{\3\2\2\2{\62\3\2\2\2|\u0081\5)\25\2"+
		"}\u0080\5)\25\2~\u0080\5+\26\2\177}\3\2\2\2\177~\3\2\2\2\u0080\u0083\3"+
		"\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\64\3\2\2\2\u0083\u0081"+
		"\3\2\2\2\b\2kqz\177\u0081\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}