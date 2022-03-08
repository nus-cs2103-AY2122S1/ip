package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.storage.Storage;

public class StorageTest {
    @BeforeEach
    public void createTempFile() throws IOException {
        String tempFileLocation = "src/test/resources/temp.txt";
        // copy contents of validFileFormat.txt over
        Files.copy(
                Path.of("src/test/resources/validFileFormat.txt"),
                Path.of(tempFileLocation),
                StandardCopyOption.REPLACE_EXISTING);
    }

    @AfterEach
    public void deleteTempFile() throws IOException {
        Files.delete(Path.of("src/test/resources/temp.txt"));
    }

    @Test
    public void constructor_correctLinesAndSize() throws IOException {
        Storage storage = new Storage("src/test/resources/temp.txt");
        ArrayList<String> lines = storage.getLines();

        String firstLine = "D | 0 | assignment | 01/09/2021";
        String secondLine = "E | 1 | concert | 03/10/2021";
        String thirdLine = "T | 0 | borrow book";
        String fourthLine = "T | 1 | go school";

        assertEquals(4, lines.size());
        assertEquals(firstLine, lines.get(0));
        assertEquals(secondLine, lines.get(1));
        assertEquals(thirdLine, lines.get(2));
        assertEquals(fourthLine, lines.get(3));
    }
}
