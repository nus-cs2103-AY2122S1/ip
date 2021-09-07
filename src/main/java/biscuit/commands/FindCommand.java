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
     * @param userInputs User input array with this structure: [command, details].
     */
    public FindCommand(String[] userInputs) {
        super(CommandType.FIND, userInputs);
    }

    /**
     * Finds tasks matching keyword.
     *
     * @param taskList Task list.
     * @param ui       Ui to display.
     * @param storage  Storage to save to.
     * @return Response to user input.
     * @throws BiscuitException Invalid input by user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BiscuitException {
        if (userInputs.length < 2) {
            throw new BiscuitException("\u0ED2(\u25C9\u1D25\u25C9)\u096D OOPS!!! The search keyword cannot be empty.");
        }

        StringBuilder message = new StringBuilder();
        int count = 1;
        for (int i = 0; i < taskList.size(); i++) {
            Task current = taskList.getTask(i);
            if (current.getDescription().contains(userInputs[1])) {
                message.append(count).append(". ").append(taskList.getTask(i));
                count++;
            }
        }
        if (count == 1) {
            message = new StringBuilder("No matching tasks found.");
        }
        return message.toString();
    }
}
