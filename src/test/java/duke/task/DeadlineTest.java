package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    private Deadline deadline = new Deadline("sell book", "2020-04-21");

    @Test
    public void getDeadline_deadlineInitialized_localDateReturned() {
        assertEquals(deadline.getDeadline().toString(), "2020-04-21");
    }

    @Test
    public void getDescription_deadlineInitialized_descriptionReturned() {
        assertEquals(deadline.getDescription(), "sell book");
    }

    @Test
    public void getStatusIcon_deadlineInitialized_statusIconReturned() {
        assertEquals(deadline.getStatusIcon(), " ");
    }

    @Test
    public void toString_deadlineInitialized_descriptionAndDeadlineReturned() {
        assertEquals(deadline.toString(), "[D][ ] sell book (by: 21 April 2020)");
    }

    @Test
    public void markAsDone_deadlineInitialized_taskMarkedAsDone() {
        Deadline deadline = new Deadline("sell book", "2020-04-21");
        deadline.markAsDone();
        assertEquals(deadline.toString(), "[D][X] sell book (by: 21 April 2020)");
    }
}
