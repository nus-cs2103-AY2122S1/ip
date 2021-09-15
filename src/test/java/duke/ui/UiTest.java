package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.TaskList;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class UiTest {
    ArrayList<Task> tasks = new ArrayList<>(
            Arrays.asList(
                    new ToDo("sleep"),
                    new Event("dinner", "7PM")
            ));
    TaskList list = new TaskList(tasks);

    @Test
    public void showTaskCountTest() {
        assertEquals("Now you have 2 tasks in your list.\n\n", Ui.showTaskCount(list));
    }

    @Test
    public void showFindResultsTest() {
        String str = "Here are the matching tasks in your list: \n"
                + "1. [T][ ] sleep\n"
                + "2. [E][ ] dinner (at: 7PM)\n";
        assertEquals(str, Ui.showFindResults(tasks));
    }

    @Test
    public void showAddedTaskTest() {
        String str = "added: [E][ ] dinner (at: 7PM)\n";
        assertEquals(str, Ui.showAddedTask(list));
    }

    @Test
    public void showDeletedTaskTest() {
        String str = "Got it! I've removed this task: \n"
                + "[E][ ] dinner (at: 7PM)\n";
        assertEquals(str, Ui.showDeletedTask(tasks.get(1)));
    }

    @Test
    public void showErrorMessageTest() {
        DukeException e = new DukeException("Sorry I don't understand what that means");
        String str = "Sorry I don't understand what that means";
        assertEquals(str, Ui.showErrorMessage(e));
    }
}
