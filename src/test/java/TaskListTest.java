import duke.DukeException;
import duke.Task;
import duke.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private final TaskList list = new TaskList();

    @Test
    public void addTest() {
        try {
            Task task = new Task("test");
            list.add(task);
            assertEquals(list.size(), 1);
        } catch (DukeException ignored) {
        }
    }

    @Test
    public void getTest() {
        try {
            Task task = new Task("test");
            list.add(task);
            assertEquals(list.get(0), task);
        } catch (DukeException ignored) {
        }
    }

    @Test
    public void deleteTest() {
        try {
            Task task = new Task("test");
            list.add(task);
            assertEquals(list.size(), 1);
            list.delete(0);
            assertEquals(list.size(), 0);
        } catch (DukeException ignored) {
        }
    }
}
