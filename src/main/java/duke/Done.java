package duke;

import java.io.IOException;

public class Done implements GeneralCommand {
    private int index;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructs Done object.
     *
     * @param index Index of task to be marked as done.
     * @param currentTask Task to be marked as done.
     */
    public Done(int index, TaskList tasks, Storage storage) {
        this.index = index;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Executes marking done of task.
     */
    @Override
    public void execute() throws IOException {
        Task currentTask = tasks.get(index);
        currentTask.setDone();
        storage.save(tasks);
    }
}
