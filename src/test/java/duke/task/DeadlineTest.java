package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.DukeException;


public class DeadlineTest {
    @Test
    public void testCreateDeadline() throws DukeException {
        Task dTask = new Deadline("read book", "2001-07-07");
        assertEquals("\t[D][ ] read book (by: Jul 07 2001)", dTask.toString());
    }

    @Test
    public void testMarkAsDone() throws DukeException {
        Task dTask = new Deadline("read book", "2001-07-07");
        dTask.markAsDone();
        assertEquals(true, dTask.isDone());
    }
}