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
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        String saveFileString = tasks.save();
        storage.save(saveFileString);
        Ui.endDuke();
    }

    /**
     * Returns true as the program should quit after "bye" is input.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
