package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class SortCommand extends Command {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Instantiates command that marks a task as done.
     *
     * @param tasks List of current user tasks.
     * @param ui Ui to handle interactions.
     * @param storage Storage for tasks.
     */
    public SortCommand(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    @Override
    public void execute() {
        tasks.sortByDate();
        storage.saveTasks(tasks);
        ui.showSortedList(tasks);
    }
}
