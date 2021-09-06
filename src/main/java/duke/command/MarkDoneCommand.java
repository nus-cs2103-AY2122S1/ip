package duke.command;


import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class MarkDoneCommand extends Command {

    private Task task;
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Instantiates command that marks a task as done.
     *
     * @param task Task to be marked as done.
     * @param tasks List of current user tasks.
     * @param ui Ui to handle interactions.
     * @param storage Storage for tasks.
     */
    public MarkDoneCommand(Task task, TaskList tasks, Ui ui, Storage storage) {
        this.task = task;
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    @Override
    public void execute() {
        task.markAsDone();
        storage.saveTasks(tasks);
        ui.markDone(this.task);
    }
}
