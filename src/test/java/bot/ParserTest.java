package bot;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import commands.Command;
import commands.DeadlineCommand;
import commands.ListCommand;

public class ParserTest {
    /**
     * Check that command parsed from string matches expectation
     */
    @Test
    public void commandFromString_correct() {
        Command deadlineCmd = Parser.commandFromString("deadline return book /by 2/12/2019 1800");
        assertTrue(deadlineCmd instanceof DeadlineCommand);

        Command listCmd = Parser.commandFromString("list");
        assertTrue(listCmd instanceof ListCommand);
    }
}
