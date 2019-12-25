// Generated from prefix.g4 by ANTLR 4.7.2
package prefix;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.stream.Collectors;

/**
 * This class provides an empty implementation of {@link prefixVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 * <p>
 * // * @param <T> The return type of the visit operation. Use {@link Void} for
 * // *            operations with no return type.
 */
public class prefixBaseVisitor extends AbstractParseTreeVisitor<String> implements prefixVisitor<String> {
    private String tab(String block) {
        return block.replace("\n", "\n\t");
    }

    @Override
    protected String aggregateResult(String aggregate, String nextResult) {
        return aggregate + "\n" + nextResult;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitCode(prefixParser.CodeContext ctx) {
        return ctx.children.stream().limit(ctx.children.size() - 1).map(this::visit).collect(Collectors.joining("\n"));
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitCodeBlock(prefixParser.CodeBlockContext ctx) {
        if (ctx.PASS() != null) {
            return "";
        }
        return visit(ctx.children.get(0));
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitStatement(prefixParser.StatementContext ctx) {
        return visit(ctx.children.get(0));
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitPrint(prefixParser.PrintContext ctx) {
        return "print(" + visit(ctx.children.get(1)) + ")";
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitAssignment(prefixParser.AssignmentContext ctx) {
        return "var " + ctx.children.get(1).getText() + " = " + visit(ctx.children.get(2));
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitIfBlock(prefixParser.IfBlockContext ctx) {
        return "if (" + visit(ctx.children.get(1)) + ") {"
                + tab("\n" + visit(ctx.children.get(2)))
                + "\n} else {"
                + tab("\n" + visit(ctx.children.get(3)))
                + "\n}";
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitIfBody(prefixParser.IfBodyContext ctx) {
        if (ctx.children.size() > 1) {
            return visit(ctx.children.get(1)) + "\n" + visit(ctx.children.get(2));
        }
        return visit(ctx.children.get(0));
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitExpression(prefixParser.ExpressionContext ctx) {
        return visit(ctx.children.get(0));
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitLogicalBinop(prefixParser.LogicalBinopContext ctx) {
        return ctx.getText();
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitLogicalUnop(prefixParser.LogicalUnopContext ctx) {
        return ctx.getText();
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitLogicalExpression(prefixParser.LogicalExpressionContext ctx) {
        switch (ctx.getRuleIndex()) {
            case 0:
                return "(" + visit(ctx.children.get(1)) + " "
                        + ctx.children.get(0).getText()
                        + " " + visit(ctx.children.get(2)) + ")";
            case 1:
                return ctx.children.get(0).getText() + visit(ctx.children.get(1));
            default:
                return visit(ctx.children.get(0));
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitLogicalAtom(prefixParser.LogicalAtomContext ctx) {
        if (ctx.VAR() != null) {
            return ctx.VAR().getSymbol().getText();
        }
        return visit(ctx.children.get(0));
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitRelation(prefixParser.RelationContext ctx) {
        return ctx.getText();
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitComparison(prefixParser.ComparisonContext ctx) {
        return "(" + visit(ctx.children.get(1)) + " "
                + ctx.children.get(0).getText()
                + " " + visit(ctx.children.get(2)) + ")";
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitArithmeticBinop(prefixParser.ArithmeticBinopContext ctx) {
        return ctx.children.get(0).getText();
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitArithmeticExpression(prefixParser.ArithmeticExpressionContext ctx) {
        if (ctx.children.size() > 1) {
            return "(" + visit(ctx.children.get(1)) + " "
                    + ctx.children.get(0).getText()
                    + " " + visit(ctx.children.get(2)) + ")";
        } else {
            return visit(ctx.children.get(0));
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public String visitArithmeticAtom(prefixParser.ArithmeticAtomContext ctx) {
        if (ctx.VAR() != null) {
            return ctx.VAR().getSymbol().getText();
        }
        return ctx.NUMBER().getSymbol().getText();
    }

    public static void main(String[] args) {
        String expression = "= a + 2 5 if > a 6 = a + a - a 3 both = b * 2 a print b";
//        String expression = "= a + 2 5";
        prefixLexer lexer = new prefixLexer(new ANTLRInputStream(expression));
        prefixParser parser = new prefixParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.code();
        System.out.println(new prefixBaseVisitor().visit(tree));
    }
}