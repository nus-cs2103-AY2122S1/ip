package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {

    @Test
    public void createEvent_withDescriptionAndDate_correctDateFormatted() {
        Event event = new Event("project meeting", "06/08/2021 1400");
        assertEquals("[E][ ] project meeting (at: Aug 06 2021 2:00 pm)", event.toString());
    }
}
