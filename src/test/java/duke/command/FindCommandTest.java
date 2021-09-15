package duke.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.EmptyDescriptionException;

/**
 * A test class which tests if FindCommand's methods work as intended.
 */
public class FindCommandTest {

    /**
     * Passes test when EmptyDescriptionException is thrown when the keyword is empty.
     */
    @Test
    public void findCommand_emptyDescription_exceptionThrown() {
        assertThrows(EmptyDescriptionException.class, () -> new FindCommand(""));
    }

    /**
     * Passes test when EmptyDescriptionException is thrown when the keyword consists of whitespace only.
     */
    @Test
    public void findCommand_blankDescription_exceptionThrown() {
        assertThrows(EmptyDescriptionException.class, () -> new FindCommand("               "));
    }
}
