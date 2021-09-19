package duke;

import duke.error.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void unkownCommand_exceptionThrown() {
        try {
            Parser.parse("unkown command");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
