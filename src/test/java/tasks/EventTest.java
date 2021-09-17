package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import side.tasks.Event;

public class EventTest {
    @Test
    public void toStringTestNoTime() {
        Event e = new Event("Test", "2020-11-11", "2020-11-12");
        assertEquals(e.toString(), "[E][ ] Test (at: 11 Nov 2020 to 12 Nov 2020)");
    }

    @Test
    public void toStringTestTime() {
        Event d = new Event("Test", "2020-11-11, 1800", "2020-11-12, 1800");
        assertEquals(d.toString(), "[E][ ] Test (at: 11 Nov 2020, 6:00:00 PM to 12 Nov 2020, 6:00:00 PM)");
    }
}
