package duke.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.EmptyDescriptionException;

/**
 * A test class which tests if TodoCommand's methods work as intended.
 */
public class TodoCommandTest {

    /**
     * Passes test when EmptyDescriptionException is thrown when the todo description is empty.
     */
    @Test
    public void todoCommand_emptyDescription_exceptionThrown() {
        assertThrows(EmptyDescriptionException.class, () -> new TodoCommand(""));
    }

    /**
     * Passes test when EmptyDescriptionException is thrown when the todo description consists of purely whitespace.
     */
    @Test
    public void todoCommand_blankDescription_exceptionThrown() {
        assertThrows(EmptyDescriptionException.class, () -> new TodoCommand("          "));
    }
}
