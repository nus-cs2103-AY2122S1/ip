import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.task.Task;

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
