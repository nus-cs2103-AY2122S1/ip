package duke.parser;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parse_invalidCommand() {
        try {
            Parser.parse("random");
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(",
                    e.getMessage());
        }
    }

    @Test
    void parse_validCommand() {
        try {
            Parser.parse("bye");
        } catch (DukeException e) {
            fail();
        }
    }
}