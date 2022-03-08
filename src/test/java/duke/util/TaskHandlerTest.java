package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Todo;

public class TaskHandlerTest {
    @Test
    void testAddTask() {
        TaskHandler taskHandler = new TaskHandler(new ArrayList<>());
        String expected = String.format("Voila! ^_^ I've added this task:\n"
                + "- %s\nYou currently have %d undone task(s) in the list.", "[T][ ] shopping", 1);
        assertEquals(expected, taskHandler.addTask(new Todo("shopping")));
    }

    @Test
    void testMarkTaskAsDone() {
        try {
            TaskHandler taskHandler = new TaskHandler(new ArrayList<>());
            taskHandler.addTask(new Todo("shopping"));
            String expected = "Good Job! :D I've marked this task as done:\n"
                    + "- [T][✓] shopping\nYou currently have 0 undone task(s) in the list.";
            assertEquals(expected, taskHandler.markTaskAsDone(1));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void testDeleteTask() {
        try {
            TaskHandler taskHandler = new TaskHandler(new ArrayList<>());
            taskHandler.addTask(new Todo("shopping"));
            String expected = "Voila! ^_^ I've deleted this task:\n"
                    + "- [T][ ] shopping\nYou currently have 0 undone task(s) in the list.";
            assertEquals(expected, taskHandler.deleteTask(1));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void testPrintTasks() {
        TaskHandler taskHandler = new TaskHandler(new ArrayList<>());
        taskHandler.addTask(new Todo("shopping"));
        taskHandler.addTask(new Deadline("internship application", "26 Dec 2021"));
        String expected = "Here are the task(s) in your list! ^_^\n\n"
                + "1. [T][ ] shopping\n"
                + "2. [D][ ] internship application\n    (by: 26 Dec 2021)";
        assertEquals(expected, taskHandler.generateList());
    }
}
