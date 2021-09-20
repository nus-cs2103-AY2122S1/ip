package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a ByeCommand to exit the Duke program.
 */
public class ByeCommand extends Command {
    /**
     * Saves tasks in storage file, shows exit messages to user and closes Ui.
     *
     * @param tasks TaskList containing tasks to be saved to storage file.
     * @param ui Ui that displays messages to users.
     * @param storage Storage that is used to save tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        System.exit(0);
        return ui.showBye();
    }

    /**
     * Returns true if object is a ByeCommand.
     *
     * @param obj Object to be compared to ByeCommand.
     * @return True if object is a ByeCommand.
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ByeCommand);
    }
}
