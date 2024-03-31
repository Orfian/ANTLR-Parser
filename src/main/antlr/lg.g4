grammar lg;

program: statement+ EOF;

statement: type (ID',')* ID';'                                          #declarationStatement
         | 'read' (ID',')* ID';'                                        #readStatement
         | 'write' (expression',')* expression';'                       #writeStatement
         | '{' statement* '}'                                           #blockStatement
         | 'if' '(' expression ')' statement ('else' statement)?        #ifStatement
         | 'while' '(' expression ')' statement                         #whileStatement
         | expression';'                                                #expressionStatement
         | ';'                                                          #emptyStatement
         ;

type: 'int'
    | 'float'
    | 'bool'
    | 'string'
    ;

expression: '-' expression                          # unaryMinus
          | '!' expression                          # logicalNot
          | '(' expression ')'                      # parentheses
          | expression op=('*'|'/'|'%') expression  # arithmetic
          | expression op=('+'|'-') expression      # arithmetic
          | expression '.' expression               # concatenation
          | expression op=('<'|'>') expression      # relational
          | expression op=('=='|'!=') expression    # equality
          | expression '&&' expression              # logical
          | expression '||' expression              # logical
          | <assoc=right> ID '=' expression         # assignment
          | ID                                      # identifier
          | literal                                 # variable
          ;

literal: INT
       | FLOAT
       | BOOL
       | STRING
       ;


INT :       '0'|[1-9][0-9]* ;
FLOAT :     [0-9]+'.'[0-9]+ ;
BOOL :      'true'|'false';
STRING :    '"' (~["\\\r\n] | EscapeSequence)* '"';
ID :        [a-zA-Z][a-zA-Z0-9]* ;

LINE_COMMENT :  '//' ~[\r\n]*       -> skip;
WS :            [ \t\r\n\u000C]+    -> skip;

fragment EscapeSequence:
    '\\' 'u005c'? [btnfr"'\\]
  | '\\' 'u005c'? ([0-3]? [0-7])? [0-7]
;