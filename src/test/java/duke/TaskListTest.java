package duke;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import duke.task.Task;
import duke.task.ToDo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testGet_toDo_success() {
        duke.TaskList test = new duke.TaskList(new ArrayList<Task>() {{add(new ToDo("eat"));}});
        assertEquals(new ToDo("eat"), test.get(0));
    }
}
