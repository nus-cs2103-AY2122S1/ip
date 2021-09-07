package nyx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import nyx.task.*;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testStringConversion_empty() {
        TaskList taskList = new TaskList();
        assertEquals("You do not have any tasks currently", taskList.toString());
    }

    @Test
    public void testStringConversion_filled() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("exercise"));
        tasks.add(new Deadline("return book", "2021-04-12 15:35"));
        tasks.add(new Event("party", "2021-04-12 15:35"));

        TaskList taskList = new TaskList(tasks);

        assertEquals("Here are the tasks in your list:\n"
                + "1. [T][ ] exercise\n"
                + "2. [D][ ] return book (by: Mon 12 Apr 2021, 3:35PM)\n"
                + "3. [E][ ] party (at: Mon 12 Apr 2021, 3:35PM)\n", taskList.toString());
    }

    @Test
    public void markDone_empty() {
        TaskList taskList = new TaskList();
        assertFalse(taskList.markDone(4));
    }

    @Test
    public void markDone_filled() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("exercise"));
        tasks.add(new Deadline("return book", "2021-04-12 15:35"));
        tasks.add(new Event("party", "2021-04-12 15:35"));

        TaskList taskList = new TaskList(tasks);

        assertTrue(taskList.markDone(1));
    }
}
