package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/**
 *  This class tests the class Task.
 *  The class Task represents the tasks in a list.
 *
 * @author Ryan Tian Jun.
 */
public class TaskTest {

    @Test
    public void markAsDone_alreadyDone_throwException() {
        Task task = new ToDo(Task.Type.T, true, "");
        try {
            assertEquals(true, task.markAsDone());
            fail();
        } catch (DukeException e) {
            assertEquals("Task has already been marked as done!", e.getMessage());
        }
    }

    @Test
    public void markAsDone_markDone_writtenCorrectly(){
        Task task = new ToDo(Task.Type.T, false, "");
        try {
            assertEquals(true, task.markAsDone());
        } catch (DukeException e) {
            fail();
        }
    }
}
