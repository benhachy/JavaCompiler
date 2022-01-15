#!/bin/sh

echo "----- Code Gen tests run -----"

cd "$(dirname "$0")"/../../.. || exit 1

cd "src/test/deca/codegen/valid"

PATH=./src/test/script/launchers:"$PATH"

for cas_de_test in *.deca
do
    if decac $cas_de_test 2>&1 | grep -q -e "$cas_de_test"
    then
        echo "Test failed - Compilation for $cas_de_test Failed"
    else
        read line<$cas_de_test
        expected_output=$(cat $cas_de_test | egrep -e "^//" | sed '0,/Resultats:/d' | sed '/Historique:/Q' | cut -c7- | sed '/^$/d')
        if [ "$expected_output" = "" ]; then
            echo "Test failed - Program compiled but no expected output found for $cas_de_test"
        else
            file_name=${cas_de_test%.*}
            program_output=$(ima $file_name.ass)
            if [ "$program_output" = "$expected_output" ]; then
                echo "Test passed - program output equals expected output for $cas_de_test"
            else
                echo "Test failed - Program output: $program_output for $cas_de_test"
            fi
        fi
    fi
done
