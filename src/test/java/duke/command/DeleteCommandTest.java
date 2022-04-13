package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommandTest {

    private Storage storage;
    private Ui ui = new Ui();
    private TaskList tasks = new TaskList();

    @Test
    public void execute_noIndex_throwsException() {
        DeleteCommand test = new DeleteCommand("");
        try {
            test.execute(tasks, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "No index was keyed in. Please try again.");
        }
    }

    @Test
    public void execute_indexOutOfRange_throwsException() {
        DeleteCommand test = new DeleteCommand("3");
        try {
            test.execute(tasks, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "The index you entered is invalid. Please try again.");
        }
    }

    @Test
    public void execute_validIndex_returnsNormally() {
        tasks.add(new Task("dummy task"));
        DeleteCommand test = new DeleteCommand("1");
        try {
            test.execute(tasks, ui, storage);
        } catch (DukeException e) {
            fail();
        }
        assertEquals(tasks.size(), 0);
    }

    // Can also handle if index is not an integer

}
