package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.main.Parser;



/**
 * Class to test parser
 */
class ParserTest {

    @Test
    void parse_emptyDescription_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parse("todo");
            fail();
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.",
                    e.getMessage());
        }
    }

    @Test
    void parse_emptyTime_exceptionThrown() {
        Parser parser = new Parser();
        try {
            parser.parse("deadline test /by");
            fail();
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The time of a deadline cannot be empty.",
                    e.getMessage());
        }
    }
}
