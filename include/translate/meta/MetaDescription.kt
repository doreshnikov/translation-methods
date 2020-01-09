package translate.meta

import grammar.Expansion
import grammar.Grammar
import grammar.token.Token
import structure.Description

object MetaDescription : Description {

    override fun getAll(): List<Token> {
        return listOf(
            LPAREN, RPAREN, EOLN, DEFINE, DESCRIBE, CHOICE,
            LTRIG, RTRIG, CHARRANGE, ASSIGN, ADD, SUB, MUL, DIV,
            LARRAY, RARRAY, SEP,
            MACRO, TOKENS, GRAMMAR, FRAGMENTS, COMPANION,
            SKIP, SYNTHESIS, INHERITANCE, COMPUTE, START, DEFAULT,
            INT_TYPE, DOUBLE_TYPE, STRING_TYPE,
            KOTLIN_FUNC, DOUBLE, INT, CHAR, STRING, RSTRING,
            SPNAME, CAMELNAME, CAPSNAME, WHITESPACE,

            all,
            m, kfPlus,
            t, tComp, tSkip, tArray, tArrayPlus, tFrag, tFragLine, tFragPlus, tLine, tDef, tPlus,
            g, gComp, gSynth, gInh, gCompv, gStart, gLine, gPlus,
            attribs, attribsPlus, attrib, type,
            rule, rules, rulesPlus,
            def, pass, defBody, defAtom, defValue, defTerm, defMod, setDef, op, defPlus,
            seq, seqPlus, atom,
            atName
        )
    }

    override fun getName(): String {
        return "<meta>"
    }

    init {
        Token.switchTo(getName())
        check()
    }

    // @formatter:off

    // special symbols

    object LPAREN       : Token.StringToken("LPAREN", "{")
    object RPAREN       : Token.StringToken("RPAREN", "}")
    object EOLN         : Token.StringToken("EOLN", ";")

    object DEFINE       : Token.StringToken("DEFINE", "::")
    object DESCRIBE     : Token.StringToken("DESCRIBE" , ":")
    object CHOICE       : Token.StringToken("CHOICE", "|")

    object LTRIG        : Token.StringToken("LTRIG", "<")
    object RTRIG        : Token.StringToken("RTRIG", ">")
    object CHARRANGE    : Token.StringToken("CHARRANGE", "..")

    object ASSIGN       : Token.StringToken("ASSIGN", "=")
    object ADD          : Token.StringToken("ADD", "+")
    object SUB          : Token.StringToken("SUB", "-")
    object MUL          : Token.StringToken("MUL", "*")
    object DIV          : Token.StringToken("DIV", "/")

    object LARRAY       : Token.StringToken("LARRAY", "[")
    object RARRAY       : Token.StringToken("RARRAY", "]")
    object SEP          : Token.StringToken("SEP", ",")

    // keywords

    object MACRO        : Token.StringToken("MACRO", "macro")
    object TOKENS       : Token.StringToken("TOKENS", "tokens")
    object GRAMMAR      : Token.StringToken("GRAMMAR", "grammar")
    object FRAGMENTS    : Token.StringToken("FRAGMENTS", "fragments")
    object COMPANION    : Token.StringToken("COMPANION", "companion")

    object SKIP         : Token.StringToken("SKIP", "skip")
    object SYNTHESIS    : Token.StringToken("SYNTHESIS", "synthesis")
    object INHERITANCE  : Token.StringToken("INHERITANCE", "inheritance")
    object COMPUTE      : Token.StringToken("COMPUTE", "compute")
    object START        : Token.StringToken("START", "start")
    object DEFAULT      : Token.StringToken("DEFAULT", "default")

    // kotlin types

    object INT_TYPE     : Token.StringToken("INT_TYPE", "Int")
    object DOUBLE_TYPE  : Token.StringToken("DOUBLE_TYPE", "Double")
    object STRING_TYPE  : Token.StringToken("STRING_TYPE", "String")

    // miscellaneous

    object KOTLIN_FUNC  : Token.RegexToken("KOTLIN_FUNC", "(\\t| {4})fun .* \\{(\n|\r\n)(\\1.*\\2)+\\1}".toRegex())

    // literals and names

