@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete outputs from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist ACTUAL_EXCEPTION.TXT del ACTUAL_EXCEPTION.TXT


REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM --- TEST NORMAL INPUTS ---
REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

REM --- TEST INPUTS THAT GIVE RISE TO EXCEPTIONS/ERROR MESSAGES ---
REM run the program, feed commands from input_exception.txt file and redirect the output to the ACTUAL_EXCEPTION.TXT
java -classpath ..\bin Duke < input_exception.txt > ACTUAL_EXCEPTION.TXT

REM compare the output to the expected output
FC ACTUAL_EXCEPTION.TXT EXPECTED_EXCEPTION.TXT