package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class EventTest {

    @Test
    public void eventTask_newEventAtCorrectTimeFormat_correctTimeFormatString() {
        Event e = new Event("New door arriving", "2021-01-21");
        assertEquals(e.toString(), "[E][ ] New door arriving (at: Jan 21 2021)");
    }

    @Test
    public void eventTask_newEventAtIncorrectTimeFormat_defaultTimeFormatString() {
        Event e = new Event("New door arriving", "2021 Jan 21");
        assertEquals(e.toString(), "[E][ ] New door arriving (at: 2021 Jan 21)");
    }
}
