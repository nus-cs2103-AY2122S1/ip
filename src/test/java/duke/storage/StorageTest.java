package duke.storage;

import duke.task.Deadline;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class StorageTest {

    @Test
    public void storageLoad_validFilePath_correctArrayList() {

        ArrayList<Task> CORRECT_ARRAYLIST_OF_TASKS = new ArrayList<Task>();

        CORRECT_ARRAYLIST_OF_TASKS.add(new ToDo("1"));
        CORRECT_ARRAYLIST_OF_TASKS.add((new ToDo("ok3")));
        CORRECT_ARRAYLIST_OF_TASKS.add((new ToDo("ok4")));
        CORRECT_ARRAYLIST_OF_TASKS.add(new Deadline("ok", LocalDate.of(2019, 12, 12)));

        Storage testStorage = new Storage("data/duke.txt");
        try {
            assertEquals(CORRECT_ARRAYLIST_OF_TASKS, testStorage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void storageLoad_invalidFilePath_fileNotFoundExceptionThrown() {
        Storage testStorage = new Storage("dduke.txt");
        String EXPECTED_MESSAGE = "dduke.txt (No such file or directory)";
        FileNotFoundException exception = assertThrows(FileNotFoundException.class, () -> {
            testStorage.load();
        });
        assertEquals(EXPECTED_MESSAGE, exception.getMessage());
    }
}
