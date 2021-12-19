package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import duke.task.Todo;
public class TaskTest {
    @Test
    public void addTaskTest() {
        TaskList item = new TaskList();
        String msg;
        try {
            msg = item.addItem(new Todo("abc"));
        } catch (DukeException e) {
            msg = "";
        }
        assertNotEquals(msg, "");
    }

    @Test
    public void markDoneTest() {
        TaskList item = new TaskList();
        String msg;
        String output = "Nice! I have marked this task as done: \n" + "T | 1 | abc";
        try {
            Todo t = new Todo("abc");
            msg = item.addItem(t);
            msg = item.markDone(0);
        } catch (DukeException e) {
            msg = "";
        }
        assertEquals("", msg);
    }
}
