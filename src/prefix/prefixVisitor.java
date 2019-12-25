// Generated from prefix.g4 by ANTLR 4.7.2
package prefix;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link prefixParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface prefixVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link prefixParser#code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCode(prefixParser.CodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link prefixParser#codeBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodeBlock(prefixParser.CodeBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link prefixParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(prefixParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link prefixParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(prefixParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link prefixParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(prefixParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link prefixParser#ifBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfBlock(prefixParser.IfBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link prefixParser#ifBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfBody(prefixParser.IfBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link prefixParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(prefixParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link prefixParser#logicalBinop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalBinop(prefixParser.LogicalBinopContext ctx);
	/**
	 * Visit a parse tree produced by {@link prefixParser#logicalUnop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalUnop(prefixParser.LogicalUnopContext ctx);
	/**
	 * Visit a parse tree produced by {@link prefixParser#logicalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalExpression(prefixParser.LogicalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link prefixParser#logicalAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAtom(prefixParser.LogicalAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link prefixParser#relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation(prefixParser.RelationContext ctx);
	/**
	 * Visit a parse tree produced by {@link prefixParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(prefixParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link prefixParser#arithmeticBinop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticBinop(prefixParser.ArithmeticBinopContext ctx);
	/**
	 * Visit a parse tree produced by {@link prefixParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticExpression(prefixParser.ArithmeticExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link prefixParser#arithmeticAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticAtom(prefixParser.ArithmeticAtomContext ctx);
}