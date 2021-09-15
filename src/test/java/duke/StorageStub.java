package duke;

import duke.exception.DukeException;

public class StorageStub implements Storable {

    // Initial tasks.
    private final TaskList tasks;

    // Initialize tasks.
    public StorageStub(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public TaskList readTasksFromData() throws DukeException {
        return tasks;
    }

    @Override
    public void saveTasksToData(TaskList taskList) throws DukeException {
        // Do Nothing
    }
}
