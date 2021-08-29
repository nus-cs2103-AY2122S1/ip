package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventsTest {
    @Test
    public void toStringTest() {
        Events events = new Events("Project meeting", "idk");
        assertEquals("[E][ ] Project meeting (at: idk)", events.toString());
    }

    @Test
    public void saveDataTest() {
        Events events = new Events("Project meeting", "idk");
        assertEquals("event 0 Project meeting /at idk", events.saveData());
    }
}
