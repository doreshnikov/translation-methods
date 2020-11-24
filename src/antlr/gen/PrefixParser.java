// Generated from Prefix.g4 by ANTLR 4.7.2
package antlr.gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrefixParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, FOR=2, PASS=3, BOTH=4, PRINT=5, PLUS=6, MINUS=7, TIMES=8, DIV=9, 
		NOT=10, XOR=11, AND=12, OR=13, GE=14, GT=15, LE=16, LT=17, EQ=18, NE=19, 
		ASSIGN=20, WHITESPACE=21, NUMBER=22, VAR=23;
	public static final int
		RULE_code = 0, RULE_codeBlock = 1, RULE_statement = 2, RULE_print = 3, 
		RULE_assignment = 4, RULE_ifBlock = 5, RULE_forBlock = 6, RULE_innerBody = 7, 
		RULE_expression = 8, RULE_logicalBinop = 9, RULE_logicalUnop = 10, RULE_logicalExpression = 11, 
		RULE_logicalAtom = 12, RULE_relation = 13, RULE_comparison = 14, RULE_arithmeticBinop = 15, 
		RULE_arithmeticExpression = 16, RULE_arithmeticAtom = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"code", "codeBlock", "statement", "print", "assignment", "ifBlock", "forBlock", 
			"innerBody", "expression", "logicalBinop", "logicalUnop", "logicalExpression", 
			"logicalAtom", "relation", "comparison", "arithmeticBinop", "arithmeticExpression", 
			"arithmeticAtom"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'for'", "'pass'", "'both'", "'print'", "'+'", "'-'", "'*'", 
			"'/'", "'!'", "'^'", "'&'", "'|'", "'>='", "'>'", "'<='", "'<'", "'=='", 
			"'!='", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "FOR", "PASS", "BOTH", "PRINT", "PLUS", "MINUS", "TIMES", 
			"DIV", "NOT", "XOR", "AND", "OR", "GE", "GT", "LE", "LT", "EQ", "NE", 
			"ASSIGN", "WHITESPACE", "NUMBER", "VAR"
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
	public String getGrammarFileName() { return "Prefix.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PrefixParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class CodeContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(PrefixParser.EOF, 0); }
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
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitCode(this);
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
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << FOR) | (1L << PASS) | (1L << PRINT) | (1L << ASSIGN))) != 0)) {
				{
				{
				setState(36);
				codeBlock();
				}
				}
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(42);
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
		public ForBlockContext forBlock() {
			return getRuleContext(ForBlockContext.class,0);
		}
		public TerminalNode PASS() { return getToken(PrefixParser.PASS, 0); }
		public CodeBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitCodeBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeBlockContext codeBlock() throws RecognitionException {
		CodeBlockContext _localctx = new CodeBlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_codeBlock);
		try {
			setState(48);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PRINT:
			case ASSIGN:
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				statement();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(45);
				ifBlock();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(46);
				forBlock();
				}
				break;
			case PASS:
				enterOuterAlt(_localctx, 4);
				{
				setState(47);
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
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(52);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PRINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				print();
				}
				break;
			case ASSIGN:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
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
		public TerminalNode PRINT() { return getToken(PrefixParser.PRINT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_print);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(PRINT);
			setState(55);
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
		public TerminalNode ASSIGN() { return getToken(PrefixParser.ASSIGN, 0); }
		public TerminalNode VAR() { return getToken(PrefixParser.VAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(ASSIGN);
			setState(58);
			match(VAR);
			setState(59);
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
		public TerminalNode IF() { return getToken(PrefixParser.IF, 0); }
		public LogicalExpressionContext logicalExpression() {
			return getRuleContext(LogicalExpressionContext.class,0);
		}
		public List<InnerBodyContext> innerBody() {
			return getRuleContexts(InnerBodyContext.class);
		}
		public InnerBodyContext innerBody(int i) {
			return getRuleContext(InnerBodyContext.class,i);
		}
		public IfBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitIfBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfBlockContext ifBlock() throws RecognitionException {
		IfBlockContext _localctx = new IfBlockContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_ifBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(IF);
			setState(62);
			logicalExpression();
			setState(63);
			innerBody();
			setState(64);
			innerBody();
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

	public static class ForBlockContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(PrefixParser.FOR, 0); }
		public TerminalNode VAR() { return getToken(PrefixParser.VAR, 0); }
		public List<ArithmeticAtomContext> arithmeticAtom() {
			return getRuleContexts(ArithmeticAtomContext.class);
		}
		public ArithmeticAtomContext arithmeticAtom(int i) {
			return getRuleContext(ArithmeticAtomContext.class,i);
		}
		public InnerBodyContext innerBody() {
			return getRuleContext(InnerBodyContext.class,0);
		}
		public ForBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitForBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForBlockContext forBlock() throws RecognitionException {
		ForBlockContext _localctx = new ForBlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_forBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(FOR);
			setState(67);
			match(VAR);
			setState(68);
			arithmeticAtom();
			setState(69);
			arithmeticAtom();
			setState(70);
			innerBody();
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

	public static class InnerBodyContext extends ParserRuleContext {
		public CodeBlockContext codeBlock() {
			return getRuleContext(CodeBlockContext.class,0);
		}
		public TerminalNode BOTH() { return getToken(PrefixParser.BOTH, 0); }
		public InnerBodyContext innerBody() {
			return getRuleContext(InnerBodyContext.class,0);
		}
		public InnerBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_innerBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitInnerBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InnerBodyContext innerBody() throws RecognitionException {
		InnerBodyContext _localctx = new InnerBodyContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_innerBody);
		try {
			setState(77);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
			case FOR:
			case PASS:
			case PRINT:
			case ASSIGN:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				codeBlock();
				}
				break;
			case BOTH:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				match(BOTH);
				setState(74);
				codeBlock();
				setState(75);
				innerBody();
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
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expression);
		try {
			setState(81);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				logicalExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(80);
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
		public TerminalNode AND() { return getToken(PrefixParser.AND, 0); }
		public TerminalNode OR() { return getToken(PrefixParser.OR, 0); }
		public TerminalNode XOR() { return getToken(PrefixParser.XOR, 0); }
		public LogicalBinopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalBinop; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitLogicalBinop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalBinopContext logicalBinop() throws RecognitionException {
		LogicalBinopContext _localctx = new LogicalBinopContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_logicalBinop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
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
		public TerminalNode NOT() { return getToken(PrefixParser.NOT, 0); }
		public LogicalUnopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalUnop; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitLogicalUnop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalUnopContext logicalUnop() throws RecognitionException {
		LogicalUnopContext _localctx = new LogicalUnopContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_logicalUnop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
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
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitLogicalExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalExpressionContext logicalExpression() throws RecognitionException {
		LogicalExpressionContext _localctx = new LogicalExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_logicalExpression);
		try {
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case XOR:
			case AND:
			case OR:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				logicalBinop();
				setState(88);
				logicalExpression();
				setState(89);
				logicalExpression();
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				logicalUnop();
				setState(92);
				logicalExpression();
				}
				break;
			case GE:
			case GT:
			case LE:
			case LT:
			case EQ:
			case NE:
			case VAR:
				enterOuterAlt(_localctx, 3);
				{
				setState(94);
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
		public TerminalNode VAR() { return getToken(PrefixParser.VAR, 0); }
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public LogicalAtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalAtom; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitLogicalAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalAtomContext logicalAtom() throws RecognitionException {
		LogicalAtomContext _localctx = new LogicalAtomContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_logicalAtom);
		try {
			setState(99);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				match(VAR);
				}
				break;
			case GE:
			case GT:
			case LE:
			case LT:
			case EQ:
			case NE:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
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
		public TerminalNode EQ() { return getToken(PrefixParser.EQ, 0); }
		public TerminalNode NE() { return getToken(PrefixParser.NE, 0); }
		public TerminalNode GT() { return getToken(PrefixParser.GT, 0); }
		public TerminalNode GE() { return getToken(PrefixParser.GE, 0); }
		public TerminalNode LT() { return getToken(PrefixParser.LT, 0); }
		public TerminalNode LE() { return getToken(PrefixParser.LE, 0); }
		public RelationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitRelation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationContext relation() throws RecognitionException {
		RelationContext _localctx = new RelationContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_relation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GE) | (1L << GT) | (1L << LE) | (1L << LT) | (1L << EQ) | (1L << NE))) != 0)) ) {
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
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitComparison(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_comparison);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			relation();
			setState(104);
			arithmeticExpression();
			setState(105);
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
		public TerminalNode PLUS() { return getToken(PrefixParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(PrefixParser.MINUS, 0); }
		public TerminalNode TIMES() { return getToken(PrefixParser.TIMES, 0); }
		public TerminalNode DIV() { return getToken(PrefixParser.DIV, 0); }
		public ArithmeticBinopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticBinop; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitArithmeticBinop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticBinopContext arithmeticBinop() throws RecognitionException {
		ArithmeticBinopContext _localctx = new ArithmeticBinopContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_arithmeticBinop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
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
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitArithmeticExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticExpressionContext arithmeticExpression() throws RecognitionException {
		ArithmeticExpressionContext _localctx = new ArithmeticExpressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_arithmeticExpression);
		try {
			setState(114);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case TIMES:
			case DIV:
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				arithmeticBinop();
				setState(110);
				arithmeticExpression();
				setState(111);
				arithmeticExpression();
				}
				break;
			case NUMBER:
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
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
		public TerminalNode VAR() { return getToken(PrefixParser.VAR, 0); }
		public TerminalNode NUMBER() { return getToken(PrefixParser.NUMBER, 0); }
		public ArithmeticAtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmeticAtom; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PrefixVisitor ) return ((PrefixVisitor<? extends T>)visitor).visitArithmeticAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArithmeticAtomContext arithmeticAtom() throws RecognitionException {
		ArithmeticAtomContext _localctx = new ArithmeticAtomContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_arithmeticAtom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\31y\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22\4\23"+
		"\t\23\3\2\7\2(\n\2\f\2\16\2+\13\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3\63\n\3\3"+
		"\4\3\4\5\4\67\n\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\5\tP\n\t\3\n\3\n\5\nT\n\n\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\rb\n\r\3\16\3\16\5\16"+
		"f\n\16\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\5\22u\n\22\3\23\3\23\3\23\2\2\24\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$\2\6\3\2\r\17\3\2\20\25\3\2\b\13\3\2\30\31\2q\2)\3\2\2\2\4\62\3"+
		"\2\2\2\6\66\3\2\2\2\b8\3\2\2\2\n;\3\2\2\2\f?\3\2\2\2\16D\3\2\2\2\20O\3"+
		"\2\2\2\22S\3\2\2\2\24U\3\2\2\2\26W\3\2\2\2\30a\3\2\2\2\32e\3\2\2\2\34"+
		"g\3\2\2\2\36i\3\2\2\2 m\3\2\2\2\"t\3\2\2\2$v\3\2\2\2&(\5\4\3\2\'&\3\2"+
		"\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*,\3\2\2\2+)\3\2\2\2,-\7\2\2\3-\3\3"+
		"\2\2\2.\63\5\6\4\2/\63\5\f\7\2\60\63\5\16\b\2\61\63\7\5\2\2\62.\3\2\2"+
		"\2\62/\3\2\2\2\62\60\3\2\2\2\62\61\3\2\2\2\63\5\3\2\2\2\64\67\5\b\5\2"+
		"\65\67\5\n\6\2\66\64\3\2\2\2\66\65\3\2\2\2\67\7\3\2\2\289\7\7\2\29:\5"+
		"\22\n\2:\t\3\2\2\2;<\7\26\2\2<=\7\31\2\2=>\5\22\n\2>\13\3\2\2\2?@\7\3"+
		"\2\2@A\5\30\r\2AB\5\20\t\2BC\5\20\t\2C\r\3\2\2\2DE\7\4\2\2EF\7\31\2\2"+
		"FG\5$\23\2GH\5$\23\2HI\5\20\t\2I\17\3\2\2\2JP\5\4\3\2KL\7\6\2\2LM\5\4"+
		"\3\2MN\5\20\t\2NP\3\2\2\2OJ\3\2\2\2OK\3\2\2\2P\21\3\2\2\2QT\5\30\r\2R"+
		"T\5\"\22\2SQ\3\2\2\2SR\3\2\2\2T\23\3\2\2\2UV\t\2\2\2V\25\3\2\2\2WX\7\f"+
		"\2\2X\27\3\2\2\2YZ\5\24\13\2Z[\5\30\r\2[\\\5\30\r\2\\b\3\2\2\2]^\5\26"+
		"\f\2^_\5\30\r\2_b\3\2\2\2`b\5\32\16\2aY\3\2\2\2a]\3\2\2\2a`\3\2\2\2b\31"+
		"\3\2\2\2cf\7\31\2\2df\5\36\20\2ec\3\2\2\2ed\3\2\2\2f\33\3\2\2\2gh\t\3"+
		"\2\2h\35\3\2\2\2ij\5\34\17\2jk\5\"\22\2kl\5\"\22\2l\37\3\2\2\2mn\t\4\2"+
		"\2n!\3\2\2\2op\5 \21\2pq\5\"\22\2qr\5\"\22\2ru\3\2\2\2su\5$\23\2to\3\2"+
		"\2\2ts\3\2\2\2u#\3\2\2\2vw\t\5\2\2w%\3\2\2\2\n)\62\66OSaet";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}