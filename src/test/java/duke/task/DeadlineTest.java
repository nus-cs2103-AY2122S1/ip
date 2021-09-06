package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;


/**
 * Tests the functionality of the Deadline class.
 */
public class DeadlineTest {

    /**
     * Tests the creating of a Deadline task.
     *
     * @throws DukeException If the user input is invalid.
     */
    @Test
    public void testCreateDeadline() throws DukeException {
        Task dTask = new Deadline("read book", "2001-07-07");
        assertEquals("\t[D][ ] read book (by: Jul 07 2001)", dTask.toString());
    }

    /**
     * Tests the markAsDone() method in the Deadline class.
     *
     * @throws DukeException If the user input is invalid.
     */
    @Test
    public void testMarkAsDone() throws DukeException {
        Task dTask = new Deadline("read book", "2001-07-07");
        dTask.setDone();
        assertEquals(true, dTask.isDone());
    }
}
