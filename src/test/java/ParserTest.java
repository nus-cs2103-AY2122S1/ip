import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void parse_unrecognisedCommand_exceptionThrown() {
        try {
            assertEquals(0, Parser.parse("abc"));
            fail(); // should not reach this line
        } catch (DukeException e) {
            assertEquals("oh no! you typed in something i cannot recognise!", e.getMessage());
        }
    }

}
