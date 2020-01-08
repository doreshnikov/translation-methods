package translate.generate

import grammar.Expansion
import grammar.Grammar
import grammar.token.Token
import structure.Description

object MetaDescription : Description {

    // @formatter:off

    // special symbols

    val LPAREN      = Token.StringToken("<lparen>", "{")
    val RPAREN      = Token.StringToken("<rparen>", "}")
    val EOLN        = Token.StringToken("<eoln>", ";")

    val DEFINE      = Token.StringToken("<define>", "::")
    val DESCRIBE    = Token.StringToken("<describe>" , ":")
    val CHOICE      = Token.StringToken("<choice>", "|")

    val LTRIG       = Token.StringToken("<ltrig>", "<")
    val RTRIG       = Token.StringToken("<rtrig>", ">")
    val CHARRANGE   = Token.StringToken("<charrange>", "..")

    val ASSIGN      = Token.StringToken("<assign>", "=")
    val ADD         = Token.StringToken("<add>", "+")
    val SUB         = Token.StringToken("<sub>", "-")
    val MUL         = Token.StringToken("<mul>", "*")
    val DIV         = Token.StringToken("<div>", "/")

    val LARRAY      = Token.StringToken("<larray>", "[")
    val RARRAY      = Token.StringToken("<rarray>", "]")
    val SEP         = Token.StringToken("<sep>", ",")

    // keywords

    val MACRO       = Token.StringToken("macro", "macro")
    val TOKENS      = Token.StringToken("tokens", "tokens")
    val GRAMMAR     = Token.StringToken("grammar", "grammar")
    val FRAGMENTS   = Token.StringToken("fragments", "fragments")
    val COMPANION   = Token.StringToken("companion", "companion")

    val SKIP        = Token.StringToken("skip", "skip")
    val SYNTHESIS   = Token.StringToken("synthesis", "synthesis")
    val INHERITANCE = Token.StringToken("inheritance", "inheritance")
    val COMPUTE     = Token.StringToken("compute", "compute")
    val START       = Token.StringToken("start", "start")
    val DEFAULT     = Token.StringToken("default", "default")

    // kotlin types

    val INT_TYPE    = Token.StringToken("Int", "Int")
    val DOUBLE_TYPE = Token.StringToken("Double", "Double")
    val STRING_TYPE = Token.StringToken("String", "String")

    // miscellaneous

    val KOTLIN_FUNC = Token.RegexToken("<kotlinfunc>", "(\\t| {4})fun .* \\{(\n|\r\n)(\\1.*\\2)+\\1}".toRegex())

    // literals and names

    val DOUBLE      = Token.RegexToken("<double>", "(0|[1-9]\\d*)\\.\\d*".toRegex())
    val INT         = Token.RegexToken("<int>", "(0|[1-9]\\d*)".toRegex())
    val CHAR        = Token.RegexToken("<char>", "'[^']'".toRegex())
    val STRING      = Token.RegexToken("<str>", "\"[^\"]*\"".toRegex())
    val RSTRING     = Token.RegexToken("<regexstr>", "r\"[^\"]*\"".toRegex())

    val SPNAME      = Token.RegexToken("<spname>", "@(\\d*|macro)\\.[a-zA-Z()]+".toRegex())
    val CAMELNAME   = Token.RegexToken("<camelname>", "[a-z]+([A-Z][a-z]*)*".toRegex())
    val CAPSNAME    = Token.RegexToken("<capsname>", "[A-Z]+".toRegex())

    val WHITESPACE  = Token.RegexToken("<whitespace>", "[ \\t\\n\\r]".toRegex())

    // states

    val ALL         = Token.StateToken("ALL")

    val M           = Token.StateToken("MACRO")
    val KFPLUS      = Token.StateToken("KFUNC+")

    val T           = Token.StateToken("TOKENS")
    val TCOMP       = Token.StateToken("T_COMPANION")
    val TSKIP       = Token.StateToken("T_SKIP")
    val TARRAY      = Token.StateToken("T_ARRAY")
    val TARRAYPLUS  = Token.StateToken("T_ARRAY+")
    val TFRAG       = Token.StateToken("T_FRAGMENTS")
    val TLINE       = Token.StateToken("T_LINE")
    val TDEF        = Token.StateToken("T_DEFINITION")
    val TPLUS       = Token.StateToken("T+")

