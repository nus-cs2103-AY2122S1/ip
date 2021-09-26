package duke;

import duke.tasktypes.Deadline;
import duke.tasktypes.Event;
import duke.tasktypes.Task;
import duke.tasktypes.ToDo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void createToDoAndMarkDone() {
        Task toDo = new ToDo("paint on book");
        toDo.markAsDone();
        assertEquals(toDo.toString(), "[T][X] paint on book");
    }

    @Test
    public void createEventAndMarkDone() {
        Task event = new Event("birthday party", "2001-01-01");
        event.markAsDone();
        assertEquals(event.toString(), "[E][X] birthday party (at: Jan 1 2001)");
    }

    @Test
    public void createDeadline() {
        Deadline deadline = new Deadline("exam book", "2021-12-31");
        assertEquals(deadline.toString(), "[D][ ] exam book (by: Dec 31 2021)");
    }

}
