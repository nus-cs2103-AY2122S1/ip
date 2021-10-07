package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void parse_doneInvalidNumber_exceptionThrown() {
        try {
            assertEquals(0, Parser.parse("done a"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("OOPS!!! Please enter a valid task number.", e.getMessage());
        }
    }

    @Test
    void parse_deleteInvalidNumber_exceptionThrown() {
        try {
            assertEquals(0, Parser.parse("delete a"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("OOPS!!! Please enter a valid task number.", e.getMessage());
        }
    }

}
