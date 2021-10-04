package seedu.duke.storage;

import seedu.duke.tasks.ToDos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testDoneItem() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDos("UnitTesting"));

        assertFalse(taskList.getTaskList().get(0).getIsDone());
        taskList.doneItem(0);
        assertTrue(taskList.getTaskList().get(0).getIsDone());
    }

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDos("UnitTesting"));

        assertFalse(taskList.getTaskList().get(0).getIsDone());
        assertEquals("[T][ ] UnitTesting", taskList.getTaskList().get(0).toString());
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDos("UnitTesting"));

        assertEquals(1, taskList.getTaskList().size());

        taskList.deleteItem(0);

        assertEquals(0, taskList.getTaskList().size());
    }
}
