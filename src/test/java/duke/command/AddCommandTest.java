package duke.command;
import duke.exception.IncompleteDescriptionException;
import duke.exception.InvalidDateFormatException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * A test class which tests if addCommand's methods work as intended.
 */
public class AddCommandTest {
    // Initialize problematic addCommand instances.
    AddCommand emptyEvent = new AddCommand("EVENT", "");
    AddCommand emptyDeadline = new AddCommand("DEADLINE", "");
    AddCommand invalidEventDate = new AddCommand("EVENT", "sample event /at tomorrow");
    AddCommand invalidDeadlineDate = new AddCommand("DEADLINE", "sample deadline /by tomorrow");

    // Initialize dummy arguments to put into execute method.
    Ui ui = new Ui();
    Storage storage = new Storage("data/tasks.txt");
    TaskList taskList = new TaskList();

    /**
     * Passes test if IncompleteDescriptionException is thrown when the event has empty description.
     */
    @Test
    public void addCommand_emptyEvent_exceptionThrown() {
        assertThrows(IncompleteDescriptionException.class, () -> emptyEvent.execute(taskList, ui, storage));
    }

    /**
     * Passes test IncompleteDescriptionException is thrown when the deadline has empty description.
     */
    @Test
    public void addCommand_emptyDeadline_exceptionThrown() {
        assertThrows(IncompleteDescriptionException.class, () -> emptyDeadline.execute(taskList, ui, storage));
    }

    /**
     * Passes test InvalidDateFormatException is thrown when the event has invalid date format.
     */
    @Test
    public void addCommand_invalidEventDate_exceptionThrown() {
        assertThrows(InvalidDateFormatException.class, () -> invalidEventDate.execute(taskList, ui, storage));
    }

    /**
     * Passes test InvalidDateFormatException is thrown when the deadline has invalid date format.
     */
    @Test
    public void addCommand_invalidDeadlineDate_exceptionThrown() {
        assertThrows(InvalidDateFormatException.class, () -> invalidDeadlineDate.execute(taskList, ui, storage));
    }
}
