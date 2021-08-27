package duke;

import duke.task.Task;
import duke.util.Storage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void saveTasks_invalidFile_success() {
        try {
            String invalidFilePath = "thisfile/doesnot.exist";
            Storage o = new Storage(invalidFilePath);
            o.saveTasks(new ArrayList<>());

            assertTrue(o.toString().equals("file path is thisfile/doesnot.exist"));
        } catch (DukeException e) {
            fail(); // should not have exception
        }
    }

    @Test
    public void loadTasks_invalidFile_success() {
        try {
            String invalidFilePath = "thisfile/doesnot.exist";
            Storage o = new Storage(invalidFilePath);
            ArrayList<Task> tasks = o.loadTasks();

            assertTrue(o.toString().equals("file path is thisfile/doesnot.exist"));
            assertTrue(tasks.equals(new ArrayList<>()));
        } catch (DukeException e) {
            fail(); // should not have exception
        }
    }
}
