import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.command.ExitCommand;
import duke.command.ListCommand;

public class ParserTest {
    @Test
    public void parseByeTest() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parseListTest() throws DukeException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

}
