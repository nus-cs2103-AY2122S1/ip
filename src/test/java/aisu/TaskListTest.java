package aisu;

import aisu.exception.AisuException;
import aisu.task.Task;
import aisu.task.Todo;
import aisu.tasklist.TaskList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTest() throws AisuException {
        // a unit test for TaskList#addTask(String, TaskTypes) method
        Todo testTodo = new Todo("add todo task");
        TaskList testTaskList = new TaskList();
        assertEquals(testTodo.toString(), testTaskList.addTask("add todo task", TaskList.TaskTypes.T).toString());
    }

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
