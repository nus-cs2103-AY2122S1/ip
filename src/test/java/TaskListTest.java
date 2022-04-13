import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.task.Task;
import duke.task.Todo;

public class TaskListTest {
    @Test
    public void removeTask() {
        Task todo1 = new Todo("TODO 1");
        List<Task> list = new ArrayList<>();
        list.add(todo1);
        TaskList taskList = new TaskList(list);

        assertEquals(1, taskList.size(), "TaskList should have 1 item initially");
        taskList.removeTask(0);
        assertEquals(0, taskList.size(), "Second task should be returned");

        assertThrows(Exception.class, () -> taskList.removeTask(0), "Should throw after there are no tasks to remove");
    }

    @Test
    public void getTask() {
        Task todo1 = new Todo("TODO 1");
        Task todo2 = new Todo("TODO 2");
        List<Task> list = new ArrayList<>();
        list.add(todo1);
        list.add(todo2);
        TaskList taskList = new TaskList(list);

        assertEquals(todo1, taskList.getTask(0), "First task should be returned");
        assertEquals(todo2, taskList.getTask(1), "Second task should be returned");
    }

    @Test
    public void filterTasks() {
        Task todo1 = new Todo("TODO 1");
        Task todo2 = new Todo("test 2");
        List<Task> list = new ArrayList<>();
        list.add(todo1);
        list.add(todo2);
        TaskList taskList = new TaskList(list);

        TaskList filteredTaskList = taskList.filterTasks("todo");
        assertEquals(1, filteredTaskList.size(), "Filtered tasklist should have one element");
        assertEquals(todo1, filteredTaskList.getTask(0), "Filtered tasklist should have correct element");
    }

    @Test
    public void size() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertEquals(0, taskList.size(), "List should be empty at the start");

        taskList.addTask(new Todo("TODO"));
        assertEquals(1, taskList.size(), "List should have correct size");
    }

    @Test
    public void items() {
        TaskList taskList = new TaskList(new ArrayList<>());

        assertNotSame(taskList.items(), taskList.items(), "TaskList.items() should return different copies");
    }
}
