package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void addItemTest() {
        Items item = new Items();
        String msg;
        msg = item.addItem(new Todo("abc"));
        assertEquals("Got it, I've added this task:\n" +
                "T | 0 | abc\n" +
                "Now you have 1 task in the list.", msg);
    }

    @Test
    public void doneTaskTest() {
        Items item = new Items();
        String msg;
        try {
            msg = item.addItem(new Todo("abc"));
            msg = item.markDone(1);
        } catch (DukeException e) {
            msg = "";
        }
        assertEquals("Nice! I've marked this task as done: \n" +
                "T | 1 | abc", msg);
    }

    @Test
    public void listTaskTest() {
        Items item = new Items();
        String msg;
        try {
            msg = item.addItem(new Todo("abc"));
            msg = item.printList();
        } catch (DukeException e) {
            msg = "";
        }
        assertEquals("These are your tasks: \n" +
                " 1. T | 0 | abc", msg);
    }
}