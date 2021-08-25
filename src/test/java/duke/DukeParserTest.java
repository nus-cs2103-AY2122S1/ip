package duke;

import duke.exception.DukeFileSystemException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeParserTest {
    @Test
    public void getCommandType_invalidCommand_invalidCommandTypeReturned() {
        assertEquals(DukeCommand.INVALID, new DukeParser().getCommandType("this is an invalid command"));
    }
}
