package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidCommandException;

public class UiTest {
    @Test
    public void testReceiveUserCommand() throws InvalidCommandException {
        Ui ui = new Ui("Bob");
        assertEquals("todo", ui.receiveUserCommand("todo homework"));
    }
}
