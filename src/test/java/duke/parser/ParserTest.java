package duke.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.commands.Command;
import duke.commands.ListCommand;


public class ParserTest {
    @Test
    public void parseCommand_list_success() {
        String command = "list";
        try {
            Command c = Parser.parseCommand(command);
            assertTrue(c instanceof ListCommand);
        } catch (Exception e) {
            fail("Should not have thrown exception.");
        }
    }
}
