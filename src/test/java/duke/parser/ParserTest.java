package duke.parser;

import duke.commands.CreateDeadlineCommand;
import duke.commands.CreateEventCommand;
import duke.commands.CreateTodoCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void TodoCommandTest() {
        Parser p = new Parser();
        assertTrue(
                p.parseCommand("todo abc")
                        instanceof CreateTodoCommand);
    }

    @Test
    public void DeadlineCommandTest() {
        Parser p = new Parser();
        assertTrue(
                p.parseCommand("deadline a /by 2021-06-13")
                        instanceof CreateDeadlineCommand);
    }

    @Test
    public void EventCommandTest() {
        Parser p = new Parser();
        assertTrue(
                p.parseCommand("event a /at a")
                        instanceof CreateEventCommand);
    }
}
