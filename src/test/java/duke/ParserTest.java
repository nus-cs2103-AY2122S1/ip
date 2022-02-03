package duke;

import duke.command.ExitCommand;
import duke.command.ListCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void listTest() {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void exitTest() {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }
}
