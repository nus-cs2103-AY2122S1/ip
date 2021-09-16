package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Deals with invalid inputs.
 */
public class UnknownCommand extends Command {

    public UnknownCommand() {

    }

    /**
     * Calls the Ui to show an error message upon receiving invalid inputs.
     */
    public String execute(TaskList task, Storage storage) {
        return "Please start the sentence with either 'todo'/'deadline'/'event'/'list'/'done'/'delete'/'bye'";
    }

    /**
     * Helper function to tell Duke to continue reading inputs
     */
    public boolean isExit() {
        return false;
    }
}
