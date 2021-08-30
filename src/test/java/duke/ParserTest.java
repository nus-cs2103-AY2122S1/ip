package duke;

import duke.command.Command;
import duke.command.HelpCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_emptyInput_exceptionThrown() {
        try {
            Parser.parse("");
            fail();
        } catch (DukeException e) {
            assertEquals("\uD83E\uDD2C You better type sumthin or else..", e.getMessage());
        }
    }

    @Test
    public void parse_invalidInput_returnHelpCommand() {
        try {
            Command c = Parser.parse("nonsense");
            assertTrue(c instanceof HelpCommand);
        } catch (DukeException e) {
            fail();
        }
    }

}