grammar Prefix;

// keywords:

IF                      : 'if' ;
FOR                     : 'for' ;
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
NE                      : '!=' ;

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
codeBlock               : statement | ifBlock | forBlock | PASS ;

statement               : print | assignment ;
print                   : PRINT expression ;
assignment              : ASSIGN VAR expression ;

ifBlock                 : IF logicalExpression innerBody innerBody ;
forBlock                : FOR VAR arithmeticAtom arithmeticAtom innerBody ;
innerBody               : codeBlock | BOTH codeBlock innerBody ;

expression              : logicalExpression | arithmeticExpression ;

logicalBinop            : AND | OR | XOR ;
logicalUnop             : NOT ;
logicalExpression       : logicalBinop logicalExpression logicalExpression
                        | logicalUnop logicalExpression
                        | logicalAtom ;
logicalAtom             : VAR | comparison ;

relation                : EQ | NE | GT | GE | LT | LE ;
comparison              : relation arithmeticExpression arithmeticExpression ;

arithmeticBinop         : PLUS | MINUS | TIMES | DIV ;
arithmeticExpression    : arithmeticBinop arithmeticExpression arithmeticExpression
                        | arithmeticAtom ;
arithmeticAtom          : VAR | NUMBER ;