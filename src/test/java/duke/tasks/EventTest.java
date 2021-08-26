package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testFormatForSave() {
        Event e = new Event("JUnit test iP", LocalDateTime.of(2021, 8, 26, 22, 30), null);
        assertEquals("E | 0 | JUnit test iP | 2021-08-26T22:30 | null", e.formatForSave());
    }

    @Test
    public void testToString_noEndTime_descriptionStartsWithAt() {
        Event e = new Event("JUnit test iP", LocalDateTime.of(2021, 8, 26, 22, 30), null);
        assertEquals("[E][ ] JUnit test iP (at: 26 Aug 2021 10.30pm)", e.toString());
    }

    @Test
    public void testToString_withEndTime_descriptionStartsWithFrom() {
        Event e = new Event("JUnit test iP",
                true,
                LocalDateTime.of(2021, 8, 26, 22, 30),
                LocalDateTime.of(2021, 8, 27, 3, 0));
        assertEquals("[E][X] JUnit test iP (from: 26 Aug 2021 10.30pm — 27 Aug 2021 3.00am)", e.toString());
    }
}
