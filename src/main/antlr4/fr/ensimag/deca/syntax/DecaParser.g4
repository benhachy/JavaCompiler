parser grammar DecaParser;

options {
    // Default language but name it anyway
    //
    language  = Java;

    // Use a superclass to implement all helper
    // methods, instance variables and overrides
    // of ANTLR default methods, such as error
    // handling.
    //
    superClass = AbstractDecaParser;

    // Use the vocabulary generated by the accompanying
    // lexer. Maven knows how to work out the relationship
    // between the lexer and parser and will build the
    // lexer before the parser. It will also rebuild the
    // parser if the lexer changes.
    //
    tokenVocab = DecaLexer;

}

// which packages should be imported?
@header {
    import fr.ensimag.deca.tree.*;
    import fr.ensimag.deca.tools.*;
    import java.io.PrintStream;
    import fr.ensimag.deca.tree.IfThenElse;
}

@members {   
    @Override
    protected AbstractProgram parseProgram() {
        return prog().tree;
    }
}

prog returns[AbstractProgram tree]
    : list_classes main EOF {
            assert($list_classes.tree != null);
            assert($main.tree != null);
            $tree = new Program($list_classes.tree, $main.tree);
            setLocation($tree, $list_classes.start);
        }
    ;

main returns[AbstractMain tree]
    : /* epsilon */ {
            $tree = new EmptyMain();
        }
    | block {
            assert($block.decls != null);
            assert($block.insts != null);
            $tree = new Main($block.decls, $block.insts);
            setLocation($tree, $block.start);
        }
    ;

block returns[ListDeclVar decls, ListInst insts]
    : OBRACE list_decl list_inst CBRACE {
            assert($list_decl.tree != null);
            assert($list_inst.tree != null);
            $decls = $list_decl.tree;
            $insts = $list_inst.tree;
        }
    ;

list_decl returns[ListDeclVar tree]
@init   {
            $tree = new ListDeclVar();
        }
    : decl_var_set[$tree]*
    ;

decl_var_set[ListDeclVar l]
    : type list_decl_var[$l,$type.tree] SEMI
    ;

list_decl_var[ListDeclVar l, AbstractIdentifier t]
    : dv1=decl_var[$t] {
        $l.add($dv1.tree);
        } (COMMA dv2=decl_var[$t] {
            assert($dv2.tree != null);
            $l.add($dv2.tree);
        }
      )*
    ;

decl_var[AbstractIdentifier t] returns[AbstractDeclVar tree]
@init   {
        AbstractInitialization initiate;
        }
    : i=ident {
            assert($i.tree != null);
            initiate=new NoInitialization();

        }
      (EQUALS e=expr {
            assert($e.tree != null);
            initiate = new Initialization($e.tree);
            setLocation(initiate, $e.start); 
        }
      )? 
      {
          $tree = new DeclVar(t,$i.tree,initiate);
          setLocation($tree, $i.start);
        }
    ;

list_inst returns[ListInst tree]
@init {
    $tree = new ListInst();
}
    : (inst {
        $tree.add($inst.tree);
        setLocation($tree,$inst.start);
        }
      )*
    ;

inst returns[AbstractInst tree]
    : e1=expr SEMI {
            assert($e1.tree != null);
            $tree=$e1.tree;
        }
    | SEMI {
            $tree = new NoOperation();
            setLocation($tree,$SEMI);
        }
    | PRINT OPARENT list_expr CPARENT SEMI {
            assert($list_expr.tree != null);
            $tree = new Print(false, $list_expr.tree);
            setLocation($tree, $PRINT);
        }
    | PRINTLN OPARENT list_expr CPARENT SEMI {
            assert($list_expr.tree != null);
            $tree = new Println(false, $list_expr.tree);
            setLocation($tree, $PRINTLN);
        }
    | PRINTX OPARENT list_expr CPARENT SEMI {
            assert($list_expr.tree != null);
            $tree = new Print(true, $list_expr.tree);
            setLocation($tree, $PRINTX);

        }
    | PRINTLNX OPARENT list_expr CPARENT SEMI {
            assert($list_expr.tree != null);
            $tree = new Println(true, $list_expr.tree);
            setLocation($tree, $PRINTLNX);
        }
    | if_then_else {
            assert($if_then_else.tree != null);
            $tree=$if_then_else.tree;
        }
    | WHILE OPARENT condition=expr CPARENT OBRACE body=list_inst CBRACE {
            assert($condition.tree != null);
            assert($body.tree != null);
            $tree = new While($condition.tree,$list_inst.tree);
            setLocation($tree,$condition.start);
        }
    | RETURN expr SEMI {
            assert($expr.tree != null);
        }
    ;

