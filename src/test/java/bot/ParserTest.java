package bot;

import commands.Command;
import commands.DeadlineCommand;

import commands.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void commandFromString_correct() {
        Command deadlineCmd = Parser.commandFromString("deadline return book /by 2/12/2019 1800");
        assertTrue(deadlineCmd instanceof DeadlineCommand);

        Command listCmd = Parser.commandFromString("list");
        assertTrue(listCmd instanceof ListCommand);
    }
}
