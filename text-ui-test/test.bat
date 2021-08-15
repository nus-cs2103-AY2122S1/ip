if exist ACTUAL.txt del ACTUAL.txt
if exist results.txt del results.txt
java -jar "ip.jar" < input.txt > ACTUAL.txt
FC ACTUAL.txt EXPECTED.txt > results.txt