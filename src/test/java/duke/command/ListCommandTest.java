package duke.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.ExtraArgumentException;

/**
 * A test class which tests if ListCommand's methods work as intended.
 */
public class ListCommandTest {

    /**
     * Passes test when ExtraArgumentException is thrown when the list command has extraneous argument.
     */
    @Test
    public void listCommand_extraArgument_exceptionThrown() {
        assertThrows(ExtraArgumentException.class, () -> new ExitCommand("list some random extra text"));
        assertThrows(ExtraArgumentException.class, () -> new ExitCommand("list 902189218"));
        assertThrows(ExtraArgumentException.class, () -> new ExitCommand("list                   "));
    }
}
