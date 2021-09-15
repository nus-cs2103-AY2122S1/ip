package duke.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.ExtraArgumentException;

/**
 * A test class which tests if ExitCommand's methods work as intended.
 */
public class ExitCommandTest {

    /**
     * Passes test when ExtraArgumentException is thrown when the exit command has extraneous argument.
     */
    @Test
    public void exitCommand_extraArgument_exceptionThrown() {
        assertThrows(ExtraArgumentException.class, () -> new ExitCommand("exit some random extra text"));
        assertThrows(ExtraArgumentException.class, () -> new ExitCommand("exit 902189218"));
        assertThrows(ExtraArgumentException.class, () -> new ExitCommand("exit                   "));
    }
}
