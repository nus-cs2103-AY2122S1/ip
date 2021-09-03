package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testToString_date() {
        assertEquals("[D][ ] deadline 1 (by: Aug 23 2021)",
                new Deadline("deadline 1", LocalDate.of(2021, 8, 23)).toString());
    }

    @Test
    public void testToString_datetime() {
        assertEquals("[D][ ] deadline 1 (by: Aug 23 2021 07:27 PM)",
                new Deadline("deadline 1", LocalDate.of(2021, 8, 23),
                        LocalTime.of(19, 27)).toString());
    }

    @Test
    public void testToSaveString_date() {
        assertEquals("| D | 0 | deadline 1 | 2021-08-23",
                new Deadline("deadline 1", LocalDate.of(2021, 8, 23)).toSaveString());
    }

    @Test
    public void testToSaveString_datetime() {
        assertEquals("| D | 0 | deadline 1 | 2021-08-23 | 19:27",
                new Deadline("deadline 1", LocalDate.of(2021, 8, 23),
                        LocalTime.of(19, 27)).toSaveString());
    }
}
