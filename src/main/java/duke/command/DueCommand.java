package duke.command;

import duke.result.Result;
import duke.result.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * A class that represents the command when the user types in 'due'.
 */
public class DueCommand extends Command {

    /**
     * Creates a DueCommand, which relies on user inputs to function.
     *
     * @param input A string containing the user's input.
     */
    public DueCommand(String input) {
        super(input);
    }

    /**
     * Displays all tasks due before a certain period, for example all tasks due before
     * the time now, +x hours/days/months.
     *
     * @param taskList   The current list of tasks from the user.
     * @param ui      An object that handles all UI related functionality. (e.g. printing)
     * @param storage An object that handles all save/load related functionality.
     * @return A Result object containing task and alias data, as well as an output message.
     */
    @Override
    public Result execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList dueTaskList = taskList.getDueTasks(input);
        String message = ui.showMessage("Here are the tasks occurring within this time period:")
                + ui.showMessage(dueTaskList.toString());
        return new Result(taskList, message);
    }
}
