/**
This code is generated by [translate.codegen.WalkerBuilder]
basing on grammar description [gen.regex.RegexGrammarInfo] derived from [translate.codegen.helpers.GrammarInfo]
*/

package gen.regex

import grammar.token.Token
import grammar.Grammar
import grammar.Expansion

import structure.ASTNode
import structure.Walker

import translate.codegen.helpers.BuilderHelper
import translate.meta.helpers.MetaGrammarInfo

@Suppress("UNCHECKED_CAST")
interface RegexWalkerBase<R> : Walker<R> {

/*
Start: regex
regex -> sequence regexPlus
regexPlus -> CHOICE regex | <eps>
sequence -> term sequencePlus
sequencePlus -> sequence | <eps>
term -> atom number closure
number -> UINT | <eps>
closure -> KLEENE closure | <eps>
atom -> LPAREN regex RPAREN | ALPHA
*/

    fun <T : Token> visitTerminal(token: T): R

    override fun visit(node: ASTNode<out Token>): R {
        return when(node.getToken()) {
            RegexGrammarInfo.LPAREN -> visit_LPAREN(node as ASTNode.TerminalNode<RegexGrammarInfo.LPAREN>)
            RegexGrammarInfo.RPAREN -> visit_RPAREN(node as ASTNode.TerminalNode<RegexGrammarInfo.RPAREN>)
            RegexGrammarInfo.KLEENE -> visit_KLEENE(node as ASTNode.TerminalNode<RegexGrammarInfo.KLEENE>)
            RegexGrammarInfo.CHOICE -> visit_CHOICE(node as ASTNode.TerminalNode<RegexGrammarInfo.CHOICE>)
            RegexGrammarInfo.ALPHA -> visit_ALPHA(node as ASTNode.TerminalNode<Token.VariantToken.VariantInstanceToken<RegexGrammarInfo.ALPHA>>)
            RegexGrammarInfo.UINT -> visit_UINT(node as ASTNode.TerminalNode<Token.VariantToken.VariantInstanceToken<RegexGrammarInfo.UINT>>)
            RegexGrammarInfo.regex -> visit_regex(node as ASTNode.InnerNode<RegexGrammarInfo.regex>)
            RegexGrammarInfo.regexPlus -> visit_regexPlus(node as ASTNode.InnerNode<RegexGrammarInfo.regexPlus>)
            RegexGrammarInfo.sequence -> visit_sequence(node as ASTNode.InnerNode<RegexGrammarInfo.sequence>)
            RegexGrammarInfo.sequencePlus -> visit_sequencePlus(node as ASTNode.InnerNode<RegexGrammarInfo.sequencePlus>)
            RegexGrammarInfo.term -> visit_term(node as ASTNode.InnerNode<RegexGrammarInfo.term>)
            RegexGrammarInfo.number -> visit_number(node as ASTNode.InnerNode<RegexGrammarInfo.number>)
            RegexGrammarInfo.closure -> visit_closure(node as ASTNode.InnerNode<RegexGrammarInfo.closure>)
            RegexGrammarInfo.atom -> visit_atom(node as ASTNode.InnerNode<RegexGrammarInfo.atom>)
            else -> throw IllegalStateException("Unknown token ${node.getToken()} met")
        }
    }

    fun visit_LPAREN(node: ASTNode.TerminalNode<RegexGrammarInfo.LPAREN>): R {
        return visitTerminal(node.getToken())
    }

    fun visit_RPAREN(node: ASTNode.TerminalNode<RegexGrammarInfo.RPAREN>): R {
        return visitTerminal(node.getToken())
    }

    fun visit_KLEENE(node: ASTNode.TerminalNode<RegexGrammarInfo.KLEENE>): R {
        return visitTerminal(node.getToken())
    }

    fun visit_CHOICE(node: ASTNode.TerminalNode<RegexGrammarInfo.CHOICE>): R {
        return visitTerminal(node.getToken())
    }

    fun visit_ALPHA(node: ASTNode.TerminalNode<Token.VariantToken.VariantInstanceToken<RegexGrammarInfo.ALPHA>>): R {
        return visitTerminal(node.getToken())
    }

    fun visit_UINT(node: ASTNode.TerminalNode<Token.VariantToken.VariantInstanceToken<RegexGrammarInfo.UINT>>): R {
        return visitTerminal(node.getToken())
    }

    /**
    regex -> sequence regexPlus
    */
    fun visit_regex(node: ASTNode.InnerNode<RegexGrammarInfo.regex>): R {
        throw IllegalStateException("Unexpected expansion ${node.getToken()} -> ${node.getExpansion()} visited while visiting traversing tree")
    }

    fun visit_regexPlus(node: ASTNode.InnerNode<RegexGrammarInfo.regexPlus>): R {
        return when (val id = node.getExpansion().getId()) {
            0 -> visit_regexPlus_0(node)
            1 -> visit_regexPlus_1(node)
            else -> throw IllegalStateException("Unexpected expansion id $id in expansion of regexPlus")
        }
    }

