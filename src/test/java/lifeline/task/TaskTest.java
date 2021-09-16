package lifeline.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testNewTaskIsDoneIsFalse() {
        Task task = new Task("read book");
        assertFalse(task.isDone());
    }

    @Test
    public void testTaskGetName() {
        Task task = new Task("read book");
        assertEquals("read book", task.getName());
    }

    @Test
    public void testTaskToString() {
        Task task = new Task("read book");
        assertEquals("[ ] read book", task.toString());
    }

    @Test
    public void testTaskToggleIsDone() {
        Task task = new Task("read book");
        assertFalse(task.isDone());
        task.setDone(true);
        assertTrue(task.isDone());
    }
}
