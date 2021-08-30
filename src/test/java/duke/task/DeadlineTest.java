package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void onlyDateTest() {
        Deadline deadline = new Deadline("deadline 1",
                LocalDate.parse("2021-08-26"));
        assertEquals("[D][ ] deadline 1 (by: 26 Aug 2021)",
                deadline.toString());
    }

    @Test
    public void dateAndTimeTest() {
        Deadline deadline = new Deadline("deadline 1",
                LocalDate.parse("2021-08-26"), LocalTime.parse("13:00"));
        assertEquals("[D][ ] deadline 1 (by: 26 Aug 2021, 13:00)",
                deadline.toString());
    }
}
