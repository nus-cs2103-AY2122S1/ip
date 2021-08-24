package duke;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void testLoad() throws IOException {
        Files.createDirectories(Paths.get("data/"));
        File dukeFile = new File("data/duke.txt");

        Storage storage = new Storage(dukeFile);
        Boolean isPrintWriter = storage.load() instanceof PrintWriter;

        assertEquals(true, isPrintWriter);
    }
}
