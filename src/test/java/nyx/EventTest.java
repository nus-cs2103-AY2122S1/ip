package nyx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion_done(){
        Event event = new Event("party", "2021-04-12 15:35", true);
        assertEquals("[E][X] party (at: Mon 12 Apr 2021, 3:35PM)", event.toString());
    }

    @Test
    public void testStringConversion_notDone() {
        Event event = new Event("party", "2021-04-12 15:35");
        assertEquals("[E][ ] party (at: Mon 12 Apr 2021, 3:35PM)", event.toString());
    }

    @Test
    public void testDataFormatConversion_notDone() {
        Event event = new Event("party", "2021-04-12 15:35");
        assertEquals("E, 0, party, 2021-04-12 15:35\n", event.dataFormat());
    }

    @Test
    public void testDataFormatConversion_done() {
        Event event = new Event("party", "2021-04-12 15:35", true);
        assertEquals("E, 1, party, 2021-04-12 15:35\n", event.dataFormat());
    }

    @Test
    public void testSetDone() {
        Event event = new Event("party", "2021-04-12 15:35");
        event.setDone();
        assertEquals("[E][X] party (at: Mon 12 Apr 2021, 3:35PM)", event.toString());
    }
}