if_then_else returns[IfThenElse tree]
@init {
        ListInst elseBranch = new ListInst();
        ListInst secondElseBranch = new ListInst();
        IfThenElse newElseIF;
}
    : if1=IF OPARENT condition=expr CPARENT OBRACE li_if=list_inst CBRACE {
        
            $tree= new IfThenElse($condition.tree,$li_if.tree,elseBranch);
            setLocation($tree,$if1);
            
        }
      (ELSE elsif=IF OPARENT elsif_cond=expr CPARENT OBRACE elsif_li=list_inst CBRACE {
            newElseIF= new IfThenElse($elsif_cond.tree,$elsif_li.tree,secondElseBranch);
            elseBranch.add(newElseIF);
            setLocation(newElseIF,$elsif);
            $tree = newElseIF;
        }
      )*
      (ELSE OBRACE li_else=list_inst CBRACE {
            $tree.setElseBranch($li_else.tree);
        }
      )?
    ;

list_expr returns[ListExpr tree]
@init   {
        $tree = new ListExpr();
        }
    : (e1=expr {
        $tree.add($e1.tree);
        }
       (COMMA e2=expr {
           $tree.add($e2.tree);
        }
       )* )?
    ;

expr returns[AbstractExpr tree]
    : assign_expr {
            assert($assign_expr.tree != null);
            $tree = $assign_expr.tree;
            setLocation($tree,$assign_expr.start);
        }
    ;

assign_expr returns[AbstractExpr tree]
    : e=or_expr (
        /* condition: expression e must be a "LVALUE" */ {
            if (! ($e.tree instanceof AbstractLValue)) {
                throw new InvalidLValue(this, $ctx);
            }
        }
        EQUALS e2=assign_expr {
            assert($e.tree != null);
            assert($e2.tree != null);
            $tree = new Assign( (AbstractLValue)$e.tree,$e2.tree);
            setLocation($tree,$EQUALS);
        }
      | /* epsilon */ {
            assert($e.tree != null);
            $tree = $e.tree;
        }
      )
    ;

or_expr returns[AbstractExpr tree]
    : e=and_expr {
            assert($e.tree != null);
            $tree = $e.tree;
        }
    | e1=or_expr OR e2=and_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Or($e1.tree,$e2.tree);
            setLocation($tree,$OR);
       }
    ;

and_expr returns[AbstractExpr tree]
    : e=eq_neq_expr {
            assert($e.tree != null);
            $tree = $e.tree;
        }
    |  e1=and_expr AND e2=eq_neq_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new And($e1.tree,$e2.tree);
            setLocation($tree,$AND);
        }
    ;

eq_neq_expr returns[AbstractExpr tree]
    : e=inequality_expr {
            assert($e.tree != null);
             $tree = $e.tree;
        }
    | e1=eq_neq_expr EQEQ e2=inequality_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Equals($e1.tree,$e2.tree);
            setLocation($tree,$EQEQ);
        }
    | e1=eq_neq_expr NEQ e2=inequality_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new NotEquals($e1.tree,$e2.tree);
            setLocation($tree,$NEQ);
        }
    ;

inequality_expr returns[AbstractExpr tree]
    : e=sum_expr {
            assert($e.tree != null);
            $tree = $e.tree;
        }
    | e1=inequality_expr LEQ e2=sum_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new LowerOrEqual($e1.tree,$e2.tree);
            setLocation($tree,$LEQ);
        }
    | e1=inequality_expr GEQ e2=sum_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new GreaterOrEqual($e1.tree,$e2.tree);
            setLocation($tree,$GEQ);
        }
    | e1=inequality_expr GT e2=sum_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Greater($e1.tree,$e2.tree);
            setLocation($tree,$GT);
        }
    | e1=inequality_expr LT e2=sum_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Lower($e1.tree,$e2.tree);
            setLocation($tree,$LT);
        }
    | e1=inequality_expr INSTANCEOF type {
            assert($e1.tree != null);
            assert($type.tree != null);
        }
    ;


sum_expr returns[AbstractExpr tree]
    : e=mult_expr {
            assert($e.tree != null);
            $tree = $e.tree;
        }
    | e1=sum_expr PLUS e2=mult_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Plus($e1.tree,$e2.tree);
            setLocation($tree,$PLUS);
        }
    | e1=sum_expr MINUS e2=mult_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Minus($e1.tree,$e2.tree);
            setLocation($tree,$MINUS);
        }
    ;

mult_expr returns[AbstractExpr tree]
    : e=unary_expr {
            assert($e.tree != null);
            $tree = $e.tree;
        }
    | e1=mult_expr TIMES e2=unary_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Multiply($e1.tree,$e2.tree);
            setLocation($tree,$TIMES);
        }
    | e1=mult_expr SLASH e2=unary_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Divide($e1.tree,$e2.tree);
            setLocation($tree,$SLASH);
        }
    | e1=mult_expr PERCENT e2=unary_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Modulo($e1.tree,$e2.tree);
            setLocation($tree,$PERCENT);
        }
    ;

