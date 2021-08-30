package duke;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void validEventTest() {
        Event e = new Event("New door arriving", "2021-01-21");
        assertEquals(e.toString(), "[E][ ] New door arriving (at: Jan 21 2021)");
    }
}
