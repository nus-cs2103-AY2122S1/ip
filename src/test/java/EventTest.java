import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import duke.task.Event;

/**
 * Test cases for Event.
 */
public class EventTest {

    /**
     * Test constructing an Event with Date only.
     */
    @Test
    public void event_dateOnly_correctStringRepresentation() {
        Event event = new Event("Jon's Birthday", "2021-08-23");
        assertEquals(event.toString(), "[E][ ] Jon's Birthday (at: Aug 23 2021)");
    }

    /**
     * Test marking an Event with Date only done.
     */
    @Test
    public void markDone_dateOnlyEvent_correctStringRepresentation() {
        Event event = new Event("Jon's Birthday", "2021-08-23");
        event.markDone();
        assertEquals(event.toString(), "[E][X] Jon's Birthday (at: Aug 23 2021)");
    }

    /**
     * Test constructing an Event with Date and Time.
     */
    @Test
    public void event_dateAndTime_correctStringRepresentation() {
        Event event = new Event("Save Harambe", "2021-08-23", "18:00");
        assertEquals(event.toString(), "[E][ ] Save Harambe (at: Aug 23 2021 06:00pm)");
    }

    /**
     * Test marking an Event with Date and Time as done.
     */
    @Test
    public void markDone_dateAndTimeEvent_correctStringRepresentation() {
        Event event = new Event("Save Harambe", "2021-08-23", "18:00");
        event.markDone();
        assertEquals(event.toString(), "[E][X] Save Harambe (at: Aug 23 2021 06:00pm)");
    }

    /**
     * Invalid date test.
     */
    @Test
    public void event_invalidDate_dateTimeParseExceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> new Event("work", "2021-20-23"));
    }

    /**
     * Invalid time test.
     */
    @Test
    public void event_invalidTime_dateTimeParseExceptionThrown() {
        assertThrows(DateTimeParseException.class, () -> new Event("work", "2021-08-23", "42:69"));
    }
}
