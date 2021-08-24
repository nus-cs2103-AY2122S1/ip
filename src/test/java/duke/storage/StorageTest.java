package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import duke.task.Task;


public class StorageTest {
    private static final String NON_EXISTENT_FILE_NAME = "ThisFileDoesNotExist.txt";

    @Test
    public void load_nonExistentFile_returnsEmptyList() {
        Storage storage = new Storage(NON_EXISTENT_FILE_NAME);
        List<Task> loadedTasks = storage.load();

        assertEquals(loadedTasks.size(), 0);
    }
}
