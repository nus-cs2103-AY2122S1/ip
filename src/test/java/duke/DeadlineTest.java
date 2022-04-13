package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadlineTest_createInstance_sameStringRep() {
        Deadline deadline = new Deadline("Finish Assignment", LocalDate.of(2021, 8, 7));
        assertEquals(deadline.toString(), "[D][ ] Finish Assignment(by: AUGUST 7 2021)");
    }
}
