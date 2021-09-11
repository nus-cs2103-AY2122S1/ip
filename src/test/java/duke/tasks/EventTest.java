package duke.tasks;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testToString_date() {
        assertEquals("[E][ ] event 1 (at: Aug 23 2021)",
                new Event("event 1", LocalDate.of(2021, 8, 23)).toString());
    }

    @Test
    public void testToString_datetime() {
        assertEquals("[E][ ] event 1 (at: Aug 23 2021 07:27 PM)",
                new Event("event 1", LocalDate.of(2021, 8, 23),
                        LocalTime.of(19, 27)).toString());
    }

    @Test
    public void testToSaveString_date() {
        assertEquals("| E | 0 | event 1 | 2021-08-23",
                new Event("event 1", LocalDate.of(2021, 8, 23)).toSaveString());
    }

    @Test
    public void testToSaveString_datetime() {
        assertEquals("| E | 0 | event 1 | 2021-08-23 | 19:27",
                new Event("event 1", LocalDate.of(2021, 8, 23),
                        LocalTime.of(19, 27)).toSaveString());
    }
}
