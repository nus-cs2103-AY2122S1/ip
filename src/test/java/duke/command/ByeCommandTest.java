package duke.command;

import duke.command.ByeCommand;
import duke.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {

    private Command c = new ByeCommand();

    @Test
    public void isExitTest() {
        assertEquals(c.isExit(), true);
    }
}
