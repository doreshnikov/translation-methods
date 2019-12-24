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

code                    : code_block* EOF ;
code_block              : statement | if_block | PASS ;

statement               : print | assignment ;
print                   : PRINT expression ;
assignment              : VAR ASSIGN expression ;

if_block                : IF logical_expression if_body if_body ;
if_body                 : code_block | BOTH code_block if_body ;

expression              : logical_expression | arithmetic_expression ;

logical_binop           : AND | OR | XOR ;
logical_unop            : NOT ;
logical_expression      : logical_binop logical_expression logical_expression
                        | logical_unop logical_expression
                        | l_atom ;
l_atom                  : VAR | comparison ;

relation                : EQ | GT | GE | LT | LE ;
comparison              : relation arithmetic_expression arithmetic_expression ;

arithmetic_binop        : PLUS | MINUS | TIMES | DIV ;
arithmetic_expression   : arithmetic_binop arithmetic_expression arithmetic_expression
                        | arithmetic_atom ;
arithmetic_atom         : VAR | NUMBER ;