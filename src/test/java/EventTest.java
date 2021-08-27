import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import task.Event;
import task.Task;

public class EventTest {
    /**
     * Test for toString method.
     */
    @Test
    public void toStringTest() {
        Task testEvent = new Event("birthday", LocalDate.parse("2021-06-29"), LocalTime.parse("12:00"));
        assertEquals(testEvent.toString(), "[E][ ] birthday (at: Jun 29 2021 12:00 PM)");
    }

}
