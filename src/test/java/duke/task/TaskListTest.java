package duke.task;

import duke.util.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void saveListTest() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask("event eat /at 2021-12-06");
        tasks.addTask("todo drink");

        System.out.println(tasks.saveList());
        assertEquals("1. [E][ ] eat (at: Dec 6 2021)\n" +
                "2. [T][ ] drink\n", tasks.saveList());
    }
}
