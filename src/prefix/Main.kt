package prefix

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import prefix.gen.prefixLexer
import prefix.gen.prefixParser
import java.io.File

fun main() {
    val expression = "" +
            "= a + 2 5 " +
            "if > a 6 = a + a - a 3 both = b * 2 a both = x + 10 * a b " +
            "if >= x 100 print b for v 1 b both print v if != v b print 0 pass"
    /*
    var a = 2 + 5
    if (a > 6) {
        var a = a + a - 3
    } else {
        var b = 2 * a
        var x = 10 + a * b
        if (x >= 100) {
            print(b)
        } else {
            for (v in 1..b) {
                print(v)
                if (v == 1) {
                    print(' ')
                } else { }
            }
        }
    }
    */
    val lexer = prefixLexer(ANTLRInputStream(expression))
    val parser = prefixParser(CommonTokenStream(lexer))
    val tree: ParseTree = parser.code()
    val result: String? = PrefixVisitor().visit(tree)
    File("src/prefix/out/Gen.kt").printWriter().use { out ->
        out.println(
            "package prefix;\n\n" +
                    "fun main() {" +
                    PrefixVisitor.tab("\n" + result) +
                    "\n}"
        )
    }
}