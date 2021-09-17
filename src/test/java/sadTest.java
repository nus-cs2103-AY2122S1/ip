import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import addon.Task.Deadline;
import addon.Task.Event;
import addon.Task.Todo;

/**
 * This class represents a test file for Duke.java.
 */

public class sadTest {
    @Test
    public void Deadline_createDeadline_deadlineStringReturned() {
        Deadline deadline = new Deadline(
                "2100 Lab3",
                LocalDate.parse("2020-09-16")
        );
        assertEquals("[ ] 2100 Lab3 (DEADLINE due on: 16 SEP 2020)", deadline.toString());
    }


    @Test
    public void Todo_completedTodo_TodoStringReturned() {
        Todo todo = new Todo(
                "Stretch"
        );
        todo.markDone();
        assertEquals("[X] Stretch (TODO)", todo.toString());
    }

    @Test
    public void Event_completeEvent_EventStringReturned() {
        Event event = new Event(
                "cake", LocalDate.parse("2020-11-18")
        );
        assertEquals("[ ] cake (EVENT occurs on: 18 NOV 2020)", event.toString());
    }
}
