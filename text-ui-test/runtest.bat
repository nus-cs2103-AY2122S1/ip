@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM compiles all file
javac -cp ..\src\main\java\petal -Xlint:none -d ..\bin ..\src\main\java\petal\*.java  ..\src\main\java\petal\task\*.java  ..\src\main\java\petal\exception\*.java  ..\src\main\java\petal\components\*.java  ..\src\main\java\petal\command\*.java
 
REM no error here, errorlevel == 0
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin petal.Petal < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

cmd \k