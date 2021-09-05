#!/usr/bin/env bash

rm ./data/duke_storage.txt
cd text-ui-test

echo "Testing I/O and logic: "
cd test-1
bash ./runtest.sh

cd ..

echo "Testing loading tasks from file: "
cd test-2
bash ./runtest-2.sh