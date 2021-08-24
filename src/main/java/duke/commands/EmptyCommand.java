package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;

public class EmptyCommand extends Command {

    /**
     * Empty Command that does nothing.
     * 
     * @param taskList the list of Tasks which is being stored.
     * @param storage  the database where the Tasks are being saved for progression.
     */
    public void execute(TaskList taskList, Storage storage) {

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
