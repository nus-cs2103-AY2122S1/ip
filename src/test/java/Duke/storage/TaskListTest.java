package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import duke.storage.TaskList;
import duke.tasks.ToDos;

public class TaskListTest {
    @Test
    public void testDoneItem() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDos("UnitTesting"));

        assertEquals(false, taskList.getTaskList().get(0).getIsDone());
        taskList.doneItem("1");
        assertEquals(true, taskList.getTaskList().get(0).getIsDone());
    }

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDos("UnitTesting"));

        assertEquals(false, taskList.getTaskList().get(0).getIsDone());
        assertEquals("[T][ ] UnitTesting", taskList.getTaskList().get(0).toString());
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDos("UnitTesting"));

        assertEquals(1, taskList.getTaskList().size());

        taskList.deleteItem("1");

        assertEquals(0, taskList.getTaskList().size());
    }
}
