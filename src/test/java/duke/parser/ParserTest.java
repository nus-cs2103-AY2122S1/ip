package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void convertDate_invalidArg_throwsException() {
        try {
            Parser.convertDate("2021-1-1");
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Please follow date format: yyyy-mm-dd");
        }
    }

    @Test
    public void convertDate_validArg_returnsNormally() {
        try {
            Parser.convertDate("2021-10-10");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidCommand_throwsException() {
        try {
            Parser.parse("invalid command");
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "I'm sorry, but I don't know what \""
                    + "invalid command" + "\" means :-(");
        }
    }

    @Test
    public void parse_validCommand_throwsException() {
        try {
            Parser.parse("bye");
        } catch (DukeException e) {
            fail();
        }
    }

}
