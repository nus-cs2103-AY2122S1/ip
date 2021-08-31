package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toStringUI() {
        Event event = new Event("project meeting", LocalDate.parse("2021-08-21"), false);
        assertEquals("[E][ ] project meeting (at: AUGUST 21 2021)", event.toString());
        event.markAsDone();
        assertEquals("[E][X] project meeting (at: AUGUST 21 2021)", event.toString());
    }

    @Test
    public void toDataString() {
        Event event = new Event("project meeting", LocalDate.parse("2021-08-21"), false);
        assertEquals("E | 0 | project meeting | 2021-08-21", event.toDataString());
        event.markAsDone();
        assertEquals("E | 1 | project meeting | 2021-08-21", event.toDataString());
    }
}
