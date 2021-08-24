#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../../../../bin" ]
then
    mkdir ../../../../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../../../src/main/java -Xlint:none -d ../../../../bin ../../../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# delete saved file from previous run
if [ -e "./data/task_list.txt" ]
then
    rm ./data/task_list.txt
fi

# run the program, feed Duke.commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../../../../bin Duke < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi

# delete saved file from previous run
if [ -e "./data/task_list.txt" ]
then
    rm ./data/task_list.txt
fi

# run the program, feed Duke.commands from input_exception.txt file and redirect the output to the ACTUAL_EXCEPTION.TXT
java -classpath ../../../../bin Duke < input_exception.txt > ACTUAL_EXCEPTION.TXT

# convert to UNIX format
cp EXPECTED_EXCEPTION.TXT EXPECTED_EXCEPTION-UNIX.TXT
dos2unix ACTUAL_EXCEPTION.TXT EXPECTED_EXCEPTION-UNIX.TXT

# compare the output to the expected output
diff ACTUAL_EXCEPTION.TXT EXPECTED_EXCEPTION-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi