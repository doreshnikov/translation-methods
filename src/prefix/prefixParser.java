// Generated from prefix.g4 by ANTLR 4.7.2
package prefix;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class prefixParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, PASS=2, BOTH=3, PRINT=4, PLUS=5, MINUS=6, TIMES=7, DIV=8, NOT=9, 
		XOR=10, AND=11, OR=12, GE=13, GT=14, LE=15, LT=16, EQ=17, ASSIGN=18, WHITESPACE=19, 
		NUMBER=20, VAR=21;
	public static final int
		RULE_code = 0, RULE_code_block = 1, RULE_statement = 2, RULE_print = 3, 
		RULE_assignment = 4, RULE_if_block = 5, RULE_if_body = 6, RULE_expression = 7, 
		RULE_logical_binop = 8, RULE_logical_unop = 9, RULE_logical_expression = 10, 
		RULE_l_atom = 11, RULE_relation = 12, RULE_comparison = 13, RULE_arithmetic_binop = 14, 
		RULE_arithmetic_expression = 15, RULE_arithmetic_atom = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"code", "code_block", "statement", "print", "assignment", "if_block", 
			"if_body", "expression", "logical_binop", "logical_unop", "logical_expression", 
			"l_atom", "relation", "comparison", "arithmetic_binop", "arithmetic_expression", 
			"arithmetic_atom"
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

	@Override
	public String getGrammarFileName() { return "prefix.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public prefixParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class CodeContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(prefixParser.EOF, 0); }
		public List<Code_blockContext> code_block() {
			return getRuleContexts(Code_blockContext.class);
		}
		public Code_blockContext code_block(int i) {
			return getRuleContext(Code_blockContext.class,i);
		}
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitCode(this);
		}
	}

	public final CodeContext code() throws RecognitionException {
		CodeContext _localctx = new CodeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_code);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << PASS) | (1L << PRINT) | (1L << VAR))) != 0)) {
				{
				{
				setState(34);
				code_block();
				}
				}
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(40);
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

	public static class Code_blockContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public If_blockContext if_block() {
			return getRuleContext(If_blockContext.class,0);
		}
		public TerminalNode PASS() { return getToken(prefixParser.PASS, 0); }
		public Code_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterCode_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitCode_block(this);
		}
	}

	public final Code_blockContext code_block() throws RecognitionException {
		Code_blockContext _localctx = new Code_blockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_code_block);
		try {
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PRINT:
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				statement();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				if_block();
				}
				break;
			case PASS:
				enterOuterAlt(_localctx, 3);
				{
				setState(44);
				match(PASS);
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

	public static class StatementContext extends ParserRuleContext {
		public PrintContext print() {
			return getRuleContext(PrintContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(49);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PRINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(47);
				print();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(48);
				assignment();
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

	public static class PrintContext extends ParserRuleContext {
		public TerminalNode PRINT() { return getToken(prefixParser.PRINT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitPrint(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_print);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(PRINT);
			setState(52);
			expression();
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

	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(prefixParser.VAR, 0); }
		public TerminalNode ASSIGN() { return getToken(prefixParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(VAR);
			setState(55);
			match(ASSIGN);
			setState(56);
			expression();
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

	public static class If_blockContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(prefixParser.IF, 0); }
		public Logical_expressionContext logical_expression() {
			return getRuleContext(Logical_expressionContext.class,0);
		}
		public List<If_bodyContext> if_body() {
			return getRuleContexts(If_bodyContext.class);
		}
		public If_bodyContext if_body(int i) {
			return getRuleContext(If_bodyContext.class,i);
		}
		public If_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterIf_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitIf_block(this);
		}
	}

	public final If_blockContext if_block() throws RecognitionException {
		If_blockContext _localctx = new If_blockContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_if_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(IF);
			setState(59);
			logical_expression();
			setState(60);
			if_body();
			setState(61);
			if_body();
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

	public static class If_bodyContext extends ParserRuleContext {
		public Code_blockContext code_block() {
			return getRuleContext(Code_blockContext.class,0);
		}
		public TerminalNode BOTH() { return getToken(prefixParser.BOTH, 0); }
		public If_bodyContext if_body() {
			return getRuleContext(If_bodyContext.class,0);
		}
		public If_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterIf_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitIf_body(this);
		}
	}

	public final If_bodyContext if_body() throws RecognitionException {
		If_bodyContext _localctx = new If_bodyContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_if_body);
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
			case PASS:
			case PRINT:
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				code_block();
				}
				break;
			case BOTH:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				match(BOTH);
				setState(65);
				code_block();
				setState(66);
				if_body();
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

	public static class ExpressionContext extends ParserRuleContext {
		public Logical_expressionContext logical_expression() {
			return getRuleContext(Logical_expressionContext.class,0);
		}
		public Arithmetic_expressionContext arithmetic_expression() {
			return getRuleContext(Arithmetic_expressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expression);
		try {
			setState(72);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				logical_expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				arithmetic_expression();
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

	public static class Logical_binopContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(prefixParser.AND, 0); }
		public TerminalNode OR() { return getToken(prefixParser.OR, 0); }
		public TerminalNode XOR() { return getToken(prefixParser.XOR, 0); }
		public Logical_binopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logical_binop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterLogical_binop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitLogical_binop(this);
		}
	}

	public final Logical_binopContext logical_binop() throws RecognitionException {
		Logical_binopContext _localctx = new Logical_binopContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_logical_binop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << XOR) | (1L << AND) | (1L << OR))) != 0)) ) {
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

	public static class Logical_unopContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(prefixParser.NOT, 0); }
		public Logical_unopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logical_unop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterLogical_unop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitLogical_unop(this);
		}
	}

	public final Logical_unopContext logical_unop() throws RecognitionException {
		Logical_unopContext _localctx = new Logical_unopContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_logical_unop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(NOT);
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

	public static class Logical_expressionContext extends ParserRuleContext {
		public Logical_binopContext logical_binop() {
			return getRuleContext(Logical_binopContext.class,0);
		}
		public List<Logical_expressionContext> logical_expression() {
			return getRuleContexts(Logical_expressionContext.class);
		}
		public Logical_expressionContext logical_expression(int i) {
			return getRuleContext(Logical_expressionContext.class,i);
		}
		public Logical_unopContext logical_unop() {
			return getRuleContext(Logical_unopContext.class,0);
		}
		public L_atomContext l_atom() {
			return getRuleContext(L_atomContext.class,0);
		}
		public Logical_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logical_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterLogical_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitLogical_expression(this);
		}
	}

	public final Logical_expressionContext logical_expression() throws RecognitionException {
		Logical_expressionContext _localctx = new Logical_expressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_logical_expression);
		try {
			setState(86);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case XOR:
			case AND:
			case OR:
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				logical_binop();
				setState(79);
				logical_expression();
				setState(80);
				logical_expression();
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(82);
				logical_unop();
				setState(83);
				logical_expression();
				}
				break;
			case GE:
			case GT:
			case LE:
			case LT:
			case EQ:
			case VAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(85);
				l_atom();
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

	public static class L_atomContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(prefixParser.VAR, 0); }
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public L_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_l_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterL_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitL_atom(this);
		}
	}

	public final L_atomContext l_atom() throws RecognitionException {
		L_atomContext _localctx = new L_atomContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_l_atom);
		try {
			setState(90);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				match(VAR);
				}
				break;
			case GE:
			case GT:
			case LE:
			case LT:
			case EQ:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				comparison();
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

	public static class RelationContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(prefixParser.EQ, 0); }
		public TerminalNode GT() { return getToken(prefixParser.GT, 0); }
		public TerminalNode GE() { return getToken(prefixParser.GE, 0); }
		public TerminalNode LT() { return getToken(prefixParser.LT, 0); }
		public TerminalNode LE() { return getToken(prefixParser.LE, 0); }
		public RelationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitRelation(this);
		}
	}

	public final RelationContext relation() throws RecognitionException {
		RelationContext _localctx = new RelationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_relation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GE) | (1L << GT) | (1L << LE) | (1L << LT) | (1L << EQ))) != 0)) ) {
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

	public static class ComparisonContext extends ParserRuleContext {
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public List<Arithmetic_expressionContext> arithmetic_expression() {
			return getRuleContexts(Arithmetic_expressionContext.class);
		}
		public Arithmetic_expressionContext arithmetic_expression(int i) {
			return getRuleContext(Arithmetic_expressionContext.class,i);
		}
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitComparison(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_comparison);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			relation();
			setState(95);
			arithmetic_expression();
			setState(96);
			arithmetic_expression();
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

	public static class Arithmetic_binopContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(prefixParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(prefixParser.MINUS, 0); }
		public TerminalNode TIMES() { return getToken(prefixParser.TIMES, 0); }
		public TerminalNode DIV() { return getToken(prefixParser.DIV, 0); }
		public Arithmetic_binopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic_binop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterArithmetic_binop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitArithmetic_binop(this);
		}
	}

	public final Arithmetic_binopContext arithmetic_binop() throws RecognitionException {
		Arithmetic_binopContext _localctx = new Arithmetic_binopContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_arithmetic_binop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << TIMES) | (1L << DIV))) != 0)) ) {
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

	public static class Arithmetic_expressionContext extends ParserRuleContext {
		public Arithmetic_binopContext arithmetic_binop() {
			return getRuleContext(Arithmetic_binopContext.class,0);
		}
		public List<Arithmetic_expressionContext> arithmetic_expression() {
			return getRuleContexts(Arithmetic_expressionContext.class);
		}
		public Arithmetic_expressionContext arithmetic_expression(int i) {
			return getRuleContext(Arithmetic_expressionContext.class,i);
		}
		public Arithmetic_atomContext arithmetic_atom() {
			return getRuleContext(Arithmetic_atomContext.class,0);
		}
		public Arithmetic_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterArithmetic_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitArithmetic_expression(this);
		}
	}

	public final Arithmetic_expressionContext arithmetic_expression() throws RecognitionException {
		Arithmetic_expressionContext _localctx = new Arithmetic_expressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_arithmetic_expression);
		try {
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case TIMES:
			case DIV:
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				arithmetic_binop();
				setState(101);
				arithmetic_expression();
				setState(102);
				arithmetic_expression();
				}
				break;
			case NUMBER:
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(104);
				arithmetic_atom();
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

	public static class Arithmetic_atomContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(prefixParser.VAR, 0); }
		public TerminalNode NUMBER() { return getToken(prefixParser.NUMBER, 0); }
		public Arithmetic_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).enterArithmetic_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof prefixListener ) ((prefixListener)listener).exitArithmetic_atom(this);
		}
	}

	public final Arithmetic_atomContext arithmetic_atom() throws RecognitionException {
		Arithmetic_atomContext _localctx = new Arithmetic_atomContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_arithmetic_atom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			_la = _input.LA(1);
			if ( !(_la==NUMBER || _la==VAR) ) {
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\27p\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22\3\2\7"+
		"\2&\n\2\f\2\16\2)\13\2\3\2\3\2\3\3\3\3\3\3\5\3\60\n\3\3\4\3\4\5\4\64\n"+
		"\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b"+
		"\5\bG\n\b\3\t\3\t\5\tK\n\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\5\fY\n\f\3\r\3\r\5\r]\n\r\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3"+
		"\20\3\21\3\21\3\21\3\21\3\21\5\21l\n\21\3\22\3\22\3\22\2\2\23\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"\2\6\3\2\f\16\3\2\17\23\3\2\7\n\3\2"+
		"\26\27\2h\2\'\3\2\2\2\4/\3\2\2\2\6\63\3\2\2\2\b\65\3\2\2\2\n8\3\2\2\2"+
		"\f<\3\2\2\2\16F\3\2\2\2\20J\3\2\2\2\22L\3\2\2\2\24N\3\2\2\2\26X\3\2\2"+
		"\2\30\\\3\2\2\2\32^\3\2\2\2\34`\3\2\2\2\36d\3\2\2\2 k\3\2\2\2\"m\3\2\2"+
		"\2$&\5\4\3\2%$\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(*\3\2\2\2)\'\3"+
		"\2\2\2*+\7\2\2\3+\3\3\2\2\2,\60\5\6\4\2-\60\5\f\7\2.\60\7\4\2\2/,\3\2"+
		"\2\2/-\3\2\2\2/.\3\2\2\2\60\5\3\2\2\2\61\64\5\b\5\2\62\64\5\n\6\2\63\61"+
		"\3\2\2\2\63\62\3\2\2\2\64\7\3\2\2\2\65\66\7\6\2\2\66\67\5\20\t\2\67\t"+
		"\3\2\2\289\7\27\2\29:\7\24\2\2:;\5\20\t\2;\13\3\2\2\2<=\7\3\2\2=>\5\26"+
		"\f\2>?\5\16\b\2?@\5\16\b\2@\r\3\2\2\2AG\5\4\3\2BC\7\5\2\2CD\5\4\3\2DE"+
		"\5\16\b\2EG\3\2\2\2FA\3\2\2\2FB\3\2\2\2G\17\3\2\2\2HK\5\26\f\2IK\5 \21"+
		"\2JH\3\2\2\2JI\3\2\2\2K\21\3\2\2\2LM\t\2\2\2M\23\3\2\2\2NO\7\13\2\2O\25"+
		"\3\2\2\2PQ\5\22\n\2QR\5\26\f\2RS\5\26\f\2SY\3\2\2\2TU\5\24\13\2UV\5\26"+
		"\f\2VY\3\2\2\2WY\5\30\r\2XP\3\2\2\2XT\3\2\2\2XW\3\2\2\2Y\27\3\2\2\2Z]"+
		"\7\27\2\2[]\5\34\17\2\\Z\3\2\2\2\\[\3\2\2\2]\31\3\2\2\2^_\t\3\2\2_\33"+
		"\3\2\2\2`a\5\32\16\2ab\5 \21\2bc\5 \21\2c\35\3\2\2\2de\t\4\2\2e\37\3\2"+
		"\2\2fg\5\36\20\2gh\5 \21\2hi\5 \21\2il\3\2\2\2jl\5\"\22\2kf\3\2\2\2kj"+
		"\3\2\2\2l!\3\2\2\2mn\t\5\2\2n#\3\2\2\2\n\'/\63FJX\\k";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}