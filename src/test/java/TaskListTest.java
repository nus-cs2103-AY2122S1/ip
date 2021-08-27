import duke.exception.TaskNotFoundException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void test_add_task() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Task task1 = new Todo("borrow book", false);
        Task task2 = new Todo("buy clothes", true);
        tasks.addTask(task1);
        tasks.addTask(task2);
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(task1);
        expected.add(task2);
        assertEquals(expected, tasks.getTasks());
    }

    @Test
    public void test_delete_task() throws TaskNotFoundException {
        TaskList tasks = new TaskList(new ArrayList<>());
        Task task1 = new Todo("borrow book", false);
        Task task2 = new Todo("buy clothes", true);
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.deleteTask(1);
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(task2);
        assertEquals(expected.size(), tasks.getTasks().size());
    }
}
