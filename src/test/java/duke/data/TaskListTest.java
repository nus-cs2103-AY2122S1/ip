package duke.data;

import duke.commands.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testDelete() {
        ArrayList<Task> test = new ArrayList<>();
        Task task = new Task("test");
        test.add(task);
        test.add(task);
        test.add(task);
        TaskList testList = new TaskList(test);
        testList.delete(1);
        assertEquals(2, testList.size());
    }
}
