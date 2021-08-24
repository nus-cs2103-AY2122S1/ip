package kayu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class DeadlineTest {

    private static Deadline deadline;

    @BeforeEach
    public void setUp() {
        String desc = "mock tests";
        LocalDate byDate = LocalDate.parse("2020-09-14");
        LocalTime byTime = LocalTime.parse("12:30");
        deadline = new Deadline(desc, byDate, byTime);
    }

    @Test
    public void testEncodingConversion() {
        assertEquals("D # 0 # mock tests # 2020-09-14 # 12:30", deadline.toEncodedString());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] mock tests (by: 2020-09-14 12:30)", deadline.toString());
    }
}