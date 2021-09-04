package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;

public class EmptyCommand extends Command {

    /**
     * Empty Command that does nothing.
     * 
     * @param taskList contains an {@code ArrayList<Task>} where all {@code Task} is
     *                 stored.
     * @param storage  the database where the Tasks are being saved for progression.
     */
    public String execute(TaskList taskList, Storage storage) {
        return Ui.EMPTY_COMMAND_REPLY;
    }

    /**
     * Checks if the user wants to exit from the application.
     * 
     * @return {@code false} as this command is not ready for user to exit the
     *         application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
