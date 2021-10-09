package duke;

import java.io.IOException;

/**
 * Delete class to maintain delete commands
 */
public class Delete implements GeneralCommand {
    private int index;
    private TaskList tasks;
    private Storage storage;
    private Task currentTask;
    private Ui ui;

    /**
     * Constructs Delete object.
     *
     * @param index Index of task to be deleted
     * @param tasks The TaskList of tasks.
     * @param storage Storage to be saved.
     * @param ui Ui to return String.
     * @throws DeleteException If Delete is incomplete.
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
     *
     * @return String to be printed on GUI.
     * @throws IOException If an input or output operation is failed or interpreted.
     */
    @Override
    public String execute() throws IOException {
        tasks.remove(index);
        storage.save(tasks);
        return ui.deleteMessageToString(currentTask, tasks);
    }
}
