package calico;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import calico.task.Task;
import calico.util.Storage;

/**
 * Unit test for Storage class.
 */
public class StorageTest {
    @Test
    public void saveTasks_invalidFile_success() {
        try {
            String invalidFilePath = "thisfile/doesnot.exist";
            Storage o = new Storage(invalidFilePath);
            o.saveTasks(new ArrayList<>());

            assertTrue(o.toString().equals("file path is thisfile/doesnot.exist"));
        } catch (CalicoException e) {
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
        } catch (CalicoException e) {
            fail(); // should not have exception
        }
    }
}
