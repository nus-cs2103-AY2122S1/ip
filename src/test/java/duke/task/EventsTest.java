package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {
    Events event = new Events("tutorial",
        LocalDate.parse("2021-08-25"),
        LocalTime.parse("14:00"),
        LocalTime.parse("16:00"));

    @Test
    public void toStringTest() {
        assertEquals("[E] [ ] tutorial (at: Aug 25 2021 14:00-16:00)", event.toString());
    }

    @Test
    public void toDataFileStringTest() {
        assertEquals("E|0|tutorial|2021-08-25|14:00|16:00", event.toDataFileString());
    }
}
