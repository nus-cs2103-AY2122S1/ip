@echo off
javac -d dist -sourcepath .\src\main\java .\src\main\java\duke\Duke.java
java -cp dist duke.Duke
