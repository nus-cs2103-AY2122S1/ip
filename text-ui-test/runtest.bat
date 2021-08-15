@ECHO OFF

REM create dist directory if it doesn't exist
if not exist .\dist mkdir .\dist

REM delete output from previous run
if exist .\text-ui-test\ACTUAL.TXT del .\text-ui-test\ACTUAL.TXT

REM compile the code into the dist folder
javac  -cp .\src -Xlint:none -d .\dist .\src\main\java\Duke.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
cat .\text-ui-test\input.txt | java -cp .\dist main.java.Duke > .\text-ui-test\ACTUAL.TXT

REM compare the output to the expected output
FC .\text-ui-test\ACTUAL.TXT .\text-ui-test\EXPECTED.TXT