    val G           = Token.StateToken("GRAMMAR")
    val GCOMP       = Token.StateToken("G_COMPANION")
    val GSYNTH      = Token.StateToken("G_SYNTHESIS")
    val GINH        = Token.StateToken("G_INHERITANCE")
    val GCOMPV      = Token.StateToken("G_COMPUTE")
    val GSTART      = Token.StateToken("G_START")
    val GLINE       = Token.StateToken("G_LINE")
    val GPLUS       = Token.StateToken("G+")

    val ATTRIBS     = Token.StateToken("ATTRIBUTES")
    val ATTRIBSPLUS = Token.StateToken("ATTRIBS+")
    val ATTRIB      = Token.StateToken("ATTRIBUTE")
    val TYPE        = Token.StateToken("TYPE")

    val RULE        = Token.StateToken("RULE")
    val RULES       = Token.StateToken("RULES")
    val RULESPLUS   = Token.StateToken("RULES+")

    val DEF         = Token.StateToken("DEFINES")
    val PASS        = Token.StateToken("PASS")
    val DEFBODY     = Token.StateToken("DEF_BODY")
    val DEFATOM     = Token.StateToken("DEF_ATOM")
    val DEFVALUE    = Token.StateToken("DEF_VALUE")
    val DEFTERM     = Token.StateToken("DEF_TERM")
    val DEFMOD      = Token.StateToken("DEF_MODIFY")
    val SETDEF      = Token.StateToken("SETDEFAULT")
    val OP          = Token.StateToken("OP")
    val DEFPLUS     = Token.StateToken("DEF+")

    val SEQ         = Token.StateToken("SEQUENCE")
    val SEQPLUS     = Token.StateToken("SEQUENCE+")
    val ATOM        = Token.StateToken("ATOM")

    val ATNAME      = Token.StateToken("ATNAME")

    // grammar

