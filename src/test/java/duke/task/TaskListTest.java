package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

/**
 * Tests the functionality of the TaskList class.
 */
public class TaskListTest {

    /**
     * Tests the addTask(Task task) method in the TaskList class.
     *
     * @throws DukeException If the user input is invalid.
     */
    @Test
    public void testAddTask() throws DukeException {
        Task dTask = new Deadline("read book", "2001-07-07");
        TaskList ls = new TaskList();
        ls.addTask(dTask);
        assertEquals("\t[D][ ] read book (by: Jul 07 2001)", ls.getTask(0).toString());
    }

    /**
     * Tests the getSize() method in the TaskList class.
     *
     * @throws DukeException If the user input is invalid.
     */
    @Test
    public void testGetSize() throws DukeException {
        Task dTask = new Deadline("read book", "2001-07-07");
        TaskList ls = new TaskList();
        ls.addTask(dTask);
        ls.addTask(dTask);
        ls.addTask(dTask);
        assertEquals(3, ls.getSize());
    }
}
