package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class DukeParserTest {

    @Test
    void parseInput_randomString_nothingParsed() {
        DukeParser testParser = new DukeParser(null);
        String input = "TEST_STRING_DOES_NOTHING";
        String actualOutput = testParser.parseInput(input).execute();

        String expectedOutput = "eeeeeee~dameda!!\n" + input + " isn't a valid command!";
        assertEquals(expectedOutput, actualOutput);
    }
}
