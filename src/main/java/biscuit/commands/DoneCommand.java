package biscuit.commands;

import biscuit.exceptions.BiscuitException;
import biscuit.storage.Storage;
import biscuit.task.Task;
import biscuit.task.TaskList;
import biscuit.ui.Ui;

/**
 * Done command to mark task as done.
 */
public class DoneCommand extends Command {

    /**
     * Constructs DoneCommand class.
     *
     * @param userInputs User input array with this structure: [command, details].
     */
    public DoneCommand(String[] userInputs) {
        super(CommandType.DONE, userInputs);
    }

    /**
     * Marks specified task as done.
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
            throw new BiscuitException("\u0ED2(\u25C9\u1D25\u25C9)\u096D OOPS!!! "
                    + "The done task number cannot be empty.");
        }

        try {
            Task current = taskList.getTask(Integer.parseInt(userInputs[1]) - 1);
            current.setDone(true);
            storage.save();
            return "Nice! I've marked this task as done, woof!\n\t" + current;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new BiscuitException("\u0ED2(\u25C9\u1D25\u25C9)\u096D OOPS!!! Please enter a valid number"
                    + (taskList.size() == 1 ? " of 1" : " from 1 to " + taskList.size()) + ".");
        }
    }
}