    /**
    regexPlus -> CHOICE regex
    */
    fun visit_regexPlus_0(node: ASTNode.InnerNode<RegexGrammarInfo.regexPlus>): R {
        throw IllegalStateException("Unexpected expansion ${node.getToken()} -> ${node.getExpansion()} visited while visiting traversing tree") 
    }

    /**
    regexPlus -> <eps>
    */
    fun visit_regexPlus_1(node: ASTNode.InnerNode<RegexGrammarInfo.regexPlus>): R {
        throw IllegalStateException("Unexpected expansion ${node.getToken()} -> ${node.getExpansion()} visited while visiting traversing tree") 
    }

    /**
    sequence -> term sequencePlus
    */
    fun visit_sequence(node: ASTNode.InnerNode<RegexGrammarInfo.sequence>): R {
        throw IllegalStateException("Unexpected expansion ${node.getToken()} -> ${node.getExpansion()} visited while visiting traversing tree")
    }

    fun visit_sequencePlus(node: ASTNode.InnerNode<RegexGrammarInfo.sequencePlus>): R {
        return when (val id = node.getExpansion().getId()) {
            0 -> visit_sequencePlus_0(node)
            1 -> visit_sequencePlus_1(node)
            else -> throw IllegalStateException("Unexpected expansion id $id in expansion of sequencePlus")
        }
    }

    /**
    sequencePlus -> sequence
    */
    fun visit_sequencePlus_0(node: ASTNode.InnerNode<RegexGrammarInfo.sequencePlus>): R {
        throw IllegalStateException("Unexpected expansion ${node.getToken()} -> ${node.getExpansion()} visited while visiting traversing tree") 
    }

    /**
    sequencePlus -> <eps>
    */
    fun visit_sequencePlus_1(node: ASTNode.InnerNode<RegexGrammarInfo.sequencePlus>): R {
        throw IllegalStateException("Unexpected expansion ${node.getToken()} -> ${node.getExpansion()} visited while visiting traversing tree") 
    }

    /**
    term -> atom number closure
    */
    fun visit_term(node: ASTNode.InnerNode<RegexGrammarInfo.term>): R {
        throw IllegalStateException("Unexpected expansion ${node.getToken()} -> ${node.getExpansion()} visited while visiting traversing tree")
    }

    fun visit_number(node: ASTNode.InnerNode<RegexGrammarInfo.number>): R {
        return when (val id = node.getExpansion().getId()) {
            0 -> visit_number_0(node)
            1 -> visit_number_1(node)
            else -> throw IllegalStateException("Unexpected expansion id $id in expansion of number")
        }
    }

    /**
    number -> UINT
    */
    fun visit_number_0(node: ASTNode.InnerNode<RegexGrammarInfo.number>): R {
        throw IllegalStateException("Unexpected expansion ${node.getToken()} -> ${node.getExpansion()} visited while visiting traversing tree") 
    }

    /**
    number -> <eps>
    */
    fun visit_number_1(node: ASTNode.InnerNode<RegexGrammarInfo.number>): R {
        throw IllegalStateException("Unexpected expansion ${node.getToken()} -> ${node.getExpansion()} visited while visiting traversing tree") 
    }

    fun visit_closure(node: ASTNode.InnerNode<RegexGrammarInfo.closure>): R {
        return when (val id = node.getExpansion().getId()) {
            0 -> visit_closure_0(node)
            1 -> visit_closure_1(node)
            else -> throw IllegalStateException("Unexpected expansion id $id in expansion of closure")
        }
    }

    /**
    closure -> KLEENE closure
    */
    fun visit_closure_0(node: ASTNode.InnerNode<RegexGrammarInfo.closure>): R {
        throw IllegalStateException("Unexpected expansion ${node.getToken()} -> ${node.getExpansion()} visited while visiting traversing tree") 
    }

    /**
    closure -> <eps>
    */
    fun visit_closure_1(node: ASTNode.InnerNode<RegexGrammarInfo.closure>): R {
        throw IllegalStateException("Unexpected expansion ${node.getToken()} -> ${node.getExpansion()} visited while visiting traversing tree") 
    }

    fun visit_atom(node: ASTNode.InnerNode<RegexGrammarInfo.atom>): R {
        return when (val id = node.getExpansion().getId()) {
            2 -> visit_atom_2(node)
            3 -> visit_atom_3(node)
            else -> throw IllegalStateException("Unexpected expansion id $id in expansion of atom")
        }
    }

    /**
    atom -> LPAREN regex RPAREN
    */
    fun visit_atom_2(node: ASTNode.InnerNode<RegexGrammarInfo.atom>): R {
        throw IllegalStateException("Unexpected expansion ${node.getToken()} -> ${node.getExpansion()} visited while visiting traversing tree") 
    }

    /**
    atom -> ALPHA
    */
    fun visit_atom_3(node: ASTNode.InnerNode<RegexGrammarInfo.atom>): R {
        throw IllegalStateException("Unexpected expansion ${node.getToken()} -> ${node.getExpansion()} visited while visiting traversing tree") 
    }

}