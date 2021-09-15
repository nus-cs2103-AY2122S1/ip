package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ItemsTest {
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
