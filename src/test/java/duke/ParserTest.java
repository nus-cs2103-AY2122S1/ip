package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testGetUserCommand() {
        Parser parser = new Parser();
        assertEquals("todo", parser.getUserCommand("todo blah blah"));
    }

    @Test
    public void testGetUserArgument() {
        Parser parser = new Parser();
        assertEquals("blah blah", parser.getUserArgument("todo blah blah"));
    }

    @Test
    public void testNoUserArgument() {
        Parser parser = new Parser();
        assertEquals("", parser.getUserArgument("todo "));
    }
}
