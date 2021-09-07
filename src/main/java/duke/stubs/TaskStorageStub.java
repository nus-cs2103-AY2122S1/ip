package duke.stubs;

import java.util.ArrayList;

import duke.io.TaskStorage;
import duke.task.Task;

/**
 * Storage stub used for testing that doesn't load or store.
 */
public class TaskStorageStub extends TaskStorage {
    public TaskStorageStub() {
        super("");
    }

    /**
     * Does not load tasks.
     *
     * @return An empty list of tasks.
     */
    @Override
    public ArrayList<Task> load() {
        return new ArrayList<>();
    }

    /**
     * Does not save tasks.
     *
     * @param tasks The tasks to be saved.
     */
    @Override
    public void save(ArrayList<Task> tasks) {}
}
