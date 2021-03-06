package regex

import parse.Parser
import structure.ASTNode
import utils.GraphvizWalker
import java.io.File
import java.text.ParseException

fun main(args: Array<String>) {

    val me = RegexGrammarInfo

    args.forEachIndexed { index, data ->
        File("data/$index.gv").bufferedWriter().use { out ->
            try {
                ASTNode
                out.write(GraphvizWalker().collect(Parser(me).parse(data)))
                println("[SUCCESS] \"$data\" -> data/$index.gv")
                Runtime.getRuntime().exec(
                    "C:\\Program Files\\Graphviz 2.44.1\\bin\\dot.exe" +
                            " -Tpdf data/$index.gv" +
                            " -o data/$index.pdf"
                )
            } catch (e: ParseException) {
                println("[FAIL] \"$data\": $e")
            }
        }
    }

}