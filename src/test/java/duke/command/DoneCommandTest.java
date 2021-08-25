package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.TaskList;
import duke.task.Todo;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;
import org.junit.jupiter.api.Test;

public class DoneCommandTest {

    @Test
    public void executeDoneCommand_invalidTaskNumber_throwDukeException() {
        int taskNumber = 0;
        try {
            TaskList tl = new TaskList();
            tl.addTask(new Todo(false,"test"));
            Storage storage = new Storage();
            Ui ui = new Ui();
            new DoneCommand(taskNumber).execute(storage, tl, ui);
        } catch (DukeException e) {
            assertEquals(String.format("Task number %d invalid.", taskNumber), e.getMessage());
        }
    }
}
