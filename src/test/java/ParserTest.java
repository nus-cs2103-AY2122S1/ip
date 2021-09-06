import duke.Command.*;
import duke.DukeException;
import duke.Parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parse_validInputs_commandReturned() throws Exception{
        assertEquals(Parser.parse(" bye  ", 1), new ExitCommand());
        assertEquals(Parser.parse("list", 1), new ListCommand());
        assertEquals(Parser.parse("todo a", 1), new AddCommand(new String[]{"todo", "a"}));
        assertEquals(Parser.parse("deadline   a  /by    b", 1), new AddCommand(new String[]{"deadline", "a", "b"}));
        assertEquals(Parser.parse("event a /at b", 1), new AddCommand(new String[]{"event", "a", "b"}));
        assertEquals(Parser.parse("delete 10000", 10000), new DeleteCommand(9999));
        assertEquals(Parser.parse("done 20000", 20000), new DoneCommand(19999));
    }

    @Test
    public void parse_invalidInputs_exceptionThrown() {
        assertThrows(DukeException.class, () -> {
            Parser.parse("bye s", 1);
        });
        assertThrows(DukeException.class, () -> {
            Parser.parse("list a", 1);
        });
        assertThrows(DukeException.class, () -> {
            Parser.parse("todo", 1);
        });
        assertThrows(DukeException.class, () -> {
            Parser.parse("deadline  /by s", 1);
        });
        assertThrows(DukeException.class, () -> {
            Parser.parse("event a /by b", 1);
        });
        assertThrows(DukeException.class, () -> {
            Parser.parse("delete -1", 0);
        });
        assertThrows(DukeException.class, () -> {
            Parser.parse("done 2", 1);
        });
    }
}