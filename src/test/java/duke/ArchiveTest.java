package duke;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.errors.ArchiveException;

class ArchiveTest {

    @Test
    void newArchive() {
        File dukeFile = new File("src/data/stub.txt");
        Archive archive = new Archive();
        try {
            archive.newArchive("stubTest");
        } catch (ArchiveException archiveException) {
            archiveException.printStackTrace();
            assertFalse(false, "newArchive method causes an Archive Exception");
        }

        File archiveFile = new File("src/archive/stubTest.txt");
        // 1. check if archive has been created
        if (!archiveFile.exists()) {
            assertFalse(false, "Archive File was not created after running newArchive");
        }

        // 2. check if archive file and duke file are the same
        if (!archiveFile.equals(dukeFile)) {
            assertFalse(false, "Archive file and Duke file are different");
        }

        // deletes archive file and assertTrue
        if (archiveFile.delete()) {
            assertTrue(true);
        }
    }

    @Test
    void deleteArchive() {
        File f = new File("src/archive/Testing.txt");
        try {
            f.createNewFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Archive archive = new Archive();
        try {
            archive.deleteArchive("Testing");
        } catch (ArchiveException archiveException) {
            archiveException.printStackTrace();
            assertFalse(true, "deleteArchive method causes an Archive Expection");
        }

        assertTrue(!f.exists());
    }
}
