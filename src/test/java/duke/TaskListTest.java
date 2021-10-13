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
     * Tests the addTask method for a valid given input.
     */
    @Test
    public void addTask_validInput_addSuccess() {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("test");
        String expected = "Got it. I've added this task:\n  "
                + "[T][MEDIUM][ ] test"
                + "\nNow you have 1 tasks in the list.";
        assertEquals(expected, tasks.addTask(todo));
    }

    /**
     * Tests the deleteTask method for a given invalid index.
     */
    @Test
    public void deleteTask_invalidIndex_printsErrorMessage() {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("test");
        tasks.add(todo);
        assertThrows(DukeException.class, () -> tasks.deleteTask(3));
    }
}
