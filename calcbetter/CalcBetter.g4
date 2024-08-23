grammar CalcBetter;

prog: stat+ ;

stat: 'print' expr SEMICOLON # printExpr
    | ID '=' expr SEMICOLON # assign
    ;

expr: expr op=('*'|'/') expr # MulDiv
    | expr op=('+'|'-') expr # AddSub
    | INT # int
    | ID # id
    | '(' expr ')' # parens
    ;

ID : [a-zA-Z]+ ;
INT : [0-9]+ ;
NEWLINE: '\r'? '\n' -> skip;
WS : [ \t]+ -> skip ;
SEMICOLON : ';' ;
MUL: '*' ;
DIV: '/' ;
SUB: '-' ;
ADD: '+' ;

