package biscuit.commands;

import biscuit.exceptions.BiscuitException;
import biscuit.storage.Storage;
import biscuit.task.Task;
import biscuit.task.TaskList;
import biscuit.ui.Ui;

/**
 * Find command to find a task by searching for a keyword.
 */
public class FindCommand extends Command {

    /**
     * Constructs FindCommand class.
     *
     * @param userInput
     */
    public FindCommand(String[] userInput) {
        super(CommandType.FIND, userInput);
    }

    /**
     * Finds tasks matching keyword.
     *
     * @param taskList Task list.
     * @param ui       Ui to display.
     * @param storage  Storage to save to.
     * @throws BiscuitException Invalid input by user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BiscuitException {
        if (userInput.length == 2) {
            int count = 1;
            for (int i = 0; i < taskList.size(); i++) {
                Task current = taskList.getTask(i);
                if (current.getDescription().contains(userInput[1])) {
                    ui.showMessage(count + ". " + taskList.getTask(i));
                    count ++;
                }
            }
            if (count == 1) {
                ui.showMessage("No matching tasks found.");
            }
        } else {
            throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! The search keyword cannot be empty.");
        }
    }
}
