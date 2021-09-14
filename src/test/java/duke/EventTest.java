package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testToString1() {
        Event event = new Event("project meeting", "4pm");
        assertEquals("[E][ ] project meeting (at: 4pm)", event.toString());
    }

    @Test
    public void testToString2() {
        Event event = new Event("supper date", "11pm");
        assertEquals("[E][ ] supper date (at: 11pm)", event.toString());
    }

    @Test
    public void testToData1() {
        Event event = new Event("project meeting", "4pm");
        assertEquals("E~S~0~S~project meeting~S~4pm", event.toData());
    }

    @Test
    public void testToData2() {
        Event event = new Event("supper date", "11pm");
        assertEquals("E~S~0~S~supper date~S~11pm", event.toData());
    }
}
