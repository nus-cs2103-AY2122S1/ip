package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;

public abstract class Command {
    /**
     * Helps to execute the command.
     * 
     * @param taskList the list of Tasks which is being stored.
     * @param storage  the database where the Tasks are being saved for progression.
     */
    public abstract void execute(TaskList taskList, Storage storage);

    /**
     * Checks if the user wants to exit from the application.
     * 
     * @return boolean whether the user wants to exit from the application.
     */
    public abstract boolean getIsExit();
}
