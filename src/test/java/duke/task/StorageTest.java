package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    private final String STORAGE_PATH_STRING = "data/tasks.txt";
    private final Path STORAGE_PATH = Paths.get("data/tasks.txt");

    @Test
    public void init_createDataFile_success() {
        try {
            Files.deleteIfExists(STORAGE_PATH);
            new Storage(STORAGE_PATH_STRING);

            assertTrue(Files.exists(STORAGE_PATH));
        } catch (DukeException | java.io.IOException e) {
            fail();
        }
    }

    @Test
    public void save_success() {
        try {
            Files.deleteIfExists(STORAGE_PATH);
            Storage storage = new Storage(STORAGE_PATH_STRING);

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
            Files.deleteIfExists(STORAGE_PATH);
            Storage storage = new Storage(STORAGE_PATH_STRING);

            ToDo todo = new ToDo("task");
            Files.write(STORAGE_PATH, List.of(todo.toDataString("|")));

            ArrayList<Task> tasks = storage.load();
            assertEquals(1, tasks.size());
            assertEquals(todo.toString(), tasks.get(0).toString());

        } catch (DukeException | java.io.IOException e) {
            fail();
        }
    }
}