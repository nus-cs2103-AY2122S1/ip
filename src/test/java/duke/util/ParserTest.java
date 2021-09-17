package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParser_invalidCommand_dukeExceptionThrown() {
        try {
            Parser.parseInputs(" ");
            fail();
        } catch (DukeException e) {
            assertEquals("Meeseeks are not born fumbling in this world for meaning Jerry! \n"
                    + "Say something I can understand!! >:(", e.getMessage());
        }
    }
}
