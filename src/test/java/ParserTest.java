import duke.exception.DukeException;
import duke.Parser;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_todoCommand() {
        Command c;
        try {
            c = Parser.parse("todo something");
        } catch (DukeException e) {
            c = null;
        }
        boolean result = c instanceof AddCommand;
        assertTrue(result);
    }

    @Test
    public void parse_listCommand() {
        Command c;
        try {
            c = Parser.parse("list");
        } catch (DukeException e) {
            c = null;
        }
        boolean result = c instanceof ListCommand;
        assertTrue(result);
    }

    @Test
    public void parse_invalidInput_exceptionThrown() {
        try {
            Parser.parse("wrong");
        } catch (DukeException e) {
            assertTrue(e.getMessage().contentEquals("Sorry, I don't know what that means :("));
            return;
        }
        fail();
    }
}
