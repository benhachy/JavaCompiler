#!/bin/sh

echo "----- Code Gen tests run -----"

cd "$(dirname "$0")"/../../.. || exit 1

cd "src/test/deca/codegen/valid/created"

RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m'

PATH=./src/test/script/launchers:"$PATH"

for cas_de_test in *.deca
do
    if decac $cas_de_test 2>&1 | grep -q -e "$cas_de_test"
    then
        echo "${RED}Test failed${NC} - Compilation for $cas_de_test Failed"
    else
        read line<$cas_de_test
        expected_output=$(cat $cas_de_test | egrep -e "^//" | sed '0,/Resultats:/d' | sed '/Historique:/Q' | cut -c7- | sed '/^$/d')
        if [ "$expected_output" = "" ]; then
            echo "${RED}Test failed${NC} - Program compiled but no expected output found for $cas_de_test"
        else
            file_name=${cas_de_test%.*}
            program_output=$(ima $file_name.ass)
            if [ "$program_output" = "$expected_output" ];
            then 
                echo "${GREEN}Test passed${NC} - program output equals expected output for $cas_de_test"
            elif [ "$expected_output" = "No output" ] && [ "$program_output" = "" ];
            then
                echo "${GREEN}Test passed${NC} - program output equals expected output for $cas_de_test"
            else
                echo "${RED}Test failed${NC} - Program output: $program_output for $cas_de_test"
            fi
        fi
        echo $expected_output
    fi
done
