package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class ToDoListTest {
    // Referenced from
    // http://www.mastertheboss.com/various-stuff/testing-java/how-to-verify-the-console-output-in-junit-tests/
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final String ls = System.lineSeparator();
    private TaskList tdlist;

    @BeforeEach
    public void setUpStreams() {
        tdlist = new TaskList(new ArrayList<Task>(), null);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void addToListTest_toDo_success() {
        assertEquals(
                "Got it. I've added this task:"
                        + ls
                        + "  [T][ ] read book"
                        + ls
                        + "Now you have 1 tasks in the list.",
                tdlist.addToList(new ToDo("read book")));
    }

    @Test
    public void addToListTest_eventDateTime_success() {
        assertEquals("Got it. I've added this task:"
                        + ls
                        + "  [E][ ] read book (at: Aug 09 2021 7.00 PM)"
                        + ls
                        + "Now you have 1 tasks in the list.",
                tdlist.addToList(new Event("read book", "2021-08-09 1900")));
    }

    @Test
    public void addToListTest_deadlineDateTime_success() {
        assertEquals("Got it. I've added this task:"
                        + ls
                        + "  [D][ ] read book (by: Aug 09 2021 7.00 PM)"
                        + ls
                        + "Now you have 1 tasks in the list.",
                tdlist.addToList(new Deadline("read book", "2021-08-09 1900")));
    }

    @Test
    public void markTaskAsDoneTest_success() {
        tdlist.addToList(new Event("read book", "2021-08-09 1900"));
        outContent.reset();
        try {
            assertEquals("Good job on completing this task!"
                            + "\n"
                            + "  [E][X] read book (at: Aug 09 2021 7.00 PM)",
                    tdlist.markTaskAsDone(1));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void markTaskAsDoneTest_exception() {
        tdlist.addToList(new Event("read book", "2021-08-09 1900"));
        try {
            tdlist.markTaskAsDone(1000);
            fail();
        } catch (DukeException e) {
            assertEquals("InvalidIndexException: You only have 1 tasks!", e.getMessage());
        }
    }

    @Test
    public void removeFromListTest_success() {
        tdlist.addToList(new Event("read book", "2021-08-09 1900"));
        outContent.reset();
        try {
            assertEquals(
                    "Noted. I've removed this task:\n"
                            + "  [E][ ] read book (at: Aug 09 2021 7.00 PM)"
                            + ls
                            + "Now you have 0 tasks in the list.",
                    tdlist.removeFromList(1));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void removeFromListTest_invalidIndexException() {
        tdlist.addToList(new Event("read book", "2021-08-09 1900"));
        try {
            tdlist.removeFromList(100);
        } catch (DukeException e) {
            assertEquals("InvalidIndexException: You only have 1 tasks!", e.getMessage());
        }
    }

    @Test
    public void removeFromListTest_dukeNegativeIndexException() {
        tdlist.addToList(new Event("read book", "2021-08-09 1900"));
        try {
            tdlist.removeFromList(-1);
        } catch (DukeException e) {
            assertEquals(
                    "DukeNegativeIndexException: You can't have negative tasks!", e.getMessage());
        }
    }
}
