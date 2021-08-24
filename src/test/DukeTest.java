import duke.tasks.Deadline;
import duke.tasks.Todo;
import duke.tasks.Event;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DukeTest {
    @Test
    public void DeadlineTest() {
        Deadline deadline = new Deadline(
                "Finish assignment",
                LocalDate.parse("2020-12-12")
        );
        assertEquals("[D][ ] Finish assignment (by: 12 DECEMBER 2020)", deadline.toString());
    }

    @Test
    public void TodoTest() {
        Todo todo = new Todo(
                "Eat my potatoes"
        );
        todo.markAsDone();
        assertEquals("[T][X] Eat my potatoes", todo.toString());
    }

    @Test
    public void EventTest() {
        Event event = new Event(
                "Meeting friends",
                "NUS"
        );
        event.markAsDone();
        assertEquals("[E][X] Meeting friends (at: NUS)", event.toString());
    }
}