// Generated from Prefix.g4 by ANTLR 4.7.2
package antlr.gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PrefixParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PrefixVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PrefixParser#code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCode(PrefixParser.CodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#codeBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodeBlock(PrefixParser.CodeBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(PrefixParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(PrefixParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(PrefixParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#ifBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfBlock(PrefixParser.IfBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#forBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForBlock(PrefixParser.ForBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#innerBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInnerBody(PrefixParser.InnerBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(PrefixParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#logicalBinop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalBinop(PrefixParser.LogicalBinopContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#logicalUnop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalUnop(PrefixParser.LogicalUnopContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#logicalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalExpression(PrefixParser.LogicalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#logicalAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAtom(PrefixParser.LogicalAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation(PrefixParser.RelationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(PrefixParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#arithmeticBinop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticBinop(PrefixParser.ArithmeticBinopContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticExpression(PrefixParser.ArithmeticExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PrefixParser#arithmeticAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticAtom(PrefixParser.ArithmeticAtomContext ctx);
}