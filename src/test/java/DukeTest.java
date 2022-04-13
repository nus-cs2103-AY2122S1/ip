import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.utils.DukeException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeTest {

    /**
     * A single Todo is added to the task list.
     */
    @Test
    void todo_single() {
        try {
            TaskList taskList = new TaskList(false);
            Parser inputH = new Parser(taskList);
            inputH.query("todo test1");
            assertEquals(taskList.contains(new Todo("test1")), true);
        } catch (DukeException e) {
            if (e.equals(Storage.ERROR_DB)) fail("Failed to delete residual files.");
            fail("Single todo not added.");
        }
    }

    /**
     * A single Deadline is added to the task list.
     */
    @Test
    void deadline_single() {
        try {
            TaskList taskList = new TaskList(false);
            Parser inputH = new Parser(taskList);
            inputH.query("deadline dl one /by 2021-08-24 18:00 / yeah");
            Deadline d = new Deadline("dl one | 2021-08-24 18:00 | yeah", false);
            assertEquals(taskList.contains(d), true);
        } catch (DukeException e) {
            if (e.equals(Storage.ERROR_DB)) fail("Failed to delete residual files.");
            fail("Single deadline not added.");
        }
    }

    /**
     * The task list is cleared.
     */
    @Test
    void clear() {
        try {
            TaskList taskList = new TaskList(false);
            Parser inputH = new Parser(taskList);
            inputH.query("todo test1");
            assertEquals(true, taskList.contains(new Todo("test1")));
            inputH.query("clear");
            assertEquals(0, taskList.size());
        } catch (DukeException e) {
            if (e.equals(Storage.ERROR_DB)) fail("Failed to delete residual files.");
            fail("Failed to clear task list");
        }
    }
    
    @Test
    void find() {
        try {
            TaskList taskListH = new TaskList(false);
            Parser inputH = new Parser(taskListH);
            inputH.query("todo test1");
            inputH.query("deadline Report /by 2021-11-21 15:20 / finalize bibliography");
            inputH.query("deadline Another report /by 2021-06-09 04:20 / test2");
            inputH.query("event dummy /at 08:00 ~ 18:00 / stock");
            assertEquals(4, taskListH.size());
            String msgH = inputH.query("find test").msg();
            msgH = msgH.replace(" matching ", " ");

            TaskList taskListI = new TaskList(false);
            Parser inputI = new Parser(taskListI);
            inputI.query("todo test1");
            inputI.query("deadline Another report /by 2021-06-09 04:20 / test2");
            assertEquals(2, taskListI.size());
            String msgI = inputI.query("list").msg();
            assertEquals(msgH, msgI);
            
        } catch (DukeException e) {
            if (e.equals(Storage.ERROR_DB)) fail("Failed to delete residual files.");
            fail("Failed to clear tasklist");
        }
    }
}
