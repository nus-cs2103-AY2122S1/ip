package daisy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import daisy.command.ExitCommand;
import daisy.command.Parser;

public class ParserTest {

    @Test
    public void parse_randomCommand_exceptionThrown() {
        try {
            assertEquals(new ExitCommand(), Parser.parse("hi"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("I'm sorry, but I don't know what that means :-(\n", e.getMessage());
        }
    }

    @Test
    public void testByeCommand() {
        try {
            assertEquals(new ExitCommand(), Parser.parse("bye"));
        } catch (DaisyException e) {
            fail();
        }
    }

}
