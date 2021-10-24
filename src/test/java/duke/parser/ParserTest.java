package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void parse_unrecognisedCommand_exceptionThrown() {
        try {
            new Parser().parse("blah");
            fail();
        } catch (DukeException e) {
            assertEquals("This command is not recognised! Please enter a valid command!", e.getMessage());
        }
    }
}
