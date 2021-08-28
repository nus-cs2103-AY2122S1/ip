package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommandTest {

    private Storage storage;
    private Ui ui = new Ui();
    private TaskList tasks = new TaskList();

    @Test
    public void execute_emptyString_throwsException() {
        FindCommand test = new FindCommand("");
        try {
            test.execute(tasks, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "No matching string was entered.");
        }
    }
}
