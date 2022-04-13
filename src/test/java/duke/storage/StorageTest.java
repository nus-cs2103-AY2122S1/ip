package duke.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class StorageTest {
    @Test
    public void save_validPath_success() {
        Storage storage = new Storage("data/tasks.txt");
        try {
            assertTrue(storage.save(new TaskList()));
        } catch (DukeException e) {
            fail();
        }
    }
}
