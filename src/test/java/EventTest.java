import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {

    @Test
    public void dateOnlyTest() {
        Event event = new Event("Jon's Birthday", "2021-08-23");
        assertEquals(event.toString(), "[E][ ] Jon's Birthday (at: Aug 23 2021)");
    }

    @Test
    public void dateOnlyMarkDoneTest() {
        Event event = new Event("Jon's Birthday", "2021-08-23");
        event.markDone();
        assertEquals(event.toString(), "[E][X] Jon's Birthday (at: Aug 23 2021)");
    }

    @Test
    public void dateTimeMarkDoneTest() {
        Event event = new Event("Save Harambe", "2021-08-23", "18:00");
        event.markDone();
        assertEquals(event.toString(), "[E][X] Save Harambe (at: Aug 23 2021 06:00pm)");
    }

    @Test
    public void dateTimeTest() {
        Event event = new Event("Save Harambe", "2021-08-23", "18:00");
        assertEquals(event.toString(), "[E][ ] Save Harambe (at: Aug 23 2021 06:00pm)");
    }

    @Test
    public void invalidDateTest() {
        assertThrows(DateTimeParseException.class, () -> {
            Event event = new Event("work", "2021-20-23");
        });
    }

    @Test
    public void invalidTimeTest() {
        assertThrows(DateTimeParseException.class, () -> {
            Event event = new Event("work", "2021-08-23", "42:69");
        });
    }
}