package regex.test

import parse.Parser
import gen.RegexGrammarInfo
import structure.ASTNode
import java.text.ParseException

@Suppress("LeakingThis")
sealed class Test(protected val name: String) {

    companion object {
        val me = RegexGrammarInfo
        val tests = mutableListOf<Test>()

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

    class CorrectnessTest(name: String, private val data: String, private val tree: ASTNode<*>? = null) : Test(name) {
        override fun run(): Boolean {
            try {
                val result = Parser(me).parse(data)
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
                Parser(me).parse(data)
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

}