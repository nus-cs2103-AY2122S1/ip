package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class StorageTest {
    @TempDir
    Path path;

    @Test
    public void readWrite() {
        Path file = path.resolve("db.txt");
        Storage storage = new Storage(file);

        try {
            storage.save("READ WRITE TEST");
            List<String> lines = storage.load();
            assertEquals("READ WRITE TEST", lines.get(0));
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}
