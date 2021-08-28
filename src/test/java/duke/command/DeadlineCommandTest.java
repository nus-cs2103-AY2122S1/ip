package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeadlineCommandTest {

    private Storage storage;
    private Ui ui = new Ui();
    private TaskList tasks = new TaskList();

    @Test
    public void execute_emptyString_throwsException() {
        DeadlineCommand test = new DeadlineCommand("");
        try {
            test.execute(tasks, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "The description of a deadline cannot be left empty. Please try again.");
        }
    }

    @Test
    public void execute_argInvalidFormat_throwsException() {
        DeadlineCommand test = new DeadlineCommand("invalid_arg");
        try {
            test.execute(tasks, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Arguments do not follow proper format. Don't forget the /by");
        }
    }

    @Test
    public void execute_validArgument_returnsNormally() {
        DeadlineCommand test = new DeadlineCommand("go shopping /by 2021-10-10");
        try {
            test.execute(tasks, ui, storage);
        } catch (DukeException e) {
            fail();
        }
        assertEquals(tasks.get(0).toString(), "[D][ ] go shopping (by: Oct 10 2021)");
    }

}
