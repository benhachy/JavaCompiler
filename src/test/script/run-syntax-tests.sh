#!/bin/sh

echo "----- Syntax tests run -----"

cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:"$PATH"

echo "----- Invalid tests Battery -----"

for cas_de_test in src/test/deca/syntax/invalid/*.deca
do
    if test_synt $cas_de_test 2>&1 | grep -q -e "$cas_de_test"
    then
        echo "Test passed for file $cas_de_test"
    else
        echo "Test failed for file  $cas_de_test"
    fi
done

for cas_de_test in src/test/deca/syntax/valid/*.deca
do
    if test_synt $cas_de_test 2>&1 | grep -q -e "$cas_de_test"
    then
        echo "Test failed for file  $cas_de_test"
    else
        echo "Test passed for file $cas_de_test"
    fi
done

: <<'END_COMMENT'
PROJET_DIR=$(cd "$(dirname "$0")"/../../../ && pwd)
cd $PROJET_DIR"/src/test/deca/syntax/invalid"
CP_FILE="$PROJET_DIR"/target/generated-sources/classpath.txt
CP="$PROJET_DIR"/target/test-classes/:"$PROJET_DIR"/target/classes/:$(cat "$CP_FILE")


cd "$(dirname "$0")"/../../.. || exit 1
PATH=./src/test/script/launchers:"$PATH"

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
    if exec java -enableassertions -cp "$CP" fr.ensimag.deca.syntax.ManualTestLex "$i" 2>&1 \
        | grep -q -e $i
    then
        echo "Test Failed for file $i"
    else
        echo "Test Passed for file $i"
    fi
done

END_COMMENT