
import Du.Task;
import Du.TaskList;
import Du.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void test_finish_task() {
        TaskList tasks = new TaskList();
        Todo t1 = new Todo("buy bread", false, tasks);
        t1.finish_task();
        assertEquals("X", t1.getStatus());
    }


}
