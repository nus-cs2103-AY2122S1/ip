#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# remove previous models.task record to make testing consistent
if [ -e "./data/task_list.txt" ]
then
    rm ./data/task_list.txt
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin Alice < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result first save: PASSED"
else
    echo "Test result first save: FAILED"
    exit 1
fi

# delete output from previous run for second test
if [ -e "./ACTUAL2.TXT" ]
then
    rm ACTUAL2.TXT
fi

java -classpath ../bin Alice < input2.txt > ACTUAL2.TXT

# convert to UNIX format
cp EXPECTED2.TXT EXPECTED-UNIX2.TXT
dos2unix ACTUAL2.TXT EXPECTED-UNIX2.TXT
# compare the output to the expected output
diff ACTUAL2.TXT EXPECTED-UNIX2.TXT

if [ $? -eq 0 ]
then
    echo "Test result second save: PASSED"
    exit 0
else
    echo "Test result second save: FAILED"
    exit 1
fi