    private val grammar = Grammar(
        ALL,

        ALL         into Expansion(M, T, G),
            M           into Expansion(MACRO, LPAREN, KFPLUS, RPAREN),
                KFPLUS      into Expansion(KOTLIN_FUNC, KFPLUS),
                KFPLUS      into Expansion(Token.UniqueToken.EPSILON),
            M           into Expansion(Token.UniqueToken.EPSILON),
            T           into Expansion(TOKENS, LPAREN, TCOMP, TFRAG, TPLUS, RPAREN),
                TCOMP       into Expansion(COMPANION, LPAREN, TSKIP, RPAREN),
                    TSKIP       into Expansion(SKIP, DESCRIBE, TARRAY, EOLN),
                        TARRAY      into Expansion(LARRAY, CAPSNAME, TARRAYPLUS, RARRAY),
                        TARRAYPLUS  into Expansion(SEP, CAPSNAME, TARRAYPLUS),
                        TARRAYPLUS  into Expansion(Token.UniqueToken.EPSILON),
                TCOMP       into Expansion(Token.UniqueToken.EPSILON),
                TFRAG       into Expansion(FRAGMENTS, LPAREN, TPLUS, RPAREN),
                TFRAG       into Expansion(Token.UniqueToken.EPSILON),
                TPLUS       into Expansion(TLINE, TPLUS),
                    TLINE       into Expansion(CAPSNAME, DESCRIBE, TDEF, EOLN),
                        TDEF        into Expansion(STRING),
                        TDEF        into Expansion(RSTRING),
                        TDEF        into Expansion(LTRIG, CHAR, CHARRANGE, CHAR, RTRIG),
                TPLUS       into Expansion(Token.UniqueToken.EPSILON),
            G           into Expansion(GRAMMAR, LPAREN, GCOMP, GPLUS, RPAREN),
                GCOMP       into Expansion(COMPANION, LPAREN, GSYNTH, GINH, GCOMPV, GSTART, RPAREN),
                    GSYNTH      into Expansion(SYNTHESIS, LPAREN, ATTRIBS, RPAREN),
                    GSYNTH      into Expansion(Token.UniqueToken.EPSILON),
                    GINH        into Expansion(INHERITANCE, LPAREN, ATTRIBS, RPAREN),
                    GINH        into Expansion(Token.UniqueToken.EPSILON),
                    GCOMPV      into Expansion(COMPUTE, LPAREN, ATTRIBS, RPAREN),
                    GCOMPV      into Expansion(Token.UniqueToken.EPSILON),
                    GSTART      into Expansion(START, DESCRIBE, CAMELNAME, EOLN),
                        ATTRIBS      into Expansion(ATTRIB, ATTRIBSPLUS),
                            ATTRIB       into Expansion(CAMELNAME, DESCRIBE, TYPE, SETDEF, EOLN),
                                TYPE        into Expansion(INT_TYPE),
                                TYPE        into Expansion(DOUBLE_TYPE),
                                TYPE        into Expansion(STRING_TYPE),
                                SETDEF          into Expansion(DEFINE, LPAREN, DEFAULT, ASSIGN, DEFVALUE, RPAREN),
                                SETDEF          into Expansion(Token.UniqueToken.EPSILON),
                            ATTRIBSPLUS  into Expansion(ATTRIB, ATTRIBSPLUS),
                            ATTRIBSPLUS  into Expansion(Token.UniqueToken.EPSILON),
                GPLUS       into Expansion(GLINE, GPLUS),
                    GLINE       into Expansion(CAMELNAME, DEF, DESCRIBE, RULES, EOLN),
                        RULES       into Expansion(RULE, RULESPLUS),
                            RULE        into Expansion(SEQ, DEF),
                                SEQ         into Expansion(ATOM, SEQPLUS),
                                    ATOM        into Expansion(CAPSNAME),
                                    ATOM        into Expansion(CAMELNAME, PASS),
                                        PASS        into Expansion(LPAREN, DEFBODY, RPAREN),
                                        PASS        into Expansion(Token.UniqueToken.EPSILON),
                                    SEQPLUS     into Expansion(ATOM, SEQPLUS),
                                    SEQPLUS     into Expansion(Token.UniqueToken.EPSILON),
                            RULESPLUS   into Expansion(CHOICE, RULE, RULESPLUS),
                            RULESPLUS   into Expansion(Token.UniqueToken.EPSILON),
                        DEF         into Expansion(DEFINE, LPAREN, DEFBODY, RPAREN),
                            DEFBODY     into Expansion(DEFATOM, DEFPLUS),
                                DEFATOM     into Expansion(CAMELNAME, ASSIGN, DEFVALUE),
                                    DEFVALUE    into Expansion(STRING),
                                    DEFVALUE    into Expansion(DEFTERM, DEFMOD),
                                        DEFTERM     into Expansion(ATNAME),
                                        DEFTERM     into Expansion(INT),
                                        DEFTERM     into Expansion(DOUBLE),
                                        DEFMOD      into Expansion(OP, DEFTERM),
                                            OP          into Expansion(ADD),
                                            OP          into Expansion(SUB),
                                            OP          into Expansion(MUL),
                                            OP          into Expansion(DIV),
                                        DEFMOD      into Expansion(Token.UniqueToken.EPSILON),
                                    DEFVALUE    into Expansion(SUB, DEFTERM),
                                        ATNAME      into Expansion(SPNAME),
                                        ATNAME      into Expansion(CAMELNAME),
                                DEFPLUS     into Expansion(SEP, DEFATOM, DEFPLUS),
                                DEFPLUS     into Expansion(Token.UniqueToken.EPSILON),
                        DEF         into Expansion(Token.UniqueToken.EPSILON),
                GPLUS       into Expansion(Token.UniqueToken.EPSILON)
    ).order()

    // @formatter:on

    override fun getGrammar(): Grammar {
        return grammar
    }

    override fun getSkippedTokens(): Set<Token> {
        return setOf(WHITESPACE)
    }

}