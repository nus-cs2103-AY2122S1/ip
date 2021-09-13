package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Deletes a task from the task list provided.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Class constructor.
     *
     * @param index Index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from the task list provided.
     *
     * @param tasks TaskList to be manipulated.
     * @param ui Ui to display command message to.
     * @param storage Storage to interact with, not necessary.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index < 0 || index >= tasks.getSize()) {
            ui.showError("Index error: please select the correct index");
            return;
        }
        ui.showMessage(this.getMessage(tasks));
        tasks.removeIndex(index);
    }

    /**
     * Returns the message to be displayed while performing the task.
     *
     * @param tasks TaskList of current tasks.
     * @return Message to display to the user.
     */
    @Override
    public String getMessage(TaskList tasks) {
        return "Noted. I've removed this task:\n"
                + tasks.getIndex(index).toString() + "\n"
                + "Now you have " + (tasks.getSize() - 1) + " tasks in the list.\n";
    }
}
