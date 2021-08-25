import duke.DukeException;
import duke.Parser;
import duke.command.ExitCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_randomCommand_exceptionThrown() {
        try {
            assertEquals(0, Parser.parse("hi"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("I'm sorry, but I don't know what that means :-(\n", e.getMessage());
        }
    }

    @Test
    public void checkDeadlineDescription_noBy_exceptionThrown() {
        try {
            Parser.checkDeadlineDescription("project work");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("The new deadline is missing a date!\n", e.getMessage());
        }
    }

    @Test
    public void testByeCommand() {
        try {
            assertEquals(new ExitCommand(), Parser.parse("bye"));
        } catch (DukeException e) {
            fail();
        }
    }

}
