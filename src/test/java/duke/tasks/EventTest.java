package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testNewEvent() {
        assertEquals("[E][ ] dinner (at: Aug 29 2021)", new Event("dinner", "Aug 29 2021").toString());
    }
}
