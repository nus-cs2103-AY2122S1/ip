@echo off
javac -d dist -sourcepath .\src\main\java\ .\src\main\java\Duke.java
java -cp dist Duke
