package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    private static final String DESCRIPTION = "task description";

    // yyyy-mm-dd format.
    private static final LocalDate TIME = LocalDate.parse("2020-01-01");

    Event event = new Event(DESCRIPTION, TIME);

    @Test
    void getTaskType_event_E() {
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
}