    object DOUBLE       : Token.RegexToken("DOUBLE", "(0|[1-9]\\d*)\\.\\d*".toRegex())
    object INT          : Token.RegexToken("INT", "(0|[1-9]\\d*)".toRegex())
    object CHAR         : Token.RegexToken("CHAR", "'[^']'".toRegex())
    object STRING       : Token.RegexToken("STRING", "\"[^\"]*\"".toRegex())
    object RSTRING      : Token.RegexToken("RSTRING", "r\"[^\"]*\"".toRegex())

    object SPNAME       : Token.RegexToken("SPNAME", "@(\\d*|macro)\\.[a-zA-Z()]+".toRegex())
    object CAMELNAME    : Token.RegexToken("CAMELNAME", "[a-z]+([A-Z][a-z]*)*".toRegex())
    object CAPSNAME     : Token.RegexToken("CAPSNAME", "[A-Z]+".toRegex())

    object WHITESPACE   : Token.RegexToken("WHITESPACE", "[ \\t\\n\\r]".toRegex())

    // states

    object all          : Token.StateToken("all")

    object m            : Token.StateToken("m")
    object kfPlus       : Token.StateToken("kfPlus")

    object t            : Token.StateToken("t")
    object tComp        : Token.StateToken("tComp")
    object tSkip        : Token.StateToken("tSkip")
    object tArray       : Token.StateToken("tArray")
    object tArrayPlus   : Token.StateToken("tArrayPlus")
    object tFrag        : Token.StateToken("tFrag")
    object tFragLine    : Token.StateToken("tFragLine")
    object tFragPlus    : Token.StateToken("tFragPlus")
    object tLine        : Token.StateToken("tLine")
    object tDef         : Token.StateToken("tDef")
    object tPlus        : Token.StateToken("tPlus")

    object g            : Token.StateToken("g")
    object gComp        : Token.StateToken("gComp")
    object gSynth       : Token.StateToken("gSynth")
    object gInh         : Token.StateToken("gInh")
    object gCompv       : Token.StateToken("gCompv")
    object gStart       : Token.StateToken("gStart")
    object gLine        : Token.StateToken("gLine")
    object gPlus        : Token.StateToken("gPlus")

    object attribs      : Token.StateToken("attribs")
    object attribsPlus  : Token.StateToken("attribsPlus")
    object attrib       : Token.StateToken("attrib")
    object type         : Token.StateToken("type")

    object rule         : Token.StateToken("rule")
    object rules        : Token.StateToken("rules")
    object rulesPlus    : Token.StateToken("rulesPlus")

    object def          : Token.StateToken("def")
    object pass         : Token.StateToken("pass")
    object defBody      : Token.StateToken("defBody")
    object defAtom      : Token.StateToken("defAtom")
    object defValue     : Token.StateToken("defValue")
    object defTerm      : Token.StateToken("defTerm")
    object defMod       : Token.StateToken("defMod")
    object setDef       : Token.StateToken("setDef")
    object op           : Token.StateToken("op")
    object defPlus      : Token.StateToken("defPlus")

    object seq          : Token.StateToken("seq")
    object seqPlus      : Token.StateToken("seqPlus")
    object atom         : Token.StateToken("atom")

    object atName       : Token.StateToken("atName")

    // grammar

