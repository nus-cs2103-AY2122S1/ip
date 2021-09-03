@ECHO OFF

if exist EXPECTED_2.TXT del EXPECTED_2.TXT
if exist data\tasks.txt del data\tasks.txt

REM compile the code into the bin folder
javac --module-path %PATH_TO_FX% --add-modules ALL-MODULE-PATH -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin duke.Launcher -mode console < input.txt > EXPECTED_2.TXT

FC EXPECTED.TXT EXPECTED_2.TXT
