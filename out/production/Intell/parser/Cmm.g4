grammar Cmm;

@header {
import ast.*;
import ast.definitions.*;
import ast.definitions.subclasses.*;
import ast.expression.*;
import ast.expression.subclasses.*;
import ast.statement.*;
import ast.statement.subclasses.*;
import ast.type.*;
import ast.type.subclasses.*;
}

program returns [Program ast]: program_definitions main_function { $program_definitions.ast.add($main_function.ast); } EOF { $ast = new Program(0,0,$program_definitions.ast); }
       ;
program_definitions returns [List<Definition> ast = new ArrayList<>()]:
        (variable_definition {$ast.addAll($variable_definition.ast);} | function_definition {$ast.add($function_definition.ast);})*
        ;

block returns [List<Statement> ast]: { $ast = new ArrayList<>(); }'{'(statement { $ast.addAll($statement.ast); } )*'}'
        | { $ast = new ArrayList<>(); } statement { $ast.addAll($statement.ast); }
        ;

variable_definition returns [List<VarDefinition> ast = new ArrayList<VarDefinition>()]:
            t=type id1=ID
                {$ast.add( new VarDefinition($t.ast.getLine(), $t.ast.getColumn(), $id1.text, $t.ast) );}
            (',' id2=ID {$ast.add( new VarDefinition($t.ast.getLine(), $t.ast.getColumn(), $id2.text, $t.ast) );} )* ';'
            ;

function_definition  returns [FuncDefinition ast] :
              primitive_type ID par=function_definition_params stm=function_block
                {$ast = new FuncDefinition($par.start.getLine(), $par.start.getCharPositionInLine()+1, $ID.text, $primitive_type.ast, $par.ast, $stm.ast);}
            | v='void' ID par=function_definition_params stm=function_block
                {$ast = new FuncDefinition($par.start.getLine(), $par.start.getCharPositionInLine()+1, $ID.text, new VoidType($v.getLine(), $v.getCharPositionInLine()+1), $par.ast, $stm.ast);}
            ;

function_block returns [List<Statement> ast = new ArrayList<>()]:
            '{' multiple_var_definitions { $ast.addAll($multiple_var_definitions.ast); } (statement { $ast.addAll($statement.ast); } )* '}'
            ;

function_definition_params returns [List<VarDefinition> ast = new ArrayList<>()]:
            '('
                (p=primitive_type i=ID
                    { $ast.add( new VarDefinition($p.ast.getLine(), $p.ast.getColumn(), $i.text, $p.ast) ); }
                (',' p2=primitive_type i2=ID { $ast.add( new VarDefinition($p2.ast.getLine(), $p2.ast.getColumn(), $i2.text, $p2.ast) ); } )*)?
            ')'
            ;

main_function returns [FuncDefinition ast]: v='void' 'main' '('')' function_block { $ast = new FuncDefinition($v.getLine(), $v.getCharPositionInLine()+1, "main", new VoidType($v.getLine(), $v.getCharPositionInLine()+1), new ArrayList<VarDefinition>(), $function_block.ast); }
            ;

write returns [List<Statement> ast]:
                { $ast = new ArrayList<>(); }
                    'write' w1=expression
                { $ast.add(new Write( $w1.ast.getLine(), $w1.ast.getColumn(), $w1.ast )); }
                    (',' w2=expression { $ast.add(new Write( $w2.ast.getLine(), $w2.ast.getColumn(), $w2.ast )); } )*
            ;
read returns [List<Statement> ast = new ArrayList<>(); ]:
                    'read' w1=expression
                { $ast.add(new Read( $w1.ast.getLine(), $w1.ast.getColumn(), $w1.ast )); }
                    (',' w2=expression { $ast.add(new Read( $w2.ast.getLine(), $w2.ast.getColumn(), $w2.ast )); } )*
            ;

statement returns [List<Statement> ast = new ArrayList<>()]:
              exp1=expression '=' exp2=expression ';'
                { $ast.add( new Assignment($exp1.ast.getLine(), $exp1.ast.getColumn(), $exp1.ast, $exp2.ast) ); }
            | read ';'
                { $ast = $read.ast; }
            | write ';'
                { $ast = $write.ast; }
            | if_statement
                { $ast.add($if_statement.ast); }
            | while_loop
                { $ast.add($while_loop.ast); }
            | function_invoke ';'
                { $ast.add($function_invoke.ast); }
            | 'return' exp=expression ';'
                { $ast.add(new Return($exp.ast.getLine(), $exp.ast.getColumn(), $exp.ast)); }
            ;

if_statement returns [IfElse ast]: 'if' '('expression')' block
                { $ast = new IfElse($expression.ast.getLine(), $expression.ast.getColumn(), $expression.ast, $block.ast, new ArrayList<>()); }
            | 'if' '('expression')' b1=block 'else' b2=block
                { $ast = new IfElse($expression.ast.getLine(), $expression.ast.getColumn(), $expression.ast, $b1.ast, $b2.ast); }
            ;
while_loop returns [WhileLoop ast]: 'while' '('expression')' block
                { $ast = new WhileLoop($expression.ast.getLine(), $expression.ast.getColumn(), $expression.ast, $block.ast ); }
            ;

