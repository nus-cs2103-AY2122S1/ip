package duke.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void storageInit_invalidPath_exceptionThrown() {
        try {
            Storage s = new Storage("");
            fail();
        } catch (IOException ex) {
            assertEquals("Unable to create/open specified file.\nTasks will not be logged.", ex.getMessage());
        }
    }
}
