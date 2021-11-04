package iris;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import iris.task.Deadline;

public class DeadlineTest {
    @Test
    public void constructor_validArguments_success() throws IrisException {
        Deadline deadline = new Deadline("test deadline", "2021-08-23");
        assertEquals("[D][ ] test deadline (by: Aug 23 2021)", deadline.toString());
    }

    @Test
    public void constructor_invalidDate_exceptionThrown() {
        try {
            new Deadline("test deadline", "Tuesday");
            fail();
        } catch (IrisException exception) {
            assertEquals("Invalid date provided.", exception.getMessage());
        }
    }

    @Test
    public void markComplete_success() throws IrisException {
        Deadline deadline = new Deadline("test deadline", "2021-08-23");
        deadline.markDone();
        assertEquals("[D][X] test deadline (by: Aug 23 2021)", deadline.toString());
    }

    @Test
    public void toCommand_incomplete_success() throws IrisException {
        Deadline deadline = new Deadline("test deadline", "2021-08-23");
        assertEquals("deadline test deadline /by 2021-08-23\n", deadline.toCommand(1));
    }

    @Test
    public void toCommand_completed_success() throws IrisException {
        Deadline deadline = new Deadline("test deadline", "2021-08-23");
        deadline.markDone();
        assertEquals("deadline test deadline /by 2021-08-23\ndone 1\n", deadline.toCommand(1));
    }
}
