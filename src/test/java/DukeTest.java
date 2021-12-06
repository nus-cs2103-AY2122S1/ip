import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class DukeTest {

    @Test
    public void todo_stringRepresentation() {
        Todo testTodo = new Todo("test");
        testTodo.markAsDone();
        String actual = testTodo.toString();
        String expected = "[T][X] test";
        assertEquals(expected, actual);
    }

    @Test
    public void deadline_stringRepresentation() {
        Deadline testDeadline = new Deadline("test", "2021-01-01");
        testDeadline.markAsDone();
        String actual = testDeadline.toString();
        String expected = "[D][X] test (by: 01 January 2021)";
        assertEquals(expected, actual);
    }

    @Test
    public void event_stringRepresentation() {
        Event testEvent = new Event("test", "Mon 2-4pm");
        testEvent.markAsDone();
        String actual = testEvent.toString();
        String expected = "[E][X] test (at: Mon 2-4pm)";
        assertEquals(expected, actual);
    }

}
