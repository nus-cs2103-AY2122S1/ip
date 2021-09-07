import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.Duke;

public class DukeTest {
    private final Duke duke;

    DukeTest() throws IOException {
        File file = new File("testFile.db");
        file.deleteOnExit();
        duke = new Duke("testFile.db");
    }

    @Test
    void testNotNull() {
        assertNotEquals(null, duke.getStorage());
        assertNotEquals(null, duke.getTaskList());
    }
}
