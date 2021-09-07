package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * This class represents a ByeCommand, which is when the user inputs "bye".
 */
public class ByeCommand extends Command {

    /**
     * Saves the storage file only, since the user is quitting Duke.
     *
     * @param tasks task list
     * @param storage storage
     * @param ui ui
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        String saveFileString = tasks.save();
        storage.save(saveFileString);
        return Ui.endDuke();
    }
}
