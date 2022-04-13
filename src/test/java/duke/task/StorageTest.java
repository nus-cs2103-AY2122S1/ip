package duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class StorageTest {
    private final String storagePathString = "data/tasks.txt";
    private final Path storagePath = Paths.get("data/tasks.txt");

    @Test
    public void init_createDataFile_success() {
        try {
            Files.deleteIfExists(storagePath);
            new Storage(storagePathString);

            assertTrue(Files.exists(storagePath));
        } catch (DukeException | java.io.IOException e) {
            fail();
        }
    }

    @Test
    public void save_success() {
        try {
            Files.deleteIfExists(storagePath);
            Storage storage = new Storage(storagePathString);

            ToDo todo = new ToDo("task");
            List<String> dataStrings = List.of(todo.toDataString("|"));

            assertDoesNotThrow(() -> storage.save(dataStrings));
        } catch (DukeException | java.io.IOException e) {
            fail();
        }
    }

    @Test
    public void load_success() {
        try {
            Files.deleteIfExists(storagePath);
            Storage storage = new Storage(storagePathString);

            ToDo todo = new ToDo("task");
            Files.write(storagePath, List.of(todo.toDataString("|")));

            ArrayList<Task> tasks = storage.load();
            assertEquals(1, tasks.size());
            assertEquals(todo.toString(), tasks.get(0).toString());

        } catch (DukeException | java.io.IOException e) {
            fail();
        }
    }
}
