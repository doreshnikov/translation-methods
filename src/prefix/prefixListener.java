// Generated from prefix.g4 by ANTLR 4.7.2
package prefix;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link prefixParser}.
 */
public interface prefixListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link prefixParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode(prefixParser.CodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode(prefixParser.CodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#code_block}.
	 * @param ctx the parse tree
	 */
	void enterCode_block(prefixParser.Code_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#code_block}.
	 * @param ctx the parse tree
	 */
	void exitCode_block(prefixParser.Code_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(prefixParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(prefixParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(prefixParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(prefixParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(prefixParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(prefixParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#if_block}.
	 * @param ctx the parse tree
	 */
	void enterIf_block(prefixParser.If_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#if_block}.
	 * @param ctx the parse tree
	 */
	void exitIf_block(prefixParser.If_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#if_body}.
	 * @param ctx the parse tree
	 */
	void enterIf_body(prefixParser.If_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#if_body}.
	 * @param ctx the parse tree
	 */
	void exitIf_body(prefixParser.If_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(prefixParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(prefixParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#logical_binop}.
	 * @param ctx the parse tree
	 */
	void enterLogical_binop(prefixParser.Logical_binopContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#logical_binop}.
	 * @param ctx the parse tree
	 */
	void exitLogical_binop(prefixParser.Logical_binopContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#logical_unop}.
	 * @param ctx the parse tree
	 */
	void enterLogical_unop(prefixParser.Logical_unopContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#logical_unop}.
	 * @param ctx the parse tree
	 */
	void exitLogical_unop(prefixParser.Logical_unopContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#logical_expression}.
	 * @param ctx the parse tree
	 */
	void enterLogical_expression(prefixParser.Logical_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#logical_expression}.
	 * @param ctx the parse tree
	 */
	void exitLogical_expression(prefixParser.Logical_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#l_atom}.
	 * @param ctx the parse tree
	 */
	void enterL_atom(prefixParser.L_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#l_atom}.
	 * @param ctx the parse tree
	 */
	void exitL_atom(prefixParser.L_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#relation}.
	 * @param ctx the parse tree
	 */
	void enterRelation(prefixParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#relation}.
	 * @param ctx the parse tree
	 */
	void exitRelation(prefixParser.RelationContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(prefixParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(prefixParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#arithmetic_binop}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic_binop(prefixParser.Arithmetic_binopContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#arithmetic_binop}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic_binop(prefixParser.Arithmetic_binopContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#arithmetic_expression}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic_expression(prefixParser.Arithmetic_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#arithmetic_expression}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic_expression(prefixParser.Arithmetic_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link prefixParser#arithmetic_atom}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic_atom(prefixParser.Arithmetic_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link prefixParser#arithmetic_atom}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic_atom(prefixParser.Arithmetic_atomContext ctx);
}