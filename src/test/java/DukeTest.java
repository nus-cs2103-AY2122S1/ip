import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Deadline;
import duke.DukeDeadlineException;
import duke.DukeEventException;
import duke.DukeTodoException;
import duke.Event;
import duke.Todo;

public class DukeTest {
    @Test
    public void todoNormalTest() {
        try {
            Todo a = new Todo("a");
            assertEquals("a", a.getName());
        } catch (DukeTodoException e) {
            assertEquals(null, e);
        }
    }

    @Test
    public void eventNormalTest() {
        try {
            Event a = new Event("project meeting /at Mon 2-4pm");
            assertEquals("project meeting ", a.getName());
            assertEquals("Mon 2-4pm", a.getTime());
        } catch (DukeEventException e) {
            assertEquals(null, e);
        }
    }

    @Test
    public void deadlineNormalTest() {
        try {
            Deadline a = new Deadline("return book /by 2019-10-15");
            assertEquals("return book ", a.getName());
            assertEquals("Oct 15 2019", a.getDate());
        } catch (DukeDeadlineException e) {
            assertEquals(null, e.getMessage());
        }
    }
}
