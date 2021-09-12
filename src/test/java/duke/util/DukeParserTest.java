package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.ui.Ui;
import org.junit.jupiter.api.Test;

class DukeParserTest {

    @Test
    void parseInput_randomString_nothingParsed() {
        DukeParser testParser = new DukeParser(null);
        String input = "TEST_STRING_DOES_NOTHING";
        String actualOutput = testParser.parseInput(input).execute();

        String expectedOutput = Ui.messageInvalidCommand(input);
        assertEquals(expectedOutput, actualOutput);
    }
}
