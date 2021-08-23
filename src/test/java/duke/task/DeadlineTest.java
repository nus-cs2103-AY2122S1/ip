package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    Deadline deadline = new Deadline("sell book", "2020-04-21");

    @Test
    public void getDeadlineTest() {
        assertEquals(deadline.getDeadline().toString(), "2020-04-21");
    }

    @Test
    public void getDescriptionTest() {
        assertEquals(deadline.getDescription(), "sell book");
    }

    @Test
    public void getStatusIconTest() {
        assertEquals(deadline.getStatusIcon(), " ");
    }

    @Test
    public void toStringTest() {
        assertEquals(deadline.toString(), "[D][ ] sell book (by: 21 April 2020)");
    }

    @Test
    public void markAsDoneTest() {
        Deadline deadline = new Deadline("sell book", "2020-04-21");
        deadline.markAsDone();
        assertEquals(deadline.toString(), "[D][X] sell book (by: 21 April 2020)");
    }
}
