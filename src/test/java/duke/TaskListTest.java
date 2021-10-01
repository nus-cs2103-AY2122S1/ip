package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Task;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        TaskList testList = new TaskList(new ArrayList<>());
        assertEquals(testList.size(), 0);
        testList.addTask(new Task("task"));
        assertEquals(testList.size(), 1);
    }

    @Test
    public void getTaskTestWithValidIndex() {
        TaskList testList = new TaskList(new ArrayList<>());
        Task task1 = new Task("task 1");
        Task task2 = new Task("task 2");
        testList.addTask(task1);
        testList.addTask(task2);

        assertEquals(testList.getTask(1), task1);
        assertEquals(testList.getTask(2), task2);
    }

    @Test
    public void getTaskTestWithInvalidIndex() {
        TaskList testList = new TaskList(new ArrayList<>());
        Task task1 = new Task("task 1");
        Task task2 = new Task("task 2");
        testList.addTask(task1);
        testList.addTask(task2);

        try {
            testList.getTask(0);
            fail("The code should not reach here");
        } catch (DukeException e) {
            assertEquals("OOPS!!! The item index you filled in is out of bound!", e.getMessage());
        }

        try {
            testList.getTask(3);
            fail("The code should not reach here");
        } catch (DukeException e) {
            assertEquals("OOPS!!! The item index you filled in is out of bound!", e.getMessage());
        }
    }
}
