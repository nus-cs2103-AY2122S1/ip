package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventTest {
    
    private static Event event;

    @BeforeEach
    public void setUp() {
        String desc = "mock tests";
        LocalDate atDate = LocalDate.parse("2020-09-14");
        LocalTime atTime = LocalTime.parse("12:30");
        event = new Event(desc, atDate, atTime);
    }

    @Test
    public void testEncodingConversion() {
        assertEquals("E # 0 # mock tests # 2020-09-14 # 12:30", event.toEncodedString());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] mock tests (at: 2020-09-14 12:30)", event.toString());
    }
}
