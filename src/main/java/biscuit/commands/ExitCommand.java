package biscuit.commands;

import biscuit.exceptions.BiscuitException;
import biscuit.storage.Storage;
import biscuit.tasks.TaskList;
import biscuit.ui.Ui;

/**
 * Exit command to exit biscuit.Biscuit
 */
public class ExitCommand extends Command {

    /**
     * Constructors for biscuit.commands.ExitCommand
     *
     * @param userInput User input array with this structure: [command, details]
     */
    public ExitCommand(String[] userInput) {
        super(CommandType.EXIT, userInput);
    }

    /**
     * Exit from biscuit.Biscuit
     *
     * @param taskList biscuit.tasks.Task list
     * @param ui       biscuit.ui.Ui to display
     * @param storage  biscuit.storage.Storage to save to
     * @throws BiscuitException Invalid input by user
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BiscuitException {
        ui.showMessage("Bye! Hope to see you again soon! 8==8");
    }
}
