package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {
    Deadlines deadline = new Deadlines("submit report",
        LocalDate.parse("2021-08-25"),
        LocalTime.parse("14:00"));

    @Test
    public void toStringTest() {
        assertEquals("[D] [ ] submit report (by: Aug 25 2021 14:00)", deadline.toString());
    }

    @Test
    public void toDataFileStringTest() {
        assertEquals("D|0|submit report|2021-08-25|14:00", deadline.toDataFileString());
    }
}
