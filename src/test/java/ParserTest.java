import duke.DukeException;
import duke.Parser;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
