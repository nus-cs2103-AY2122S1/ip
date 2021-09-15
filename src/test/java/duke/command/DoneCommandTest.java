package duke.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDescriptionException;
import duke.exception.TaskNotFoundException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;


/**
 * A test class which tests if DoneCommand's methods work as intended.
 */
public class DoneCommandTest {

    /**
     * Passes test when EmptyDescriptionException is thrown when the index is empty.
     */
    @Test
    public void doneCommand_emptyDescription_exceptionThrow() {
        assertThrows(EmptyDescriptionException.class, () -> new DoneCommand(""));
    }

    /**
     * Passes test when EmptyDescriptionException is thrown when the index consists of whitespace only.
     */
    @Test
    public void doneCommand_blankDescription_exceptionThrow() {
        assertThrows(EmptyDescriptionException.class, () -> new DoneCommand("      "));
    }

    /**
     * Passes test when InvalidDescriptionException is thrown when the index is invalid.
     */
    @Test
    public void doneCommand_invalidArgument_exceptionThrown() {
        assertThrows(InvalidDescriptionException.class, () -> new DoneCommand("some dummy text."));
        assertThrows(InvalidDescriptionException.class, () -> new DoneCommand("0"));
        assertThrows(InvalidDescriptionException.class, () -> new DoneCommand("-3"));
        assertThrows(InvalidDescriptionException.class, () -> new DoneCommand("1.33"));
    }

    /**
     * Passes test when TaskNotFoundException is thrown when the index is a positive integer larger than task numbers.
     */
    @Test
    public void doneCommand_taskNotFound_exceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks.txt");
        assertThrows(TaskNotFoundException.class, () -> new DoneCommand("1000").execute(tasks, ui, storage));
    }
}
