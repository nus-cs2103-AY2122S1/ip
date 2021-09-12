package dac.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dac.exception.InvalidInputException;
import dac.task.Deadline;
import dac.task.Event;
import dac.task.ToDo;

public class TaskListTest {

    @Test
    public void getList_emptyList_returnsEmpty() {
        TaskList taskList = new TaskList();
        assertEquals("There are no items in the task list.", taskList.getList());
    }

    @Test
    public void getList_filledList_returnsTasks() throws InvalidInputException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Task 1"));
        taskList.addTask(new Deadline("Task 2", "2021-08-23 0134"));
        taskList.addTask(new Event("Task 3", "2021-08-23 0134-0140"));
        assertEquals("Tasks in task list:\n"
                + "\t1. [T][ ] Task 1\n"
                + "\t2. [D][ ] Task 2 (by: Aug 23 2021 01:34 AM)\n"
                + "\t3. [E][ ] Task 3 (at: Aug 23 2021 01:34 AM to 01:40 AM)", taskList.getList());
    }

    @Test
    public void testGetTask() throws InvalidInputException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Task 1"));
        assertEquals("[T][ ] Task 1", taskList.getTask(0).toString());
        Assertions.assertThrows(InvalidInputException.class, () -> {
            taskList.getTask(1);
        });
    }

}
