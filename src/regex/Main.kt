package regex

import parse.Parser
import structure.Tree
import java.io.File
import java.text.ParseException

fun main(args: Array<String>) {

    val me = Regex

    args.forEachIndexed { index, data ->
        File("data/$index.gv").bufferedWriter().use { out ->
            try {
                Tree.resetAll()
                out.write(Parser(me.grammar).parse(data).toGraphViz())
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