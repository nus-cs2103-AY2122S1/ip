@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM obtain all .java files in relative path
dir ..\src\main\java\sora /s /b /a-d > sources_raw.txt
(for /f "tokens=1,2 delims= " %%G in (sources_raw.txt) do (
    set "line=%%H"
    setlocal enabledelayedexpansion
    set "line=!line:IDEA\IdeaProjects\ip=..!"
    echo(!line!
    endlocal
)) > sources_modified.txt

REM compile the code into the bin folder
javac -cp ..\src\main\java\sora -Xlint:none -d ..\bin @sources_modified.txt

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin sora.Sora < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
