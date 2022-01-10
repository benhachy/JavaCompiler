#!/bin/sh

cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:"$PATH"

echo "----- context tests run -----"

echo "----- Invalid tests Battery -----"

for i in *.deca 
do
    #echo " $i "
    if test_context src/test/deca/context/invalid/provided/$i 2>&1
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
    if test_context src/test/deca/context/invalid/provided/$i 2>&1 |
        | grep -q -e $i
    then
        echo "Test Failed for file $i"
    else
        echo "Test Passed for file $i"
    fi
done