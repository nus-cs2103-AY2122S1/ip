package duke;

import duke.exception.DukeException;
import duke.storage.Storage;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test class for {@code Storage}.
 */
public class StorageTest {
    @Test

    /**
     * Tests the {@code load} method when the directory of the save
     * file does not exist.
     */
    public void load_folderDoesNotExist() {
        try {
            Storage storageOne = new Storage("storageTestFolder/data.txt");
            storageOne.loadData();
        } catch (DukeException | IOException e) {
            assertEquals("Error loading file!", e.getMessage());
            File testFolder = new File("storageTestOne");
            assertTrue(testFolder.exists() && testFolder.isDirectory());
        }
    }

    /**
     * Tests the {@code load} method when the save file itself
     * does not exist.
     */
    @Test
    public void load_saveFileDoesNotExist_exceptionThrown() {
        try {
            File testFolder = new File("storageTestFolderTwo");
            if (!testFolder.exists()) {
                boolean isDirectoryCreated = testFolder.mkdir();
                if (!isDirectoryCreated) {
                    throw new DukeException("Folder failed to be created!");
                }
            }
            Storage storageTwo = new Storage("storageTestFolderTwo/data.txt");
            storageTwo.loadData();
        } catch (DukeException | IOException e) {
            assertEquals("Save file failed!", e.getMessage());
        }
    }
}
