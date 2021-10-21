package stub;

import java.io.File;
import java.io.IOException;

import duke.Duke;
import duke.logic.Storage;
import duke.task.TaskList;

/**
 * A dummy class for Duke that does not support CLI.
 */
public class DukeStub extends Duke {
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Creates a new dummy Duke stub.
     *
     * @throws IOException when there is a problem creating the temporary test file.
     */
    public DukeStub() throws IOException {
        super("testFile.db");
        taskList = new TaskList();
        storage = new StorageStub();
        File file = new File("testFile.db");
        file.delete();
    }

    public DukeStub(String filePath) throws IOException {
        this();
    }

    @Override
    public TaskList getTaskList() {
        return taskList;
    }

    @Override
    public Storage getStorage() {
        return storage;
    }

    @Override
    public void run() {
        // Should not run
    }
}
