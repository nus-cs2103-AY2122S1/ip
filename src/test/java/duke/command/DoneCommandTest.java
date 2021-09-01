package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.TaskList;
import duke.task.Todo;
import duke.util.DukeException;
import duke.util.Storage;

public class DoneCommandTest {

    @Test
    public void executeDoneCommand_invalidTaskNumber_throwDukeException() {
        int taskNumber = 0;
        try {
            TaskList tl = new TaskList();
            tl.addTask(new Todo(false, "test"));
            Storage storage = new Storage();
            new DoneCommand(taskNumber).execute(storage, tl);
        } catch (DukeException e) {
            assertEquals(String.format("Task number %d invalid.", taskNumber), e.getMessage());
        }
    }
}
