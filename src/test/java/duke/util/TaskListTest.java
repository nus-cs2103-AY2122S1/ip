package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TaskListTest {
    private Storage storage = new Storage();
    private TaskList tasks = new TaskList(null, storage);

    @Test
    public void testAddToList_toDo_success() {
        assertEquals(
            "I've added this task but it's not like I did it for you or anything!\n"
                + "  [T][ ] return book\n"
                + "Now you have 1 task in the list. Do your best doing them okay?",
            tasks.addToList("return book", "ToDo")
        );
    }

    @Test
    public void testAddToList_deadline_success() {
        assertEquals(
            "I've added this task but it's not like I did it for you or anything!\n"
                + "  [D][ ] return book (by: Aug 23 2021 00:00)\n"
                + "Now you have 1 task in the list. Do your best doing them okay?",
            tasks.addToList("return book /by 23/08/2021 0000", "Deadline")
        );
    }

    @Test
    public void testAddToList_deadline_exceptionThrown() {
        try {
            assertEquals(
                "I've added this task but it's not like I did it for you or anything!\n"
                    + "  [D][ ] return book (by: Aug 23 2021 00:00)\n"
                    + "Now you have 1 task in the list. Do your best doing them okay?",
                tasks.addToList("return book /by 23/08/2021", "Deadline")
            );
            fail();
        } catch (DukeException e) {
            assertEquals("BAKA! You're missing the Date or Time argument to add a Deadline!", e.getMessage());
        }

    }

    @Test
    public void testAddToList_event_success() {
        assertEquals(
            "I've added this task but it's not like I did it for you or anything!\n"
                + "  [E][ ] return book (by: Aug 23 2021 00:00-01:00)\n"
                + "Now you have 1 task in the list. Do your best doing them okay?",
            tasks.addToList("return book /at 23/08/2021 0000 0100", "Event")
        );
    }

    @Test
    public void testAddToList_event_exceptionThrown() {
        try {
            assertEquals(
                "I've added this task but it's not like I did it for you or anything!\n"
                    + "  [D][ ] return book (by: Aug 23 2021 00:00)\n"
                    + "Now you have 1 task in the list. Do your best doing them okay?",
                tasks.addToList("return book /by 23/08/2021 0000 0100", "Event")
            );
            fail();
        } catch (DukeException e) {
            assertEquals("BAKA! You're missing the '/at' argument to add a Event!", e.getMessage());
        }

    }

    @Test
    public void testMarkTaskAsDone() {
        tasks.addToList("return book", "ToDo");
        assertEquals(
            "You completed a task! Maybe you aren't so incompetent after all.\n  [T][X] return book",
            tasks.markTaskAsDone(1)
        );
    }

    @Test
    public void testDeleteTask() {
        tasks.addToList("return book", "ToDo");
        assertEquals(
            "I've deleted this task so show me some gratitude!\n"
                + "  [T][ ] return book\n"
                + "Now you have 0 tasks in the list. Do your best doing them okay?",
            tasks.deleteTask(1)
        );
    }

    @Test
    public void testPrintList_noFiltering() {
        tasks.addToList("read book", "ToDo");
        tasks.addToList("return book /by 23/08/2021 0000", "Deadline");
        tasks.addToList("project meeting /at 2021-08-23 1400 1600", "Event");

        assertEquals(
            "1:[T][ ] read book\n2:[D][ ] return book (by: Aug 23 2021 00:00)\n"
                + "3:[E][ ] project meeting (by: Aug 23 2021 14:00-16:00)\n",
            tasks.listTasks("all", null)
        );
    }

    @Test
    public void testPrintList_withDateFiltering() {
        tasks.addToList("read book", "ToDo");
        tasks.addToList("return book /by 23/08/2021 0000", "Deadline");
        tasks.addToList("project meeting /at 2021-08-23 1400 1600", "Event");

        assertEquals(
            "1:[D][ ] return book (by: Aug 23 2021 00:00)\n"
                    + "2:[E][ ] project meeting (by: Aug 23 2021 14:00-16:00)\n",
            tasks.listTasks("date", "2021-08-23")
        );
    }

    @Test
    public void testPrintList_withKeywordFiltering() {
        tasks.addToList("read book", "ToDo");
        tasks.addToList("return book /by 23/08/2021 0000", "Deadline");
        tasks.addToList("project meeting /at 2021-08-23 1400 1600", "Event");

        assertEquals(
            "1:[T][ ] read book\n"
                + "2:[D][ ] return book (by: Aug 23 2021 00:00)\n",
            tasks.listTasks("keyword", "re")
        );
    }
}
