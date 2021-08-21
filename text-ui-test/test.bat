if exist results.txt del results.txt
java -jar "ip.jar" < input.txt
FC ACTUAL.txt EXPECTED.txt > results.txt