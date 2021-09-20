package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to display the in-app help tool.
 *
 * @author Sherman Ng Wei Sheng
 */
public class HelpCommand extends Command {
    private final boolean isExit;

    /**
     * Constructor for the help command.
     */
    public HelpCommand() {
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
     * Executes the command to return the content of the help page.
     *
     * @param list TaskList before execution of the command.
     * @param ui Ui object to log the execution of the command.
     * @param storage Storage object that references the path to store the updated list of tasks.
     * @return The string to be printed.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String data = HelpProvider.getContent();
        return ui.printAndReturnMessage(data);
    }
}
