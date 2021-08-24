import duke.task.Deadline;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {
    @Test
    public void deadline_toString() {
        assertEquals("[D][x] homework (by: Thursday, 11 November 2021)",
                new Deadline("homework", LocalDate.of(2021, 11, 11), true).toString());
    }

    @Test
    public void todo_setDone() {
        ToDo todo = new ToDo("homework");
        todo.setDone(true);
        assertTrue(todo.isDone());
    }
}
