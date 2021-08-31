package cs2103.duke.test;

import cs2103.duke.DukeException;
import cs2103.duke.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testAddTodo() throws DukeException {
        TaskList tl = new TaskList();
        assertEquals("____________________________________________________________\n"
                        + "New todo task added:\n"
                        + "[T][ ]test1\n"
                        + "You now have 1 item(s) in your task list.\n"
                        + "____________________________________________________________",
                tl.addTask("todo", "test1", ""));
    }

    @Test
    public void testAddDeadline() throws DukeException {
        TaskList tl = new TaskList();
        assertEquals("____________________________________________________________\n"
                        + "New deadline task added:\n"
                        + "[D][ ]ms3 (by: 2021 07 20)\n"
                        + "You now have 1 item(s) in your task list.\n"
                        + "____________________________________________________________",
                tl.addTask("deadline", "ms3", "2021-07-20"));
    }

    @Test
    public void testFindTask() throws DukeException {
        TaskList tl = new TaskList();
        tl.addTask("todo", "test1", "");
        tl.addTask("deadline", "ms3", "2021-07-20");
        assertEquals("____________________________________________________________\n"
                + "Here are the matching tasks in your list:\n"
                + "1.[T][ ]test1\n"
                + "____________________________________________________________",
                tl.findTasks("test"));
    }
}
