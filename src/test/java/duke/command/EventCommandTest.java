package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommandTest {

    private Storage storage;
    private Ui ui = new Ui();
    private TaskList tasks = new TaskList();

    @Test
    public void execute_emptyString_throwsException() {
        EventCommand test = new EventCommand("");
        try {
            test.execute(tasks, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "The description of a event cannot be left empty. Please try again.");
        }
    }

    @Test
    public void execute_argInvalidFormat_throwsException() {
        EventCommand test = new EventCommand("invalid_arg");
        try {
            test.execute(tasks, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Arguments do not follow proper format. Don't forget the /at");
        }
    }

    @Test
    public void execute_validArgument_returnsNormally() {
        EventCommand test = new EventCommand("go shopping /at 2021-10-10");
        try {
            test.execute(tasks, ui, storage);
        } catch (DukeException e) {
            fail();
        }
        assertEquals(tasks.get(0).toString(), "[E][ ] go shopping (at: Oct 10 2021)");
    }


}
