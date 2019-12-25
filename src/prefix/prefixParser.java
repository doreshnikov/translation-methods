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
		RULE_code = 0, RULE_codeBlock = 1, RULE_statement = 2, RULE_print = 3, 
		RULE_assignment = 4, RULE_ifBlock = 5, RULE_ifBody = 6, RULE_expression = 7, 
		RULE_logicalBinop = 8, RULE_logicalUnop = 9, RULE_logicalExpression = 10, 
		RULE_logicalAtom = 11, RULE_relation = 12, RULE_comparison = 13, RULE_arithmeticBinop = 14, 
		RULE_arithmeticExpression = 15, RULE_arithmeticAtom = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"code", "codeBlock", "statement", "print", "assignment", "ifBlock", "ifBody", 
			"expression", "logicalBinop", "logicalUnop", "logicalExpression", "logicalAtom", 
			"relation", "comparison", "arithmeticBinop", "arithmeticExpression", 
			"arithmeticAtom"
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
		public List<CodeBlockContext> codeBlock() {
			return getRuleContexts(CodeBlockContext.class);
		}
		public CodeBlockContext codeBlock(int i) {
			return getRuleContext(CodeBlockContext.class,i);
		}
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitCode(this);
			else return visitor.visitChildren(this);
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << PASS) | (1L << PRINT) | (1L << ASSIGN))) != 0)) {
				{
				{
				setState(34);
				codeBlock();
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

	public static class CodeBlockContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public IfBlockContext ifBlock() {
			return getRuleContext(IfBlockContext.class,0);
		}
		public TerminalNode PASS() { return getToken(prefixParser.PASS, 0); }
		public CodeBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitCodeBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeBlockContext codeBlock() throws RecognitionException {
		CodeBlockContext _localctx = new CodeBlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_codeBlock);
		try {
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PRINT:
			case ASSIGN:
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
				ifBlock();
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
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
			case ASSIGN:
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
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
		public TerminalNode ASSIGN() { return getToken(prefixParser.ASSIGN, 0); }
		public TerminalNode VAR() { return getToken(prefixParser.VAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(ASSIGN);
			setState(55);
			match(VAR);
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

	public static class IfBlockContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(prefixParser.IF, 0); }
		public LogicalExpressionContext logicalExpression() {
			return getRuleContext(LogicalExpressionContext.class,0);
		}
		public List<IfBodyContext> ifBody() {
			return getRuleContexts(IfBodyContext.class);
		}
		public IfBodyContext ifBody(int i) {
			return getRuleContext(IfBodyContext.class,i);
		}
		public IfBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitIfBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfBlockContext ifBlock() throws RecognitionException {
		IfBlockContext _localctx = new IfBlockContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_ifBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(IF);
			setState(59);
			logicalExpression();
			setState(60);
			ifBody();
			setState(61);
			ifBody();
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

	public static class IfBodyContext extends ParserRuleContext {
		public CodeBlockContext codeBlock() {
			return getRuleContext(CodeBlockContext.class,0);
		}
		public TerminalNode BOTH() { return getToken(prefixParser.BOTH, 0); }
		public IfBodyContext ifBody() {
			return getRuleContext(IfBodyContext.class,0);
		}
		public IfBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitIfBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfBodyContext ifBody() throws RecognitionException {
		IfBodyContext _localctx = new IfBodyContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ifBody);
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
			case PASS:
			case PRINT:
			case ASSIGN:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				codeBlock();
				}
				break;
			case BOTH:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				match(BOTH);
				setState(65);
				codeBlock();
				setState(66);
				ifBody();
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
		public LogicalExpressionContext logicalExpression() {
			return getRuleContext(LogicalExpressionContext.class,0);
		}
		public ArithmeticExpressionContext arithmeticExpression() {
			return getRuleContext(ArithmeticExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
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
				logicalExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				arithmeticExpression();
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

	public static class LogicalBinopContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(prefixParser.AND, 0); }
		public TerminalNode OR() { return getToken(prefixParser.OR, 0); }
		public TerminalNode XOR() { return getToken(prefixParser.XOR, 0); }
		public LogicalBinopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalBinop; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitLogicalBinop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalBinopContext logicalBinop() throws RecognitionException {
		LogicalBinopContext _localctx = new LogicalBinopContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_logicalBinop);
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

	public static class LogicalUnopContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(prefixParser.NOT, 0); }
		public LogicalUnopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalUnop; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitLogicalUnop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalUnopContext logicalUnop() throws RecognitionException {
		LogicalUnopContext _localctx = new LogicalUnopContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_logicalUnop);
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

	public static class LogicalExpressionContext extends ParserRuleContext {
		public LogicalBinopContext logicalBinop() {
			return getRuleContext(LogicalBinopContext.class,0);
		}
		public List<LogicalExpressionContext> logicalExpression() {
			return getRuleContexts(LogicalExpressionContext.class);
		}
		public LogicalExpressionContext logicalExpression(int i) {
			return getRuleContext(LogicalExpressionContext.class,i);
		}
		public LogicalUnopContext logicalUnop() {
			return getRuleContext(LogicalUnopContext.class,0);
		}
		public LogicalAtomContext logicalAtom() {
			return getRuleContext(LogicalAtomContext.class,0);
		}
		public LogicalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitLogicalExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalExpressionContext logicalExpression() throws RecognitionException {
		LogicalExpressionContext _localctx = new LogicalExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_logicalExpression);
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
				logicalBinop();
				setState(79);
				logicalExpression();
				setState(80);
				logicalExpression();
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(82);
				logicalUnop();
				setState(83);
				logicalExpression();
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
				logicalAtom();
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

	public static class LogicalAtomContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(prefixParser.VAR, 0); }
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public LogicalAtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalAtom; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitLogicalAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalAtomContext logicalAtom() throws RecognitionException {
		LogicalAtomContext _localctx = new LogicalAtomContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_logicalAtom);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitRelation(this);
			else return visitor.visitChildren(this);
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
		public List<ArithmeticExpressionContext> arithmeticExpression() {
			return getRuleContexts(ArithmeticExpressionContext.class);
		}
		public ArithmeticExpressionContext arithmeticExpression(int i) {
			return getRuleContext(ArithmeticExpressionContext.class,i);
		}
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitComparison(this);
			else return visitor.visitChildren(this);
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
			arithmeticExpression();
			setState(96);
			arithmeticExpression();
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

	public static class ArithmeticBinopContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(prefixParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(prefixParser.MINUS, 0); }
		public TerminalNode TIMES() { return getToken(prefixParser.TIMES, 0); }
		public TerminalNode DIV() { return getToken(prefixParser.DIV, 0); }
		public ArithmeticBinopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticBinop; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitArithmeticBinop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticBinopContext arithmeticBinop() throws RecognitionException {
		ArithmeticBinopContext _localctx = new ArithmeticBinopContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_arithmeticBinop);
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

	public static class ArithmeticExpressionContext extends ParserRuleContext {
		public ArithmeticBinopContext arithmeticBinop() {
			return getRuleContext(ArithmeticBinopContext.class,0);
		}
		public List<ArithmeticExpressionContext> arithmeticExpression() {
			return getRuleContexts(ArithmeticExpressionContext.class);
		}
		public ArithmeticExpressionContext arithmeticExpression(int i) {
			return getRuleContext(ArithmeticExpressionContext.class,i);
		}
		public ArithmeticAtomContext arithmeticAtom() {
			return getRuleContext(ArithmeticAtomContext.class,0);
		}
		public ArithmeticExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitArithmeticExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticExpressionContext arithmeticExpression() throws RecognitionException {
		ArithmeticExpressionContext _localctx = new ArithmeticExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_arithmeticExpression);
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
				arithmeticBinop();
				setState(101);
				arithmeticExpression();
				setState(102);
				arithmeticExpression();
				}
				break;
			case NUMBER:
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(104);
				arithmeticAtom();
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

	public static class ArithmeticAtomContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(prefixParser.VAR, 0); }
		public TerminalNode NUMBER() { return getToken(prefixParser.NUMBER, 0); }
		public ArithmeticAtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticAtom; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof prefixVisitor ) return ((prefixVisitor<? extends T>)visitor).visitArithmeticAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticAtomContext arithmeticAtom() throws RecognitionException {
		ArithmeticAtomContext _localctx = new ArithmeticAtomContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_arithmeticAtom);
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
		"\3\2\2\289\7\24\2\29:\7\27\2\2:;\5\20\t\2;\13\3\2\2\2<=\7\3\2\2=>\5\26"+
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