package command;

import duke.Storage;
import duke.TaskList;

/**
 * Command to get help for usage of Duke.
 *
 * @author felix-ong
 */
public class HelpCommand extends Command {
    private static final String HELP_MESSAGE = "Please enter one of the following commands:\n todo(or t) DESCRIPTION"
            + "\n deadline(or d) DESCRIPTION /by DATETIME(yyyy-MM-dd kkmm)"
            + "\n event(or e) DESCRIPTION /at DATETIME(yyyy-MM-dd kkmm)"
            + "\n list \n find KEYWORD \n done TASK_INDEX \n delete TASK_INDEX \n bye(to quit)";

    /**
     * Executes the specific actions for this command.
     *
     * @param tasks   Handles the list of tasks.
     * @param storage Handles the saving and loading of tasks.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
    }

    /**
     * Returns true if the command calls for the program to exit,
     * false otherwise.
     *
     * @return true if command calls for the program to exit, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return HELP_MESSAGE;
    }
}
