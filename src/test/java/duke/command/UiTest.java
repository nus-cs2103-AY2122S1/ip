package duke.command;

import duke.exception.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void testReceiveUserCommand() throws InvalidCommandException {
        Ui ui = new Ui("Bob");
        assertEquals("todo", ui.receiveUserCommand("todo homework"));
    }
}
