package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.TaskList;
import duke.task.Todo;
import duke.util.DukeException;
import duke.util.Storage;

public class DoneCommandTest {

    @Test
    public void executeDoneCommand_validTaskNumber_stringResponse() {
        int taskNumber = 1;
        TaskList tl = new TaskList();
        tl.addTask(new Todo(false, "test"));
        Storage storage = new Storage();
        String response = null;
        try {
            response = new DoneCommand(taskNumber).execute(storage, tl);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals("Can do! Task 1 marked as done:\n  [T][X] test", response);
    }
}
