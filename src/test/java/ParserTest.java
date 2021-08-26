import duke.DukeException;
import duke.Parser;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void todoTest(){
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
    public void listTest(){
        Command c;
        try {
            c = Parser.parse("list");
        } catch (DukeException e) {
            c = null;
        }
        boolean result = c instanceof ListCommand;
        assertTrue(result);
    }
}
