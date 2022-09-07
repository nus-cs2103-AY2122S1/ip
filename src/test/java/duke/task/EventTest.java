package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toStringTest() {
        Event event = new Event("Project meeting", "idk");
        assertEquals("[E][ ] Project meeting (at: idk)", event.toString());
    }

    @Test
    public void saveDataTest() {
        Event event = new Event("Project meeting", "idk");
        assertEquals("event 0 Project meeting /at idk", event.saveData());
    }
}
