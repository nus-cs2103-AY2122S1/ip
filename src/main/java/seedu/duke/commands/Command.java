package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;

public abstract class Command {
    protected static final String DATA_STORAGE_ISDONE_FALSE = " | 0 | ";
    protected static final String DATA_STORAGE_TASK_SYMBOL = "T";

    /**
     * Executes the command.
     * 
     * @param taskList contains an {@code ArrayList<Task>} where all {@code Task} is
     *                 stored.
     * @param storage  the database where the Tasks are being saved for progression.
     */
    public abstract String execute(TaskList taskList, Storage storage);

    /**
     * Checks if the user wants to exit from the application.
     * 
     * @return a boolean whether the user wants to exit the application.
     */
    public abstract boolean isExit();
}
