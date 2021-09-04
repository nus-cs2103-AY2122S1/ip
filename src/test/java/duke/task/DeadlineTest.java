package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void constructor_success() {
        Deadline deadline = new Deadline("test deadline", "tomorrow");

        String expected = "[D][ ] test deadline (by: tomorrow)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void markAsDone_success() {
        Deadline deadline = new Deadline("test deadline", "yesterday");
        deadline.markAsDone();

        String expected = "[D][X] test deadline (by: yesterday)";
        assertEquals(expected, deadline.toString());
    }
}
