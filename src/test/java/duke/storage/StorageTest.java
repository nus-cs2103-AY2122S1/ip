package duke.storage;

import duke.exceptions.DukeException;
import duke.task.TaskList;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    public void load_invalidFilepath_exceptionThrown() {
        final String invalidFilePath = "data";

        assertThrows(DukeException.class, () -> {
            new Storage(invalidFilePath).load();
        });
    }

    @Test
    public void writeTasksToFile_invalidFilepath_exceptionThrown() {
        final String invalidFilePath = "data";

        assertThrows(DukeException.class, () -> {
            new Storage(invalidFilePath).writeTasksToFile(new TaskList(new ArrayList<>()));
        });
    }

}
