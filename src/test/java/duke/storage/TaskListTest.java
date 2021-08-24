package duke.storage;

import duke.DukeException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void getInstance_singletonPattern_alwaysReturnsTheSameInstance() {
        try {
            TaskList tasks1 = TaskList.getInstance();
            TaskList tasks2 = TaskList.getInstance();
            assertEquals(tasks1, tasks2);
        } catch (DukeException e) {
            assertEquals("Cannot read tasks from memory!", e.getMessage());
        }
    }


}
