import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.common.Duke;
import duke.common.Storage;
import duke.common.TaskList;

public class StorageTest {
    @Test
    public void createTaskDirTest() {
        final String testFileName = "createDirTest/createDirTest.txt";

        // Test creation of directory
        Storage testStorage = new Storage(testFileName);
        // assert empty directory is created
        assertTrue(Files.isDirectory(Paths.get(testFileName).getParent().getFileName()));
        try {
            assertTrue(isEmpty(Paths.get(testFileName).getParent().getFileName()));
        } catch (IOException e) {
            Assertions.fail("IOException when checking if teststorage is empty");
        }

        // Test initialising from non-existent file
        TaskList testTaskList = testStorage.initialise();
        try {
            assertTrue(testTaskList.isEmpty());
        } catch (Duke.DukeException e) {
            Assertions.fail("numTasks not 0 but tasklist is empty");
        }

        new File(testFileName).delete();
        new File("createDirTest").delete();
    }

    // Helper functions below
    public static boolean isEmpty(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)) {
                return !directory.iterator().hasNext();
            }
        }

        return false;
    }
}
