package stub;

import java.io.File;
import java.io.IOException;

import duke.Duke;

/**
 * A dummy class for Duke that does not support CLI.
 */
public class DukeStub extends Duke {
    /**
     * Creates a new dummy Duke stub.
     * @throws IOException when there is a problem creating the temporary test file.
     */
    public DukeStub() throws IOException {
        super("testFile.db");
        File file = new File("testFile.db");
        file.deleteOnExit();
    }

    @Override
    public void run() {
        // Should not run
    }
}
