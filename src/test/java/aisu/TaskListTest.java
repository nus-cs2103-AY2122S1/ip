package aisu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import aisu.exception.AisuException;
import aisu.task.Task;
import aisu.task.Todo;
import aisu.tasklist.TaskList;

/**
 * This class encapsulates a unit test for the Ui class.
 *
 * @author Liaw Xin Yan
 */
public class TaskListTest {
    /**
     * Tests the addTask method for a valid given input.
     */
    @Test
    public void addTest() throws AisuException {
        // a unit test for TaskList#addTask(String, TaskTypes) method
        Todo testTodo = new Todo("add todo task");
        TaskList testTaskList = new TaskList();
        assertEquals(testTodo.toString(), testTaskList.addTask("add todo task", TaskList.TaskTypes.T).toString());
    }

    /**
     * Tests the deleteTask method for a valid given input.
     */
    @Test
    public void deleteTest() throws AisuException {
        // a unit test for TaskList#deleteTask(int) method
        Todo testTodo = new Todo("delete this task");
        List<Task> list = new ArrayList<>();
        list.add(testTodo);
        TaskList testTaskList = new TaskList(list);
        assertEquals(testTodo.toString(), testTaskList.deleteTask(1).toString());
    }
}
