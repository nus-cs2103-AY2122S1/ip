import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

public class TaskTest {
    @Test
    public void todoTest() {
        Todo todo = new Todo("test todo");
        assertEquals("T | 0 | test todo", todo.getFileString());
    }

    @Test
    public void deadlineTest() {
        Deadline deadline = new Deadline("test deadline", "Jan 12 2021 01:00");
        assertEquals("D | 0 | test deadline | Jan 12 2021 01:00", deadline.getFileString());
    }

    @Test
    public void eventTest() {
        Event event = new Event("test event", "Jan 12 2021 01:00");
        assertEquals("E | 0 | test event | Jan 12 2021 01:00", event.getFileString());
    }
}
