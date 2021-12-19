package duke.test;
import duke.Deadline;
import duke.exception.InvalidDeadlineException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class that Test functionality of a Deadline.
 *
 */
public class DeadlineTest {
    /**
     * Test writeTask() method.
     *
     * @throws InvalidDeadlineException when users gives invalid deadline.
     */
    @Test
    public void testWriteTask() throws InvalidDeadlineException {
        Deadline t = new Deadline("Task 1" , "2021-10-03");
        assertEquals("D | 0 | Task 1 | Oct 3 2021", t.writeTask());
    }

    /**
     * Test writeTask() method when users give invalid input.
     *
     */
    @Test
    public void testWriteTask2() {
        try {
            Deadline t = new Deadline("Task 1" , "03/10/2021");
            assertEquals("", t.writeTask());
        } catch (InvalidDeadlineException e) {
            assertEquals("Please enter deadline in the format yyyy-mm-dd",
                    e.getMessage());
        }
    }
}
