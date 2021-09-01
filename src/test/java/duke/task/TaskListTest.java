package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.DukeException;


public class TaskListTest {

    @Test
    public void testAddTask() throws DukeException {
        Task dTask = new Deadline("read book", "2001-07-07");
        TaskList ls = new TaskList();
        ls.addTask(dTask);
        assertEquals("\t[D][ ] read book (by: Jul 07 2001)", ls.getTask(0).toString());
    }

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