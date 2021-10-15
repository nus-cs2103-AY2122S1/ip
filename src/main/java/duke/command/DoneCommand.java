package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to mark a task as done.
 */
public class DoneCommand extends Command {
    private final int index;

    /**
     * Class constructor.
     *
     * @param index Index of task to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task as done.
     * @param tasks TaskList containing the task to be marked.
     * @param ui Ui to display command message to.
     * @param storage Storage to interact with, not necessary.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index < 0 || index >= tasks.getSize()) {
            ui.showError("Index error: please select the correct index");
            return;
        }

        tasks.getIndex(index).setDone();
        ui.showMessage(getMessage(tasks));
    }

    /**
     * Returns the message to be displayed while performing the task.
     *
     * @param tasks TaskList of current tasks.
     * @return Message to display to the user.
     */
    @Override
    public String getMessage(TaskList tasks) {
        return "Nice! I've marked this task as done: \n"
                + tasks.getIndex(index).toString()
                + "\n";
    }
}
