package luke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventToStringTest() {
        try {
            Event event = new Event("testing-event", "2021-08-20");
            assertEquals("[E][ ] testing-event(at: Aug 20 2021)", event.toString());
        } catch (LukeException e) {
            assertEquals(LukeException.INVALID_DATE_FORMAT_EXCEPTION.getMessage(), e.getMessage());
        }
    }

    @Test
    public void eventSaveStringTest() {
        try {
            Event event = new Event("testing-event", "2021-08-20");
            assertEquals("E,0,testing-event,2021-08-20", event.saveString());
        } catch (LukeException e) {
            assertEquals(LukeException.INVALID_DATE_FORMAT_EXCEPTION.getMessage(), e.getMessage());
        }
    }
}