package dac.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dac.exception.StorageMissingException;

public class StorageTest {
    @Test
    public void writeToStorage_missingFile_throwsException() {
        Assertions.assertThrows(IOException.class, () -> {
            new Storage("/storage/testdirectory").writeToStorage("", false);
        });
    }

    @Test
    public void testWriteAndReadFromFile() {
        Storage storage = new Storage("testFile.txt");
        try {
            storage.writeToStorage("Coming from storage test.", false);
            ArrayList<String> results = storage.getStorageContents();
            assertEquals("Coming from storage test.", results.get(0));
        } catch (StorageMissingException | IOException e) {
            Assertions.fail();
        }
    }
}
