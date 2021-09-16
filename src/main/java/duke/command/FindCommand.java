package duke.command;

import duke.result.Result;
import duke.result.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * A class that represents the command when the user types in 'find'.
 */
public class FindCommand extends Command {

    /**
     * Creates a FindCommand, which relies on user inputs to function.
     *
     * @param input A string containing the user's input.
     */
    public FindCommand(String input) {
        super(input);
    }

    /**
     * Displays all tasks that contain a phrase given by the user.
     *
     * @param taskList   The current list of tasks from the user.
     * @param ui      An object that handles all UI related functionality. (e.g. printing)
     * @param storage An object that handles all save/load related functionality.
     * @return A Result object containing task and alias data, as well as an output message.
     */
    @Override
    public Result execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList matchingTaskList = taskList.findTasks(input);
        String message = ui.showMessage("Here are the matching tasks in your list:")
                + ui.showMessage(matchingTaskList.toString());
        return new Result(taskList, message);
    }
}
