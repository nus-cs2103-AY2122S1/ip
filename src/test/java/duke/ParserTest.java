package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exception.InvalidTaskException;
import exception.NoDescriptionException;

public class ParserTest {
    @Test
    public void testParseCommand1() {
        Parser parser = new Parser();
        String invalidCommand = "Invalid command! Please enter the following commands only:\n"
                + "list\ndone (task number)\n"
                + "delete (task number)\ntodo (description)\n"
                + "deadline (description) /by (time)\nevent (description) /at (time)";
        try {
            assertEquals(Command.LIST, parser.parseCommand("blah"));
            fail();
        } catch (InvalidTaskException e) {
            assertEquals(invalidCommand, e.getMessage());
        }
    }

    @Test
    public void testParseCommand2() {
        Parser parser = new Parser();
        try {
            assertEquals(2, parser.getTaskNo("done 2"));
        } catch (NoDescriptionException e) {
            fail();
        }
    }

    @Test
    public void testParseDescription1() {
        Parser parser = new Parser();
        try {
            assertEquals("todo", parser.parseDescription("todo"));
            fail();
        } catch (NoDescriptionException e) {
            assertEquals("Description of task cannot be empty!", e.getMessage());
        }
    }
}
