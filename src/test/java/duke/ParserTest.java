package duke;

import duke.commands.Command;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void testParseExit() throws DukeException {
        Command c = new Parser().parse("bye");
        assertTrue(c.isExit());
    }

}
