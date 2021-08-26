package ligma;

import ligma.command.AddCommand;
import ligma.command.Command;
import ligma.task.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testParseCommand() {
        Command expected = new AddCommand(Task.TaskType.TODO, "build ningguang");
        try {
            assertEquals(expected, Parser.parseCommand("todo build ningguang"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParseCommandWithException() {
        try {
            assertEquals(null, Parser.parseCommand("maki"));
        } catch (Exception e) {
            assertEquals("Sorry, command does not exist.", e.getMessage());
        }
    }
}
