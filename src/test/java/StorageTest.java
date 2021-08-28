import duke.Storage;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {

    @Test
    public void readWriteTest() {
        Storage storage = new Storage("data/test.txt");

        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task("task a"));
        tasks.add(new Task("task b"));
        tasks.add(new Task("task c"));

        storage.writeToDisk(tasks);

        assertEquals(tasks, storage.readFromDisk());
    }
}