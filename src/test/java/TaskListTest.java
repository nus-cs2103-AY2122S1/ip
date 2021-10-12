
import Du.TaskList;
import Du.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void test_log_add_task() {
        TaskList tasks = new TaskList();
        Task t1 = new Task("buy bread", false, tasks);
        Task t2 = new Task("read book", false, tasks);
        ArrayList<Task> actual = new ArrayList<Task>();
        actual.add(t1);
        actual.add(t2);
        assertEquals(actual, tasks.getList_of_tasks());
    }


    @Test
    public void test_finish_task() {
        TaskList tasks = new TaskList();
        Task t1 = new Task("buy bread", false, tasks);
        t1.finish_task();
        assertEquals("X", t1.getStatus());
    }




}
