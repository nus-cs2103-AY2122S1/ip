package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void showTaskTest() {
        Event e1 = new Event("running", "02/09/2021 1600", false);
        assertEquals("[E][ ] running (at: Sep 02 2021, 16:00)", e1.showTask());
    }

    @Test
    public void saveTaskTest() {
        Event e1 = new Event("dancing", "02/09/2021 1800", false);
        assertEquals("E | 0 | dancing | 02/09/2021 1800", e1.saveTask());
    }
}