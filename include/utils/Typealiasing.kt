package utils

import grammar.Expansion
import grammar.token.RepresentedBy
import grammar.token.Token
import grammar.token.RestrictedBy

typealias ST = Token.State
typealias PD = Token.SpecialToken
typealias AT = Token.AlphaToken
typealias E = Expansion

typealias END = Token.END
typealias EPSILON = Token.EPSILON

typealias NUMBER = Token.RepresentationToken.AnyNumber
typealias ALPHA = Token.RepresentationToken.AnyAlpha

typealias TR = RestrictedBy
typealias TRGeneral = RestrictedBy.General
typealias TRLexer = RestrictedBy.Lexer
typealias TRRepresentation = RestrictedBy.Representation
typealias TRState = RestrictedBy.State
typealias TRFirst = RestrictedBy.First
typealias TRFollow = RestrictedBy.Follow

typealias RB<T> = RepresentedBy<T>
typealias RBObject<T> = RepresentedBy.RepresentedByObject<T>