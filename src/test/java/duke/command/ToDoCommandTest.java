package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoCommandTest {

    private Storage storage;
    private Ui ui = new Ui();
    private TaskList tasks = new TaskList();

    @Test
    public void execute_emptyString_throwsException() {
        ToDoCommand test = new ToDoCommand("");
        try {
            test.execute(tasks, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "The description of a todo cannot be left empty. Please try again.");
        }
    }

    @Test
    public void execute_validArgument_returnsNormally() {
        ToDoCommand test = new ToDoCommand("go shopping");
        try {
            test.execute(tasks, ui, storage);
        } catch (DukeException e) {
            fail();
        }
        assertEquals(tasks.get(0).toString(), "[T][ ] go shopping");
    }

}
