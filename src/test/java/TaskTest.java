import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Task;

/**
 * A class to test the methods in <code>Task</code>.
 */
public class TaskTest {

    @Test
    public void testGetStatusIcon() {
        Task task = new Task("description", true);
        String status = task.getStatusIcon();
        assertEquals("X", status);
    }
}
