package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void constructor_success() {
        Deadline deadline;
        deadline = new Deadline("test deadline", "Nov 11 1111");
        String expected = "[D][ ] test deadline (by: Nov 11 1111)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void markAsDone_success() {
        Deadline deadline = new Deadline("test deadline", "Dec 12 1122");
        deadline.markAsDone();
        String expected = "[D][X] test deadline (by: Dec 12 1122)";
        assertEquals(expected, deadline.toString());
    }
}
