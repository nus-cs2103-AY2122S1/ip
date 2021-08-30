package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import duke.task.Task;
import duke.task.ToDo;

public class StorageTest {
    @TempDir
    Path path;

    @Test
    public void readWrite() {
        Path file = path.resolve("db.txt");
        Storage storage = new Storage(file);
        List<Task> tasks = Arrays.asList(ToDo.of(false, "TEST TASK HERE"));

        try {
            storage.save(tasks);
            List<Task> readTasks = storage.load();
            assertEquals(tasks.toString(), readTasks.toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}
