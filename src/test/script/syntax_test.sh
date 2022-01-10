cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:"$PATH"


test_synt src/test/deca/syntax/$1
: <<'END_COMMENT'

if test_synt src/test/deca/syntax/$1 2>&1 | grep -q -e "$1"
then
    echo "Test failed for file  $1."
else
    echo "Test passed for file $1."
fi

: <<'END_COMMENT'
for cas_de_test in src/test/deca/syntax/invalid/provided/*.deca
do
    if test_synt "$cas_de_test" 2>&1 | grep -q -e "$cas_de_test"
    then
        echo "Test passed for file $cas_de_test."
    else
        echo "Test failed for file  $cas_de_test."
    fi
done
END_COMMENT