package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.ToDoTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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