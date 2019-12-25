grammar prefix;

// keywords:

IF                      : 'if' ;
PASS                    : 'pass' ;
BOTH                    : 'both' ;
PRINT                   : 'print' ;

// operators:

PLUS                    : '+' ;
MINUS                   : '-' ;
TIMES                   : '*' ;
DIV                     : '/' ;

NOT                     : '!' ;
XOR                     : '^' ;
AND                     : '&' ;
OR                      : '|' ;

GE                      : '>=' ;
GT                      : '>' ;
LE                      : '<=' ;
LT                      : '<' ;
EQ                      : '==' ;

ASSIGN                  : '=' ;

// variables:

fragment LETTER         : [a-zA-Z] ;
fragment START          : LETTER | '_' ;
fragment DIGIT          : [0-9] ;
fragment SIGN           : PLUS | MINUS ;

WHITESPACE              : [ \r\n\t] -> skip ;
NUMBER                  : DIGIT+ ;
VAR                     : START (START | DIGIT)* ;

// parser:

code                    : codeBlock* EOF ;
codeBlock               : statement | ifBlock | PASS ;

statement               : print | assignment ;
print                   : PRINT expression ;
assignment              : ASSIGN VAR expression ;

ifBlock                 : IF logicalExpression ifBody ifBody ;
ifBody                  : codeBlock | BOTH codeBlock ifBody ;

expression              : logicalExpression | arithmeticExpression ;

logicalBinop            : AND | OR | XOR ;
logicalUnop             : NOT ;
logicalExpression       : logicalBinop logicalExpression logicalExpression
                        | logicalUnop  logicalExpression
                        | logicalAtom ;
logicalAtom             : VAR | comparison ;

relation                : EQ | GT | GE | LT | LE ;
comparison              : relation arithmeticExpression arithmeticExpression ;

arithmeticBinop         : PLUS | MINUS | TIMES | DIV ;
arithmeticExpression    : arithmeticBinop arithmeticExpression arithmeticExpression
                        | arithmeticAtom ;
arithmeticAtom          : VAR | NUMBER ;