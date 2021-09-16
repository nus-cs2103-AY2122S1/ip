package duke.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Todo;

class TaskListTest {
    @Test
    void testDelete() {
        try {
            TaskList tl = new TaskList();
            Todo task = new Todo("bake cake");
            tl.addTask(task);
            assertEquals(task.toString(), tl.deleteTask(1));
        } catch (DukeException | IOException e) {
            fail();
        }
    }

    @Test
    void testComplete() {
        try {
            assertEquals("test", new TaskList().completeTask(1));
            fail();
        } catch (DukeException e) {
            assertEquals("duke.exception.InvalidIndexException: Sorry >.< but this task does not exist!",
                    e.toString());
        } catch (IOException e) {
            fail();
        }
    }
}
