@echo off
javac -d dist -sourcepath .\src .\src\main\java\Duke.java
java -cp dist main.java.Duke
