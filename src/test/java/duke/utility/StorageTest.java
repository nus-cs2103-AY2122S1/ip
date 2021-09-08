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
            assertEquals("No such file or directory", ex.getMessage());
        }
    }
}
