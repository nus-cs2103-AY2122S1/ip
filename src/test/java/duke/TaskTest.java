package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.task.Todo;

public class TaskTest {
    @Test
    public void addItemTest() throws DukeException, IOException {
        TaskList item = new TaskList();
        String msg;
        msg = item.addItem(new Todo("abc"));
        assertEquals("Got it, I've added this task:\n"
                + "T | 0 | abc\n"
                + "Now you have 1 task in the list.", msg);
    }

    @Test
    public void doneTaskTest() {
        TaskList item = new TaskList();
        String msg;
        try {
            msg = item.addItem(new Todo("abc"));
            msg = item.markDone(1);
        } catch (DukeException | IOException e) {
            msg = "";
        }
        assertEquals("Nice! I've marked this task as done: \n"
                + "T | 1 | abc", msg);
    }

    @Test
    public void listTaskTest() {
        TaskList item = new TaskList();
        String msg;
        try {
            msg = item.addItem(new Todo("abc"));
            msg = item.printList();
        } catch (DukeException | IOException e) {
            msg = "";
        }
        assertEquals("These are your tasks: \n"
                + " 1. T | 0 | abc", msg);
    }
}
