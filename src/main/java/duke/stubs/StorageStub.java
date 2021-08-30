package duke.stubs;

import java.util.ArrayList;

import duke.io.Storage;
import duke.task.Task;

/**
 * Storage stub used for testing that doesn't load or store
 */
public class StorageStub extends Storage {
    public StorageStub() {
        super("");
    }

    /**
     * Does not load tasks
     *
     * @return an empty list of tasks
     */
    @Override
    public ArrayList<Task> load() {
        return new ArrayList<>();
    }

    /**
     * Does not save tasks
     *
     * @param tasks The tasks to be saved
     */
    @Override
    public void save(ArrayList<Task> tasks) {}
}
