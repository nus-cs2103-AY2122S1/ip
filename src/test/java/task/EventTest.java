package task;

import main.java.duke.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    private Event e = new Event(false, "tutorial", "2021-03-21 2103");

    @Test
    public void constructor() {
        assertEquals("[E][ ] tutorial (at: 21 Mar 2021 9.03pm)", e.toString());
    }

    @Test
    public void setDone() {
        e.setDone();
        assertEquals("[E][X] tutorial (at: 21 Mar 2021 9.03pm)", e.toString());
    }

    @Test
    public void onDate() {
        assertEquals(false, e.onDate(LocalDate.of(2021, 4, 1)));
        assertEquals(true, e.onDate(LocalDate.of(2021, 3, 21)));
    }

}
