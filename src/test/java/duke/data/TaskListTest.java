package duke.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.data.task.Todo;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList tasks = new TaskList(new ArrayList<>());
        try {
            Task t = new Todo("clean house");
            tasks.add(t);
            assertEquals(1, tasks.getCount());
            assertEquals(t, tasks.get(0));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testDeleteTask() {
        TaskList tasks = new TaskList(new ArrayList<>());
        try {
            Task t2 = new Todo("mop floor");
            tasks.add(new Todo("clean house"));
            tasks.add(t2);
            tasks.delete(0);
            assertEquals(1, tasks.getCount());
            assertEquals(t2, tasks.get(0));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getTask_invalidIndex_exceptionThrown() {
        TaskList tasks = new TaskList(new ArrayList<>());
        try {
            tasks.add(new Todo("clean house"));
            assertEquals(0, tasks.get(1));
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The item you are trying to access in the list does not exist :(", e.toString());
        }
    }
}
