package duke.util;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoListTest {
    // Referenced from http://www.mastertheboss.com/various-stuff/testing-java/how-to-verify-the-console-output-in-junit-tests/
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private ToDoList tdlist;
    private final String ls = System.lineSeparator();

    @BeforeEach
    public void setUpStreams() {
        tdlist = new ToDoList(new ArrayList<Task>(), null);
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
        tdlist.addToList(new ToDo("read book"));
        assertEquals("\t-------------------------------------------------------------------------" + ls +
                        "\tGot it. I've added this task:" + ls +
                        "\t\t" + "[T][ ] read book" + ls + "\tNow you have 1 tasks in the list." + ls +
                        "\t-------------------------------------------------------------------------" + ls,
                outContent.toString());
    }

    @Test
    public void addToListTest_eventDateTime_success() {
        tdlist.addToList(new Event("read book", "2021-08-09 1900"));
        assertEquals("\t-------------------------------------------------------------------------" + ls +
                        "\tGot it. I've added this task:" + ls +
                        "\t\t" + "[E][ ] read book (at: Aug 09 2021 7.00 PM)" + ls +
                        "\tNow you have 1 tasks in the list." + ls +
                        "\t-------------------------------------------------------------------------" + ls,
                outContent.toString());
    }

    @Test
    public void addToListTest_deadlineDateTime_success() {
        tdlist.addToList(new Deadline("read book", "2021-08-09 1900"));
        assertEquals("\t-------------------------------------------------------------------------" + ls +
                        "\tGot it. I've added this task:" + ls +
                        "\t\t" + "[D][ ] read book (by: Aug 09 2021 7.00 PM)" + ls +
                        "\tNow you have 1 tasks in the list." + ls +
                        "\t-------------------------------------------------------------------------" + ls,
                outContent.toString());
    }

    @Test
    public void markTaskAsDoneTest_success() {
        tdlist.addToList(new Event("read book", "2021-08-09 1900"));
        outContent.reset();
        try {
            tdlist.markTaskAsDone(1);
            assertEquals("\t-------------------------------------------------------------------------" + ls +
                            "\tGood job on completing this task!" + ls +
                            "\t\t" + "[E][X] read book (at: Aug 09 2021 7.00 PM)" + ls +
                            "\t-------------------------------------------------------------------------" + ls,
                    outContent.toString());
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
            assertEquals("InvalidIndexException: You only have 1 tasks!",
                    e.getMessage());
        }
    }

    @Test
    public void removeFromListTest_success() {
        tdlist.addToList(new Event("read book", "2021-08-09 1900"));
        outContent.reset();
        try {
            tdlist.removeFromList(1);
            assertEquals("\t-------------------------------------------------------------------------" + ls +
                            "\tNoted. I've removed this task:" + ls +
                            "\t\t" + "[E][ ] read book (at: Aug 09 2021 7.00 PM)" + ls +
                            "\tNow you have 0 tasks in the list." + ls +
                            "\t-------------------------------------------------------------------------" + ls,
                    outContent.toString());
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
            assertEquals("InvalidIndexException: You only have 1 tasks!",
                    e.getMessage());
        }
    }

    @Test
    public void removeFromListTest_DukeNegativeIndexException() {
        tdlist.addToList(new Event("read book", "2021-08-09 1900"));
        try {
            tdlist.removeFromList(-1);
        } catch (DukeException e) {
            assertEquals("DukeNegativeIndexException: You can't have negative tasks!",
                    e.getMessage());
        }
    }
}
