package duke.logic.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void createDeadline() {
        assertEquals("[D] [ ] run (by: Oct 20 2020)",
                new Deadline("run", "", LocalDate.parse("2020-10-20")).toString());
    }

}
