package duke.storage;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void load_invalidFolder() {
        Storage storage = new Storage("./invalid/duke.txt");
        try {
            storage.load();
        } catch (DukeException e) {
            assertEquals("OOPS!!! \"invalid\" folder does not exist.",
                    e.getMessage());
        }
    }

    @Test
    void load_invalidFile() {
        Storage storage = new Storage("./data/notduke.txt");
        try {
            storage.load();
        } catch (DukeException e) {
            assertEquals("OOPS!!! File not found.",
                    e.getMessage());
        }
    }

    @Test
    void load_validFilePath() {
        Storage storage = new Storage("./data/duke.txt");
        try {
            storage.load();
        } catch (DukeException e) {
            fail();
        }
    }
}