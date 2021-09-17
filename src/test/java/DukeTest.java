/**
 * This class represents a test file for Duke.java.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;


public class DukeTest {
    @Test
    public void Deadline_createDeadline_deadlineStringReturned() {
        Deadline deadline = new Deadline(
                "Finish assignment",
                LocalDateTime.parse("2020-12-12T23:59")
        );
        assertEquals("[D][ ] Finish assignment (by: 12 Dec 2020 11:59 PM) ", deadline.toString());
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
                LocalDateTime.parse("2021-09-09T10:30"),
                LocalTime.parse("11:30")
        );
        event.markAsDone();
        assertEquals("[E][X] Meeting friends (at: 9 Sep 2021 10:30 AM - 11:30 AM) ", event.toString());
    }
}
