package duke.command;

import duke.task.TaskList;
import duke.ui.Storage;
import duke.ui.Ui;

/**
 * Represents the command to print the list.
 *
 * @author Sherman Ng Wei Sheng
 */
public class PrintCommand extends Command {
    private boolean isExit;

    /**
     * Constructor for the print command.
     */
    public PrintCommand() {
        this.isExit = false;
    }

    /**
     * Returns true if the command is a program terminating command.
     *
     * @return True if it is a terminating command and false otherwise.
     */
    @Override
    public boolean isAExitCommand() {
        return isExit;
    }

    /**
     * Executes the command to print the list.
     *
     * @param list TaskList before execution of the command.
     * @param ui Ui object to log the execution of the command.
     * @param storage Storage object that references the path to store the updated list of tasks.
     * @return The string to be printed.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return ui.printAndReturnListString(list);
    }
}
