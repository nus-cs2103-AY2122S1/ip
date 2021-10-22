package storage;

import duke.storage.Storage;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void writeFileTest() throws FileNotFoundException {
        Storage s = new Storage("data/test.txt");
        PrintWriter writer = new PrintWriter("data/test.txt");
        writer.close();
        assertEquals(s.loadFile().size(), 0);
    }
}
