package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_bye() {
        try {
            assertTrue(Parser.parse("bye") instanceof ExitCommand);
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_list() {
        try {
            assertTrue(Parser.parse("list") instanceof ListCommand);
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_invalidInput_dukeExceptionThrown() {
        try {
            Parser.parse("klaskdlasd");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
