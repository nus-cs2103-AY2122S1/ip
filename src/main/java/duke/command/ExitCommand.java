package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a Command to terminate the program.
 */
public class ExitCommand extends Command {

    /**
     * The constructor for an ExitCommand object.
     */
    public ExitCommand() {

    }

    /**
     * Executes the Command to terminate the program.
     *
     * @param tasks The given TaskList.
     * @param ui The given Ui to print messages to the user.
     * @param storage The given Storage to save changes to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTemplate(" Bye. Hope to see you again soon!");
    }

    /**
     * Returns whether the program is to be terminated or not.
     *
     * @return True if it is the exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
