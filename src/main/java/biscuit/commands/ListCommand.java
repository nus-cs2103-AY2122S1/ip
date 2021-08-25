package biscuit.commands;

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
     * @param userInputs User input array with this structure: [command, details].
     */
    public ListCommand(String[] userInputs) {
        super(CommandType.LIST, userInputs);
    }

    /**
     * Lists tasks.
     *
     * @param taskList Task list to add.
     * @param ui       Ui to display.
     * @param storage  Storage to save to.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            ui.showMessage("List is currently empty.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                ui.showMessage(i + 1 + ". " + taskList.getTask(i));
            }
        }
    }
}
