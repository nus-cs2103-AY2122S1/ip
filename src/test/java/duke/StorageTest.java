package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Tests the storage capability of duke.
 */
public class StorageTest {
    /**
     * Tests storage.
     * @throws FileNotFoundException if no file is found.
     * @throws DukeException if storage mechanism is unsuccessful.
     */
    @Test
    public void loadFile() throws FileNotFoundException, DukeException {
        Storage s = Storage.createStorage("data/test.txt");
        PrintWriter writer = new PrintWriter("data/test.txt");
        writer.close();
        assertEquals(s.load(new TaskList()).size(), 0);
    }
}
