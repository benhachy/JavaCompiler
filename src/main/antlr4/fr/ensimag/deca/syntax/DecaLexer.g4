lexer grammar DecaLexer;

options {
   language=Java;
   // Tell ANTLR to make the generated lexer class extend the
   // the named class, which is where any supporting code and
   // variables will be placed.
   superClass = AbstractDecaLexer;
}

@members {
} 
fragment LETTER : ( 'a'  ..  'z' | 'A'  ..  'Z');
fragment DIGIT : '0' .. '9';

ASM : 'asm' ;
CLASS : 'class' ;
ELSE : 'else';
IF :'if';
NEW :'new' ;
NULL :'null' ;
READINT :'readInt' ;
READFLOAT :'readFloat' ;
PROTECTED :'protected' ;
RETURN :'return' ;
THIS :'this' ;
INSTANCEOF :'instanceof';
EXTENDS :'extends' ;
PRINTLN :  'println';
PRINTLNX :  'printlnx';
PRINT :  'print';
PRINTX :  'printx';
FALSE : 'false';
TRUE : 'true' ; 
WHILE : 'while';
 
IDENT : (LETTER | '$' | '_')(LETTER | DIGIT | '$' | '_')*;

COMMENT : ('//' .*? '\n' | '/*' .*? '*/'){
   skip();
}; 
CPARENT : ')';
OPARENT : '(';
SEMI : ';' ;
OBRACE : '{' ;
CBRACE :'}' ;
COMMA : ',' ;
EQUALS : '=' ;
AND : '&&';
DOT : '.' ;
EXCLAM : '!';
OR : '||';
NEQ : '!=';
EQEQ : '==';
POSITIVE_DIGIT : '1' .. '9';
INT : '0' | POSITIVE_DIGIT DIGIT* ;
PLUS : '+';
MINUS : '-';
GEQ : '>=';
LEQ : '<=';
GT : '>';
LT : '<';
TIMES : '*';
SLASH :  '/';
PERCENT : '%';
ESPACE : ' ';
TAB : '\t';
EOL : '\n'; 
STRING_CAR : LETTER | DIGIT | LT   | GT | EQUALS | PLUS | MINUS |  TIMES  | SLASH |  PERCENT |  DOT | COMMA | OPARENT  |  CPARENT  | OBRACE | CBRACE
EXCLAM |  SEMI | EQEQ | NEQ | GEQ | LEQ | AND | OR | ESPACE ;

NUM : DIGIT+ ;
SIGN : '+' | '-'  ;// Meme chose
EXP : ('E' | 'e') SIGN NUM;
DEC : NUM '.' NUM;
FLOATDEC : (DEC | DEC EXP) ('F' | 'f' ); // Normalement sans espace 
DIGITHEX : '0' .. '9' | 'A' .. 'F' | 'a' .. 'f';
NUMHEX : DIGITHEX+;
FLOATHEX : ('0x' | '0X') NUMHEX '.' NUMHEX ('P' | 'p') SIGN NUM ('F' | 'f' ); // Meme chose 
FLOAT : FLOATDEC | FLOATHEX;

STRING : '"' (STRING_CAR | '\\"' | '\\\\')* '"' ;
MULTI_LINE_STRING : '"' (STRING_CAR | '\n' |  '\\"' | '\\\\')* '"';

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {
              skip(); // avoid producing a token
          }
;


   
  