package duke;

import java.io.IOException;

public class Delete implements GeneralCommand{
    private int index;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructs Delete object.
     *
     * @param index Index of task to be deleted.
     * @param tasks The TaskList of tasks.
     */
    Delete(String command, TaskList tasks, Storage storage) {
        this.index = Integer.parseInt(command.substring(7)) - 1;
        this.tasks = tasks;
        this.storage = storage;

        assert index <= tasks.size() : "Index out of bounds";

    }

    /**
     * Executes deletion of task.
     */
    @Override
    public void execute() throws IOException, DeleteException {
        if (index >= tasks.size()) {
            throw new DeleteException();
        }
        tasks.remove(index);
        storage.save(tasks);
    }
}
