package duke.utility;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    
    @Test
    public void storageInit_invalidPath_exceptionThrown() {
        try {
            Storage s = new Storage("");
            fail();
        } catch (IOException ex) {
            
        }
    }
}
