package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Handles closing and exiting Duke.
 */
public class CommandExit extends Command {
    /**
     * Constructor for the ExitCommand.
     */
    public CommandExit() {}

    /**
     * Closes and exits Duke.
     * Saving the tasks in Duke to Storage.
     *
     * @param taskList The TaskList that is saved in Duke.
     * @param ui       The Ui of Duke.
     * @param storage  The Storage of Duke.
     * @return String from UI.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.write(taskList);
        return ui.displayExitUi();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        return obj instanceof CommandExit;
    }

    /**
     * Returns true as an exit command.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
