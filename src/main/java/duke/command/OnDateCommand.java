package duke.command;

import duke.task.TaskList;

import duke.util.Ui;
import duke.util.Storage;

/**
 * A class that represents the command when the user types in 'ondate'.
 */
public class OnDateCommand extends Command {

    /**
     * Creates an OnDateCommand, which relies on user inputs to function.
     *
     * @param input A string containing the user's input.
     */
    public OnDateCommand(String input) {
        super(input);
    }

    /**
     * Displays all tasks due on a specific date.
     *
     * @param tasks   The current list of tasks from the user.
     * @param ui      An object that handles all UI related functionality. (e.g. printing)
     * @param storage An object that handles all save/load related functionality.
     * @return The input task list, unmodified.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks occurring on this date:");
        ui.showIndentedMessage(tasks.getOnDateTasks(input).toString());
        return tasks;
    }
}
