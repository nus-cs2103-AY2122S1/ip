package duke;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class StorageTest {

    @TempDir
    Path tempDir;

    @Test
    void write_string_success() throws IOException {
        Path path = tempDir.resolve("test.txt");
        Storage s = new Storage(path.toString());

        s.write("123");
        s.close();

        assertArrayEquals(new String[]{"123"}, Files.readAllLines(path).toArray());
    }
}
