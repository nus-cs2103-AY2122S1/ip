package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
        taskList.addTask(new Todo("Placeholder"));
        assertEquals(
                expected,
                taskList.toString()
        );
    }
}
