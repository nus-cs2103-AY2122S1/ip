package duke.task;

import duke.Duke;
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
    private String filePathString = "data/tasks.txt";
    private Path filePath = Paths.get("data/tasks.txt");
    
    @Test
    public void init_createDataFile_success() {
        try {
            Files.deleteIfExists(filePath);
            new Storage(filePathString);
            
            assertTrue(Files.exists(filePath));
        } catch (DukeException | java.io.IOException e){
            fail();
        }
    }
    
    @Test
    public void save_success() {
        try {
            Files.deleteIfExists(filePath);
            Storage storage = new Storage(filePathString);
            
            ToDo todo = new ToDo("task");
            List<String> dataStrings = List.of(todo.toDataString("|"));
            
            assertDoesNotThrow(() -> storage.save(dataStrings));
        } catch (DukeException | java.io.IOException e){
            fail();
        }
    }

    @Test
    public void load_success() {
        try {
            Files.deleteIfExists(filePath);
            Storage storage = new Storage(filePathString);

            ToDo todo = new ToDo("task");
            Files.write(filePath, List.of(todo.toDataString("|")));

            ArrayList<Task> tasks = storage.load();
            assertEquals(1, tasks.size());
            assertEquals(todo.toString(), tasks.get(0).toString());

        } catch (DukeException | java.io.IOException e){
            fail();
        }
    }
}
