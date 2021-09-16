package duke;

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
        try {
            msg = item.addItem(new Todo("abc"));
            msg = item.markDone(0);
        } catch (DukeException e) {
            msg = "";
        }
        assertNotEquals(msg, "");
    }
}
