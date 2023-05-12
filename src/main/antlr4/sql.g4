grammar sql;

@parser::header {
package parser;
}

@lexer::header {
package parser;
}

CREATE: C R E A T E;

TABLE: T A B L E;

PRIMARY: P R I M A R Y; 

KEY: K E Y;

INT: I N T;

INTEGER: I N T E G E R;

VARCHAR: V A R C H A R;

DATE: D A T E;

DOUBLE:D O U B L E;

UPDATE:U P D A T E;

SET:S E T;

WHERE:W H E R E;

INSERT:I N S E R T;

INTO :I N T O;

VALUES:V A L U E S;

DELETE: D E L E T E;

FROM: F R O M;

AND: A N D;

INDEX: I N D E X;

ON: O N;

USING: U S I N G;

OCTREE: O C T R E E;

statement:insert|update|create_table|delete|create_index;

create_index:CREATE INDEX index_name ON table_name index_columns USING OCTREE SEMICOL?;

index_name: ID;

index_columns: LPRAN index_column (COMMA index_column)* RPRAN;

index_column: ID;

create_table: CREATE TABLE table_name creation_columns SEMICOL?;

creation_columns: LPRAN creation_column (COMMA creation_column)* COMMA PRIMARY KEY LPRAN pk RPRAN RPRAN;
 
creation_column: name data_type;

pk:ID;

name: ID;

data_type:integer (LPRAN INTEG RPRAN)? | VARCHAR LPRAN INTEG RPRAN | DOUBLE LPRAN INTEG COMMA INTEG RPRAN | DATE;

integer: INT | INTEGER;

update : UPDATE table_name SET update_list WHERE update_condition SEMICOL?;

update_list: update_item (COMMA update_item)*;

update_item: update_name EQUAL update_value;

update_name: column_name;

update_value:value;

update_condition: update_condition_name EQUAL update_condition_value;

update_condition_name:column_name;

update_condition_value:value;

delete: DELETE FROM table_name (WHERE delete_conditions)? SEMICOL?;

delete_conditions: delete_condition (AND delete_condition)*;

delete_condition: delete_condition_name EQUAL delete_condition_value;

delete_condition_name:column_name;

delete_condition_value:value;

insert: INSERT INTO table_name column_list? values_clause SEMICOL?;

column_list: LPRAN column_name (COMMA column_name)* RPRAN;

values_clause: VALUES LPRAN value (COMMA value)* RPRAN;

value: INTEG | STRING | DOUB| DAT;

table_name: ID;

column_name: ID|DATE|CREATE|TABLE|PRIMARY|KEY|INT|INTEGER|VARCHAR|DATE|DOUBLE|UPDATE|SET|WHERE|INSERT|INTO|VALUES|DELETE|FROM|AND;

ID: [a-zA-Z_][a-zA-Z0-9_]*;

INTEG: [0-9]+;

DOUB: INTEG POINT INTEG;

DAT: '\''( [0-9][0-9][0-9][0-9] DASH MONTH DASH DAY ) '\'';

MONTH: [0][1-9] | [1][0-2];

DAY: [0][1-9] | [1-2][0-9] | [3][0-1];

DASH:'-';

POINT: '.';

SEMICOL:';';

STRING:	( 'n' )? '\'' ( '\'\'' | ~('\'') )* '\'' | ( 'n' )? DOUBLE_QUOTE ( '\'\'' | ~('\'') )* DOUBLE_QUOTE | ( 'n' )? DOUBLE_QUOTE STRING DOUBLE_QUOTE ;

DOUBLE_QUOTE:'"';

EQUAL: '=';

LPRAN: '(';

RPRAN:')';

COMMA:',';

WS: [ \t\r\n]+ -> skip;

fragment A: [aA];
fragment B: [bB];
fragment C: [cC];
fragment D: [dD];
fragment E: [eE];
fragment F: [fF];
fragment G: [gG];
fragment H: [hH];
fragment I: [iI];
fragment J: [jJ];
fragment K: [kK];
fragment L: [lL];
fragment M: [mM];
fragment N: [nN];
fragment O: [oO];
fragment P: [pP];
fragment Q: [qQ];
fragment R: [rR];
fragment S: [sS];
fragment T: [tT];
fragment U: [uU];
fragment V: [vV];
fragment W: [wW];
fragment X: [xX];
fragment Y: [yY];
fragment Z: [zZ];

