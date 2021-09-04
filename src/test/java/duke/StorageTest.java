import duke.Duke;
import duke.Storage;
import org.junit.jupiter.api.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    Storage s;

    @Test
    public void storageTest_invalidPath_exceptionThrown() {
        try {
            s = new Storage("Z:/data/tasks.txt");
            fail();
        } catch (java.io.IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void storageTest_validPath_success() {
        try {
            s = new Storage("a/b/c/tasks.txt");
        } catch (java.io.IOException e) {
            System.err.println(e.getMessage());
            fail();
        }
    }
}
