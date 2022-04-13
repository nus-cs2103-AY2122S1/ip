package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class EventTest {
    private final String errorHeader = "(O_O;) Oh no!! ";

    @Test
    public void init_emptyDescription_throwException() {
        try {
            new Event("", "2pm");
            fail();
        } catch (DukeException e) {
            assertEquals(errorHeader + "Looks like you forgot to include a description of the event.", e.toString());
        }
    }

    @Test
    public void init_emptyAt_throwException() {
        try {
            new Event("task", "");
            fail();
        } catch (DukeException e) {
            assertEquals(errorHeader + "Looks like you forgot to include when the event is at.", e.toString());
        }
    }

    @Test
    public void testToString() {
        try {
            Event event = new Event("task", "1-2pm");

            assertEquals("[E][ ] task (at: 1-2pm)", event.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testDataString() {
        try {
            Event event = new Event("task", "1-2pm");

            assertEquals("E|0|task|1-2pm", event.toDataString("|"));
        } catch (DukeException e) {
            fail();
        }
    }
}
