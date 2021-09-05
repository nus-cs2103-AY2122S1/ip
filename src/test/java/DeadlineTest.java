import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Deadline;

/**
 * A class to test the methods in <code>Deadline</code>.
 */
public class DeadlineTest {

    @Test
    public void testDeadlineToString() {
        Deadline deadline = new Deadline("description", "tomorrow", false);
        assertEquals("[D][ ] description  (by: tomorrow)", deadline.toString());
    }
}
