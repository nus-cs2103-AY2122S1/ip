import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.command.ExitCommand;
import duke.command.ListCommand;

public class ParserTest {
    @Test
    public void parseBye() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parseList() throws DukeException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

}
