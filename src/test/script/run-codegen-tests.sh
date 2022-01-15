#!/bin/sh

echo "----- Code Gen tests run -----"

cd "$(dirname "$0")"/../../.. || exit 1

cd "src/test/deca/codegen/valid"

PATH=./src/test/script/launchers:"$PATH"

for cas_de_test in *.deca
do
    file_header=$(cat $cas_de_test | egrep -e "^//")
    echo $file_header
done

<<END_COMMENT
if decac $cas_de_test 2>&1 | grep -q -e "$cas_de_test"
then
    echo "Compilation for $cas_de_test Failed"
else
    echo "Assambler file for $cas_de_test generated"
    read line<$cas_de_test
    expected_output=${line#*//}
    file_name=${cas_de_test%.*}
    program_output=$(ima $file_name.ass)
    if [ $program_output = $expected_output ]; then
        echo "Test passed program output equals expected output"
    else
        echo "Test failed, Program output:"
        echo $program_output
    fi
fi
END_COMMENT