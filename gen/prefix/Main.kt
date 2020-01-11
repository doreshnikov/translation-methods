package prefix

import parse.Parser
import structure.Visitor

fun main() {
    val s = "" +
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
    val visitor = ClassLoader.getSystemClassLoader()
        .loadClass("prefix.PrefixVisitor")
        .kotlin.objectInstance as Visitor<*, *>
    println(visitor.collect(Parser(PrefixGrammarInfo).parse(s)))
}