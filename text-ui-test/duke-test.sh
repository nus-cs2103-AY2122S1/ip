#!/usr/bin/env bash

rm ./data/duke_storage.txt
cd text-ui-test

echo "Testing I/O and logic: "
bash ./runtest.sh

echo "Testing loading tasks from file: "
bash ./runtest-2.sh