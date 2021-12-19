package kermit.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testGetShortForm() {
        assertEquals("E", new Event("Test event", LocalDate.parse("2021-04-05")).getShortForm());
    }

    @Test
    public void testToString() {
        Event event = new Event("Test event", LocalDate.parse("2021-04-05"));
        assertEquals("[E] [] Test event (at: Apr 5 2021)",
                event.toString());
        event.markAsComplete();
        assertEquals("[E] [X] Test event (at: Apr 5 2021)",
                event.toString());
    }
}
