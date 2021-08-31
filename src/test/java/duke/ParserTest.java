package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import command.Command;
import exceptions.NoSuchCommandException;


/**
 * JUnit test class for Parser.
 */
public class ParserTest {

    /**
     * Tests if the Parser returns an equivalent ToDo object.
     */
    @Test
    public void parseToDo() {
        String userInput = "todo homework";
        try {
            assertEquals(Command.of("todo", "homework"), Parser.parse(userInput));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    /**
     * Tests if the Parser returns an equivalent Event object.
     */
    @Test
    public void parseEvent() {
        String userInput = "event lecture /at 2021-08-25";
        try {
            assertEquals(Command.of("event", "lecture /at 2021-08-25"), Parser.parse(userInput));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    /**
     * Tests if the Parser returns an equivalent Deadline object.
     */
    @Test
    public void parseDeadline() {
        String userInput = "deadline submit assignment /by 2021-08-25";
        try {
            assertEquals(Command.of("deadline", "submit assignment /by 2021-08-25"), Parser.parse(userInput));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    /**
     * Tests if the correct exception is thrown when an invalid command is parsed.
     */
    @Test
    public void noSuchCommandExceptionTest() {
        String userInput = "duke is stupid";
        try {
            assertThrows(NoSuchCommandException.class, () -> Parser.parse(userInput));
        } catch (Exception e) {
            fail("Unknown exception thrown: " + e.getMessage());
        }
    }
}
