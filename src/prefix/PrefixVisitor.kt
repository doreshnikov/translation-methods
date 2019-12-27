package prefix

import prefix.gen.prefixBaseVisitor
import prefix.gen.prefixParser
import prefix.gen.prefixParser.*

class PrefixVisitor : prefixBaseVisitor<String>() {

    companion object {
        fun tab(block: String): String {
            return block.replace("\n", "\n\t")
        }
    }

    override fun defaultResult(): String {
        return ""
    }

    override fun aggregateResult(aggregate: String, nextResult: String): String {
        return aggregate + "\n" + nextResult
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitCode(ctx: CodeContext?): String {
        return visitChildren(ctx)
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitCodeBlock(ctx: CodeBlockContext): String {
        return if (ctx.PASS() != null) {
            ""
        } else visit(ctx.getChild(0))
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitStatement(ctx: StatementContext): String {
        return visit(ctx.getChild(0))
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitPrint(ctx: PrintContext): String {
        return "print(" + visit(ctx.getChild(1)) + ")"
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitAssignment(ctx: AssignmentContext): String {
        return "var " + ctx.getChild(1).text + " = " + visit(ctx.getChild(2))
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitIfBlock(ctx: IfBlockContext): String {
        return ("if (" + visit(ctx.getChild(1)) + ") {"
                + tab("\n" + visit(ctx.getChild(2)))
                + "\n} else {"
                + tab("\n" + visit(ctx.getChild(3)))
                + "\n}")
    }

    override fun visitForBlock(ctx: ForBlockContext): String {
        return ("for (" + ctx.VAR().text + " in " +
                ctx.arithmeticAtom(0).text + ".." + ctx.arithmeticAtom(1).text + ") {"
                + tab("\n" + visit(ctx.getChild(4)))
                + "\n}")
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitInnerBody(ctx: prefixParser.InnerBodyContext): String {
        return if (ctx.children.size > 1) {
            visit(ctx.getChild(1)) + "\n" + visit(ctx.getChild(2))
        } else visit(ctx.getChild(0))
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitExpression(ctx: ExpressionContext): String {
        return visit(ctx.getChild(0))
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitLogicalBinop(ctx: LogicalBinopContext): String {
        return ctx.text
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitLogicalUnop(ctx: LogicalUnopContext): String {
        return ctx.text
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitLogicalExpression(ctx: LogicalExpressionContext): String {
        return when (ctx.childCount) {
            3 -> "(" + visit(ctx.getChild(1)) +
                    " " + ctx.getChild(1).text +
                    " " + visit(ctx.getChild(2)) + ")"
            2 -> ctx.getChild(0).text + visit(ctx.getChild(1))
            else -> visit(ctx.getChild(0))
        }
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitLogicalAtom(ctx: LogicalAtomContext): String {
        return if (ctx.VAR() != null) {
            ctx.VAR().text
        } else visit(ctx.getChild(0))
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitRelation(ctx: RelationContext): String {
        return ctx.text
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitComparison(ctx: ComparisonContext): String {
        return ("(" + visit(ctx.getChild(1)) + " "
                + ctx.getChild(0).text
                + " " + visit(ctx.getChild(2)) + ")")
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitArithmeticBinop(ctx: ArithmeticBinopContext): String {
        return ctx.getChild(0).text
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitArithmeticExpression(ctx: ArithmeticExpressionContext): String {
        return if (ctx.children.size > 1) {
            ("(" + visit(ctx.getChild(1)) + " "
                    + ctx.getChild(0).text
                    + " " + visit(ctx.getChild(2)) + ")")
        } else {
            visit(ctx.getChild(0))
        }
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation returns the result of calling
     * [.visitChildren] on `ctx`.
     */
    override fun visitArithmeticAtom(ctx: ArithmeticAtomContext): String {
        return if (ctx.VAR() != null) {
            ctx.VAR().text
        } else ctx.NUMBER().text
    }

}