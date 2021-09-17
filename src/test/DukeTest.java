/**
 * This class represents a test file for Duke.java.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

import duke.tasks.Deadline;
import duke.tasks.Todo;
import duke.tasks.Event;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void Deadline_createDeadline_deadlineStringReturned() {
        Deadline deadline = new Deadline(
                "Finish assignment",
                LocalDate.parse("2020-12-12")
        );
        assertEquals("[D][ ] Finish assignment (by: 12 DECEMBER 2020) ", deadline.toString());
    }

    @Test
    public void Todo_completeTodo_TodoStringReturned() {
        Todo todo = new Todo(
                "Eat my potatoes"
        );
        todo.markAsDone();
        assertEquals("[T][X] Eat my potatoes ", todo.toString());
    }

    @Test
    public void Event_completeEvent_EventStringReturned() {
        Event event = new Event(
                "Meeting friends",
                "NUS"
        );
        event.markAsDone();
        assertEquals("[E][X] Meeting friends (at: NUS) ", event.toString());
    }
}