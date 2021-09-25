package tasks;

import exceptions.DukeInvalidDateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testTaskDescription() throws DukeInvalidDateException {
        Task task = Deadline.newDeadlineTask("blah -by blah");
        String expected = "blah (by: blah)";
        assertEquals(expected, task.taskDescription());
    }
}
