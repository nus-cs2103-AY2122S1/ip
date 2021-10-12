import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import back.Deadline;
import back.Event;
import back.Todo;

/**
 * This class represents a test file for Duke.java.
 */

public class TasksTest {
    @Test
    public void deadlineConstructor_createDeadline_deadlineStringReturned() {
        Deadline deadline = new Deadline(
                "2100 Lab3",
                LocalDate.parse("2020-09-16")
        );
        assertEquals("[ ] 2100 Lab3 (DEADLINE due on: 16 SEP 2020)", deadline.toString());
    }


    @Test
    public void todoMarkDone_completedTodo_todoStringReturned() {
        Todo todo = new Todo(
                "Stretch"
        );
        todo.markDone();
        assertEquals("[X] Stretch (TODO)", todo.toString());
    }

    @Test
    public void eventConstructor_createEvent_eventStringReturned() {
        Event event = new Event(
                "cake", LocalDate.parse("2020-11-18")
        );
        assertEquals("[ ] cake (EVENT occurs on: 18 NOV 2020)", event.toString());
    }
}
