package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ItemsTest {
    @Test
    public void addTaskTest() {
        Items item = new Items();
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
        Items item = new Items();
        String msg;
        try {
            msg = item.addItem(new Todo("abc"));
            msg = item.markDone(1);
        } catch (DukeException e) {
            msg = "";
        }
        assertNotEquals(msg, "");
    }
}
