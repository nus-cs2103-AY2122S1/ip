package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommandTest {

    private Storage storage;
    private Ui ui = new Ui();
    private TaskList tasks = new TaskList();

    @Test
    public void execute_noIndex_throwsException() {
        DoneCommand test = new DoneCommand("");
        try {
            test.execute(tasks, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "No index was keyed in. Please try again.");
        }
    }

    @Test
    public void execute_indexOutOfRange_throwsException() {
        DoneCommand test = new DoneCommand("3");
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
        DoneCommand test = new DoneCommand("1");
        try {
            test.execute(tasks, ui, storage);
        } catch (DukeException e) {
            fail();
        }
        assertTrue(tasks.get(0).getIsDone());

    }

    // Can also handle if index is not an integer

}
