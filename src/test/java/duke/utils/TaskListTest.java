package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.exceptions.DuplicateTaskException;
import duke.tasks.Todo;

public class TaskListTest {

    @Test
    public void testEmptyTaskList() {
        String expected = "No tasks added yet!";
        TaskList taskList = new TaskList();
        assertEquals(
                expected,
                taskList.toString()
        );
    }

    @Test
    public void testAddTask() {
        String expected = "1. [T][ ] Placeholder";
        TaskList taskList = new TaskList();
        try {
            taskList.addTask(new Todo("Placeholder"));
        } catch (DuplicateTaskException e) {
            // should not occur
        }
        assertEquals(
                expected,
                taskList.toString()
        );
    }
}
