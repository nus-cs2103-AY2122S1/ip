import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Command;
import duke.DukeException;
import duke.Parser;

public class ParserTest {
    /**
     * Tests if Parser parses "bye" into Exit command.
     *
     * @throws DukeException
     */
    @Test
    public void testExit() throws DukeException {
        String userInput = "bye";
        Command c = Parser.parse(userInput);
        assertEquals(true, c.isExit());
    }

    /**
     * Tests if parser does not parse "todo read" into Exit command.
     *
     * @throws DukeException
     */
    @Test
    public void testAddTodo() throws DukeException {
        String userInput = "todo read";
        Command c = Parser.parse(userInput);
        assertEquals(false, c.isExit());
    }
}
