package duke;

import duke.utils.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testGetUserCommand() {
        assertEquals("todo", Parser.getUserCommand("todo blah blah"));
    }

    @Test
    public void testGetUserArgument() {
        assertEquals("blah blah", Parser.getUserArgument("todo blah blah"));
    }

    @Test
    public void testNoUserArgument() {
        assertEquals("", Parser.getUserArgument("todo "));
    }
}
