package translate.test

import parse.Parser
import translate.codegen.VisitorBuilder
import translate.codegen.WalkerBuilder
import translate.codegen.helpers.GrammarInfo
import translate.meta.helpers.MetaGrammarInfo
import java.io.File
import java.text.ParseException

fun main() {

    listOf("regex", "calculator", "prefix").forEach { name ->
        val str = File("$loc\\include\\translate\\examples\\$name.my").bufferedReader().use {
            return@use it.readText()
        }
        val uname = singleUpperCase(name)
        try {
            val grammarInfoObject = Class.forName("$name.${uname}GrammarInfo").kotlin.objectInstance as GrammarInfo
            File("$loc\\gen\\$name\\${uname}WalkerBase.kt").bufferedWriter().use { out ->
                out.write(WalkerBuilder(name, grammarInfoObject).getAll())
            }
            val visitorBuilder = VisitorBuilder(name, grammarInfoObject, Parser(MetaGrammarInfo).parse(str)).prepare()
            File("$loc\\gen\\$name\\${uname}Visitor.kt").bufferedWriter().use { out ->
                out.write(visitorBuilder.getAll())
            }
            File("$loc\\gen\\$name\\${uname}Data.kt").bufferedWriter().use { out ->
                out.write(visitorBuilder.getDataClass())
            }
            println("[SUCCESS] $name")
        } catch (e: ParseException) {
            println("[FAIL] $name: $e at ${e.errorOffset}")
        } catch (e: IllegalStateException) {
            println("[FAIL] $name: $e")
        } catch (e: ClassNotFoundException) {
            println("[INVALID] $name: $e")
        }
    }

}