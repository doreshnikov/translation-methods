package test

import parse.Parser
import structure.ASTNode
import structure.Visitor
import translate.codegen.helpers.GrammarInfo
import java.text.ParseException

@Suppress("LeakingThis")
sealed class Test(protected val name: String) {

    companion object {
        val tests = mutableListOf<Test>()

        var grammarInfoObject: GrammarInfo? = null
        var visitorObject: Visitor<*, *>? = null

        fun register(grammarInfo: GrammarInfo, visitor: Visitor<*, *>?) {
            if (grammarInfoObject == null) {
                grammarInfoObject = grammarInfo
                visitorObject = visitor
            } else {
                throw IllegalStateException("Tests already have a registered grammar")
            }
        }

        fun runAll() {
            println("Running tests:")
            var passed = 0
            tests.forEach { if (it.run()) passed++ }.also { println() }
            println("TESTS: ${tests.size}\n" + "Passed: ${passed}, Failed: ${tests.size - passed}")
        }
    }

    init {
        tests.add(this)
    }

    abstract fun run(): Boolean

    class ParseTest(name: String, private val data: String, private val tree: ASTNode<*>? = null) : Test(name) {
        override fun run(): Boolean {
            try {
                val result = Parser(grammarInfoObject!!).parse(data)
                try {
                    tree?.checkIsSame(result)
                    println("[PASSED] $name")
                    return true
                } catch (e: IllegalStateException) {
                    println("[FAILED] $name: $e")
                }
            } catch (e: Exception) {
                println("[FAILED] $name: $e")
            }
            return false
        }
    }

    class ParseExceptionTest(name: String, private val data: String) : Test(name) {
        override fun run(): Boolean {
            try {
                Parser(grammarInfoObject!!).parse(data)
                println("[FAILED] $name: no exception occurred")
            } catch (e: ParseException) {
                println("[PASSED] $name: ${e.message}")
                return true
            } catch (e: Exception) {
                println("[FAILED] $name: invalid exception $e")
            }
            return false
        }
    }

    class VisitorDataTest(name: String, private val data: String, private val expect: (Any) -> Boolean) : Test(name) {
        override fun run(): Boolean {
            try {
                val result = Parser(grammarInfoObject!!).parse(data)
                try {
                    val collect = expect(visitorObject!!.collect(result)!!)
                    if (collect) {
                        println("[PASSED] $name")
                        return true
                    } else {
                        println("[FAILED] $name: expectation failed:")
                        return false
                    }
                } catch (e: IllegalStateException) {
                    println("[FAILED] $name: $e")
                }
            } catch (e: Exception) {
                println("[FAILED] $name: $e")
            }
            return false
        }
    }

}