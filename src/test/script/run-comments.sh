#!/bin/sh

echo "----- Messages d'erreurs -----"

cd "$(dirname "$0")"/../../.. || exit 1

cd "src/test/deca/context/invalid/created"

RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m'

PATH=./src/test/script/launchers:"$PATH"

for cas_de_test in *.deca
do
    test_contex cas_de_test
done
