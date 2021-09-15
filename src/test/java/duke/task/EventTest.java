package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class EventTest {

    private static final String DESCRIPTION = "task description";

    // yyyy-mm-dd format.
    private static final LocalDate TIME = LocalDate.parse("2020-01-01");

    private final Event event = new Event(DESCRIPTION, TIME);

    @Test
    void getTaskType_event_stringE() {
        assertEquals("E", event.getTaskType());
    }

    @Test
    void getTime_event_localDate() {
        assertEquals(TIME, event.getTime());
    }

    @Test
    void toString_event_formattedString() {
        assertEquals("[E][ ] " + DESCRIPTION + " (at: 1 January 2020)", event.toString());
    }

    @Test
    void equals_sameEvent_true() {
        assertEquals(event, new Event(DESCRIPTION, TIME));
    }

    @Test
    void equals_differentEvent_false() {
        LocalDate otherLocalDate = LocalDate.parse("2020-02-02");

        Event doneEvent = new Event(DESCRIPTION, TIME);
        doneEvent.markAsDone();

        Event differentTimeEvent = new Event(DESCRIPTION, otherLocalDate);

        Event differentDescriptionEvent = new Event("other", TIME);

        assertNotEquals(event, doneEvent);
        assertNotEquals(event, differentTimeEvent);
        assertNotEquals(event, differentDescriptionEvent);
    }
}
