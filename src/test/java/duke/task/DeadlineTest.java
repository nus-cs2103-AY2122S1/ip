package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        Task testDeadline = new Deadline("run", "2021-10-10", "18:00");
        assertEquals("[D][ ] run (by: Oct 10 2021 18:00)", testDeadline.toString());
    }
}