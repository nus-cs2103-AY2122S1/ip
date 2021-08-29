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
import duke.task.Todo;

public class TaskHandlerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final String SEPARATOR = "\t-------------------------------------------------------";

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testAddTask() {
        TaskHandler taskHandler = new TaskHandler(new ArrayList<>());
        taskHandler.addTask(new Todo("shopping"));
        String expected = String.format("%s\n\t%s\n%s\n", SEPARATOR, "Voila! ^_^ I've added this task:\n\t"
                + "  [T][] shopping"
                + "\n\tYou currently have 1 task(s) in the list.", SEPARATOR);
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    void testMarkTaskAsDone() {
        try {
            TaskHandler taskHandler = new TaskHandler(new ArrayList<>());
            taskHandler.addTask(new Todo("shopping"));
            outputStreamCaptor.reset();
            taskHandler.markTaskAsDone(1);
            String expected = String.format(
                    "%s\n\t%s\n%s\n", SEPARATOR, "Good Job! :D I've marked this task as done:\n\t"
                            + "  [T][X] shopping\n\tYou currently have 0 undone task(s) in the list.", SEPARATOR);
            assertEquals(expected, outputStreamCaptor.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void testDeleteTask() {
        try {
            TaskHandler taskHandler = new TaskHandler(new ArrayList<>());
            taskHandler.addTask(new Todo("shopping"));
            outputStreamCaptor.reset();
            taskHandler.deleteTask(1);
            String expected = String.format(
                    "%s\n\t%s\n%s\n", SEPARATOR, "Voila! ^_^ I've deleted this task:\n\t  "
                            + "[T][] shopping\n\tYou currently have 0 task(s) in the list.", SEPARATOR);
            assertEquals(expected, outputStreamCaptor.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void testPrintTasks() {
        TaskHandler taskHandler = new TaskHandler(new ArrayList<>());
        taskHandler.addTask(new Todo("shopping"));
        taskHandler.addTask(new Deadline("internship application", "26 Dec 2021"));
        outputStreamCaptor.reset();
        taskHandler.printTasks();
        String expected = "\t-------------------------------------------------------\n\t"
                + "Here are the task(s) in your list! n_n\n\t\t"
                + "1. [T][] shopping\n\t\t"
                + "2. [D][] internship application (by: 26 Dec 2021)\n\t\n"
                + "\t-------------------------------------------------------\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }
}
