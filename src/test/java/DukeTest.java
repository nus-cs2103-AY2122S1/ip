import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Todo;
import duke.utils.DukeException;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeTest {
    
    @Test
    void singleTodo() {
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
    
    @Test
    void singleDeadline() {
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
    
    @Test
    void clear() {
        try {
            TaskList taskList = new TaskList(false);
            Parser inputH = new Parser(taskList);
            inputH.query("todo test1");
            assertEquals(true, taskList.contains(new Todo("test1")) );
            inputH.query("clear");
            assertEquals(0, taskList.size());
        } catch (DukeException e) {
            if (e.equals(Storage.ERROR_DB)) fail("Failed to delete residual files.");
            fail("Failed to clear tasklist");
        }
    }
}
