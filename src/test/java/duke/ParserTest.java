package duke;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.commands.Command;

public class ParserTest {

    @Test
    public void testParseExit() throws DukeException {
        Command c = new Parser().parse("bye");
        assertTrue(c.isExit());
    }

}
