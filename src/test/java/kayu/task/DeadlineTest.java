package kayu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeadlineTest {

    private Deadline deadline;

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
        assertEquals("[D][ ] mock tests\n   (by: 2020-09-14 12:30)", deadline.toString());
    }
}
