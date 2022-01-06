#!/bin/sh

#echo "Syntax tests run"

#echo "Invalid tests Battery"

#we change directory to the invalid syntaxt test directory
PROJET_DIR=$(cd "$(dirname "$0")"/../../../ && pwd)
cd $PROJET_DIR"/src/test/deca/syntax/invalid"
CP_FILE="$PROJET_DIR"/target/generated-sources/classpath.txt
CP="$PROJET_DIR"/target/test-classes/:"$PROJET_DIR"/target/classes/:$(cat "$CP_FILE")

#exec java -enableassertions -cp "$CP" fr.ensimag.deca.syntax.ManualTestLex "chaine_incomplete.deca" 2>1

for i in *.deca 
do
    #echo " $i "
    if exec java -enableassertions -cp "$CP" fr.ensimag.deca.syntax.ManualTestLex "$i" 2>&1 \
        | grep -q -e $i
    then
        echo "Test Passed for file $i"
    else
        echo "Test Failed for file $i"
    fi
done

#we change directory to the valid syntaxt test directory
cd ".."
cd "valid"

for i in *.deca 
do
    #echo " $i "
    if exec java -enableassertions -cp "$CP" fr.ensimag.deca.syntax.ManualTestLex "$i" 2>&1 \
        | grep -q -e $i
    then
        echo "Test Failed for file $i"
    else
        echo "Test Passed for file $i"
    fi
done
