@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL_CORRECT_LOG.TXT del ACTUAL_CORRECT_LOG.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Duke < input_correct.txt > ACTUAL_CORRECT_LOG.TXT

REM compare the output to the expected output
FC ACTUAL_CORRECT_LOG.TXT EXPECTED_CORRECT_LOG.TXT

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Duke < input_incorrect.txt > ACTUAL_ERROR_LOG.TXT

REM compare the output to the expected output
FC ACTUAL_ERROR_LOG.TXT EXPECTED_ERROR_LOG.TXT