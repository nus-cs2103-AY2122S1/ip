package janet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList tasks = new TaskList();
        tasks.add(new Task("Description"));
        assertEquals(tasks.get(0).toString(), new Task("Description").toString());
    }

    @Test
    public void testRemoveTask_valid() {
        TaskList tasks = new TaskList();
        tasks.add(new Task("Task"));
        assertEquals(tasks.size(), 1);
        assertEquals(tasks.get(0).toString(), new Task("Task").toString());
        assertEquals(tasks.delete(0).toString(), new Task("Task").toString());
        assertEquals(tasks.size(), 0);
    }

    @Test
    public void testRemoveTask_invalid() {
        TaskList tasks = new TaskList();
        try {
            assertEquals(tasks.delete(0).toString(), new Task("Task").toString());
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Index 0 out of bounds for length 0");
        }
    }
}
