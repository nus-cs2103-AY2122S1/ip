package biscuit.commands;

import biscuit.exceptions.BiscuitException;
import biscuit.storage.Storage;
import biscuit.task.TaskList;
import biscuit.ui.Ui;

/**
 * Exit command to exit Biscuit.
 */
public class ExitCommand extends Command {

    /**
     * Constructs ExitCommand class.
     *
     * @param userInput User input array with this structure: [command, details].
     */
    public ExitCommand(String[] userInput) {
        super(CommandType.EXIT, userInput);
    }

    /**
     * Exits from Biscuit.
     *
     * @param taskList Task list.
     * @param ui       Ui to display.
     * @param storage  Storage to save to.
     * @throws BiscuitException Invalid input by user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BiscuitException {
        ui.showMessage("Bye! Hope to see you again soon! 8==8");
    }
}
