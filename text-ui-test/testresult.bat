@ECHO OFF

if exist EXPECTED_2.TXT del EXPECTED_2.TXT


REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

java -classpath ..\bin duke.App < input.txt > EXPECTED_2.TXT

FC EXPECTED.TXT EXPECTED_2.TXT
