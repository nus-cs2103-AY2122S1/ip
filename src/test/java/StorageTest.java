import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import duke.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Test Storage for correct saving and loading of files.
 */
public class StorageTest {
    /**
     * The Temp path to test for saving and creating files from.
     */
    @TempDir
    Path tempPath;

    /**
     * Test loading and saving to a file path.
     */
    @Test
    public void loadAndSave() {
        Path file = tempPath.resolve("duke.txt");
        TaskList saveList = new TaskList();
        Todo test = new Todo("life is tough");
        test.markDone();
        saveList.add(test);
        saveList.add(new Deadline("monke", "2021-08-23", "18:00"));
        saveList.add(new Event("monke", "2021-08-23", "18:00"));
        Storage saveStorage = new Storage(saveList, file.toString());

        TaskList loadList = new TaskList();
        Storage loadStorage = new Storage(loadList, file.toString());

        String expectedResult = "Here are the tasks in your list:\n"
                + "1.[T][X] life is tough\n"
                + "2.[D][ ] monke (by: Aug 23 2021 06:00PM)\n"
                + "3.[E][ ] monke (at: Aug 23 2021 06:00PM)";

        try {
            saveStorage.save();
            loadStorage.load();
            assertEquals(expectedResult, loadList.toString());
        } catch (Exception e) {
            fail();
        }
    }
}
