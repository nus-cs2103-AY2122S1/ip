package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventToStringTest() {
        try {
            Event event = new Event("testing-event", "2021-08-20");
            assertEquals("[E][ ] testing-event(at: Aug 20 2021)", event.toString());
        } catch (DukeException e) {
            assertEquals(new DukeException("Invalid timing format! Enter dates in yyyy-mm-dd format").getMessage(), e.getMessage());
        }
    }

    @Test
    public void eventSaveStringTest() {
        try {
            Event event = new Event("testing-event", "2021-08-20");
            assertEquals("E,0,testing-event,2021-08-20", event.saveString());
        } catch (DukeException e) {
            assertEquals(new DukeException("Invalid timing format! Enter dates in yyyy-mm-dd format").getMessage(), e.getMessage());
        }
    }

    @Test
    public void invalidDateTest() {
        try {
            Event event = new Event("testing-event", "20 Aug");
        } catch (DukeException e) {
            assertEquals(new DukeException("Invalid timing format! Enter dates in yyyy-mm-dd format").getMessage(), e.getMessage());
        }
    }
}
