import duke.Command;
import duke.DukeException;
import duke.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testExit() throws DukeException {
        String userInput = "bye";
        Command c = Parser.parse(userInput);
        assertEquals(true, c.isExit());
    }

    @Test
    public void testAddTodo() throws DukeException {
        String userInput = "todo read";
        Command c = Parser.parse(userInput);
        assertEquals(false, c.isExit());
    }
}
