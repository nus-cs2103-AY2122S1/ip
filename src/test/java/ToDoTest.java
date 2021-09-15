import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void test_task_status_1() {
        Task newTask = new Todo("borrow book", false);
        assertEquals("0", newTask.getStatus());
    }

    @Test
    public void test_task_status_2() {
        Task newTask = new Todo("borrow book", false);
        newTask.checkOffTask();
        assertEquals("1", newTask.getStatus());
    }
}
