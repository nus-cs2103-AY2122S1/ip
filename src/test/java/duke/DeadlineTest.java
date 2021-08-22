package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineTest() {
        Deadline deadline = new Deadline("Finish Assignment", LocalDate.of(2021,8,7));
        assertEquals(deadline.toString(),"[D][ ] Finish Assignment(by: AUGUST 7 2021)");
    }
}
