package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        Event event = new Event("project meeting", LocalDate.parse("2021-08-21"), false);
        assertEquals("[E][ ] project meeting (at: AUGUST 21 2021)", event.toString());
        event.markAsDone();
        assertEquals("[E][X] project meeting (at: AUGUST 21 2021)", event.toString());
    }

    @Test
    public void testToDataString() {
        Event event = new Event("project meeting", LocalDate.parse("2021-08-21"), false);
        assertEquals("E | 0 | project meeting | 2021-08-21", event.toDataString());
        event.markAsDone();
        assertEquals("E | 1 | project meeting | 2021-08-21", event.toDataString());
    }
}
