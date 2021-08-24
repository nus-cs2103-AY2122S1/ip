package biscuit.commands;

import biscuit.exceptions.BiscuitException;
import biscuit.storage.Storage;
import biscuit.tasks.TaskList;
import biscuit.ui.Ui;

/**
 * List command to list tasks
 */
public class ListCommand extends Command {

    /**
     * Constructors for biscuit.commands.ListCommand
     *
     * @param userInput User input array with this structure: [command, details]
     */
    public ListCommand(String[] userInput) {
        super(CommandType.LIST, userInput);
    }

    /**
     * Lists tasks
     *
     * @param taskList biscuit.tasks.Task list to add
     * @param ui       biscuit.ui.Ui to display
     * @param storage  biscuit.storage.Storage to save to
     * @throws BiscuitException Invalid input by user
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
