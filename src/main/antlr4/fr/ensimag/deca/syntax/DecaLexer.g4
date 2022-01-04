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
fragment LETTER : ( 'a'  ..  'z' | 'A'  ..  'Z'){
   System.out.println("Lettre retrouvé");
   System.out.println(getText());
}; 
fragment DIGIT : '0' .. '9';
COMMENT : '//' .*? '\n' | '/*' .*? '*/' {skip()}; 
CPARENT : ')';
OPARENT : '(';
SEMI : ';' ;
OBRACE : '{' ;
CBRACE :'}' ;
COMMA : ',' ;
EQUALS : '==' ;




STRING_CAR : LETTER | DIGIT | '<'  | '>' | '=' | '+' | '-' |  '*'  | '/' |  '%' |  '.' | COMMA | OPARENT  |  CPARENT  | OBRACE | CBRACE
'!' |  SEMI | EQUALS | '!=' | '>=' | '<=' | '&&' | '||';


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

   
  
