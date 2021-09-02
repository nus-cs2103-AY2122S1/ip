package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.ExitCommand;

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
    public void testByeCommand() {
        try {
            assertEquals(new ExitCommand(), Parser.parse("bye"));
        } catch (DukeException e) {
            fail();
        }
    }

}