    private val grammar = Grammar(
        all,

        all         into Expansion(m, t, g),
            m           into Expansion(MACRO, LPAREN, kfPlus, RPAREN),
                kfPlus      into Expansion(KOTLIN_FUNC, kfPlus),
                kfPlus      into Expansion(Token.UniqueToken.EPSILON),
            m           into Expansion(Token.UniqueToken.EPSILON),
            t           into Expansion(TOKENS, LPAREN, tComp, tFrag, tPlus, RPAREN),
                tComp       into Expansion(COMPANION, LPAREN, tSkip, RPAREN),
                    tSkip       into Expansion(SKIP, DESCRIBE, tArray, EOLN),
                        tArray      into Expansion(LARRAY, CAPSNAME, tArrayPlus, RARRAY),
                        tArrayPlus  into Expansion(SEP, CAPSNAME, tArrayPlus),
                        tArrayPlus  into Expansion(Token.UniqueToken.EPSILON),
                tComp       into Expansion(Token.UniqueToken.EPSILON),
                tFrag       into Expansion(FRAGMENTS, LPAREN, tFragPlus, RPAREN),
                    tFragPlus   into Expansion(tFragLine, tFragPlus),
                        tFragLine   into Expansion(CAPSNAME, DESCRIBE, STRING, EOLN),
                    tFragPlus   into Expansion(Token.UniqueToken.EPSILON),
                tFrag       into Expansion(Token.UniqueToken.EPSILON),
                tPlus       into Expansion(tLine, tPlus),
                    tLine       into Expansion(CAPSNAME, DESCRIBE, tDef, EOLN),
                        tDef        into Expansion(STRING),
                        tDef        into Expansion(RSTRING),
                        tDef        into Expansion(LTRIG, CHAR, CHARRANGE, CHAR, RTRIG),
                tPlus       into Expansion(Token.UniqueToken.EPSILON),
            g           into Expansion(GRAMMAR, LPAREN, gComp, gPlus, RPAREN),
                gComp       into Expansion(COMPANION, LPAREN, gSynth, gInh, gCompv, gStart, RPAREN),
                    gSynth      into Expansion(SYNTHESIS, LPAREN, attribs, RPAREN),
                    gSynth      into Expansion(Token.UniqueToken.EPSILON),
                    gInh        into Expansion(INHERITANCE, LPAREN, attribs, RPAREN),
                    gInh        into Expansion(Token.UniqueToken.EPSILON),
                    gCompv      into Expansion(COMPUTE, LPAREN, attribs, RPAREN),
                    gCompv      into Expansion(Token.UniqueToken.EPSILON),
                    gStart      into Expansion(START, DESCRIBE, CAMELNAME, EOLN),
                        attribs      into Expansion(attrib, attribsPlus),
                            attrib       into Expansion(CAMELNAME, DESCRIBE, type, setDef, EOLN),
                                type        into Expansion(INT_TYPE),
                                type        into Expansion(DOUBLE_TYPE),
                                type        into Expansion(STRING_TYPE),
                                setDef          into Expansion(DEFINE, LPAREN, DEFAULT, ASSIGN, defValue, RPAREN),
                                setDef          into Expansion(Token.UniqueToken.EPSILON),
                            attribsPlus  into Expansion(attrib, attribsPlus),
                            attribsPlus  into Expansion(Token.UniqueToken.EPSILON),
                gPlus       into Expansion(gLine, gPlus),
                    gLine       into Expansion(CAMELNAME, def, DESCRIBE, rules, EOLN),
                        rules       into Expansion(rule, rulesPlus),
                            rule        into Expansion(seq, def),
                                seq         into Expansion(atom, seqPlus),
                                    atom        into Expansion(CAPSNAME),
                                    atom        into Expansion(CAMELNAME, pass),
                                        pass        into Expansion(LPAREN, defBody, RPAREN),
                                        pass        into Expansion(Token.UniqueToken.EPSILON),
                                    seqPlus     into Expansion(atom, seqPlus),
                                    seqPlus     into Expansion(Token.UniqueToken.EPSILON),
                            rulesPlus   into Expansion(CHOICE, rule, rulesPlus),
                            rulesPlus   into Expansion(Token.UniqueToken.EPSILON),
                        def         into Expansion(DEFINE, LPAREN, defBody, RPAREN),
                            defBody     into Expansion(defAtom, defPlus),
                                defAtom     into Expansion(CAMELNAME, ASSIGN, defValue),
                                    defValue    into Expansion(STRING),
                                    defValue    into Expansion(defTerm, defMod),
                                        defTerm     into Expansion(atName),
                                        defTerm     into Expansion(INT),
                                        defTerm     into Expansion(DOUBLE),
                                        defMod      into Expansion(op, defTerm),
                                            op          into Expansion(ADD),
                                            op          into Expansion(SUB),
                                            op          into Expansion(MUL),
                                            op          into Expansion(DIV),
                                        defMod      into Expansion(Token.UniqueToken.EPSILON),
                                    defValue    into Expansion(SUB, defTerm),
                                        atName      into Expansion(SPNAME),
                                        atName      into Expansion(CAMELNAME),
                                defPlus     into Expansion(SEP, defAtom, defPlus),
                                defPlus     into Expansion(Token.UniqueToken.EPSILON),
                        def         into Expansion(Token.UniqueToken.EPSILON),
                gPlus       into Expansion(Token.UniqueToken.EPSILON)
    ).order()

    // @formatter:on

    override fun getGrammar(): Grammar {
        return grammar
    }

    override fun getSkippedTokens(): Set<Token> {
        return setOf(WHITESPACE)
    }

}