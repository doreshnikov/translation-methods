package regex

import parse.Parser
import structure.ASTNode
import java.io.File
import java.text.ParseException

fun main(args: Array<String>) {

    val me = RegexDescription

    args.forEachIndexed { index, data ->
        File("data/$index.gv").bufferedWriter().use { out ->
            try {
                ASTNode
                out.write(GraphvisVisitor().collect(Parser(me).parse(data)))
                println("[SUCCESS] \"$data\" -> data/$index.gv")
                Runtime.getRuntime().exec(
                    "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe" +
                            " -Tpdf data/$index.gv" +
                            " -o data/$index.pdf"
                )
            } catch (e: ParseException) {
                println("[FAIL] \"$data\": $e")
            }
        }
    }

}