package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    /**
     * Test the Event constructor and toString methods.
     */
    @Test
    public void toStringTest() {
        assertEquals("[E][ ] sample event (at: Dec 02 2019 18:00)",
                new Event("sample event /at 02/12/2019 1800").toString());
    }
}
