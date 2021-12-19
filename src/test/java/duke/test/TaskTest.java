package duke.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Task;
import org.junit.jupiter.api.Test;

/**
 * A class that tests the functionality of a Task class.
 *
 */
public class TaskTest {
    /**
     * Test functionality of writeTask().
     *
     */
    @Test
    public void testWriteTask() {
        Task t = new Task("Task 1");
        assertEquals("0 | Task 1", t.writeTask());
    }
}
