package duke.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Todo;

class TaskListTest {
    @Test
    void delete() {
        TaskList tl = new TaskList();
        Todo task = new Todo("bake cake");
        tl.add(task);
        try {
            assertEquals(task.toString(), tl.delete(1));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void complete() {
        try {
            assertEquals("test", new TaskList().complete(1));
            fail();
        } catch (DukeException e) {
            assertEquals("duke.exception.InvalidIndexException: Sorry >.< but this task does not exist!",
                    e.toString());
        }
    }
}
