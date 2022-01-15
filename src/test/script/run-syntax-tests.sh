#!/bin/sh

echo "----- Syntax tests run -----"

cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:"$PATH"
RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m'

echo "----- Invalid tests Battery -----"

for cas_de_test in src/test/deca/syntax/invalid/*.deca
do
    if test_synt $cas_de_test 2>&1 | grep -q -e "$cas_de_test"
    then
        echo "${GREEN}Test passed${NC} - for file $cas_de_test"
    else
        echo "${RED}Test failed${NC} - for file  $cas_de_test"
    fi
done

echo "----- Valid tests Battery -----"

for cas_de_test in src/test/deca/syntax/valid/*.deca
do
    if test_synt $cas_de_test 2>&1 | grep -q -e "$cas_de_test"
    then
        echo "${RED}Test failed${NC} - for file  $cas_de_test"
    else
        echo "${GREEN}Test passed${NC} - for file $cas_de_test"
    fi
done