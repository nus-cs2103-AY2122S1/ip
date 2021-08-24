package biscuit.commands;

import biscuit.exceptions.BiscuitException;
import biscuit.storage.Storage;
import biscuit.task.TaskList;
import biscuit.ui.Ui;

/**
 * List command to list tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs ListCommand class.
     *
     * @param userInput User input array with this structure: [command, details].
     */
    public ListCommand(String[] userInput) {
        super(CommandType.LIST, userInput);
    }

    /**
     * Lists tasks.
     *
     * @param taskList Task list to add.
     * @param ui       Ui to display.
     * @param storage  Storage to save to.
     * @throws BiscuitException Invalid input by user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BiscuitException {
        if (taskList.isEmpty()) {
            ui.showMessage("List is currently empty.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                ui.showMessage(i + 1 + ". " + taskList.getTask(i));
            }
        }
    }
}
