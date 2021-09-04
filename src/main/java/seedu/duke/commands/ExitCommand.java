package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;

public class ExitCommand extends Command {

    /**
     * Replies with an Exit message when this function is triggered.
     * 
     * @param taskList contains an {@code ArrayList<Task>} where all {@code Task} is
     *                 stored.
     * @param storage  the database where the Tasks are being saved for progression.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Ui.printMessage(Ui.BYE_MESSAGE);
    }

    /**
     * Checks if the user wants to exit from the application.
     * 
     * @return {@code true} as this command is ready for user to exit the
     *         application.
     */
    @Override
    public boolean isExit() {
        return true;
    };
}
