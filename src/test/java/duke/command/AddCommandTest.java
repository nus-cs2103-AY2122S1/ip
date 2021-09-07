package duke.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.IncompleteDescriptionException;
import duke.exception.InvalidDateFormatException;

/**
 * A test class which tests if addCommand's methods work as intended.
 */
public class AddCommandTest {

    /**
     * Passes test if IncompleteDescriptionException is thrown when the event has empty description.
     */
    @Test
    public void addCommand_emptyEvent_exceptionThrown() {
        assertThrows(IncompleteDescriptionException.class, () -> new AddCommand("EVENT", ""));
    }

    /**
     * Passes test IncompleteDescriptionException is thrown when the deadline has empty description.
     */
    @Test
    public void addCommand_emptyDeadline_exceptionThrown() {
        assertThrows(IncompleteDescriptionException.class, () -> new AddCommand("DEADLINE", ""));
    }

    /**
     * Passes test InvalidDateFormatException is thrown when the event has invalid date format.
     */
    @Test
    public void addCommand_invalidEventDate_exceptionThrown() {
        assertThrows(InvalidDateFormatException.class, () ->
                new AddCommand("EVENT", "sample event /at tomorrow"));
    }

    /**
     * Passes test InvalidDateFormatException is thrown when the deadline has invalid date format.
     */
    @Test
    public void addCommand_invalidDeadlineDate_exceptionThrown() {
        assertThrows(InvalidDateFormatException.class, () ->
                new AddCommand("DEADLINE", "sample deadline /by tomorrow"));
    }
}
