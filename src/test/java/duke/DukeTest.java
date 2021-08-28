package duke;

import duke.tasktypes.Deadlines;
import duke.tasktypes.Events;
import duke.tasktypes.Task;
import duke.tasktypes.ToDos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void createToDoAndMarkDone() {
        Task toDo = new ToDos("paint on book");
        toDo.markAsDone();
        assertEquals(toDo.toString(), "[T][X] paint on book");
    }

    @Test
    public void createEventAndMarkDone() {
        Task event = new Events("birthday party", "2001-01-01");
        event.markAsDone();
        assertEquals(event.toString(), "[E][X] birthday party (at: Jan 1 2001)");
    }

    @Test
    public void createDeadline() {
        Deadlines deadline = new Deadlines("exam book", "2021-12-31");
        assertEquals(deadline.toString(), "[D][ ] exam book (by: Dec 31 2021)");
    }

}
