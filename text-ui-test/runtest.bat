@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

@REM REM compile the code into the bin folder
@REM javac  -cp ..\src\duke -Xlint:none -d ..\bin ..\src\duke\Duke.java

REM obtain all .java files in absolute path
dir ..\src\duke /s /b /a-d > sources.txt

REM compile the code into the bin folder
javac -cp ..\src\duke -Xlint:none -d ..\bin @sources.txt

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
