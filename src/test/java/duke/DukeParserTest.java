package duke;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DukeParserTest {

    @Test
    void parseInput_randomString_nothingParsed() {


        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DukeParser testParser = new DukeParser(null);
        String input = "TEST_STRING_DOES_NOTHING";
        testParser.parseInput(input).execute();

        String expectedOutput  = "  →   " + "☹ eeeeeee~dameda!! " + input + " isn't a valid command!\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}