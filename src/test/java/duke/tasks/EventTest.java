package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testStringConversion() {
        Task event = new Event("project meeting", LocalDate.parse("2021-08-29"));
        assertEquals("[E][ ] project meeting (at: Aug 29 2021)", event.toString());
    }

    @Test
    public void testFormatConversion() {
        Task event = new Event("project meeting", LocalDate.parse("2021-08-29"));
        assertEquals("E, 0, project meeting, 2021-08-29", event.format());
    }

    @Test
    public void testMarkAsDone() {
        Task event = new Event("return book", LocalDate.parse("2020-08-29"));
        assertEquals(" ", event.getStatusIcon());
        event.markAsDone();
        assertEquals("X", event.getStatusIcon());
    }
}
