package stub;

import java.io.File;
import java.io.IOException;

import duke.logic.Storage;
import duke.task.TaskList;

public class StorageStub extends Storage {
    /**
     * Creates a new instance of a storage stub that does not save anything.
     */
    public StorageStub() throws IOException {
        super("testFile.db", new TaskList());
        File file = new File("testFile.db");
        file.delete();
    }

    @Override
    public void updateDukeTextFile() {
        // do nothing
    }
}
