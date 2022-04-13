package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


class EventTest {
    private static Event event = new Event("event", LocalDate.parse("2021-08-09"));

    @Test
    void testToString_success() {
        assertEquals("[E][ ] event(at: 08 09 2021)", event.toString());
    }

    @Test
    void saveStrings_success() {
        assertEquals("E::0::event::2021-08-09", String.join("::", event.getAsSaveStrings()));
    }
}
