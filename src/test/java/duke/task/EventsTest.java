package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class EventsTest {

    @Test
    public void testToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm");
        LocalDateTime at = LocalDateTime.parse("27/08/2021 1400", formatter);
        String desc = "Birthday!!";
        Event events = new Event(desc, at);
        assertEquals("[E][ ] Birthday!! (at: 27 Aug 2021 1400)", events.toString());
    }

    @Test
    public void testToSaveString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm");
        LocalDateTime at = LocalDateTime.parse("27/08/2021 1400", formatter);
        String desc = "Birthday!!";
        Event events = new Event(desc, at);
        assertEquals("E|0|Birthday!!|27/08/2021 1400", events.toSaveString());
    }
}
