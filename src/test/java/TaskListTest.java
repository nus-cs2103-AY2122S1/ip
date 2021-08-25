import duke.DukeException;
import duke.TaskList;
import duke.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void deleteTask_3Task_taskDeleted() {
        try {
            TaskList tasks = new TaskList();
            tasks.addTask(new ToDo("1"));
            tasks.addTask(new ToDo("2"));
            tasks.addTask(new ToDo("3"));
            tasks.deleteTask(1);
            assertEquals("1. [T][ ] 1\n2. [T][ ] 3", tasks.toString());
        } catch (DukeException err) {
            fail();
        }

    }


}
