package duke.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Task;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testWriteTask() {
        Task t = new Task("Task 1");
        assertEquals("0 | Task 1", t.writeTask());
    }
}
