import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import task.Task;
import task.Todo;

public class TaskListTest {
    /**
     * Tests if findTask method of TaskList works as expected.
     */
    @Test
    public void testFind() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Read Book"));
        tasks.add(new Todo("Eat Dinner"));
        TaskList taskList = new TaskList(tasks);
        assertEquals(1, taskList.findTask("read").size());
    }
}
