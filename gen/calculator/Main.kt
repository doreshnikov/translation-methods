package calculator

import parse.Parser
import structure.Visitor

fun main() {
    val s = "1 + 2 * 3 - 4 + 2 * (100 - 98) * (3000 - 1000 * (1 + 1))"
    // 4003
    val visitor = ClassLoader.getSystemClassLoader()
        .loadClass("calculator.CalculatorVisitor")
        .kotlin.objectInstance as Visitor<*, *>
    println(visitor.collect(Parser(CalculatorGrammarInfo).parse(s)))
}