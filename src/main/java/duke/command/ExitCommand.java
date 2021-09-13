package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to trigger Duke to exit.
 */
public class ExitCommand extends Command {
    /**
     * Exits Duke.
     * @param tasks TaskList to be be saved.
     * @param ui Ui to display command message to.
     * @param storage Storage to store the current task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveFile(tasks);
        ui.showMessage(this.getMessage(tasks));
        ui.exit();
    }

    /**
     * Returns the message to be displayed while performing the task.
     *
     * @param tasks TaskList of current tasks.
     * @return Message to display to the user.
     */
    @Override
    public String getMessage(TaskList tasks) {
        return "Bye. Don't come again!";
    }

    /**
     * Returns exit flag to Duke to terminate the loop.
     *
     * @return Exit flag for Duke to terminate the loop.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
