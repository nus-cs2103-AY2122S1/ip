package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;

public class UiCommand extends Command {
    private final String uiMsg;

    /**
     * Primary Constructor.
     * 
     * @param uiMsg is the message that the Ui will print for the user to read.
     */
    public UiCommand(String uiMsg) {
        this.uiMsg = uiMsg;
    }

    /**
     * Helps to print UI message for the user when command is executed.
     * 
     * @param taskList the list of Tasks which is being stored.
     * @param storage  the database where the Tasks are being saved for progression.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.printMessage(this.uiMsg);
    }

    /**
     * Checks if the user wants to exit from the application.
     * 
     * @return boolean whether the user wants to exit from the application.
     */
    @Override
    public boolean getIsExit() {
        return false;
    }
}
