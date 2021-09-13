package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Stub class for testing classes reliant on Command.
 */
public class CommandStub extends Command {
    /**
     * Displays a dummy message on the UI.
     *
     * @param tasks TaskList to be manipulated, not necessary.
     * @param ui Ui to interact with.
     * @param storage Storage to interact with, not necessary.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
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
        return "Stub message";
    }
}
