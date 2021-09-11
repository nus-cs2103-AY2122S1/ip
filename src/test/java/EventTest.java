import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] abc def (at: today)", new Event("abc def", "today").toString());
        assertEquals("[E][ ] abc def (at: 123)", new Event("abc def", "123").toString());
        assertEquals("[E][ ] abc def (at: 13-12-2021)", new Event("abc def", "13-12-2021").toString());
    }

    @Test
    public void testDateHandling() {
        assertEquals("[E][ ] abc def (at: 05/12/2021)", new Event("abc def", "2021-12-05").toString());
    }

    @Test
    public void testMarkDone() {
        Event e = new Event("abc def", "today");
        assertEquals("[E][ ] abc def (at: today)", e.toString());
        e.markDone();
        assertEquals("[E][X] abc def (at: today)", e.toString());
    }

    @Test
    public void testPrintToFile() {
        Event e = new Event("abc def", "today");
        assertEquals("E | 0 | abc def | today", e.printToFile());
        e.markDone();
        assertEquals("E | 1 | abc def | today", e.printToFile());

        assertEquals("E | 0 | abc def | 2021-12-05", new Event("abc def", "2021-12-05").printToFile());
    }
}
