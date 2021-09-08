package duke;

import java.io.IOException;

public class Delete implements GeneralCommand{
    private int index;
    private TaskList tasks;
    private Storage storage;
    private Task currentTask;
    private Ui ui;

    /**
     * Constructs Delete object.
     *
     * @param index Index of task to be deleted.
     * @param tasks The TaskList of tasks.
     */
    Delete(int index, TaskList tasks, Storage storage, Ui ui) throws DeleteException {
        this.index = index;
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
        try {
            this.currentTask = tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DeleteException();
        }
        assert index <= tasks.size() : "Index out of bounds";
    }

    /**
     * Executes deletion of task.
     */
    @Override
    public String execute() throws IOException {
        tasks.remove(index);
        storage.save(tasks);
        return ui.deleteMessageToString(currentTask, tasks);
    }
}
