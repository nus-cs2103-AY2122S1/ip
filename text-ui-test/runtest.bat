@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java\duke -Xlint:none -d ..\bin ..\src\main\java\duke\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin duke.Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
REM This line is commented out in favour of replicating the behaviour of the provided runtest.sh
REM FC ACTUAL.TXT EXPECTED.TXT

fc ACTUAL.TXT EXPECTED.TXT
if errorlevel 1 (
    echo Test result: FAILED
	exit /b 1
) else (
    echo Test result: PASSED
)