unary_expr returns[AbstractExpr tree]
    : op=MINUS e=unary_expr {
            assert($e.tree != null);
            $tree = new UnaryMinus($e.tree);
            setLocation($tree,$op);
        }
    | op=EXCLAM e=unary_expr {
            assert($e.tree != null);
            $tree = new Not($e.tree);
            setLocation($tree,$op);
        }
    | select_expr {
            assert($select_expr.tree != null);
            $tree = $select_expr.tree;
        }
    ;

select_expr returns[AbstractExpr tree]
    : e=primary_expr {
            assert($e.tree != null);
            $tree = $e.tree;
        }
    //not yet implemented partie objet
    | e1=select_expr DOT i=ident {
            assert($e1.tree != null);
            assert($i.tree != null);
        }
        (o=OPARENT args=list_expr CPARENT {
            // we matched "e1.i(args)"
            assert($args.tree != null);
            
        }
        | /* epsilon */ {
            // we matched "e.i"
        }
        )
    ;

primary_expr returns[AbstractExpr tree]
    : ident {
            assert($ident.tree != null);
            $tree=$ident.tree;
        }
    | m=ident OPARENT args=list_expr CPARENT {
            assert($args.tree != null);
            assert($m.tree != null);
            //not yet
        }
    | OPARENT expr CPARENT {
            assert($expr.tree != null);
            $tree=$expr.tree;
        }
    | READINT OPARENT CPARENT {
        //ce constructeur n'est pas implemente
            $tree = new ReadInt();
            setLocation($tree,$READINT);

        }
    | READFLOAT OPARENT CPARENT {
        //ce constructeur n'est pas implemente
            $tree = new ReadFloat();
            setLocation($tree,$READFLOAT);
        }
    | NEW ident OPARENT CPARENT {
            assert($ident.tree != null);
            // $tree = new New($ident.tree);
            // setLocation($tree,$NEW);
        }
    | cast=OPARENT type CPARENT OPARENT expr CPARENT {
        //not yet implemented
            assert($type.tree != null);
            assert($expr.tree != null);
        }
    | literal {
            assert($literal.tree != null);
            $tree = $literal.tree;
        }
    ;

type returns[AbstractIdentifier tree]
    : ident {
            assert($ident.tree != null);
            $tree=$ident.tree;
        }
    ;

literal returns[AbstractExpr tree]
    : INT {
        //il faut ajouter un try catch erreur ici
            $tree = new IntLiteral(Integer.parseInt($INT.text));
            setLocation($tree, $INT);
        }
    | fd=FLOAT {
            $tree = new FloatLiteral(Float.parseFloat( $fd.text));
            setLocation($tree, $fd);
        }
    | STRING {
            $tree = new StringLiteral($STRING.text.substring(1,$STRING.text.length()-1));
            setLocation($tree, $STRING);
        }
    | TRUE {
            $tree = new BooleanLiteral(true);
            setLocation($tree, $TRUE);
        }
    | FALSE {
            $tree = new BooleanLiteral(false);
            setLocation($tree, $FALSE);
        }
    | THIS {
            // $tree = new This();
            // setLocation($tree, $THIS);

        }
    | NULL {
            // $tree = new Null();
            // setLocation($tree, $NULL);

        }
    ;

ident returns[AbstractIdentifier tree]
    : IDENT {
        $tree = new Identifier( SymbolTable.creerSymbol($IDENT.text) );
        setLocation($tree, $IDENT);
        }
    ;

/****     Class related rules     ****/

list_classes returns[ListDeclClass tree]
@init{
    $tree = new ListDeclClass();
}
    :
      (c1=class_decl {
        }
      )*
    ;

class_decl
    : CLASS name=ident superclass=class_extension OBRACE class_body CBRACE {
        }
    ;

class_extension returns[AbstractIdentifier tree]
    : EXTENDS ident {
        }
    | /* epsilon */ {
        }
    ;

class_body
    : (m=decl_method {
        }
      | decl_field_set
      )*
    ;

decl_field_set
    : v=visibility t=type list_decl_field
      SEMI
    ;

visibility
    : /* epsilon */ {
        }
    | PROTECTED {
        }
    ;

list_decl_field
    : dv1=decl_field
        (COMMA dv2=decl_field
      )*
    ;

decl_field
    : i=ident {
        }
      (EQUALS e=expr {
        }
      )? {
        }
    ;

decl_method
@init {
}
    : type ident OPARENT params=list_params CPARENT (block {
        }
      | ASM OPARENT code=multi_line_string CPARENT SEMI {
        }
      ) {
        }
    ;

list_params
    : (p1=param {
        } (COMMA p2=param {
        }
      )*)?
    ;

multi_line_string returns[String text, Location location]
    : s=STRING {
            $text = $s.text;
            $location = tokenLocation($s);
        }
    | s=MULTI_LINE_STRING {
            $text = $s.text;
            $location = tokenLocation($s);
        }
    ;

param
    : type ident {
        }
    ;