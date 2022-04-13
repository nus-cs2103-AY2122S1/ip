package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {
    @Test
    public void testEncoding() {
        Event event1 = new Event("ceremony", "tmr");
        assertEquals(event1.encoding(), "E&&InProgress&&ceremony&&tmr");
        event1.setDone();
        assertEquals(event1.encoding(), "E&&Done&&ceremony&&tmr");
    }

    @Test
    public void testToString() {
        Event event1 = new Event("ceremony", "tmr");
        assertEquals(event1.toString(), "[E][ ] ceremony (at: tmr)");
        event1.setDone();
        assertEquals(event1.toString(), "[E][X] ceremony (at: tmr)");
    }

    @Test
    public void testGetDate() {
        Event event1 = new Event("ceremony", "tmr");
        Event event2 = new Event("ceremony", "2021-02-23");
        assertEquals(event1.getDate(), null);
        assertEquals(event2.getDate(), LocalDate.parse("2021-02-23"));
    }
}
