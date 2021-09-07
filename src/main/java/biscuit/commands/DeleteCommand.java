package biscuit.commands;

import biscuit.exceptions.BiscuitException;
import biscuit.storage.Storage;
import biscuit.task.Task;
import biscuit.task.TaskList;
import biscuit.ui.Ui;

/**
 * Delete command to delete task.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs DeleteCommand class.
     *
     * @param userInputs User input array with this structure: [command, details].
     */
    public DeleteCommand(String[] userInputs) {
        super(CommandType.DELETE, userInputs);
    }

    /**
     * Deletes specified task from list.
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
            throw new BiscuitException("\u0ED2(\u25C9\u1D25\u25C9)\u096D "
                    + "OOPS!!! The delete task number cannot be empty.");
        }

        try {
            int index = Integer.parseInt(userInputs[1]) - 1;
            Task toDelete = taskList.getTask(index);
            taskList.removeTask(index);
            storage.save();
            return "Noted. I've removed the following task:\n\t" + toDelete
                    + "\nNow you have " + taskList.size() + " tasks in the list.";
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new BiscuitException("\u0ED2(\u25C9\u1D25\u25C9)\u096D OOPS!!! Please enter a valid number"
                    + (taskList.size() == 1 ? " of 1" : " from 1 to " + taskList.size()) + ".");
        }
    }
}
