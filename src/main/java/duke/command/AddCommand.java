package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

/**
 * Adds a task to the task list provided.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Class constructor.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the task list.
     *
     * @param tasks TaskList for task to be added to.
     * @param ui Ui to display command message to.
     * @param storage Storage to interact with, not necessary.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showMessage(this.message(tasks));
    }

    /**
     * Message to be displayed while performing the task.
     *
     * @param tasks TaskList of current tasks.
     * @return Message to display to the user.
     */
    @Override
    public String message(TaskList tasks) {
        return "Got it. I've added this task:\n"
                + task.toString()
                + "\n";
    }
}
