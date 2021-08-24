package duke.task;

import duke.Storage;
import duke.exception.DukeException;
import duke.parser.DukeParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void markDone_noSuchTask_exceptionThrown() {
        try {
            TaskList list = new TaskList();
            list.addTask(new Todo("test"));
            list.markDone(2);
            fail();
        } catch (DukeException e) {
            assertEquals("Hey, there is no such task!", e.getMessage());
        }
    }
}
