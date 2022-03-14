package duke.storage;

import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @TempDir
    Path tempDirectory;

    @Test
    public void writeToFile_string_success() throws IOException {
        Path path = tempDirectory.resolve("tasks.txt");
        Storage storage = new Storage(path.toString());
        try {
            storage.saveTask(new Todo("test sentence", false));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String actual = (String) Files.readAllLines(path).toArray()[0];
        assertEquals("[T][ ] test sentence", actual);
    }

    @Test
    public void overWriteFile_newString_success() throws IOException {
        Path path = tempDirectory.resolve("tasks.txt");
        Storage storage = new Storage(path.toString());
        try {
            storage.saveTask(new Todo("test sentence", false));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<TaskList> taskListArrayList = new ArrayList<>();
        taskListArrayList.add(new Todo("new test sentence", false));
        storage.overwriteList(taskListArrayList);

        String actual = (String) Files.readAllLines(path).toArray()[0];
        assertEquals("[T][ ] new test sentence", actual);
    }
}
