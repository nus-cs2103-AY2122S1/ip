import duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    static final String DESCRIPTION = "I am an event task";
    static final String DATE = "Mon 2-4pm";

    @Test
    public void testStringConversion_done() {
        Event task = new Event(DESCRIPTION, DATE, true);
        String expectedOutput = "[E][X] " + DESCRIPTION + " (at: " + DATE + ")";
        assertEquals(expectedOutput, task.toString());
    }

    @Test
    public void testStringConversion_undone() {
        Event task = new Event(DESCRIPTION, DATE, false);
        String expectedOutput = "[E][] " + DESCRIPTION + " (at: " + DATE + ")";
        assertEquals(expectedOutput, task.toString());
    }

    @Test
    public void testStringWriteConversion() {
        Event task = new Event(DESCRIPTION, DATE, true);
        String expectedOutput = "event | 1 | I am an event task | Mon 2-4pm";
        assertEquals(expectedOutput, task.toWriteString());
    }
}
