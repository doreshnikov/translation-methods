package utils

import grammar.Expansion
import grammar.Token

typealias ST = Token.State
typealias AT = Token.AlphaToken
typealias PD = Token.PredefinedToken
typealias E = Expansion

typealias END = Token.END
typealias EPSILON = Token.EPSILON

typealias TR = TokenRestricted
typealias TRUniversal = TokenRestricted.Universal
typealias TRState = TokenRestricted.State
typealias TRFirst = TokenRestricted.First
typealias TRFollow = TokenRestricted.Follow