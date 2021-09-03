import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;
import duke.tasks.ToDo;

public class DukeTasksTest {
    @Test
    public void toDoTaskTest() {
        ToDo item = new ToDo("test todo description");
        item.completeTask();
        String expected = "T | 1 | test todo description";
        assertEquals(expected, item.getFileRepr());
    }

    @Test
    public void deadlineTaskTest() {
        Deadline item = new Deadline("some deadline description",
                LocalDateTime.of(2011, 12, 2, 4, 59));
        String expected = "[D][ ] some deadline description (by: Dec 02 2011 04:59)";
        assertEquals(expected, item.toString());
    }
}
