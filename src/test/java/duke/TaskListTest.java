package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * This class encapsulates a unit test for the Ui class.
 *
 * @author Kleon Ang
 */
public class TaskListTest {
    /**
     * Test the printReply method for a valid given input.
     */
    @Test
    public void addTask_validInput_addSuccess() {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("test");
        String expected = "Got it. I've added this task:\n  "
                + "[T][ ] test"
                + "\nNow you have 1 tasks in the list.";
        assertEquals(expected, tasks.addTask(todo));
    }

    /**
     * Test the showLoadingError method for a given fileName.
     */
    @Test
    public void deleteTask_invalidIndex_printsErrorMessage() {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("test");
        tasks.add(todo);
        assertThrows(DukeException.class, () -> tasks.deleteTask(3));
    }
}
