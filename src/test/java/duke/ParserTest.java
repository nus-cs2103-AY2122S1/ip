package duke;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.exception.DukeException;


public class ParserTest {
    @Test
    public void parse_todoString_success() throws DukeException {
        assertTrue(Parser.parse("todo todo description") instanceof AddCommand);
    }
}
