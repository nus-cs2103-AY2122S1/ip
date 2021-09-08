package duke;

import java.io.IOException;

public class Done implements GeneralCommand {
    private int index;
    private TaskList tasks;
    private Storage storage;
    private Task currentTask;
    private Ui ui;

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
     */
    @Override
    public String execute() throws IOException {
        currentTask.setDone();
        storage.save(tasks);
        return ui.doneMessageToString(currentTask);
    }
}
