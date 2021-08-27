package duke.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TaskListTest {
    private Storage storage = new Storage();
    private TaskList tasks = new TaskList(null, storage);
    //@@author dfa
    //Reused from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    // with minor modifications
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    //@@author

    @Test
    public void testAddToList_toDo_success() {
        assertArrayEquals(new String[] {
            "I've added this task but it's not like I did it for you or anything!",
            "  [T][ ] return book",
            "Now you have 1 task in the list. Do your best doing them okay?"
        }, tasks.addToList("return book", "ToDo"));
    }

    @Test
    public void testAddToList_deadline_success() {
        assertArrayEquals(new String[] {
            "I've added this task but it's not like I did it for you or anything!",
            "  [D][ ] return book (by: Aug 23 2021 00:00)",
            "Now you have 1 task in the list. Do your best doing them okay?"
        }, tasks.addToList("return book /by 23/08/2021 0000", "Deadline"));
    }

    @Test
    public void testAddToList_deadline_exceptionThrown() {
        try {
            assertArrayEquals(new String[] {
                "I've added this task but it's not like I did it for you or anything!",
                "  [D][ ] return book (by: Aug 23 2021 00:00)",
                "Now you have 1 task in the list. Do your best doing them okay?"
            }, tasks.addToList("return book /by 23/08/2021", "Deadline"));
            fail();
        } catch (DukeException e) {
            assertEquals("BAKA! You're missing the Date or Time argument to add a Deadline!", e.getMessage());
        }

    }

    @Test
    public void testAddToList_event_success() {
        assertArrayEquals(new String[] {
            "I've added this task but it's not like I did it for you or anything!",
            "  [E][ ] return book (by: Aug 23 2021 00:00-01:00)",
            "Now you have 1 task in the list. Do your best doing them okay?"
        }, tasks.addToList("return book /at 23/08/2021 0000 0100", "Event"));
    }

    @Test
    public void testAddToList_event_exceptionThrown() {
        try {
            assertArrayEquals(new String[] {
                "I've added this task but it's not like I did it for you or anything!",
                "  [D][ ] return book (by: Aug 23 2021 00:00)",
                "Now you have 1 task in the list. Do your best doing them okay?"
            }, tasks.addToList("return book /by 23/08/2021 0000 0100", "Event"));
            fail();
        } catch (DukeException e) {
            assertEquals("BAKA! You're missing the '/at' argument to add a Event!", e.getMessage());
        }

    }

    @Test
    public void testMarkTaskAsDone() {
        tasks.addToList("return book", "ToDo");
        assertArrayEquals(new String[] {
            "You completed a task! Maybe you aren't so incompetent after all.",
            "  [T][X] return book"
        }, tasks.markTaskAsDone(1));
    }

    @Test
    public void testDeleteTask() {
        tasks.addToList("return book", "ToDo");
        assertArrayEquals(new String[] {
            "I've deleted this task so show me some gratitude!",
            "  [T][ ] return book",
            "Now you have 0 tasks in the list. Do your best doing them okay?"
        }, tasks.deleteTask(1));
    }

    @Test
    public void testPrintList_noFiltering() {
        tasks.addToList("read book", "ToDo");
        tasks.addToList("return book /by 23/08/2021 0000", "Deadline");
        tasks.addToList("project meeting /at 2021-08-23 1400 1600", "Event");
        tasks.printList("all", null);

        assertEquals(
            "     1:[T][ ] read book\n     2:[D][ ] return book (by: Aug 23 2021 00:00)\n"
                + "     3:[E][ ] project meeting (by: Aug 23 2021 14:00-16:00)\n",
            outContent.toString());
    }

    @Test
    public void testPrintList_withDateFiltering() {
        tasks.addToList("read book", "ToDo");
        tasks.addToList("return book /by 23/08/2021 0000", "Deadline");
        tasks.addToList("project meeting /at 2021-08-23 1400 1600", "Event");
        tasks.printList("date", "2021-08-23");

        assertEquals(
            "     1:[D][ ] return book (by: Aug 23 2021 00:00)\n"
                    + "     2:[E][ ] project meeting (by: Aug 23 2021 14:00-16:00)\n",
            outContent.toString());
    }

    @Test
    public void testPrintList_withKeywordFiltering() {
        tasks.addToList("read book", "ToDo");
        tasks.addToList("return book /by 23/08/2021 0000", "Deadline");
        tasks.addToList("project meeting /at 2021-08-23 1400 1600", "Event");
        tasks.printList("keyword", "re");

        assertEquals(
            "     1:[T][ ] read book\n"
                + "     2:[D][ ] return book (by: Aug 23 2021 00:00)\n",
            outContent.toString());
    }
}
