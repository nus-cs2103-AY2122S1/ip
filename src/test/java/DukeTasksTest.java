import duke.tasks.Deadline;
import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTasksTest {
    @Test
    public void ToDoTaskTest() {
        ToDo item = new ToDo("test todo description");
        item.completeTask();
        String expected = "T | 1 | test todo description";
        assertEquals(expected, item.getFileRepr());
    }

    @Test
    public void DeadlineTaskTest() {
        Deadline item = new Deadline("some deadline description",
                LocalDateTime.of(2011,12,2,4,59));
        String expected = "[D][ ] some deadline description (by: Dec 02 2011 04:59)";
        assertEquals(expected, item.toString());
    }
}
