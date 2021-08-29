package duke.tasklist;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void getList_emptyList_returnsEmpty() {
        TaskList taskList = new TaskList(new ArrayList<String>());
        assertEquals("There are no items in the task list.", taskList.getList());
    }

    @Test
    public void getList_filledList_returnsTasks() {
        TaskList taskList = new TaskList(new ArrayList<String>());
        taskList.addTask(new ToDo("Task 1"));
        taskList.addTask(new Deadline("Task 2", "2021-08-23 0134"));
        taskList.addTask(new Event("Task 3", "2021-08-23 0134-0140"));
        assertEquals("Tasks in task list:\n" +
                "\t\t1. [T][ ] Task 1\n" +
                "\t\t2. [D][ ] Task 2 (by: Aug 23 2021 0:92AM)\n" +
                "\t\t3. [E][ ] Task 3 (at: Aug 23 2021 0:92AM to 0:96AM)", taskList.getList());
    }

    @Test
    public void testGetTask() {
        TaskList taskList = new TaskList(new ArrayList<String>());
        taskList.addTask(new ToDo("Task 1"));
        assertEquals("[T][ ] Task 1", taskList.getTask(0).toString());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.getTask(1);
        });
    }

}