expression returns [Expression ast]:
              '('exp=expression')'
                { $ast = $exp.ast; }
            | array=expression '['idx=expression']'
                { $ast = new Indexing($array.ast.getLine(),$array.ast.getColumn(),$array.ast,$idx.ast); }
            | function_invoke
                { $ast = $function_invoke.ast;}
            | '('t=type')' exp=expression
                { $ast = new Cast( $t.ast.getLine(), $t.ast.getColumn(), $t.ast, $exp.ast ); }
            | '-' exp=expression
                { $ast = new UnaryMinus( $exp.ast.getLine(), $exp.ast.getColumn(), $exp.ast ); }
            | exp=expression '.' ID
                { $ast = new FieldAccess( $exp.ast.getLine(), $exp.ast.getColumn(), $ID.text, $exp.ast );}
            | left=expression op=('*'|'/'|'%') right=expression
                { $ast = new Arithmetic( $left.ast.getLine(), $left.ast.getColumn(), $op.text, $left.ast, $right.ast ); }
            | left=expression op=('+'|'-') right=expression
                { $ast = new Arithmetic( $left.ast.getLine(), $left.ast.getColumn(), $op.text, $left.ast, $right.ast ); }
            | left=expression op=('>'|'>='|'<'|'<='|'!='|'==') right=expression
                { $ast = new Relational( $left.ast.getLine(), $left.ast.getColumn(), $op.text, $left.ast, $right.ast ); }
            | left=expression op=('&&'|'||') right=expression
                { $ast = new Logical( $left.ast.getLine(), $left.ast.getColumn(), $op.text, $left.ast, $right.ast ); }
            | '!' exp=expression
                { $ast = new UnaryNegation( $exp.ast.getLine(), $exp.ast.getColumn(), $exp.ast ); }
            | REAL_CONSTANT
                { $ast = new DoubleLiteral( $REAL_CONSTANT.getLine(), $REAL_CONSTANT.getCharPositionInLine()+1, LexerHelper.lexemeToReal($REAL_CONSTANT.text) ); }
            | INT_CONSTANT
                { $ast = new IntLiteral( $INT_CONSTANT.getLine(), $INT_CONSTANT.getCharPositionInLine()+1, LexerHelper.lexemeToInt($INT_CONSTANT.text) ); }
            | CHAR_CONSTANT
                { $ast = new CharLiteral( $CHAR_CONSTANT.getLine(), $CHAR_CONSTANT.getCharPositionInLine()+1, LexerHelper.lexemeToChar($CHAR_CONSTANT.text) ); }
            | ID
                { $ast = new Variable( $ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text ); }
            ;

function_invoke returns [FunctionInvoke ast]: ID '('')'
                { $ast = new FunctionInvoke( $ID.getLine(), $ID.getCharPositionInLine()+1, new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text) ,new ArrayList<Expression>() ); }
            | ID '('list_of_expressions')'
                { $ast = new FunctionInvoke( $ID.getLine(), $ID.getCharPositionInLine()+1, new Variable($ID.getLine(), $ID.getCharPositionInLine()+1, $ID.text) ,$list_of_expressions.ast ); }
            ;

list_of_expressions returns [List<Expression> ast = new ArrayList<>()]:
              expression { $ast.add($expression.ast); } (',' expression { $ast.add($expression.ast); })*
            ;

type returns [Type ast]: primitive_type { $ast = $primitive_type.ast; }
    | ty=type '['INT_CONSTANT']'
        { $ast = ArrayType.createArray($ty.ast.getLine(), $ty.ast.getColumn(), $ty.ast, LexerHelper.lexemeToInt($INT_CONSTANT.text) ); }

    | st='struct' '{'def=record_fields'}'
        { $ast = new RecordType( $st.getLine(), $st.getCharPositionInLine()+1, $def.ast ); }
    ;

multiple_var_definitions returns [List<VarDefinition> ast = new ArrayList<>()]:
    (variable_definition { $ast.addAll( $variable_definition.ast ); } )*
    ;

record_fields returns [List<RecordField> ast = new ArrayList<>()]:
        (record_fields_definition {$ast.addAll( $record_fields_definition.ast );} )*
    ;

record_fields_definition returns [List<RecordField> ast = new ArrayList<>()]:
    t=type id1=ID
        {$ast.add( new RecordField($t.ast.getLine(), $t.ast.getColumn(), $id1.text, $t.ast) );}
    (',' id2=ID {$ast.add( new RecordField($t.ast.getLine(), $t.ast.getColumn(), $id2.text, $t.ast) );} )* ';'
    ;

primitive_type returns [Type ast]: op='int' { $ast = new IntType( $op.getLine(), $op.getCharPositionInLine()+1 ); }
            | op='char' { $ast = new CharType( $op.getLine(), $op.getCharPositionInLine()+1 ); }
            | op='double' { $ast = new DoubleType( $op.getLine(), $op.getCharPositionInLine()+1 ); }
            ;



INT_CONSTANT: '0'
            | [1-9][0-9]*
            ;



CHAR_CONSTANT: '\''CHAR_CONTENT'\''
            ;
fragment
CHAR_CONTENT: .
            | '\\'INT_CONSTANT
            | '\\n'
            | '\\t'
            ;

fragment
DIGIT: [0-9]
        ;


REAL_CONSTANT: REAL_MANTISSA REAL_EXPONENT?
            | INT_CONSTANT REAL_EXPONENT
            ;

fragment
REAL_MANTISSA: (INT_CONSTANT)?'.'(DIGIT+)
            | (INT_CONSTANT)'.'DIGIT*
            ;
fragment
REAL_EXPONENT: ('e'|'E')'-'?INT_CONSTANT
            ;



SINGLE_LINE_COMMENT: '//'.*? ('\r'?'\n') -> skip // (EOF | '\r'?'\n')
            ;
MULTI_LINE_COMMENT: '/*'.*?'*/' -> skip
            ;


ID: ('_' | LETTER) ('_' | DIGIT | LETTER)*
            ;
fragment
LETTER: [a-zA-Z]
            ;

SPACE: (' ' | [\t\n\r]+) -> skip; //('\t' | ' ' | '\n' | '\r')+ -> skip
