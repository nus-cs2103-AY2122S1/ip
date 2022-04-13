package duke;

import java.io.IOException;

/**
 * Done class to maintain done commands.
 */
public class Done implements GeneralCommand {
    private int index;
    private TaskList tasks;
    private Storage storage;
    private Task currentTask;
    private Ui ui;

    /**
     * Constructs Done object.
     *
     * @param index Index of task to be marked as done.
     * @param tasks The TaskList of tasks.
     * @param storage Storage to be saved.
     * @param ui Ui to return String.
     * @throws DoneException If Done is incomplete.
     */
    public Done(int index, TaskList tasks, Storage storage, Ui ui) throws DoneException {
        this.index = index;
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
        try {
            this.currentTask = tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DoneException();
        }
    }

    /**
     * Executes marking done of task.
     *
     * @return String to be printed on GUI.
     * @throws IOException If an input or output operation is failed or interpreted.
     */
    @Override
    public String execute() throws IOException, DukeException {
        Task toggledStatusTask = currentTask.getToggledDone();
        tasks.set(index, toggledStatusTask);
        storage.save(tasks);
        return ui.doneMessageToString(toggledStatusTask, toggledStatusTask.getDone());
    }
}
