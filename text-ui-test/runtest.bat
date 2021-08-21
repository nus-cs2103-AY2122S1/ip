@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM delete saved taskList from previous run
del taskList.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java\duke\exception -Xlint:none -d ..\bin ..\src\main\java\duke\exception\*.java
javac  -cp ..\src\main\java\duke\task -Xlint:none -d ..\bin ..\src\main\java\duke\task\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin ..\src\main\java\duke\Duke.java < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT