import Command.Command;
import duke.Parser;
import exceptions.NoSuchCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parseToDo() {
        String userInput = "todo homework";
        try {
            assertEquals(Command.of("todo", "homework"), Parser.parse(userInput));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void parseEvent() {
        String userInput = "event lecture /at 2021-08-25";
        try {
            assertEquals(Command.of("event", "lecture /at 2021-08-25"), Parser.parse(userInput));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void parseDeadline() {
        String userInput = "deadline submit assignment /by 2021-08-25";
        try {
            assertEquals(Command.of("deadline", "submit assignment /by 2021-08-25"), Parser.parse(userInput));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

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
