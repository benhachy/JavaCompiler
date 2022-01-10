#!/bin/sh

echo "----- $1 test -----"

#we change directory to the invalid syntaxt test directory
PROJET_DIR=$(cd "$(dirname "$0")"/../../../ && pwd)
cd $PROJET_DIR"/src/test/deca/syntax"
CP_FILE="$PROJET_DIR"/target/generated-sources/classpath.txt
CP="$PROJET_DIR"/target/test-classes/:"$PROJET_DIR"/target/classes/:$(cat "$CP_FILE")

#test execution using java deca lexer
exec java -enableassertions -cp "$CP" fr.ensimag.deca.syntax.ManualTestLex $1