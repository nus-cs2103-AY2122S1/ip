#!/usr/bin/env bash

# change to script directory
cd "${0%/*}"
cd ..
./gradlew clean shadowJar -PchooseMain=Tiger

cd text-ui-test

java  -jar $(find ../build/libs/ -mindepth 1 -print -quit) < input.txt > ACTUAL.TXT

cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix EXPECTED-UNIX.TXT ACTUAL.TXT
diff EXPECTED-UNIX.TXT ACTUAL.TXT
if [ $? -eq 0 ]
then
    echo "Text UI test passed!"
    exit 0
else
    echo "Text UI test failed!"
    exit 1
fi



