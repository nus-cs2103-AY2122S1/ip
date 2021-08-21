package aisu;

import aisu.task.Task;
import aisu.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasklistTest {
    @Test
    public void addTest() throws AisuException {
        // a unit test for Tasklist#addTask(String, TaskTypes) method
        Todo testTodo = new Todo("add todo task");
        Tasklist testTasklist = new Tasklist();
        assertEquals(testTodo.toString(), testTasklist.addTask("add todo task", Tasklist.TaskTypes.T).toString());
    }

    @Test
    public void deleteTest() throws AisuException {
        // a unit test for Tasklist#deleteTask(int) method
        Todo testTodo = new Todo("delete this task");
        List<Task> list = new ArrayList<>();
        list.add(testTodo);
        Tasklist testTasklist = new Tasklist(list);
        assertEquals(testTodo.toString(), testTasklist.deleteTask(1).toString());
    }
}
